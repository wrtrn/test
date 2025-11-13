package nbank.secondPart;

import io.restassured.specification.RequestSpecification;
import nbank.BaseTest;
import nbank.models.AccountResponse;
import nbank.models.CreateUserResponse;
import nbank.models.DepositMoneyRequest;
import nbank.models.TransferMoney;
import nbank.requests.CreateAccountRequester;
import nbank.requests.DepositMoneyRequester;
import nbank.requests.GetCustomerAccountsRequester;
import nbank.requests.TransferMoneyRequester;
import nbank.specs.RequestSpecs;
import nbank.specs.ResponseSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TransferTest extends BaseTest {

    @Test
    public void transferMoneyFromEmptyAccount() {
        CreateUserResponse userResponse = createUser();

        RequestSpecification authAsCreatedUser = RequestSpecs.authAsUser(userResponse.getUsername(), userResponse.getPassword());

        AccountResponse accountResponse1 = new CreateAccountRequester(authAsCreatedUser, ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        AccountResponse accountResponse2 = new CreateAccountRequester(authAsCreatedUser, ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        long accountNumber1 = Integer.parseInt(accountResponse1.getAccountNumber().substring(3));
        long accountNumber2 = Integer.parseInt(accountResponse2.getAccountNumber().substring(3));

        TransferMoney body = TransferMoney.builder()
                .amount(50)
                .senderAccountId(accountNumber1)
                .receiverAccountId(accountNumber2)
                .build();

        new TransferMoneyRequester(authAsCreatedUser, ResponseSpecs.requestReturnsBadRequest())
                .post(body);
    }

    @Test
    public void userCanTransferBetweenHisOwnAccounts() {
        CreateUserResponse userResponse = createUser();

        RequestSpecification authAsCreatedUser = RequestSpecs.authAsUser(userResponse.getUsername(), userResponse.getPassword());

        AccountResponse accountResponse1 = new CreateAccountRequester(authAsCreatedUser, ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        AccountResponse accountResponse2 = new CreateAccountRequester(authAsCreatedUser, ResponseSpecs.entityWasCreated())
                .post(null)
                .extract()
                .as(AccountResponse.class);

        long accountNumber1 = Integer.parseInt(accountResponse1.getAccountNumber().substring(3));
        long accountNumber2 = Integer.parseInt(accountResponse2.getAccountNumber().substring(3));

        DepositMoneyRequest depositRequest = DepositMoneyRequest.builder()
                .id(accountNumber1)
                .balance(500)
                .build();

        //deposit money request
        new DepositMoneyRequester(
                authAsCreatedUser,
                ResponseSpecs.requestReturnsOK())
                .post(depositRequest);

        TransferMoney body = TransferMoney.builder()
                .amount(50)
                .senderAccountId(accountNumber1)
                .receiverAccountId(accountNumber2)
                .build();

        new TransferMoneyRequester(authAsCreatedUser, ResponseSpecs.requestReturnsOK())
                .post(body);

        AccountResponse[] responseAccounts = new GetCustomerAccountsRequester(authAsCreatedUser, ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        softly.assertThat(responseAccounts[0].getBalance()).isEqualTo(450);
        softly.assertThat(responseAccounts[1].getBalance()).isEqualTo(50);
    }

    @Test
    public void userSeesOnlyHisAccounts() {

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

        long accountNumber1 = Integer.parseInt(accountResponse1.getAccountNumber().substring(3));

        AccountResponse[] accountsResponse = new GetCustomerAccountsRequester(authAsCreatedUser1, ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        softly.assertThat(accountsResponse.length).isEqualTo(1);
        softly.assertThat(accountsResponse[0].getId()).isEqualTo(accountNumber1);
        softly.assertThat(accountsResponse[0].getAccountNumber()).isEqualTo("ACC" + accountNumber1);
    }

    @ParameterizedTest
    @ValueSource(ints = {10000, 9999, 1})
    public void userCanTransferToAnotherUser(int amount) {
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

        long accountNumber1 = Integer.parseInt(accountResponse1.getAccountNumber().substring(3));
        long accountNumber2 = Integer.parseInt(accountResponse2.getAccountNumber().substring(3));


        DepositMoneyRequest depositRequest = DepositMoneyRequest.builder()
                .id(accountNumber1)
                .balance(5000)
                .build();

        //deposit money request
        for (int i = 0; i < 2; i++) {
            new DepositMoneyRequester(
                    authAsCreatedUser1,
                    ResponseSpecs.requestReturnsOK())
                    .post(depositRequest);
        }

        TransferMoney body = TransferMoney.builder()
                .amount(amount)
                .senderAccountId(accountNumber1)
                .receiverAccountId(accountNumber2)
                .build();

        new TransferMoneyRequester(authAsCreatedUser1, ResponseSpecs.requestReturnsOK())
                .post(body);

        AccountResponse[] responseAccountsUser1 = new GetCustomerAccountsRequester(authAsCreatedUser1, ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        softly.assertThat(responseAccountsUser1[0].getBalance()).isEqualTo(10000 - amount);

        AccountResponse[] responseAccountsUser2 = new GetCustomerAccountsRequester(authAsCreatedUser2, ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        softly.assertThat(responseAccountsUser2[0].getBalance()).isEqualTo(amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {10001, 0, -400})
    public void userCanTransferToAnotherUserInvalidAmountOfMoney(int amount) {
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

        long accountNumber1 = Integer.parseInt(accountResponse1.getAccountNumber().substring(3));
        long accountNumber2 = Integer.parseInt(accountResponse2.getAccountNumber().substring(3));


        DepositMoneyRequest depositRequest = DepositMoneyRequest.builder()
                .id(accountNumber1)
                .balance(5000)
                .build();

        //deposit money request
        for (int i = 0; i < 2; i++) {
            new DepositMoneyRequester(
                    authAsCreatedUser1,
                    ResponseSpecs.requestReturnsOK())
                    .post(depositRequest);
        }

        TransferMoney body = TransferMoney.builder()
                .amount(amount)
                .senderAccountId(accountNumber1)
                .receiverAccountId(accountNumber2)
                .build();

        new TransferMoneyRequester(authAsCreatedUser1, ResponseSpecs.requestReturnsBadRequest())
                .post(body);

        AccountResponse[] responseAccountsUser1 = new GetCustomerAccountsRequester(authAsCreatedUser1, ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        Assertions.assertEquals(10000, responseAccountsUser1[0].getBalance());

        AccountResponse[] responseAccountsUser2 = new GetCustomerAccountsRequester(authAsCreatedUser2, ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        softly.assertThat(responseAccountsUser2[0].getBalance()).isEqualTo(0);
    }

    @Test
    public void transferAmountGreaterThanSenderBalance() {
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

        long accountNumber1 = Integer.parseInt(accountResponse1.getAccountNumber().substring(3));
        long accountNumber2 = Integer.parseInt(accountResponse2.getAccountNumber().substring(3));


        DepositMoneyRequest depositRequest = DepositMoneyRequest.builder()
                .id(accountNumber1)
                .balance(500)
                .build();

        //deposit money request
        new DepositMoneyRequester(
                authAsCreatedUser1,
                ResponseSpecs.requestReturnsOK())
                .post(depositRequest);

        TransferMoney body = TransferMoney.builder()
                .amount(550)
                .senderAccountId(accountNumber1)
                .receiverAccountId(accountNumber2)
                .build();

        new TransferMoneyRequester(authAsCreatedUser1, ResponseSpecs.requestReturnsBadRequest())
                .post(body);

        AccountResponse[] responseAccountsUser1 = new GetCustomerAccountsRequester(authAsCreatedUser1, ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        softly.assertThat(responseAccountsUser1[0].getBalance()).isEqualTo(500);

        AccountResponse[] responseAccountsUser2 = new GetCustomerAccountsRequester(authAsCreatedUser2, ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(AccountResponse[].class);

        softly.assertThat(responseAccountsUser2[0].getBalance()).isEqualTo(0);
    }
}
