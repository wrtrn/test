package nbank.fistPart;

import nbank.BaseTest;
import nbank.models.CreateUserRequest;
import nbank.requests.skeleton.Endpoint;
import nbank.requests.skeleton.requesters.CrudRequester;
import nbank.requests.steps.AdminSteps;
import nbank.specs.RequestSpecs;
import nbank.specs.ResponseSpecs;
import org.junit.jupiter.api.Test;

public class CreateAccountTest extends BaseTest {

    @Test
    public void userCanCreateAccountTest() {
        CreateUserRequest userRequest = AdminSteps.createUser();

        new CrudRequester(RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated())
                .post(null);

        // запросить все аккаунты пользователя и проверить, что наш аккаунт там
    }
}