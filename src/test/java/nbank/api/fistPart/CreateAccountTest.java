package nbank.api.fistPart;

import nbank.api.BaseTest;
import nbank.api.models.CreateUserRequest;
import nbank.api.requests.skeleton.Endpoint;
import nbank.api.requests.skeleton.requesters.CrudRequester;
import nbank.api.requests.steps.AdminSteps;
import nbank.api.specs.RequestSpecs;
import nbank.api.specs.ResponseSpecs;
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