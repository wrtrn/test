package nbank.secondPart;

import io.restassured.specification.RequestSpecification;
import nbank.BaseTest;
import nbank.generators.RandomData;
import nbank.models.AccountResponse;
import nbank.models.CreateUserRequest;
import nbank.models.Transaction;
import nbank.models.TransactionsResponse;
import nbank.requests.steps.AdminSteps;
import nbank.requests.steps.UserSteps;
import nbank.specs.RequestSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static nbank.requests.steps.UserSteps.getAccountsTransactions;

public class TransactionsTest extends BaseTest {

    @Test
    public void userCanSeeTransactionForHisAccount() {
        int depositValue = RandomData.getDepositForFutureTransfer();

        CreateUserRequest userRequest1 = AdminSteps.createUser();
        RequestSpecification authAsUser1 = RequestSpecs.authAsUser(userRequest1.getUsername(), userRequest1.getPassword());
        CreateUserRequest userRequest2 = AdminSteps.createUser();
        RequestSpecification authAsUser2 = RequestSpecs.authAsUser(userRequest2.getUsername(), userRequest2.getPassword());

        AccountResponse account1 = UserSteps.createAccount(authAsUser1);
        AccountResponse account2 = UserSteps.createAccount(authAsUser2);

        UserSteps.depositMoney(authAsUser1, account1, depositValue);

        long firstAccountNumber = Integer.parseInt(account1.getAccountNumber().substring(3));
        long secondAccountNumber = Integer.parseInt(account2.getAccountNumber().substring(3));

        UserSteps.transferMoney(authAsUser1, depositValue-50, firstAccountNumber, secondAccountNumber);

        TransactionsResponse transactions = getAccountsTransactions(authAsUser1, firstAccountNumber);
        softly.assertThat(depositValue).isEqualTo(transactions.getTransactions()[0].getAmount());
        softly.assertThat("DEPOSIT").isEqualTo(transactions.getTransactions()[0].getType());
        softly.assertThat(depositValue-50).isEqualTo(transactions.getTransactions()[1].getAmount());
        softly.assertThat("TRANSFER_OUT").isEqualTo(transactions.getTransactions()[1].getType());
    }

    @Test
    public void userCanNotSeeTransactionForAnotherPersonAccount() {
        CreateUserRequest userRequest1 = AdminSteps.createUser();
        RequestSpecification authAsUser1 = RequestSpecs.authAsUser(userRequest1.getUsername(), userRequest1.getPassword());
        CreateUserRequest userRequest2 = AdminSteps.createUser();
        RequestSpecification authAsUser2 = RequestSpecs.authAsUser(userRequest2.getUsername(), userRequest2.getPassword());

        AccountResponse account2 = UserSteps.createAccount(authAsUser2);
        long secondAccountNumber = Integer.parseInt(account2.getAccountNumber().substring(3));

        String getAccountsBodyResponse = UserSteps.getAccountsTransactionsForInvalidAccount(authAsUser1, secondAccountNumber);
        Assertions.assertEquals("You do not have permission to access this account", getAccountsBodyResponse);
    }

    @Test
    public void userCanNotSeeTransactionForNotExistingAccount() {
        CreateUserRequest userRequest = AdminSteps.createUser();
        RequestSpecification authAsUser = RequestSpecs.authAsUser(userRequest.getUsername(), userRequest.getPassword());

        AccountResponse account = UserSteps.createAccount(authAsUser);
        long accountNumber = Integer.parseInt(account.getAccountNumber().substring(3));

        String getAccountsBodyResponse = UserSteps.getAccountsTransactionsForInvalidAccount(authAsUser, accountNumber + 1);
        Assertions.assertEquals("You do not have permission to access this account", getAccountsBodyResponse);
    }
}
