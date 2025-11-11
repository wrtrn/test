package nbank.secondPart;

import io.restassured.specification.RequestSpecification;
import nbank.BaseTest;
import nbank.models.CreateUserResponse;
import nbank.models.ProfileResponse;
import nbank.models.ProfileUpdateRequest;
import nbank.requests.CustomerProfileRequester;
import nbank.specs.RequestSpecs;
import nbank.specs.ResponseSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProfileTest extends BaseTest {

    @Test
    public void userCanUpdateProfileName() {

        CreateUserResponse userResponse = createUser();

        RequestSpecification authAsCreatedUser = RequestSpecs.authAsUser(userResponse.getUsername(), userResponse.getPassword());

        ProfileUpdateRequest body = ProfileUpdateRequest.builder()
                .name("Ivan")
                .build();

        new CustomerProfileRequester(authAsCreatedUser, ResponseSpecs.requestReturnsOK())
                .put(body);

        ProfileResponse response = new CustomerProfileRequester(authAsCreatedUser, ResponseSpecs.requestReturnsOK())
                .get().extract().as(ProfileResponse.class);


        Assertions.assertEquals("Ivan", response.getName());
    }

    @Test
    public void userCanSetEmptyName() {
        CreateUserResponse userResponse = createUser();

        RequestSpecification authAsCreatedUser = RequestSpecs.authAsUser(userResponse.getUsername(), userResponse.getPassword());

        ProfileUpdateRequest body = ProfileUpdateRequest.builder()
                .name("")
                .build();

        new CustomerProfileRequester(authAsCreatedUser, ResponseSpecs.requestReturnsOK())
                .put(body);

        ProfileResponse response = new CustomerProfileRequester(authAsCreatedUser, ResponseSpecs.requestReturnsOK())
                .get().extract().as(ProfileResponse.class);

        Assertions.assertEquals("", response.getName());
    }
}
