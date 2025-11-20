package nbank.ui.secondPart;

import com.codeborne.selenide.Selenide;
import nbank.api.generators.RandomData;
import nbank.api.models.AccountResponse;
import nbank.common.annotations.AccountCreation;
import nbank.common.annotations.DepositMoney;
import nbank.common.annotations.UserSession;
import nbank.common.storage.SessionStorage;
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

    @UserSession
    @AccountCreation(2)
    @DepositMoney(value = 5000, multiplier = 2, account = 1)
    @Test
    public void userCanSendBalanceToAnotherAccount() {
        MakeATransfer makeATransfer = new MakeATransfer();

        int transferValue = getTransferValue();

        List<AccountResponse> accounts = SessionStorage.getSteps().getAllAccounts();

        makeATransfer
                .open()
                .selectAccountFromSelector(accounts.getFirst())
                .enterRecipientName(RandomData.getUsername())
                .enterRecipientAccountNumber(accounts.getLast())
                .enterAmount(transferValue)
                .checkConfirmation()
                .clickDepositButton()
                .checkAlertMessageAndAccept(TRANSFER_MESSAGE_PART1.getMessage() + transferValue +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART2.getMessage() +
                        accounts.getLast().getAccountNumber() +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART3.getMessage());

        Selenide.refresh();

        Double account1Balance = makeATransfer.getSelectorAccountBalance(accounts.getFirst());
        Double account2Balance = makeATransfer.getSelectorAccountBalance(accounts.getLast());

        //В списке Choose an account на странице отображаются верные балансы аккаунтов
        Assertions.assertEquals(10000 - transferValue, account1Balance);
        Assertions.assertEquals(transferValue, account2Balance);

        accounts = SessionStorage.getSteps().getAllAccounts();
        AccountResponse firstAccount = accounts.getFirst();
        AccountResponse secondAccount = accounts.getLast();

        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(firstAccount.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(secondAccount.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(10000 - transferValue, account1response.getBalance());
        Assertions.assertEquals(transferValue, account2response.getBalance());
    }

    @UserSession
    @AccountCreation(2)
    @DepositMoney(value = 5000, multiplier = 2, account = 1)
    @Test
    public void userCannotTransferWithEmptyFields() {

        new MakeATransfer()
                .open()
                .clickDepositButton()
                .checkAlertMessageAndAccept(PLEASE_FILL_ALL_FIELDS_AND_CONFIRM.getMessage());

        List<AccountResponse> accounts = SessionStorage.getSteps().getAllAccounts();
        AccountResponse firstAccount = accounts.getFirst();
        AccountResponse secondAccount = accounts.getLast();
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(firstAccount.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(secondAccount.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(10000, account1response.getBalance());
        Assertions.assertEquals(0, account2response.getBalance());
    }

    @UserSession
    @AccountCreation(2)
    @DepositMoney(value = 5000, multiplier = 1, account = 1)
    @Test
    public void userCannotTransferWithEmptyAccount() {

        List<AccountResponse> accounts = SessionStorage.getSteps().getAllAccounts();

        new MakeATransfer()
                .open()
                .selectAccountFromSelector(accounts.getFirst())
                .enterRecipientName(RandomData.getUsername())
                .enterAmount(1)
                .checkConfirmation()
                .clickDepositButton()
                .checkAlertMessageAndAccept(PLEASE_FILL_ALL_FIELDS_AND_CONFIRM.getMessage());

        accounts = SessionStorage.getSteps().getAllAccounts();
        AccountResponse firstAccount = accounts.getFirst();
        AccountResponse secondAccount = accounts.getLast();
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(firstAccount.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(secondAccount.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(5000, account1response.getBalance());
        Assertions.assertEquals(0, account2response.getBalance());
    }

    @UserSession
    @AccountCreation(2)
    @DepositMoney(value = 5000, multiplier = 1, account = 1)
    @Test
    public void userCanTransferWithEmptyRecipientName() {
        int transferValue = 1;

        List<AccountResponse> accounts = SessionStorage.getSteps().getAllAccounts();

        new MakeATransfer()
                .open()
                .selectAccountFromSelector(accounts.getFirst())
                .enterRecipientAccountNumber(accounts.getLast())
                .enterAmount(transferValue)
                .checkConfirmation()
                .clickDepositButton()
                .checkAlertMessageAndAccept(TRANSFER_MESSAGE_PART1.getMessage() + transferValue +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART2.getMessage() +
                        accounts.getLast().getAccountNumber() +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART3.getMessage());

        accounts = SessionStorage.getSteps().getAllAccounts();
        AccountResponse firstAccount = accounts.getFirst();
        AccountResponse secondAccount = accounts.getLast();
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(firstAccount.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(secondAccount.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(5000 - transferValue, account1response.getBalance());
        Assertions.assertEquals(transferValue, account2response.getBalance());
    }

    @UserSession
    @AccountCreation(2)
    @DepositMoney(value = 5000, multiplier = 1, account = 1)
    @Test
    public void userCannotTransferWithEmptyAmount() {

        List<AccountResponse> accounts = SessionStorage.getSteps().getAllAccounts();

        new MakeATransfer()
                .open()
                .selectAccountFromSelector(accounts.getFirst())
                .enterRecipientName(RandomData.getUsername())
                .enterRecipientAccountNumber(accounts.getLast())
                .checkConfirmation()
                .clickDepositButton()
                .checkAlertMessageAndAccept(PLEASE_FILL_ALL_FIELDS_AND_CONFIRM.getMessage());

        accounts = SessionStorage.getSteps().getAllAccounts();
        AccountResponse firstAccount = accounts.getFirst();
        AccountResponse secondAccount = accounts.getLast();

        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(firstAccount.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(secondAccount.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(5000, account1response.getBalance());
        Assertions.assertEquals(0, account2response.getBalance());
    }

    @UserSession
    @AccountCreation(2)
    @DepositMoney(value = 5000, multiplier = 1, account = 1)
    @Test
    public void userCannotTransferWithEmptyConfirmCheck() {
        List<AccountResponse> accounts = SessionStorage.getSteps().getAllAccounts();

        new MakeATransfer()
                .open()
                .selectAccountFromSelector(accounts.getFirst())
                .enterRecipientName(RandomData.getUsername())
                .enterRecipientAccountNumber(accounts.getLast())
                .enterAmount(1)
                .clickDepositButton()
                .checkAlertMessageAndAccept(PLEASE_FILL_ALL_FIELDS_AND_CONFIRM.getMessage());

        accounts = SessionStorage.getSteps().getAllAccounts();
        AccountResponse firstAccount = accounts.getFirst();
        AccountResponse secondAccount = accounts.getLast();

        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(firstAccount.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(secondAccount.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(5000, account1response.getBalance());
        Assertions.assertEquals(0, account2response.getBalance());
    }

    @UserSession
    @AccountCreation(2)
    @DepositMoney(value = 5000, multiplier = 3, account = 1)
    @ParameterizedTest
    @ValueSource(ints = {-5, 0, 10001})
    public void userCanNotSendBalanceWithInvalidSum(int transferValue) {

        List<AccountResponse> accounts = SessionStorage.getSteps().getAllAccounts();

        new MakeATransfer()
                .open()
                .selectAccountFromSelector(accounts.getFirst())
                .enterRecipientName(RandomData.getUsername())
                .enterRecipientAccountNumber(accounts.getLast())
                .enterAmount(transferValue)
                .checkConfirmation()
                .clickDepositButton()
                .checkAlertMessageAndAccept(INSUFFICIENT_FUNDS_OR_INVALID_ACCOUNTS.getMessage());

        accounts = SessionStorage.getSteps().getAllAccounts();
        AccountResponse firstAccount = accounts.getFirst();
        AccountResponse secondAccount = accounts.getLast();

        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(firstAccount.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(secondAccount.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(15000, account1response.getBalance());
        Assertions.assertEquals(0, account2response.getBalance());
    }

    @UserSession
    @AccountCreation(2)
    @DepositMoney(value = 5000, multiplier = 4, account = 1)
    @Test
    public void userCanSendBalanceToAnotherAccountUsingTransferAgain() {
        int transferValue = getTransferValue();
        MakeATransfer makeATransfer = new MakeATransfer();

        List<AccountResponse> accounts = SessionStorage.getSteps().getAllAccounts();

        makeATransfer
                .open()
                .selectAccountFromSelector(accounts.getFirst())
                .enterRecipientName(RandomData.getUsername())
                .enterRecipientAccountNumber(accounts.getLast())
                .enterAmount(transferValue)
                .checkConfirmation()
                .clickDepositButton()
                .checkAlertMessageAndAccept(TRANSFER_MESSAGE_PART1.getMessage() + transferValue +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART2.getMessage() +
                        accounts.getLast().getAccountNumber() +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART3.getMessage());

        Selenide.refresh();

        makeATransfer
                .transferOutAgain(accounts.getFirst(), transferValue);

        accounts = SessionStorage.getSteps().getAllAccounts();
        AccountResponse firstAccount = accounts.getFirst();
        AccountResponse secondAccount = accounts.getLast();

        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 1 уменьшился)
        //Баланс пополнился (проверка через бэкенд, что баланс аккаунта 2 увеличился)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(firstAccount.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(secondAccount.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(20000 - transferValue * 2, account1response.getBalance());
        Assertions.assertEquals(transferValue * 2, account2response.getBalance());
    }
}
