package nbank.ui;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import nbank.models.CreateUserRequest;

import static com.codeborne.selenide.Selenide.$;

public class UserStepsUi {
    public static void createUserAndLogin(CreateUserRequest user) {
        Selenide.open("/login");

        $(Selectors.byAttribute("placeholder", "Username")).sendKeys(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Password")).sendKeys(user.getPassword());
        $("button").click();
    }
}
