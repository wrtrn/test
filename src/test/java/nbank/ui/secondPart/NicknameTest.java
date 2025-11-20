package nbank.ui.secondPart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.conditions.Visible;
import nbank.api.models.CreateUserRequest;
import nbank.api.requests.steps.AdminSteps;
import nbank.ui.BaseUiTest;
import nbank.ui.pages.EditProfile;
import nbank.ui.pages.UserDashboard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static nbank.api.generators.RandomData.getUsername;
import static nbank.ui.pages.BankAlert.NAME_UPDATED_SUCCESSFULLY;
import static nbank.ui.pages.BankAlert.PLEASE_ENTER_A_VALID_NAME;

public class NicknameTest extends BaseUiTest {

    @Test
    public void userCanChangeNickname() {
        UserDashboard userDashboard = new UserDashboard();

        String newName = getUsername() + " " + getUsername();

        CreateUserRequest user = AdminSteps.createUser();
        authAsUser(user);

        userDashboard.open().getRightCornerNameText().shouldBe(Condition.visible);

        String welcomeText = userDashboard.getWelcomeText().getText();
        String rightCornerNameText = userDashboard.getRightCornerNameText().getText();
        Assertions.assertEquals("Welcome, noname!", welcomeText);
        Assertions.assertEquals("Noname", rightCornerNameText);


        userDashboard.openEditProfile();
        Selenide.sleep(1000);
        new EditProfile().setNewName(newName).checkAlertMessageAndAccept(NAME_UPDATED_SUCCESSFULLY.getMessage());

        userDashboard.open().getRightCornerNameText().shouldBe(Condition.visible);

        welcomeText = userDashboard.createNewAccount().getWelcomeText().getText();
        rightCornerNameText = userDashboard.getRightCornerNameText().getText();

        Assertions.assertEquals("Welcome, " + newName + "!", welcomeText);
        Assertions.assertEquals(newName, rightCornerNameText);
    }


    @ParameterizedTest
    @ValueSource(strings = {"ivan", "", "1234", "$$"})
    public void userCanNotChangeNicknameToInvalid(String newName) {
        EditProfile editProfile = new EditProfile();
        UserDashboard userDashboard = new UserDashboard();

        CreateUserRequest user = AdminSteps.createUser();

        authAsUser(user);

        editProfile.open();
        Selenide.sleep(1000);

        editProfile.setNewName(newName).checkAlertMessageAndAccept(PLEASE_ENTER_A_VALID_NAME.getMessage());

        userDashboard.open().getRightCornerNameText().shouldBe(Condition.visible);

        String welcomeText = userDashboard.getWelcomeText().getText();
        String rightCornerNameText = userDashboard.getRightCornerNameText().getText();
        Assertions.assertEquals("Welcome, noname!", welcomeText);
        Assertions.assertEquals("Noname", rightCornerNameText);
    }
}
