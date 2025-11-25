package nbank.ui.pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import nbank.api.models.AccountResponse;

import static com.codeborne.selenide.Selenide.$;

public class DepositMoney extends BasePage<DepositMoney> {

    private SelenideElement accountSelector = $(Selectors.byCssSelector(".account-selector"));
    private SelenideElement amountTextBox = $(Selectors.byCssSelector(".deposit-input"));
    private SelenideElement depositButton = $(Selectors.byCssSelector(".btn-primary"));

    @Override
    public String url() {
        return "/deposit";
    }

    public DepositMoney selectParticularAccount(AccountResponse account) {
        accountSelector.selectOptionContainingText(account.getAccountNumber());
        return this;
    }

    public DepositMoney enterAmount(int depositValue) {
        amountTextBox.type(String.valueOf(depositValue));
        return this;
    }

    public DepositMoney clickDepositBtn() {
        depositButton.click();
        return this;
    }

    public String getSelectorAccountText(AccountResponse account) {
        return $(Selectors.byXpath("//option[@value='" + account.getAccountNumber().substring(3) + "']")).getText();
    }
}
