package nbank;

import nbank.generators.RandomData;
import nbank.models.CreateUserRequest;
import nbank.models.CreateUserResponse;
import nbank.models.UserRole;
import nbank.requests.AdminCreateUserRequester;
import nbank.specs.RequestSpecs;
import nbank.specs.ResponseSpecs;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected SoftAssertions softly;

    @BeforeEach
    public void setupTest() {
        this.softly = new SoftAssertions();
    }

    @AfterEach
    public void afterTest() {
        softly.assertAll();
    }

    public CreateUserResponse createUser() {
        String username = RandomData.getUsername();
        String password = RandomData.getPassword();

        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .username(username)
                .password(password)
                .role(UserRole.USER.toString())
                .build();


        CreateUserResponse createUserResponse = new AdminCreateUserRequester(RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(createUserRequest).extract().as(CreateUserResponse.class);
        createUserResponse.setPassword(password);
        return createUserResponse;
    }
}
