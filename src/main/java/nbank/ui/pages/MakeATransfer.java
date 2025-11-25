package nbank.ui.pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import nbank.api.models.AccountResponse;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MakeATransfer extends BasePage<MakeATransfer> {
    private SelenideElement accountSelector = $(Selectors.byCssSelector(".account-selector"));
    private SelenideElement recipientName = $(Selectors.byCssSelector("[placeholder='Enter recipient name']"));
    private SelenideElement recipientAccountNumber = $(Selectors.byCssSelector("[placeholder='Enter recipient account number']"));
    private SelenideElement amount = $(Selectors.byCssSelector("[placeholder='Enter amount']"));
    private SelenideElement confirmCheckBox = $(Selectors.byCssSelector("#confirmCheck"));
    private SelenideElement depositButton = $(Selectors.byCssSelector(".btn-primary"));
    private SelenideElement transferAgainButton = $(byXpath("//button[contains(text(),'Transfer Again')]"));
    private SelenideElement repeatNearTransferOut = $(byXpath("//span[contains(., 'TRANSFER_OUT')]/following-sibling::button"));
    private SelenideElement repeatAccountSelector = $(Selectors.byCssSelector("select.form-control"));
    private SelenideElement repeatAmount = $(byXpath("//label[contains(., 'Amount:')]/following-sibling::input"));
    private SelenideElement repeatSendTransferButton = $(Selectors.byCssSelector(".btn-success"));

    @Override
    public String url() {
        return "/transfer";
    }

    public MakeATransfer transferOutAgain(AccountResponse accountSender, int transferValue) {
        transferAgainButton.click();
        repeatNearTransferOut.click();
        repeatAccountSelector.selectOptionContainingText(accountSender.getAccountNumber());
        repeatAmount.type(String.valueOf(transferValue));
        confirmCheckBox.click();
        repeatSendTransferButton.click();
        return this;
    }

    public MakeATransfer selectAccountFromSelector(AccountResponse account) {
        accountSelector.selectOptionContainingText(account.getAccountNumber());
        return this;
    }

    public MakeATransfer enterRecipientName(String name) {
        recipientName.type(name);
        return this;
    }

    public MakeATransfer enterRecipientAccountNumber(AccountResponse account) {
        recipientAccountNumber.type(account.getAccountNumber());
        return this;
    }

    public MakeATransfer enterAmount(int value) {
        amount.type(String.valueOf(value));
        return this;
    }

    public MakeATransfer checkConfirmation() {
        confirmCheckBox.click();
        return this;
    }

    public MakeATransfer clickDepositButton() {
        depositButton.click();
        return this;
    }

    public Double getSelectorAccountBalance(AccountResponse account) {
        String accountText = $(Selectors.byXpath("//option[@value='" + account.getAccountNumber().substring(3) + "']")).getText();
        return Double.parseDouble(accountText.substring(accountText.indexOf('$') + 1, accountText.indexOf(')')));
    }
}
