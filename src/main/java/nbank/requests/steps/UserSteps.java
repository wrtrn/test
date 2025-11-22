package nbank.requests.steps;

import io.restassured.specification.RequestSpecification;
import nbank.models.*;
import nbank.requests.skeleton.requesters.CrudRequester;
import nbank.requests.skeleton.requesters.ValidatedCrudRequester;
import nbank.specs.ResponseSpecs;

import static nbank.requests.skeleton.Endpoint.*;

public class UserSteps {

    public static AccountResponse createAccount(RequestSpecification user) {
        return new ValidatedCrudRequester<AccountResponse>(user, ACCOUNTS, ResponseSpecs.entityWasCreated()).post(null);
    }

    public static void depositMoney(RequestSpecification user, AccountResponse account, int amount) {
        new CrudRequester(user, ACCOUNTS_DEPOSIT, ResponseSpecs.requestReturnsOK()).post(generateDepositRequest(account, amount));
    }

    public static void depositInvalidSumOfMoney(RequestSpecification user, AccountResponse account, int amount) {
        new CrudRequester(user, ACCOUNTS_DEPOSIT, ResponseSpecs.requestReturnsBadRequest()).post(generateDepositRequest(account, amount));
    }

    public static void depositToInvalidAccount(RequestSpecification user, AccountResponse account, int amount) {
        new CrudRequester(user, ACCOUNTS_DEPOSIT, ResponseSpecs.requestReturnsForbidden()).post(generateDepositRequest(account, amount));
    }

    private static DepositMoneyRequest generateDepositRequest(AccountResponse account, int amount) {
        return DepositMoneyRequest.builder()
                .id(account.getId())
                .balance(amount)
                .build();
    }

    public static AccountResponse[] getCustomerAccounts(RequestSpecification user) {
        return new CrudRequester(user, CUSTOMER_ACCOUNTS, ResponseSpecs.requestReturnsOK())
                .get(0)
                .extract()
                .as(AccountResponse[].class);
    }

    public static void updateProfile(RequestSpecification user, String username) {
        ProfileUpdateRequest body = ProfileUpdateRequest.builder()
                .name(username)
                .build();

        new CrudRequester(user, CUSTOMER_PROFILE, ResponseSpecs.requestReturnsOK()).update(body);
    }

    public static ProfileResponse getCustomerProfile(RequestSpecification user) {
        return new ValidatedCrudRequester<ProfileResponse>(user, CUSTOMER_PROFILE, ResponseSpecs.requestReturnsOK()).get(0);
    }

    public static void transferMoney(RequestSpecification user, double amount, long senderAccountId, long receiverAccountId) {
        TransferMoney body = generateTransferMoney(amount, senderAccountId, receiverAccountId);

        new CrudRequester(user, ACCOUNTS_TRANSFER, ResponseSpecs.requestReturnsOK())
                .post(body);
    }

    public static String transferMoneyBadRequest(RequestSpecification user, int amount, long senderAccountId, long receiverAccountId) {
        TransferMoney body = generateTransferMoney(amount, senderAccountId, receiverAccountId);

        return new CrudRequester(user, ACCOUNTS_TRANSFER, ResponseSpecs.requestReturnsBadRequest())
                .post(body).extract().asString();
    }

    private static TransferMoney generateTransferMoney(double amount, long senderAccountId, long receiverAccountId) {
        return TransferMoney.builder()
                .amount(amount)
                .senderAccountId(senderAccountId)
                .receiverAccountId(receiverAccountId)
                .build();
    }

    public static TransactionsResponse getAccountsTransactions(RequestSpecification user, long accountId) {
        return new ValidatedCrudRequester<TransactionsResponse>(user, ACCOUNTS_ID_TRANSACTIONS, ResponseSpecs.requestReturnsOK()).get(accountId);
    }

    public static String getAccountsTransactionsForInvalidAccount(RequestSpecification user, long accountId) {
        return new CrudRequester(user, ACCOUNTS_ID_TRANSACTIONS, ResponseSpecs.requestReturnsForbidden()).get(accountId).extract().asString();
    }
}