package nbank;

import nbank.generators.RandomData;
import nbank.models.CreateUserRequest;
import nbank.models.CreateUserResponse;
import nbank.models.UserRole;
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
}
