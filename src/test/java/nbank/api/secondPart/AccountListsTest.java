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

public class AccountListsTest extends BaseTest {

    @Test
    public void newUserGetsEmptyAccountListTest() {
        CreateUserRequest userRequest = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        AccountResponse[] arr = UserSteps.getCustomerAccounts(authAsUser);
        Assertions.assertEquals(0, arr.length);
    }

    @Test
    public void twoAccountsCreationTest() {
        CreateUserRequest userRequest = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        AccountResponse account1 = UserSteps.createAccount(authAsUser);
        AccountResponse account2 = UserSteps.createAccount(authAsUser);

        int firstAccountNumber = Integer.parseInt(account1.getAccountNumber().substring(3));
        int secondAccountNumber = Integer.parseInt(account2.getAccountNumber().substring(3));
        Assertions.assertEquals(firstAccountNumber, secondAccountNumber - 1);
    }
}
