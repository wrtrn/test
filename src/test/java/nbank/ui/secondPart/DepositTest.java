package nbank.ui.secondPart;

import io.restassured.specification.RequestSpecification;
import nbank.api.generators.RandomData;
import nbank.api.models.AccountResponse;
import nbank.api.models.CreateUserRequest;
import nbank.api.requests.steps.AdminSteps;
import nbank.api.requests.steps.UserSteps;
import nbank.api.specs.RequestSpecs;
import nbank.common.annotations.AccountCreation;
import nbank.common.annotations.UserSession;
import nbank.common.storage.SessionStorage;
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

    @UserSession
    @AccountCreation
    @ParameterizedTest
    @ValueSource(ints = {4999, 5000, 1})
    public void userCanDepositHisAccount(int depositValue) {
        List<AccountResponse> accounts = SessionStorage.getSteps().getAllAccounts();

        new DepositMoney()
                .open()
                .selectParticularAccount(accounts.getFirst())
                .enterAmount(depositValue)
                .clickDepositBtn()
                .checkAlertMessageAndAccept(DEPOSIT_MESSAGE_PART1.getMessage()
                        + depositValue +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART2.getMessage() +
                        accounts.getFirst().getAccountNumber() +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART3.getMessage());

        accounts = SessionStorage.getSteps().getAllAccounts();
        Assertions.assertEquals(depositValue, accounts.getFirst().getBalance());
    }

    @UserSession
    @AccountCreation(2)
    @Test
    public void balanceIncreasesOnTheCorrectAccount() {
        int depositValue = RandomData.getDepositValue();

        List<AccountResponse> accounts = SessionStorage.getSteps().getAllAccounts();

        new DepositMoney()
                .open()
                .selectParticularAccount(accounts.getFirst())
                .enterAmount(depositValue)
                .clickDepositBtn()
                .checkAlertMessageAndAccept(DEPOSIT_MESSAGE_PART1.getMessage()
                        + depositValue +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART2.getMessage() +
                        accounts.getFirst().getAccountNumber() +
                        BankAlert.DEPOSIT_TRANSFER_MESSAGE_PART3.getMessage())
                .open();


        String account1Text = new DepositMoney().getSelectorAccountText(accounts.getFirst());
        String account2Text = new DepositMoney().getSelectorAccountText(accounts.getLast());

        Double account1Balance = Double.parseDouble(account1Text.substring(account1Text.indexOf('$') + 1, account1Text.indexOf(')')));
        Double account2Balance = Double.parseDouble(account2Text.substring(account2Text.indexOf('$') + 1, account2Text.indexOf(')')));

        //В списке Choose an account на странице отображаются верные балансы аккаунтов
        Assertions.assertEquals(depositValue, account1Balance);
        Assertions.assertEquals(0, account2Balance);

        accounts = SessionStorage.getSteps().getAllAccounts();
        AccountResponse firstAccount = accounts.getFirst();
        AccountResponse secondAccount = accounts.getLast();

        //Баланс пополнился (проверка через бэкенд, что аккаунт 1 с балансом)
        //Баланс пополнился (проверка через бэкенд, что аккаунт 2 пустой)
        AccountResponse account1response = accounts.stream().filter(el -> el.getAccountNumber().equals(firstAccount.getAccountNumber())).findFirst().orElse(null);
        AccountResponse account2response = accounts.stream().filter(el -> el.getAccountNumber().equals(secondAccount.getAccountNumber())).findFirst().orElse(null);
        Assertions.assertEquals(depositValue, account1response.getBalance());
        Assertions.assertEquals(0, account2response.getBalance());
    }

    @UserSession
    @AccountCreation
    @Test
    public void alertAppearsWhenSendingDepositMoneyFormWithoutAccount() {
        int depositValue = RandomData.getDepositValue();

        new DepositMoney()
                .open()
                .enterAmount(depositValue)
                .clickDepositBtn()
                .checkAlertMessageAndAccept(PLEASE_SELECT_AN_ACCOUNT.getMessage());
    }


    @UserSession
    @AccountCreation
    @Test
    public void alertAppearsWhenSendingDepositMoneyFormWithoutAmount() {
        List<AccountResponse> accounts = SessionStorage.getSteps().getAllAccounts();

        new DepositMoney()
                .open()
                .selectParticularAccount(accounts.getFirst())
                .clickDepositBtn()
                .checkAlertMessageAndAccept(PLEASE_ENTER_A_VALID_AMOUNT.getMessage());
    }

    @UserSession
    @AccountCreation
    @Test
    public void alertAppearsWhenSendingDepositMoneyFormWithAmountMoreThanFiveThousands() {
        int depositValue = RandomData.getDepositValue() + 5000;

        List<AccountResponse> accounts = SessionStorage.getSteps().getAllAccounts();

        new DepositMoney()
                .open()
                .selectParticularAccount(accounts.getFirst())
                .enterAmount(depositValue)
                .clickDepositBtn()
                .checkAlertMessageAndAccept(PLEASE_DEPOSIT_LESS_THAN_5000.getMessage());
    }

    @UserSession
    @AccountCreation
    @ParameterizedTest
    @ValueSource(ints = {-5, 0})
    public void userCannotDepositInvalidAmountOfMoney(int depositValue) {
        List<AccountResponse> accounts = SessionStorage.getSteps().getAllAccounts();

        new DepositMoney()
                .open()
                .selectParticularAccount(accounts.getFirst())
                .enterAmount(depositValue)
                .clickDepositBtn()
                .checkAlertMessageAndAccept(PLEASE_ENTER_A_VALID_AMOUNT.getMessage());

        accounts = SessionStorage.getSteps().getAllAccounts();
        Assertions.assertEquals(0, accounts.getFirst().getBalance());
    }
}
