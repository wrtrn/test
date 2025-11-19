package nbank.ui.secondPart;

import com.codeborne.selenide.Selenide;
import io.restassured.specification.RequestSpecification;
import nbank.api.generators.RandomData;
import nbank.api.models.AccountResponse;
import nbank.api.models.CreateUserRequest;
import nbank.api.requests.steps.AdminSteps;
import nbank.api.requests.steps.UserSteps;
import nbank.api.specs.RequestSpecs;
import nbank.ui.BaseUiTest;
import nbank.ui.pages.BankAlert;
import nbank.ui.pages.MakeATransfer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static nbank.api.generators.RandomData.getTransferValue;
import static nbank.ui.pages.BankAlert.*;

public class TransferTest extends BaseUiTest {

    @Test
    public void userCanSendBalanceToAnotherAccount() {
        MakeATransfer makeATransfer = new MakeATransfer();

        int transferValue = getTransferValue();
        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account1 = UserSteps.createAccount(authAsUser);
        AccountResponse account2 = UserSteps.createAccount(authAsUser);

        for (int i = 0; i < 2; i++) {
            UserSteps.depositMoney(authAsUser, account1, 5000);
        }
        authAsUser(user);

        makeATransfer
                .open()
                .selectAccountFromSelector(account1)
                .enterRecipientName(RandomData.getUsername())
                .enterRecipientAccountNumber(account2)
                .enterAmount(transferValue)
                .checkConfirmation()
                .clickDepositButton()
                .checkAlertMessageAndAccept(TRANSFER_MESSAGE_PART1.getMessage() + transferValue +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART2.getMessage() +
                        account2.getAccountNumber() +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART3.getMessage());

        Selenide.refresh();

        Double account1Balance = makeATransfer.getSelectorAccountBalance(account1);
        Double account2Balance = makeATransfer.getSelectorAccountBalance(account2);

        //В списке Choose an account на странице отображаются верные балансы аккаунтов
        Assertions.assertEquals(10000 - transferValue, account1Balance);
        Assertions.assertEquals(transferValue, account2Balance);

        List<AccountResponse> accounts = new UserSteps(user.getUsername(), user.getPassword()).getAllAccounts();
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
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

        new MakeATransfer()
                .open()
                .clickDepositButton()
                .checkAlertMessageAndAccept(PLEASE_FILL_ALL_FIELDS_AND_CONFIRM.getMessage());


        List<AccountResponse> accounts = new UserSteps(user.getUsername(), user.getPassword()).getAllAccounts();
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
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

        new MakeATransfer()
                .open()
                .selectAccountFromSelector(account1)
                .enterRecipientName(RandomData.getUsername())
                .enterAmount(1)
                .checkConfirmation()
                .clickDepositButton()
                .checkAlertMessageAndAccept(PLEASE_FILL_ALL_FIELDS_AND_CONFIRM.getMessage());

        List<AccountResponse> accounts = new UserSteps(user.getUsername(), user.getPassword()).getAllAccounts();
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(depositValue, account1response.getBalance());
        Assertions.assertEquals(0, account2response.getBalance());
    }

    @Test
    public void userCanTransferWithEmptyRecipientName() {
        int depositValue = RandomData.getDepositValue();
        int transferValue = 1;

        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account1 = UserSteps.createAccount(authAsUser);
        AccountResponse account2 = UserSteps.createAccount(authAsUser);
        UserSteps.depositMoney(authAsUser, account1, depositValue);
        authAsUser(user);

        new MakeATransfer()
                .open()
                .selectAccountFromSelector(account1)
                .enterRecipientAccountNumber(account2)
                .enterAmount(transferValue)
                .checkConfirmation()
                .clickDepositButton()
                .checkAlertMessageAndAccept(TRANSFER_MESSAGE_PART1.getMessage() + transferValue +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART2.getMessage() +
                        account2.getAccountNumber() +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART3.getMessage());

        List<AccountResponse> accounts = new UserSteps(user.getUsername(), user.getPassword()).getAllAccounts();
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(depositValue - transferValue, account1response.getBalance());
        Assertions.assertEquals(transferValue, account2response.getBalance());
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

        new MakeATransfer()
                .open()
                .selectAccountFromSelector(account1)
                .enterRecipientName(RandomData.getUsername())
                .enterRecipientAccountNumber(account2)
                .checkConfirmation()
                .clickDepositButton()
                .checkAlertMessageAndAccept(PLEASE_FILL_ALL_FIELDS_AND_CONFIRM.getMessage());

        List<AccountResponse> accounts = new UserSteps(user.getUsername(), user.getPassword()).getAllAccounts();
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
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

        new MakeATransfer()
                .open()
                .selectAccountFromSelector(account1)
                .enterRecipientName(RandomData.getUsername())
                .enterRecipientAccountNumber(account2)
                .enterAmount(1)
                .clickDepositButton()
                .checkAlertMessageAndAccept(PLEASE_FILL_ALL_FIELDS_AND_CONFIRM.getMessage());

        List<AccountResponse> accounts = new UserSteps(user.getUsername(), user.getPassword()).getAllAccounts();
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
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

        new MakeATransfer()
                .open()
                .selectAccountFromSelector(account1)
                .enterRecipientName(RandomData.getUsername())
                .enterRecipientAccountNumber(account2)
                .enterAmount(transferValue)
                .checkConfirmation()
                .clickDepositButton()
                .checkAlertMessageAndAccept(INSUFFICIENT_FUNDS_OR_INVALID_ACCOUNTS.getMessage());

        List<AccountResponse> accounts = new UserSteps(user.getUsername(), user.getPassword()).getAllAccounts();
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(15000, account1response.getBalance());
        Assertions.assertEquals(0, account2response.getBalance());
    }

    @Test
    public void userCanSendBalanceToAnotherAccountUsingTransferAgain() {
        int transferValue = getTransferValue();
        MakeATransfer makeATransfer = new MakeATransfer();

        CreateUserRequest user = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());
        AccountResponse account1 = UserSteps.createAccount(authAsUser);
        AccountResponse account2 = UserSteps.createAccount(authAsUser);

        for (int i = 0; i < 4; i++) {
            UserSteps.depositMoney(authAsUser, account1, 5000);
        }

        authAsUser(user);

        makeATransfer
                .open()
                .selectAccountFromSelector(account1)
                .enterRecipientName(RandomData.getUsername())
                .enterRecipientAccountNumber(account2)
                .enterAmount(transferValue)
                .checkConfirmation()
                .clickDepositButton()
                .checkAlertMessageAndAccept(TRANSFER_MESSAGE_PART1.getMessage() + transferValue +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART2.getMessage() +
                        account2.getAccountNumber() +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART3.getMessage());

        Selenide.refresh();

        makeATransfer
                .transferOutAgain(account1, transferValue);

        List<AccountResponse> accounts = new UserSteps(user.getUsername(), user.getPassword()).getAllAccounts();
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(account1.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(account2.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(20000 - transferValue * 2, account1response.getBalance());
        Assertions.assertEquals(transferValue * 2, account2response.getBalance());
    }
}
