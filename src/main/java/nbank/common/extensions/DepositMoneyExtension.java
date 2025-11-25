package nbank.common.extensions;

import io.restassured.specification.RequestSpecification;
import nbank.api.models.AccountResponse;
import nbank.api.models.CreateUserRequest;
import nbank.api.requests.steps.UserSteps;
import nbank.api.specs.RequestSpecs;
import nbank.common.annotations.DepositMoney;
import nbank.common.storage.SessionStorage;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DepositMoneyExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        DepositMoney annotation = context.getRequiredTestMethod().getAnnotation(DepositMoney.class);
        if (annotation != null) {

            CreateUserRequest user = SessionStorage.getUser();
            RequestSpecification authAsUser = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

            AccountResponse account = SessionStorage.getSteps().getAllAccounts().get(annotation.account() - 1);

            for (int i = 0; i < annotation.multiplier(); i++) {
                UserSteps.depositMoney(authAsUser, account, annotation.value());
            }
        }
    }
}
