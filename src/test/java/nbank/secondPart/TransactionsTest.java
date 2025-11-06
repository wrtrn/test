package nbank.secondPart;

import io.restassured.http.ContentType;
import nbank.BaseApiTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TransactionsTest extends BaseApiTest {

    @Test
    public void userCanSeeTransactionForHisAccount() {
        int firstUserAccountNumber = createAccount(getFirstUserToken());
        int secondUserAccountNumber = createAccount(getSecondUserToken());

        addMoneyToAccount(getFirstUserToken(), firstUserAccountNumber, 500);

        String requestBody = String.format(
                """
                                {
                                  "senderAccountId": %s,
                                  "receiverAccountId": %s,
                                  "amount": %s
                                }
                        """, firstUserAccountNumber, secondUserAccountNumber, 50);

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .body(requestBody)
                .when()
                .post("/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        List<Map<String, Object>> transactionsJson = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .when()
                .get("/api/v1/accounts/" + firstUserAccountNumber + "/transactions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getList("$");

        Map<String, Object> lastTransaction = transactionsJson.get(transactionsJson.size() - 1);
        Integer amount = ((Number) lastTransaction.get("amount")).intValue();
        String type = (lastTransaction.get("type")).toString();
        Assertions.assertEquals(50, amount);
        Assertions.assertEquals("TRANSFER_OUT", type);
    }

    @Test
    public void userCanNotSeeTransactionForAnotherPersonAccount() {
        int secondUserAccountNumber = createAccount(getSecondUserToken());

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .when()
                .get("/api/v1/accounts/" + secondUserAccountNumber + "/transactions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void userCanNotSeeTransactionForNotExistingAccount() {
        int secondUserAccountNumber = createAccount(getSecondUserToken());

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .when()
                .get("/api/v1/accounts/" + secondUserAccountNumber + 100 + "/transactions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }
}
