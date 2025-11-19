package nbank.ui;

import com.codeborne.selenide.Selenide;
import nbank.models.CreateUserRequest;
import nbank.models.LoginUserRequest;
import nbank.requests.skeleton.Endpoint;
import nbank.requests.skeleton.requesters.CrudRequester;
import nbank.specs.RequestSpecs;
import nbank.specs.ResponseSpecs;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class UserStepsUi {
    public static void createUserAndLogin(CreateUserRequest user) {
        Selenide.open("/login");

        String userAuthHeader = new CrudRequester(
                RequestSpecs.unauthSpec(),
                Endpoint.LOGIN,
                ResponseSpecs.requestReturnsOK())
                .post(LoginUserRequest.builder().username(user.getUsername()).password(user.getPassword()).build())
                .extract()
                .header("Authorization");

        Selenide.open("/");

        executeJavaScript("localStorage.setItem('authToken', arguments[0]);", userAuthHeader);
        Selenide.open("/dashboard");
    }
}
