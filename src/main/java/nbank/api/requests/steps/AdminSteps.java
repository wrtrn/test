package nbank.api.requests.steps;

import nbank.api.generators.RandomModelGenerator;
import nbank.api.models.CreateUserRequest;
import nbank.api.models.CreateUserResponse;
import nbank.api.requests.skeleton.Endpoint;
import nbank.api.requests.skeleton.requesters.ValidatedCrudRequester;
import nbank.api.specs.RequestSpecs;
import nbank.api.specs.ResponseSpecs;

import java.util.List;

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

    public static List<CreateUserResponse> getAllUsers() {
        return new ValidatedCrudRequester<CreateUserResponse>(
                RequestSpecs.adminSpec(),
                Endpoint.ADMIN_USER,
                ResponseSpecs.requestReturnsOK()).getAll(CreateUserResponse[].class);
    }
}