package nbank.ui.secondPart;

import io.restassured.specification.RequestSpecification;
import nbank.api.generators.RandomData;
import nbank.api.models.AccountResponse;
import nbank.api.models.CreateUserRequest;
import nbank.api.requests.steps.AdminSteps;
import nbank.api.requests.steps.UserSteps;
import nbank.api.specs.RequestSpecs;
import nbank.ui.BaseUiTest;
import nbank.ui.pages.BankAlert;
import nbank.ui.pages.DepositMoney;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static nbank.ui.pages.BankAlert.*;

public class DepositTest extends BaseUiTest {

    @ParameterizedTest
    @ValueSource(ints = {4999, 5000, 1})
    public void userCanDepositHisAccount(int depositValue) {

        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account = UserSteps.createAccount(authAsUser);
        authAsUser(user);

        new DepositMoney()
                .open()
                .selectParticularAccount(account)
                .enterAmount(depositValue)
                .clickDepositBtn()
                .checkAlertMessageAndAccept(DEPOSIT_MESSAGE_PART1.getMessage()
                        + depositValue +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART2.getMessage() +
                        account.getAccountNumber() +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART3.getMessage());

        List<AccountResponse> accounts = new UserSteps(user.getUsername(), user.getPassword()).getAllAccounts();
        Assertions.assertEquals(depositValue, accounts.getFirst().getBalance());
    }

    @Test
    public void balanceIncreasesOnTheCorrectAccount() {
        int depositValue = RandomData.getDepositValue();

        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account1 = UserSteps.createAccount(authAsUser);
        AccountResponse account2 = UserSteps.createAccount(authAsUser);
        authAsUser(user);

        new DepositMoney()
                .open()
                .selectParticularAccount(account1)
                .enterAmount(depositValue)
                .clickDepositBtn()
                .checkAlertMessageAndAccept(DEPOSIT_MESSAGE_PART1.getMessage()
                        + depositValue +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART2.getMessage() +
                        account1.getAccountNumber() +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART3.getMessage())
                .open();


        String account1Text = new DepositMoney().getSelectorAccountText(account1);
        String account2Text = new DepositMoney().getSelectorAccountText(account2);

        Double account1Balance = Double.parseDouble(account1Text.substring(account1Text.indexOf('$') + 1, account1Text.indexOf(')')));
        Double account2Balance = Double.parseDouble(account2Text.substring(account2Text.indexOf('$') + 1, account2Text.indexOf(')')));

        //В списке Choose an account на странице отображаются верные балансы аккаунтов
        Assertions.assertEquals(depositValue, account1Balance);
        Assertions.assertEquals(0, account2Balance);

        List<AccountResponse> accounts = new UserSteps(user.getUsername(), user.getPassword()).getAllAccounts();

        //Баланс пополнился (проверка через бэкенд, что аккаунт 1 с балансом)
        //Баланс пополнился (проверка через бэкенд, что аккаунт 2 пустой)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(depositValue, account1response.getBalance());
        Assertions.assertEquals(0, account2response.getBalance());
    }

    @Test
    public void alertAppearsWhenSendingDepositMoneyFormWithoutAccount() {
        int depositValue = RandomData.getDepositValue();

        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        UserSteps.createAccount(authAsUser);
        authAsUser(user);

        new DepositMoney()
                .open()
                .enterAmount(depositValue)
                .clickDepositBtn()
                .checkAlertMessageAndAccept(PLEASE_SELECT_AN_ACCOUNT.getMessage());
    }

    @Test
    public void alertAppearsWhenSendingDepositMoneyFormWithoutAmount() {
        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account = UserSteps.createAccount(authAsUser);
        authAsUser(user);

        new DepositMoney()
                .open()
                .selectParticularAccount(account)
                .clickDepositBtn()
                .checkAlertMessageAndAccept(PLEASE_ENTER_A_VALID_AMOUNT.getMessage());
    }

    @Test
    public void alertAppearsWhenSendingDepositMoneyFormWithAmountMoreThanFiveThousands() {
        int depositValue = RandomData.getDepositValue() + 5000;

        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account = UserSteps.createAccount(authAsUser);
        authAsUser(user);

        new DepositMoney()
                .open()
                .selectParticularAccount(account)
                .enterAmount(depositValue)
                .clickDepositBtn()
                .checkAlertMessageAndAccept(PLEASE_DEPOSIT_LESS_THAN_5000.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, 0})
    public void userCannotDepositInvalidAmountOfMoney(int depositValue) {
        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account = UserSteps.createAccount(authAsUser);
        authAsUser(user);

        new DepositMoney()
                .open()
                .selectParticularAccount(account)
                .enterAmount(depositValue)
                .clickDepositBtn()
                .checkAlertMessageAndAccept(PLEASE_ENTER_A_VALID_AMOUNT.getMessage());

        List<AccountResponse> accounts = new UserSteps(user.getUsername(), user.getPassword()).getAllAccounts();
        Assertions.assertEquals(0, accounts.getFirst().getBalance());
    }
}
