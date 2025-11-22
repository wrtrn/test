package nbank.api.requests.skeleton;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nbank.api.models.*;

@Getter
@AllArgsConstructor
public enum Endpoint {
    ADMIN_USER(
            "/admin/users",
            CreateUserRequest.class,
            CreateUserResponse.class
    ),

    LOGIN(
            "/auth/login",
            LoginUserRequest.class,
            LoginUserResponse.class
    ),

    ACCOUNTS(
            "/accounts",
            BaseModel.class,
            AccountResponse.class
    ),

    CUSTOMER_ACCOUNTS(
            "/customer/accounts",
            null,
            ProfileResponse.class
    ),

    ACCOUNTS_DEPOSIT(
            "/accounts/deposit",
            DepositMoneyRequest.class,
            AccountResponse.class
    ),

    CUSTOMER_PROFILE(
            "/customer/profile",
            ProfileUpdateRequest.class,
            ProfileResponse.class
    ),

    ACCOUNTS_TRANSFER(
            "/accounts/transfer",
            TransferMoney.class,
            TransferMoney.class
    ),

    ACCOUNTS_ID_TRANSACTIONS(
            "/accounts/{accountId}/transactions",
            null,
            TransactionsResponse.class
    ),
    ;


    private final String url;
    private final Class<? extends BaseModel> requestModel;
    private final Class<? extends BaseModel> responseModel;

    public String getUrl(Object... args) {
        if (args == null || args.length == 0) {
            return url;
        }

        boolean hasNonNull = false;
        for (Object arg : args) {
            if (arg != null) {
                hasNonNull = true;
                break;
            }
        }
        if (!hasNonNull) {
            return url;
        }

        String result = url;
        for (Object arg : args) {
            result = result.replaceFirst("\\{[^/]+}", arg.toString());
        }
        return result;
    }
}