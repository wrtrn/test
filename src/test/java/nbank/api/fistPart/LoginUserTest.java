package nbank.api.fistPart;

import nbank.api.BaseTest;
import nbank.models.CreateUserRequest;
import nbank.models.CreateUserResponse;
import nbank.models.LoginUserRequest;
import nbank.requests.skeleton.Endpoint;
import nbank.requests.skeleton.requesters.CrudRequester;
import nbank.requests.skeleton.requesters.ValidatedCrudRequester;
import nbank.requests.steps.AdminSteps;
import nbank.specs.RequestSpecs;
import nbank.specs.ResponseSpecs;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class LoginUserTest extends BaseTest {

    @Test
    public void adminCanGenerateAuthTokenTest() {
        LoginUserRequest userRequest = LoginUserRequest.builder()
                .username("admin")
                .password("admin")
                .build();

        new ValidatedCrudRequester<CreateUserResponse>(RequestSpecs.unauthSpec(),
                Endpoint.LOGIN,
                ResponseSpecs.requestReturnsOK())
                .post(userRequest);
    }

    @Test
    public void userCanGenerateAuthTokenTest() {
        CreateUserRequest userRequest = AdminSteps.createUser();

        new CrudRequester(RequestSpecs.unauthSpec(),
                Endpoint.LOGIN,
                ResponseSpecs.requestReturnsOK())
                .post(LoginUserRequest.builder().username(userRequest.getUsername()).password(userRequest.getPassword()).build())
                .header("Authorization", Matchers.notNullValue());
    }
}