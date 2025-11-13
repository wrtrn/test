package nbank.requests;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import nbank.models.BaseModel;

import static io.restassured.RestAssured.given;

public class DepositMoneyRequester extends Request {
    public DepositMoneyRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification);
    }

    @Override
    public ValidatableResponse post(BaseModel model) {
        return given()
                .spec(requestSpecification)
                .body(model)
                .post("/api/v1/accounts/deposit").then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse get() {
        return null;
    }

    @Override
    public ValidatableResponse put(BaseModel model) {
        return null;
    }
}
