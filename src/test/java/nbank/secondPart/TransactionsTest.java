package nbank.secondPart;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import nbank.BaseTest;
import nbank.models.*;
import nbank.requests.CreateAccountRequester;
import nbank.requests.DepositMoneyRequester;
import nbank.requests.GetAccountTransactionsRequester;
import nbank.requests.TransferMoneyRequester;
import nbank.specs.RequestSpecs;
import nbank.specs.ResponseSpecs;
import org.junit.jupiter.api.Test;

import static nbank.specs.ResponseSpecs.requestReturnsForbidden;
import static org.hamcrest.Matchers.equalTo;

public class TransactionsTest extends BaseTest {

    @Test
    public void userCanSeeTransactionForHisAccount() {

        CreateUserResponse userResponse1 = createUser();
        CreateUserResponse userResponse2 = createUser();

        RequestSpecification authAsCreatedUser1 = RequestSpecs.authAsUser(userResponse1.getUsername(), userResponse1.getPassword());
        RequestSpecification authAsCreatedUser2 = RequestSpecs.authAsUser(userResponse2.getUsername(), userResponse2.getPassword());

        AccountResponse accountResponse1 = new CreateAccountRequester(authAsCreatedUser1, ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        AccountResponse accountResponse2 = new CreateAccountRequester(authAsCreatedUser2, ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        //deposit money json
        DepositMoneyRequest depositRequest = DepositMoneyRequest.builder()
                .id(accountResponse1.getId())
                .balance(500)
                .build();

        //deposit money request
        new DepositMoneyRequester(
                authAsCreatedUser1,
                ResponseSpecs.requestReturnsOK())
                .post(depositRequest);

        long accountNumber1 = Integer.parseInt(accountResponse1.getAccountNumber().substring(3));
        long accountNumber2 = Integer.parseInt(accountResponse2.getAccountNumber().substring(3));

        TransferMoney body = TransferMoney.builder()
                .amount(50)
                .senderAccountId(accountNumber1)
                .receiverAccountId(accountNumber2)
                .build();

        new TransferMoneyRequester(authAsCreatedUser1, ResponseSpecs.requestReturnsOK())
                .post(body);

        Transaction[] transactions =
                new GetAccountTransactionsRequester(authAsCreatedUser1, ResponseSpecs.requestReturnsOK())
                        .get(accountNumber1).extract().as(Transaction[].class);

        softly.assertThat(500).isEqualTo(transactions[0].getAmount());
        softly.assertThat("DEPOSIT").isEqualTo(transactions[0].getType());

        softly.assertThat(50).isEqualTo(transactions[1].getAmount());
        softly.assertThat("TRANSFER_OUT").isEqualTo(transactions[1].getType());
    }

    @Test
    public void userCanNotSeeTransactionForAnotherPersonAccount() {
        CreateUserResponse userResponse1 = createUser();
        CreateUserResponse userResponse2 = createUser();

        RequestSpecification authAsCreatedUser1 = RequestSpecs.authAsUser(userResponse1.getUsername(), userResponse1.getPassword());
        RequestSpecification authAsCreatedUser2 = RequestSpecs.authAsUser(userResponse2.getUsername(), userResponse2.getPassword());

        AccountResponse accountResponse2 = new CreateAccountRequester(authAsCreatedUser2, ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        long accountNumber2 = Integer.parseInt(accountResponse2.getAccountNumber().substring(3));

        new GetAccountTransactionsRequester(authAsCreatedUser1, requestReturnsForbidden())
                .get(accountNumber2).assertThat().body(equalTo("You do not have permission to access this account"));
    }

    @Test
    public void userCanNotSeeTransactionForNotExistingAccount() {
        CreateUserResponse userResponse = createUser();

        RequestSpecification authAsCreatedUser = RequestSpecs.authAsUser(userResponse.getUsername(), userResponse.getPassword());

        AccountResponse accountResponse = new CreateAccountRequester(authAsCreatedUser, ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        long accountNumber = Integer.parseInt(accountResponse.getAccountNumber().substring(3));

        String errorText = "You do not have permission to access this account";
        new GetAccountTransactionsRequester(authAsCreatedUser, requestReturnsForbidden(errorText))
                .get(accountNumber + 1).assertThat().body(equalTo(errorText));
    }
}
