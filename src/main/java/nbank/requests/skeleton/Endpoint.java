package nbank.requests.skeleton;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nbank.models.*;

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
    ;


    private final String url;
    private final Class<? extends BaseModel> requestModel;
    private final Class<? extends BaseModel> responseModel;
}