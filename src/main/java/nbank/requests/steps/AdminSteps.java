package nbank.requests.steps;

import nbank.generators.RandomModelGenerator;
import nbank.models.CreateUserRequest;
import nbank.models.CreateUserResponse;
import nbank.requests.skeleton.Endpoint;
import nbank.requests.skeleton.requesters.ValidatedCrudRequester;
import nbank.specs.RequestSpecs;
import nbank.specs.ResponseSpecs;

public class AdminSteps {
    public static CreateUserRequest createUser() {
        CreateUserRequest userRequest =
                RandomModelGenerator.generate(CreateUserRequest.class);

        new ValidatedCrudRequester<CreateUserResponse>(
                RequestSpecs.adminSpec(),
                Endpoint.ADMIN_USER,
                ResponseSpecs.entityWasCreated())
                .post(userRequest);

        return userRequest;
    }
}