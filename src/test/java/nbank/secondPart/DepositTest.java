package nbank.secondPart;

import io.restassured.specification.RequestSpecification;
import nbank.BaseTest;
import nbank.models.AccountResponse;
import nbank.models.CreateUserResponse;
import nbank.models.DepositMoneyRequest;
import nbank.requests.CreateAccountRequester;
import nbank.requests.DepositMoneyRequester;
import nbank.requests.GetCustomerAccountsRequester;
import nbank.specs.RequestSpecs;
import nbank.specs.ResponseSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DepositTest extends BaseTest {


    @ParameterizedTest
    @ValueSource(ints = {4999, 5000, 1})
    public void DepositToAccount(int expectedAmount) {
        CreateUserResponse userResponse = createUser();

        RequestSpecification authAsCreatedUser = RequestSpecs.authAsUser(userResponse.getUsername(), userResponse.getPassword());

        AccountResponse accountResponse = new CreateAccountRequester(authAsCreatedUser, ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        //deposit money json
        DepositMoneyRequest depositRequest = DepositMoneyRequest.builder()
                .id(accountResponse.getId())
                .balance(expectedAmount)
                .build();

        //deposit money request
        new DepositMoneyRequester(
                authAsCreatedUser,
                ResponseSpecs.requestReturnsOK())
                .post(depositRequest);

        AccountResponse accountsResponse = new GetCustomerAccountsRequester(authAsCreatedUser, ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class)[0];

        Assertions.assertEquals(expectedAmount, accountsResponse.getTransactions().getFirst().getAmount());
    }

    @ParameterizedTest
    @ValueSource(ints = {5001, -100, 0})
    public void depositProhibitedAmountOfMoney(int expectedAmount) {

        CreateUserResponse userResponse = createUser();

        RequestSpecification authAsCreatedUser = RequestSpecs.authAsUser(userResponse.getUsername(), userResponse.getPassword());

        AccountResponse accountResponse = new CreateAccountRequester(authAsCreatedUser, ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        //deposit money json
        DepositMoneyRequest depositRequest = DepositMoneyRequest.builder()
                .id(accountResponse.getId())
                .balance(expectedAmount)
                .build();

        //deposit money request
        new DepositMoneyRequester(
                authAsCreatedUser,
                ResponseSpecs.requestReturnsBadRequest())
                .post(depositRequest);

        AccountResponse accountsResponse = new GetCustomerAccountsRequester(authAsCreatedUser, ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class)[0];

        Assertions.assertEquals(0, accountsResponse.getTransactions().size());
    }

    @Test
    public void depositToNonExistingAccount() {

        CreateUserResponse userResponse = createUser();

        RequestSpecification authAsCreatedUser = RequestSpecs.authAsUser(userResponse.getUsername(), userResponse.getPassword());

        AccountResponse accountResponse = new CreateAccountRequester(authAsCreatedUser, ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        //deposit money json
        DepositMoneyRequest depositRequest = DepositMoneyRequest.builder()
                .id(accountResponse.getId() + 1)
                .balance(500)
                .build();

        //deposit money request
        new DepositMoneyRequester(
                authAsCreatedUser,
                ResponseSpecs.requestReturnsForbidden())
                .post(depositRequest);
    }

    @Test
    public void depositToSomeoneElseAccount() {

        CreateUserResponse userResponse1 = createUser();
        CreateUserResponse userResponse2 = createUser();

        RequestSpecification authAsCreatedUser1 = RequestSpecs.authAsUser(userResponse1.getUsername(), userResponse1.getPassword());
        RequestSpecification authAsCreatedUser2 = RequestSpecs.authAsUser(userResponse2.getUsername(), userResponse2.getPassword());

        AccountResponse accountResponse2 = new CreateAccountRequester(authAsCreatedUser2, ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        //deposit money json
        DepositMoneyRequest depositRequest = DepositMoneyRequest.builder()
                .id(accountResponse2.getId())
                .balance(500)
                .build();

        //deposit money request
        new DepositMoneyRequester(
                authAsCreatedUser1,
                ResponseSpecs.requestReturnsForbidden())
                .post(depositRequest);
    }
}
