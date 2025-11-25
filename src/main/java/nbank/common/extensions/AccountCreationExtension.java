package nbank.common.extensions;

import io.restassured.specification.RequestSpecification;
import nbank.api.models.CreateUserRequest;
import nbank.api.requests.steps.UserSteps;
import nbank.api.specs.RequestSpecs;
import nbank.common.annotations.AccountCreation;
import nbank.common.storage.SessionStorage;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AccountCreationExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        AccountCreation annotation = context.getRequiredTestMethod().getAnnotation(AccountCreation.class);
        if (annotation != null) {
            int accountCount = annotation.value();

            CreateUserRequest user = SessionStorage.getUser();
            RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

            for (int i = 0; i < accountCount; i++) {
                UserSteps.createAccount(authAsUser);
            }
        }
    }
}
