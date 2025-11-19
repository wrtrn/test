package nbank.ui.pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class EditProfile extends BasePage<EditProfile> {
    private SelenideElement newNameTextField = $(".form-control");
    private SelenideElement saveChangesButton = $(Selectors.byCssSelector(".btn-primary"));

    @Override
    public String url() {
        return "/edit-profile";
    }

    public EditProfile setNewName(String name) {
        newNameTextField.setValue(name);
        saveChangesButton.click();
        return this;
    }
}
