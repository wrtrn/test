package nbank.ui.pages;

import lombok.Getter;

@Getter
public enum BankAlert {
    USER_CREATED_SUCCESSFULLY("✅ User created successfully!"),
    USERNAME_MUST_BE_BETWEEN_3_AND_15_CHARACTERS("Username must be between 3 and 15 characters"),
    NEW_ACCOUNT_CREATED("✅ New Account Created! Account Number: "),
    DEPOSIT_MESSAGE_PART1("✅ Successfully deposited $"),
    TRANSFER_MESSAGE_PART1("✅ Successfully transferred $"),
    DEPOSIT_TRANSFER_MESSAGE_PART2(" to account "),
    DEPOSIT_TRANSFER_MESSAGE_PART3("!"),
    PLEASE_FILL_ALL_FIELDS_AND_CONFIRM("❌ Please fill all fields and confirm."),
    INSUFFICIENT_FUNDS_OR_INVALID_ACCOUNTS("❌ Error: Invalid transfer: insufficient funds or invalid accounts"),
    NAME_UPDATED_SUCCESSFULLY("✅ Name updated successfully!"),
    PLEASE_ENTER_A_VALID_NAME("❌ Please enter a valid name."),
    PLEASE_ENTER_A_VALID_AMOUNT("❌ Please enter a valid amount."),
    PLEASE_DEPOSIT_LESS_THAN_5000("❌ Please deposit less or equal to 5000$."),
    PLEASE_SELECT_AN_ACCOUNT("❌ Please select an account.");

    private final String message;

    BankAlert(String message) {
        this.message = message;
    }
}
