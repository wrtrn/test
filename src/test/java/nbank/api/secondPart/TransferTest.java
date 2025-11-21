package nbank.api.secondPart;

import io.restassured.specification.RequestSpecification;
import nbank.api.BaseTest;
import nbank.api.models.AccountResponse;
import nbank.api.models.CreateUserRequest;
import nbank.api.requests.steps.AdminSteps;
import nbank.api.requests.steps.UserSteps;
import nbank.api.specs.RequestSpecs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static nbank.api.requests.steps.UserSteps.*;

public class TransferTest extends BaseTest {

    @Test
    public void transferMoneyFromEmptyAccount() {
        CreateUserRequest userRequest = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        AccountResponse account1 = createAccount(authAsUser);
        AccountResponse account2 = createAccount(authAsUser);

        int firstAccountNumber = Integer.parseInt(account1.getAccountNumber().substring(3));
        int secondAccountNumber = Integer.parseInt(account2.getAccountNumber().substring(3));

        transferMoneyBadRequest(authAsUser, 50, firstAccountNumber, secondAccountNumber);
    }

    @Test
    public void userCanTransferBetweenHisOwnAccounts() {
        CreateUserRequest userRequest = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        AccountResponse account1 = createAccount(authAsUser);
        AccountResponse account2 = createAccount(authAsUser);

        long firstAccountNumber = Integer.parseInt(account1.getAccountNumber().substring(3));
        long secondAccountNumber = Integer.parseInt(account2.getAccountNumber().substring(3));

        depositMoney(authAsUser, account1, 500);

        transferMoney(authAsUser, 50, firstAccountNumber, secondAccountNumber);

        AccountResponse[] accounts = getCustomerAccounts(authAsUser);

        AccountResponse account1response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);

        softly.assertThat(account1response.getBalance()).isEqualTo(450);
        softly.assertThat(account2response.getBalance()).isEqualTo(50);
    }

    @Test
    public void userSeesOnlyHisAccounts() {
        CreateUserRequest userRequest1 = AdminSteps.createUser();
        RequestSpecification authAsUser1 = RequestSpecs.authAsUser(userRequest1.getUsername(), userRequest1.getPassword());
        CreateUserRequest userRequest2 = AdminSteps.createUser();
        RequestSpecification authAsUser2 = RequestSpecs.authAsUser(userRequest2.getUsername(), userRequest2.getPassword());

        AccountResponse account1 = createAccount(authAsUser1);
        createAccount(authAsUser2);

        long firstAccountNumber = Integer.parseInt(account1.getAccountNumber().substring(3));

        AccountResponse[] accountsResponse = getCustomerAccounts(authAsUser1);

        softly.assertThat(accountsResponse.length).isEqualTo(1);
        softly.assertThat(accountsResponse[0].getId()).isEqualTo(firstAccountNumber);
        softly.assertThat(accountsResponse[0].getAccountNumber()).isEqualTo("ACC" + firstAccountNumber);
    }

    @ParameterizedTest
    @ValueSource(ints = {10000, 9999, 1})
    public void userCanTransferToAnotherUser(int amount) {
        CreateUserRequest userRequest1 = AdminSteps.createUser();
        RequestSpecification authAsUser1 = RequestSpecs.authAsUser(userRequest1.getUsername(), userRequest1.getPassword());
        CreateUserRequest userRequest2 = AdminSteps.createUser();
        RequestSpecification authAsUser2 = RequestSpecs.authAsUser(userRequest2.getUsername(), userRequest2.getPassword());

        AccountResponse account1 = createAccount(authAsUser1);
        AccountResponse account2 = createAccount(authAsUser2);
        long firstAccountNumber = Integer.parseInt(account1.getAccountNumber().substring(3));
        long secondAccountNumber = Integer.parseInt(account2.getAccountNumber().substring(3));

        for (int i = 0; i < 2; i++) {
            UserSteps.depositMoney(authAsUser1, account1, 5000);
        }
        transferMoney(authAsUser1, amount, firstAccountNumber, secondAccountNumber);

        AccountResponse[] responseAccountsUser1 = getCustomerAccounts(authAsUser1);
        softly.assertThat(responseAccountsUser1[0].getBalance()).isEqualTo(10000 - amount);

        AccountResponse[] responseAccountsUser2 = getCustomerAccounts(authAsUser2);
        softly.assertThat(responseAccountsUser2[0].getBalance()).isEqualTo(amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {10001, 0, -400})
    public void userCanNotTransferToAnotherUserInvalidAmountOfMoney(int amount) {
        CreateUserRequest userRequest1 = AdminSteps.createUser();
        RequestSpecification authAsUser1 = RequestSpecs.authAsUser(userRequest1.getUsername(), userRequest1.getPassword());
        CreateUserRequest userRequest2 = AdminSteps.createUser();
        RequestSpecification authAsUser2 = RequestSpecs.authAsUser(userRequest2.getUsername(), userRequest2.getPassword());

        AccountResponse account1 = createAccount(authAsUser1);
        AccountResponse account2 = createAccount(authAsUser2);
        long firstAccountNumber = Integer.parseInt(account1.getAccountNumber().substring(3));
        long secondAccountNumber = Integer.parseInt(account2.getAccountNumber().substring(3));

        for (int i = 0; i < 2; i++) {
            UserSteps.depositMoney(authAsUser1, account1, 5000);
        }
        String errorResponseBody = transferMoneyBadRequest(authAsUser1, amount, firstAccountNumber, secondAccountNumber);

        softly.assertThat(errorResponseBody)
                .isEqualTo("Invalid transfer: insufficient funds or invalid accounts");

        AccountResponse[] responseAccountsUser1 = getCustomerAccounts(authAsUser1);
        softly.assertThat(responseAccountsUser1[0].getBalance()).isEqualTo(10000);

        AccountResponse[] responseAccountsUser2 = getCustomerAccounts(authAsUser2);
        softly.assertThat(responseAccountsUser2[0].getBalance()).isEqualTo(0);
    }
}
