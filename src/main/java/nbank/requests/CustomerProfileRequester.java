package nbank.requests;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import nbank.models.ProfileUpdateRequest;

import static io.restassured.RestAssured.given;

public class CustomerProfileRequester extends Request<ProfileUpdateRequest> {
    private final String ENDPOINT = "/api/v1/customer/profile";

    public CustomerProfileRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification);
    }

    @Override
    public ValidatableResponse post(ProfileUpdateRequest model) {
        return null;
    }

    @Override
    public ValidatableResponse get() {
        return given()
                .spec(requestSpecification)
                .get(ENDPOINT)
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse put(ProfileUpdateRequest model) {
        return given()
                .spec(requestSpecification)
                .body(model)
                .put(ENDPOINT)
                .then()
                .assertThat()
                .spec(responseSpecification);
    }
}
