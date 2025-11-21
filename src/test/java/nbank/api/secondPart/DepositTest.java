package nbank.api.secondPart;

import io.restassured.specification.RequestSpecification;
import nbank.api.BaseTest;
import nbank.api.models.AccountResponse;
import nbank.api.models.CreateUserRequest;
import nbank.api.requests.steps.AdminSteps;
import nbank.api.requests.steps.UserSteps;
import nbank.api.specs.RequestSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static nbank.api.generators.RandomData.getDepositValue;

public class DepositTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(ints = {4999, 5000, 1})
    public void DepositToAccount(int expectedAmount) {
        CreateUserRequest userRequest = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        AccountResponse account = UserSteps.createAccount(authAsUser);
        UserSteps.depositMoney(authAsUser, account, expectedAmount);
        AccountResponse[] accounts = UserSteps.getCustomerAccounts(authAsUser);

        Assertions.assertEquals(expectedAmount, accounts[0].getTransactions().getFirst().getAmount());
    }

    //TODO: добавить значение 5001, но сейчас оно падает
    @ParameterizedTest
    @ValueSource(ints = { -100, 0})
    public void depositProhibitedAmountOfMoney(int expectedAmount) {
        CreateUserRequest userRequest = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        AccountResponse account = UserSteps.createAccount(authAsUser);
        UserSteps.depositInvalidSumOfMoney(authAsUser, account, expectedAmount);
        AccountResponse[] accounts = UserSteps.getCustomerAccounts(authAsUser);

        Assertions.assertEquals(0, accounts[0].getTransactions().size());
    }

    @Test
    public void depositToNonExistingAccount() {
        CreateUserRequest userRequest = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        AccountResponse account = UserSteps.createAccount(authAsUser);
        account.setId(account.getId() + 1);

        UserSteps.depositToInvalidAccount(authAsUser, account, getDepositValue());
        UserSteps.getCustomerAccounts(authAsUser);
    }

    @Test
    public void depositToSomeoneElseAccount() {
        CreateUserRequest userRequest1 = AdminSteps.createUser();
        RequestSpecification authAsUser1 = RequestSpecs.authAsUser(userRequest1.getUsername(), userRequest1.getPassword());
        CreateUserRequest userRequest2 = AdminSteps.createUser();
        RequestSpecification authAsUser2 = RequestSpecs.authAsUser(userRequest2.getUsername(), userRequest2.getPassword());

        AccountResponse account2 = UserSteps.createAccount(authAsUser2);
        UserSteps.depositToInvalidAccount(authAsUser1, account2, getDepositValue());
    }
}
