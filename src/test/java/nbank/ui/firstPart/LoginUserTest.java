package nbank.ui.firstPart;


import nbank.api.BaseTest;
import nbank.api.models.CreateUserRequest;
import nbank.api.models.CreateUserResponse;
import nbank.api.models.LoginUserRequest;
import nbank.api.requests.skeleton.Endpoint;
import nbank.api.requests.skeleton.requesters.CrudRequester;
import nbank.api.requests.skeleton.requesters.ValidatedCrudRequester;
import nbank.api.requests.steps.AdminSteps;
import nbank.api.specs.RequestSpecs;
import nbank.api.specs.ResponseSpecs;
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
