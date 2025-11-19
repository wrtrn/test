package nbank.ui.secondPart;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import io.restassured.specification.RequestSpecification;
import nbank.api.generators.RandomData;
import nbank.api.models.AccountResponse;
import nbank.api.models.CreateUserRequest;
import nbank.api.requests.steps.AdminSteps;
import nbank.api.requests.steps.UserSteps;
import nbank.api.specs.RequestSpecs;
import nbank.ui.BaseUiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Alert;

import java.util.Arrays;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static nbank.api.generators.RandomData.getTransferValue;
import static org.assertj.core.api.Assertions.assertThat;

public class TransferTest extends BaseUiTest {

    @Test
    public void userCanSendBalanceToAnotherAccount() {
        int transferValue = getTransferValue();
        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account1 = UserSteps.createAccount(authAsUser);
        AccountResponse account2 = UserSteps.createAccount(authAsUser);

        long firstAccountNumber = Integer.parseInt(account1.getAccountNumber().substring(3));
        long secondAccountNumber = Integer.parseInt(account2.getAccountNumber().substring(3));

        for (int i = 0; i < 2; i++) {
            UserSteps.depositMoney(authAsUser, account1, 5000);
        }

        authAsUser(user);
        Selenide.open("/dashboard");
        $(byXpath("//button[contains(text(),'Make a Transfer')]")).click();
        $(Selectors.byCssSelector(".account-selector")).selectOptionContainingText(account1.getAccountNumber());
        $(Selectors.byCssSelector("[placeholder='Enter recipient name']")).type(RandomData.getUsername());
        $(Selectors.byCssSelector("[placeholder='Enter recipient account number']")).type(account2.getAccountNumber());
        $(Selectors.byCssSelector("[placeholder='Enter amount']")).type(String.valueOf(transferValue));
        $(Selectors.byCssSelector("#confirmCheck")).click();
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("✅ Successfully transferred $" + transferValue + " to account " + account2.getAccountNumber() + "!");

        Selenide.refresh();
        String account1Text = $(byXpath("//option[@value='" + firstAccountNumber + "']")).getText();
        String account2Text = $(byXpath("//option[@value='" + secondAccountNumber + "']")).getText();

        Double account1Balance = Double.parseDouble(account1Text.substring(account1Text.indexOf('$') + 1, account1Text.indexOf(')')));
        Double account2Balance = Double.parseDouble(account2Text.substring(account2Text.indexOf('$') + 1, account2Text.indexOf(')')));

        //В списке Choose an account на странице отображаются верные балансы аккаунтов
        Assertions.assertEquals(10000 - transferValue, account1Balance);
        Assertions.assertEquals(transferValue, account2Balance);

        AccountResponse[] accounts = UserSteps.getCustomerAccounts(authAsUser);
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(10000 - transferValue, account1response.getBalance());
        Assertions.assertEquals(transferValue, account2response.getBalance());
    }

    @Test
    public void userCannotTransferWithEmptyFields() {
        int depositValue = RandomData.getDepositValue();
        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account1 = UserSteps.createAccount(authAsUser);
        AccountResponse account2 = UserSteps.createAccount(authAsUser);
        UserSteps.depositMoney(authAsUser, account1, depositValue);
        authAsUser(user);
        Selenide.open("/dashboard");

        $(byXpath("//button[contains(text(),'Make a Transfer')]")).click();
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("❌ Please fill all fields and confirm.");

        AccountResponse[] accounts = UserSteps.getCustomerAccounts(authAsUser);
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(depositValue, account1response.getBalance());
        Assertions.assertEquals(0, account2response.getBalance());
    }

    @Test
    public void userCannotTransferWithEmptyAccount() {
        int depositValue = RandomData.getDepositValue();
        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account1 = UserSteps.createAccount(authAsUser);
        AccountResponse account2 = UserSteps.createAccount(authAsUser);
        UserSteps.depositMoney(authAsUser, account1, depositValue);
        authAsUser(user);
        Selenide.open("/dashboard");

        $(byXpath("//button[contains(text(),'Make a Transfer')]")).click();

        $(Selectors.byCssSelector("[placeholder='Enter recipient name']")).type(RandomData.getUsername());
        $(Selectors.byCssSelector("[placeholder='Enter recipient account number']")).type(account2.getAccountNumber());
        $(Selectors.byCssSelector("[placeholder='Enter amount']")).type("1");
        $(Selectors.byCssSelector("#confirmCheck")).click();
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("❌ Please fill all fields and confirm.");

        AccountResponse[] accounts = UserSteps.getCustomerAccounts(authAsUser);
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(depositValue, account1response.getBalance());
        Assertions.assertEquals(0, account2response.getBalance());
    }

    @Test
    public void userCanTransferWithEmptyRecipientName() {
        int depositValue = RandomData.getDepositValue();
        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account1 = UserSteps.createAccount(authAsUser);
        AccountResponse account2 = UserSteps.createAccount(authAsUser);
        UserSteps.depositMoney(authAsUser, account1, depositValue);
        authAsUser(user);
        Selenide.open("/dashboard");

        $(byXpath("//button[contains(text(),'Make a Transfer')]")).click();

        $(Selectors.byCssSelector(".account-selector")).selectOptionContainingText(account1.getAccountNumber());
        $(Selectors.byCssSelector("[placeholder='Enter recipient account number']")).type(account2.getAccountNumber());
        $(Selectors.byCssSelector("[placeholder='Enter amount']")).type("1");
        $(Selectors.byCssSelector("#confirmCheck")).click();
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("✅ Successfully transferred $" + 1 + " to account " + account2.getAccountNumber() + "!");

        AccountResponse[] accounts = UserSteps.getCustomerAccounts(authAsUser);
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(depositValue - 1, account1response.getBalance());
        Assertions.assertEquals(1, account2response.getBalance());
    }

    @Test
    public void userCannotTransferWithEmptyAmount() {
        int depositValue = RandomData.getDepositValue();
        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account1 = UserSteps.createAccount(authAsUser);
        AccountResponse account2 = UserSteps.createAccount(authAsUser);
        UserSteps.depositMoney(authAsUser, account1, depositValue);
        authAsUser(user);
        Selenide.open("/dashboard");

        $(byXpath("//button[contains(text(),'Make a Transfer')]")).click();

        $(Selectors.byCssSelector(".account-selector")).selectOptionContainingText(account1.getAccountNumber());
        $(Selectors.byCssSelector("[placeholder='Enter recipient name']")).type(RandomData.getUsername());
        $(Selectors.byCssSelector("[placeholder='Enter recipient account number']")).type(account2.getAccountNumber());
        $(Selectors.byCssSelector("#confirmCheck")).click();
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("❌ Please fill all fields and confirm.");

        AccountResponse[] accounts = UserSteps.getCustomerAccounts(authAsUser);
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(depositValue, account1response.getBalance());
        Assertions.assertEquals(0, account2response.getBalance());
    }

    @Test
    public void userCannotTransferWithEmptyConfirmCheck() {
        int depositValue = RandomData.getDepositValue();
        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account1 = UserSteps.createAccount(authAsUser);
        AccountResponse account2 = UserSteps.createAccount(authAsUser);
        UserSteps.depositMoney(authAsUser, account1, depositValue);
        authAsUser(user);
        Selenide.open("/dashboard");

        $(byXpath("//button[contains(text(),'Make a Transfer')]")).click();

        $(Selectors.byCssSelector(".account-selector")).selectOptionContainingText(account1.getAccountNumber());
        $(Selectors.byCssSelector("[placeholder='Enter recipient name']")).type(RandomData.getUsername());
        $(Selectors.byCssSelector("[placeholder='Enter recipient account number']")).type(account2.getAccountNumber());
        $(Selectors.byCssSelector("[placeholder='Enter amount']")).type("1");
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("❌ Please fill all fields and confirm.");

        AccountResponse[] accounts = UserSteps.getCustomerAccounts(authAsUser);
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(depositValue, account1response.getBalance());
        Assertions.assertEquals(0, account2response.getBalance());
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, 0, 10001})
    public void userCanNotSendBalanceWithInvalidSum(int transferValue) {
        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account1 = UserSteps.createAccount(authAsUser);
        AccountResponse account2 = UserSteps.createAccount(authAsUser);

        for (int i = 0; i < 3; i++) {
            UserSteps.depositMoney(authAsUser, account1, 5000);
        }

        authAsUser(user);
        Selenide.open("/dashboard");
        $(byXpath("//button[contains(text(),'Make a Transfer')]")).click();
        $(Selectors.byCssSelector(".account-selector")).selectOptionContainingText(account1.getAccountNumber());
        $(Selectors.byCssSelector("[placeholder='Enter recipient name']")).type(RandomData.getUsername());
        $(Selectors.byCssSelector("[placeholder='Enter recipient account number']")).type(account2.getAccountNumber());
        $(Selectors.byCssSelector("[placeholder='Enter amount']")).type(String.valueOf(transferValue));
        $(Selectors.byCssSelector("#confirmCheck")).click();
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("❌ Error: Invalid transfer: insufficient funds or invalid accounts");

        AccountResponse[] accounts = UserSteps.getCustomerAccounts(authAsUser);
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(15000, account1response.getBalance());
        Assertions.assertEquals(0, account2response.getBalance());
    }

    @Test
    public void userCanSendBalanceToAnotherAccountUsingTransferAgain() {
        int transferValue = getTransferValue();
        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account1 = UserSteps.createAccount(authAsUser);
        AccountResponse account2 = UserSteps.createAccount(authAsUser);

        for (int i = 0; i < 4; i++) {
            UserSteps.depositMoney(authAsUser, account1, 5000);
        }

        authAsUser(user);
        Selenide.open("/dashboard");
        $(byXpath("//button[contains(text(),'Make a Transfer')]")).click();
        $(Selectors.byCssSelector(".account-selector")).selectOptionContainingText(account1.getAccountNumber());
        $(Selectors.byCssSelector("[placeholder='Enter recipient name']")).type(RandomData.getUsername());
        $(Selectors.byCssSelector("[placeholder='Enter recipient account number']")).type(account2.getAccountNumber());
        $(Selectors.byCssSelector("[placeholder='Enter amount']")).type(String.valueOf(transferValue));
        $(Selectors.byCssSelector("#confirmCheck")).click();
        $(Selectors.byCssSelector(".btn-primary")).click();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText).contains("✅ Successfully transferred $" + transferValue + " to account " + account1.getAccountNumber() + "!");

        Selenide.refresh();
        $(byXpath("//button[contains(text(),'Transfer Again')]")).click();
        $(byXpath("//span[contains(., 'TRANSFER_OUT')]/following-sibling::button")).click();
        $(Selectors.byCssSelector("select.form-control")).selectOptionContainingText(account2.getAccountNumber());
        $(byXpath("//label[contains(., 'Amount:')]/following-sibling::input")).type(String.valueOf(transferValue));
        $(Selectors.byCssSelector("#confirmCheck")).click();
        $(Selectors.byCssSelector(".btn-success")).click();

        AccountResponse[] accounts = UserSteps.getCustomerAccounts(authAsUser);
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = Arrays.stream(accounts).filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(20000 - transferValue*2, account1response.getBalance());
        Assertions.assertEquals(transferValue*2, account2response.getBalance());
    }
}
