package nbank.secondPart;

import io.restassured.specification.RequestSpecification;
import nbank.BaseTest;
import nbank.models.AccountResponse;
import nbank.models.CreateUserResponse;
import nbank.models.ProfileResponse;
import nbank.requests.CreateAccountRequester;
import nbank.requests.GetCustomerAccountsRequester;
import nbank.specs.RequestSpecs;
import nbank.specs.ResponseSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountListsTest extends BaseTest {

    @Test
    public void newUserGetsEmptyAccountListTest() {
        CreateUserResponse userResponse = createUser();
        RequestSpecification authAsCreatedUser = RequestSpecs.authAsUser(userResponse.getUsername(), userResponse.getPassword());

        ProfileResponse[] arr = new GetCustomerAccountsRequester(authAsCreatedUser, ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(ProfileResponse[].class);

        Assertions.assertEquals(0, arr.length);
    }

    @Test
    public void twoAccountsCreationTest() {
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

        int firstAccountNumber = Integer.parseInt(accountResponse1.getAccountNumber().substring(3));
        int secondAccountNumber = Integer.parseInt(accountResponse2.getAccountNumber().substring(3));

        Assertions.assertEquals(firstAccountNumber, secondAccountNumber - 1);
    }
}
