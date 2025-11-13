package nbank.requests;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import nbank.models.TransferMoney;

import static io.restassured.RestAssured.given;

public class TransferMoneyRequester extends Request<TransferMoney> {
    public TransferMoneyRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification);
    }

    @Override
    public ValidatableResponse post(TransferMoney model) {
        return given()
                .spec(requestSpecification)
                .body(model)
                .post("/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse get() {
        return null;
    }

    @Override
    public ValidatableResponse put(TransferMoney model) {
        return null;
    }
}
