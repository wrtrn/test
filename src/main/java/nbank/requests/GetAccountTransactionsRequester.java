package nbank.requests;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import nbank.models.BaseModel;

import static io.restassured.RestAssured.given;

public class GetAccountTransactionsRequester extends Request {
    public GetAccountTransactionsRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification);
    }

    @Override
    public ValidatableResponse post(BaseModel model) {
        return null;
    }

    @Override
    public ValidatableResponse get() {
        return null;
    }

    public ValidatableResponse get(long accountId) {
        return given()
                .spec(requestSpecification)
                .get("/api/v1/accounts/" + accountId + "/transactions")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    @Override
    public ValidatableResponse put(BaseModel model) {
        return null;
    }
}
