package nbank.ui.secondPart;

import com.codeborne.selenide.Selectors;
import io.restassured.specification.RequestSpecification;
import nbank.generators.RandomData;
import nbank.models.AccountResponse;
import nbank.models.CreateUserRequest;
import nbank.requests.steps.AdminSteps;
import nbank.requests.steps.UserSteps;
import nbank.specs.RequestSpecs;
import nbank.ui.BaseUiTest;
import nbank.ui.UserStepsUi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Alert;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.assertj.core.api.Assertions.assertThat;

public class DepositTest extends BaseUiTest {

    @ParameterizedTest
    @ValueSource(ints = {4999, 5000, 1})
    public void userCanDepositHisAccount(int depositValue) {
        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account = UserSteps.createAccount(authAsUser);

        UserStepsUi.createUserAndLogin(user);
        $(Selectors.byXpath("//button[contains(text(),'Deposit Money')]")).click();
        $(Selectors.byCssSelector(".account-selector")).selectOptionContainingText(account.getAccountNumber());
        $(Selectors.byCssSelector(".deposit-input")).type(String.valueOf(depositValue));
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("✅ Successfully deposited $" + depositValue + " to account " + account.getAccountNumber() + "!");

        AccountResponse[] accounts = UserSteps.getCustomerAccounts(authAsUser);
        Assertions.assertEquals(depositValue, accounts[0].getBalance());
    }

    @Test
    public void balanceIncreasesOnTheCorrectAccount() {
        int depositValue = RandomData.getDepositValue();

        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account1 = UserSteps.createAccount(authAsUser);
        AccountResponse account2 = UserSteps.createAccount(authAsUser);

        long firstAccountNumber = Integer.parseInt(account1.getAccountNumber().substring(3));
        long secondAccountNumber = Integer.parseInt(account2.getAccountNumber().substring(3));

        UserStepsUi.createUserAndLogin(user);
        $(Selectors.byXpath("//button[contains(text(),'Deposit Money')]")).click();
        $(Selectors.byCssSelector(".account-selector")).selectOptionContainingText(account1.getAccountNumber());
        $(Selectors.byCssSelector(".deposit-input")).type(String.valueOf(depositValue));
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("✅ Successfully deposited $" + depositValue + " to account " + account1.getAccountNumber() + "!");
        alert.accept();

        $(Selectors.byXpath("//button[contains(text(),'Deposit Money')]")).click();
        String account1Text = $(Selectors.byXpath("//option[@value='" + firstAccountNumber + "']")).getText();
        String account2Text = $(Selectors.byXpath("//option[@value='" + secondAccountNumber + "']")).getText();

        Double account1Balance = Double.parseDouble(account1Text.substring(account1Text.indexOf('$') + 1, account1Text.indexOf(')')));
        Double account2Balance = Double.parseDouble(account2Text.substring(account2Text.indexOf('$') + 1, account2Text.indexOf(')')));

        //В списке Choose an account на странице отображаются верные балансы аккаунтов
        Assertions.assertEquals(depositValue, account1Balance);
        Assertions.assertEquals(0, account2Balance);

        AccountResponse[] accounts = UserSteps.getCustomerAccounts(authAsUser);

        //Баланс пополнился (проверка через бэкенд, что аккаунт 1 с балансом)
        //Баланс пополнился (проверка через бэкенд, что аккаунт 2 пустой)
        AccountResponse account1response = Arrays.stream(accounts).filter(el->el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = Arrays.stream(accounts).filter(el->el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(depositValue, account1response.getBalance());
        Assertions.assertEquals(0, account2response.getBalance());
    }

    @Test
    public void alertAppearsWhenSendingDepositMoneyFormWithoutAccount() {
        int depositValue = RandomData.getDepositValue();

        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account = UserSteps.createAccount(authAsUser);

        UserStepsUi.createUserAndLogin(user);
        $(Selectors.byXpath("//button[contains(text(),'Deposit Money')]")).click();
        $(Selectors.byCssSelector(".deposit-input")).type(String.valueOf(depositValue));
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("❌ Please select an account.");
    }

    @Test
    public void alertAppearsWhenSendingDepositMoneyFormWithoutAmount() {
        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account = UserSteps.createAccount(authAsUser);

        UserStepsUi.createUserAndLogin(user);
        $(Selectors.byXpath("//button[contains(text(),'Deposit Money')]")).click();
        $(Selectors.byCssSelector(".account-selector")).selectOptionContainingText(account.getAccountNumber());
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("❌ Please enter a valid amount.");
    }

    @Test
    public void alertAppearsWhenSendingDepositMoneyFormWithAmountMoreThanFiveThousands() {
        int depositValue = RandomData.getDepositValue() + 5000;

        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account = UserSteps.createAccount(authAsUser);

        UserStepsUi.createUserAndLogin(user);
        $(Selectors.byXpath("//button[contains(text(),'Deposit Money')]")).click();
        $(Selectors.byCssSelector(".account-selector")).selectOptionContainingText(account.getAccountNumber());
        $(Selectors.byCssSelector(".deposit-input")).type(String.valueOf(depositValue));
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("❌ Please deposit less or equal to 5000$.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, 0})
    public void userCannotDepositInvalidAmountOfMoney(int depositValue) {
        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account = UserSteps.createAccount(authAsUser);

        UserStepsUi.createUserAndLogin(user);
        $(Selectors.byXpath("//button[contains(text(),'Deposit Money')]")).click();
        $(Selectors.byCssSelector(".account-selector")).selectOptionContainingText(account.getAccountNumber());
        $(Selectors.byCssSelector(".deposit-input")).type(String.valueOf(depositValue));
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("❌ Please enter a valid amount.");

        AccountResponse[] accounts = UserSteps.getCustomerAccounts(authAsUser);
        Assertions.assertEquals(0, accounts[0].getBalance());
    }
}
