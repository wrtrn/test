package nbank.ui.secondPart;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import nbank.api.models.CreateUserRequest;
import nbank.api.requests.steps.AdminSteps;
import nbank.ui.BaseUiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Selenide.*;
import static nbank.api.generators.RandomData.getUsername;
import static org.assertj.core.api.Assertions.assertThat;

public class NicknameTest extends BaseUiTest {

    @Test
    public void userCanChangeNickname() {
        String newName = getUsername() + " " + getUsername();

        CreateUserRequest user = AdminSteps.createUser();

        authAsUser(user);
        Selenide.open("/dashboard");

        Selenide.sleep(1000);
        String mainPageNameText = $x("//*[@class='welcome-text']").getText();
        String rightCornerNameText = $x("//*[@class='user-name']").getText();
        Assertions.assertEquals("Welcome, noname!", mainPageNameText);
        Assertions.assertEquals("Noname", rightCornerNameText);

        $x("//*[@class='user-name']").click();
        Selenide.sleep(1000);
        $(".form-control").setValue(newName);
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("✅ Name updated successfully!");
        alert.accept();

        Selenide.refresh();
        $(".btn-outline-primary").click();

        Selenide.sleep(1000);
        mainPageNameText = $x("//*[@class='welcome-text']").getText();
        rightCornerNameText = $x("//*[@class='user-name']").getText();

        Assertions.assertEquals("Welcome, " + newName + "!", mainPageNameText);
        Assertions.assertEquals(newName, rightCornerNameText);
    }


    @ParameterizedTest
    @ValueSource(strings = {"ivan", "", "1234", "$$"})
    public void userCanNotChangeNicknameToInvalid(String newName) {

        CreateUserRequest user = AdminSteps.createUser();

        authAsUser(user);
        Selenide.open("/dashboard");

        Selenide.sleep(1000);
        String mainPageNameText = $x("//*[@class='welcome-text']").getText();
        String rightCornerNameText = $x("//*[@class='user-name']").getText();
        Assertions.assertEquals("Welcome, noname!", mainPageNameText);
        Assertions.assertEquals("Noname", rightCornerNameText);

        $x("//*[@class='user-name']").click();
        Selenide.sleep(1000);
        $(".form-control").setValue(newName);
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("❌ Please enter a valid name.");
        alert.accept();

        Selenide.refresh();
        $(".btn-outline-primary").click();

        Selenide.sleep(1000);
        mainPageNameText = $x("//*[@class='welcome-text']").getText();
        rightCornerNameText = $x("//*[@class='user-name']").getText();
        Assertions.assertEquals("Welcome, noname!", mainPageNameText);
        Assertions.assertEquals("Noname", rightCornerNameText);
    }
}
