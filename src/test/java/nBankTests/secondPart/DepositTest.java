package nBankTests.secondPart;

import io.restassured.http.ContentType;
import nBankTests.BaseApiTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class DepositTest extends BaseApiTest {

    @ParameterizedTest
    @ValueSource(ints = {4999, 5000, 1})
    public void DepositToAccount(int expectedAmount) {
        int accountNumber = createAccount(getFirstUserToken());

        addMoneyToAccount(getFirstUserToken(), accountNumber, expectedAmount);

        Double amountFromResponse = getAccountBalance(getFirstUserToken(), accountNumber);

        Assertions.assertEquals(expectedAmount, amountFromResponse);
    }

    @ParameterizedTest
    @ValueSource(ints = {5001, -100, 0})
    public void depositProhibitedAmountOfMoney(int expectedAmount) {
        int accountNumber = createAccount(getFirstUserToken());

        String body = String.format(
                """
                        {
                          "id": %s,
                          "balance": %s
                        }
                        """, accountNumber, expectedAmount);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .body(body)
                .when()
                .post("/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract();

        Double amountFromResponce = getAccountBalance(getFirstUserToken(), accountNumber);

        Assertions.assertEquals(amountFromResponce, 0);
    }

    @Test
    public void depositToSomeoneElseOrNonExistingAccount() {
        int someoneElseAccountNumber = createAccount(getSecondUserToken());

        String body = String.format(
                """
                        {
                          "id": %s,
                          "balance": %s
                        }
                        """, someoneElseAccountNumber, 100);


        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .body(body)
                .when()
                .post("/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .extract();

        Double amountFromResponce = getAccountBalance(getSecondUserToken(), someoneElseAccountNumber);

        Assertions.assertEquals(amountFromResponce, 0);
    }

    @Test
    public void depositToNonExistingAccount() {
        int nonExistingAccountNumber = createAccount(getSecondUserToken()) + 100;

        String body = String.format(
                """
                        {
                          "id": %s,
                          "balance": %s
                        }
                        """, nonExistingAccountNumber, 100);


        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .body(body)
                .when()
                .post("/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .extract();

        Assertions.assertThrows(NullPointerException.class, () -> getAccountBalance(getSecondUserToken(), nonExistingAccountNumber));
    }
}
