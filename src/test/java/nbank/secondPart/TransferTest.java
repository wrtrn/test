package nbank.secondPart;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import nbank.BaseApiTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class TransferTest extends BaseApiTest {

    @Test
    public void transferMoneyFromEmptyAccount() {
        int firstAccountNumber = createAccount(getFirstUserToken());
        int secondAccountNumber = createAccount(getFirstUserToken());

        String requestBody = String.format(
                """
                                {
                                  "senderAccountId": %s,
                                  "receiverAccountId": %s,
                                  "amount": 50
                                }
                        """, firstAccountNumber, secondAccountNumber);

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .body(requestBody)
                .when()
                .post("/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void userCanTransferBetweenHisOwnAccounts() {
        int firstAccountNumber = createAccount(getFirstUserToken());
        int secondAccountNumber = createAccount(getFirstUserToken());

        addMoneyToAccount(getFirstUserToken(), firstAccountNumber, 500);

        String requestBody = String.format(
                """
                                {
                                  "senderAccountId": %s,
                                  "receiverAccountId": %s,
                                  "amount": 50
                                }
                        """, firstAccountNumber, secondAccountNumber);

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

        Double firstAccountBalance = getAccountBalance(getFirstUserToken(), firstAccountNumber);
        Double secondAccountBalance = getAccountBalance(getFirstUserToken(), secondAccountNumber);

        Assertions.assertEquals(450, firstAccountBalance);
        Assertions.assertEquals(50, secondAccountBalance);
    }

    @Test
    public void userSeesOnlyHisAccounts() {
        int secondUserAccountNumber = createAccount(getSecondUserToken());

        JsonPath firstUserAccountsJson = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .when()
                .get("/api/v1/customer/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath();

        Assertions.assertThrows(NullPointerException.class, () -> firstUserAccountsJson.getInt("find { it.id == " + secondUserAccountNumber + " }?.id"));
    }

    @ParameterizedTest
    @ValueSource(ints = {10000, 9999, 1})
    public void userCanTransferToAnotherUser(int amount) {
        int firstUserAccountNumber = createAccount(getFirstUserToken());
        int secondUserAccountNumber = createAccount(getSecondUserToken());

        addMoneyToAccount(getFirstUserToken(), firstUserAccountNumber, 5000);
        addMoneyToAccount(getFirstUserToken(), firstUserAccountNumber, 5000);

        String requestBody = String.format(
                """
                                {
                                  "senderAccountId": %s,
                                  "receiverAccountId": %s,
                                  "amount": %s
                                }
                        """, firstUserAccountNumber, secondUserAccountNumber, amount);

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

        Double firstAccountBalance = getAccountBalance(getFirstUserToken(), firstUserAccountNumber);
        Double secondAccountBalance = getAccountBalance(getSecondUserToken(), secondUserAccountNumber);

        Assertions.assertEquals(10000 - amount, firstAccountBalance);
        Assertions.assertEquals(amount, secondAccountBalance);
    }

    @ParameterizedTest
    @ValueSource(ints = {10001, 0, -400})
    public void userCanTransferToAnotherUserInvalidAmountOfMoney(int amount) {
        int firstUserAccountNumber = createAccount(getFirstUserToken());
        int secondUserAccountNumber = createAccount(getSecondUserToken());

        addMoneyToAccount(getFirstUserToken(), firstUserAccountNumber, 5000);
        addMoneyToAccount(getFirstUserToken(), firstUserAccountNumber, 5000);
        addMoneyToAccount(getFirstUserToken(), firstUserAccountNumber, 5000);

        String requestBody = String.format(
                """
                                {
                                  "senderAccountId": %s,
                                  "receiverAccountId": %s,
                                  "amount": %s
                                }
                        """, firstUserAccountNumber, secondUserAccountNumber, amount);

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .body(requestBody)
                .when()
                .post("/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);


        Double firstAccountBalance = getAccountBalance(getFirstUserToken(), firstUserAccountNumber);
        Double secondAccountBalance = getAccountBalance(getSecondUserToken(), secondUserAccountNumber);

        Assertions.assertEquals(15000, firstAccountBalance);
        Assertions.assertEquals(0, secondAccountBalance);
    }

    @Test
    public void transferAmountGreaterThanSenderBalance() {
        int firstUserAccountNumber = createAccount(getFirstUserToken());
        int secondUserAccountNumber = createAccount(getSecondUserToken());

        addMoneyToAccount(getFirstUserToken(), firstUserAccountNumber, 5000);

        String requestBody = String.format(
                """
                                {
                                  "senderAccountId": %s,
                                  "receiverAccountId": %s,
                                  "amount": %s
                                }
                        """, firstUserAccountNumber, secondUserAccountNumber, 5050);

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .body(requestBody)
                .when()
                .post("/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

        Double firstAccountBalance = getAccountBalance(getFirstUserToken(), firstUserAccountNumber);
        Double secondAccountBalance = getAccountBalance(getSecondUserToken(), secondUserAccountNumber);

        Assertions.assertEquals(5000, firstAccountBalance);
        Assertions.assertEquals(0, secondAccountBalance);
    }
}
