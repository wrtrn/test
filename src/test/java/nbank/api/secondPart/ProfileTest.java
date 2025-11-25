package nbank.api.secondPart;

import io.restassured.specification.RequestSpecification;
import nbank.api.BaseTest;
import nbank.api.generators.RandomData;
import nbank.api.models.CreateUserRequest;
import nbank.api.models.ProfileResponse;
import nbank.api.requests.steps.AdminSteps;
import nbank.api.requests.steps.UserSteps;
import nbank.api.specs.RequestSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProfileTest extends BaseTest {

    @Test
    public void userCanUpdateProfileName() {
        CreateUserRequest userRequest = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        String newUsername = RandomData.getUsername();
        UserSteps.updateProfile(authAsUser, newUsername);

        ProfileResponse response = UserSteps.getCustomerProfile(authAsUser);
        Assertions.assertEquals(newUsername, response.getName());
    }

    @Test
    public void userCanSetEmptyName() {
        CreateUserRequest userRequest = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        UserSteps.updateProfile(authAsUser, "");

        ProfileResponse response = UserSteps.getCustomerProfile(authAsUser);
        Assertions.assertEquals("", response.getName());
    }
}
