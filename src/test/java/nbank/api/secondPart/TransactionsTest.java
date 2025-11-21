package nbank.api.secondPart;

import io.restassured.specification.RequestSpecification;
import nbank.api.BaseTest;
import nbank.api.models.AccountResponse;
import nbank.api.models.CreateUserRequest;
import nbank.api.models.Transaction;
import nbank.api.requests.steps.AdminSteps;
import nbank.api.requests.steps.UserSteps;
import nbank.api.specs.RequestSpecs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static nbank.api.requests.steps.UserSteps.getAccountsTransactions;

public class TransactionsTest extends BaseTest {

    @Test
    public void userCanSeeTransactionForHisAccount() {

        CreateUserRequest userRequest1 = AdminSteps.createUser();
        RequestSpecification authAsUser1 = RequestSpecs.authAsUser(userRequest1.getUsername(), userRequest1.getPassword());
        CreateUserRequest userRequest2 = AdminSteps.createUser();
        RequestSpecification authAsUser2 = RequestSpecs.authAsUser(userRequest2.getUsername(), userRequest2.getPassword());

        AccountResponse account1 = UserSteps.createAccount(authAsUser1);
        AccountResponse account2 = UserSteps.createAccount(authAsUser2);

        UserSteps.depositMoney(authAsUser1, account1, 500);

        long firstAccountNumber = Integer.parseInt(account1.getAccountNumber().substring(3));
        long secondAccountNumber = Integer.parseInt(account2.getAccountNumber().substring(3));

        UserSteps.transferMoney(authAsUser1, 50, firstAccountNumber, secondAccountNumber);

        Transaction[] transactions = getAccountsTransactions(authAsUser1, firstAccountNumber);
        double depositSum = Arrays.stream(transactions).filter(tr->tr.getType().equals("DEPOSIT")).findFirst().orElse(null).getAmount();
        double transferOutSum = Arrays.stream(transactions).filter(tr->tr.getType().equals("TRANSFER_OUT")).findFirst().orElse(null).getAmount();

        softly.assertThat(500.0).isEqualTo(depositSum);
        softly.assertThat(50.0).isEqualTo(transferOutSum);
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
