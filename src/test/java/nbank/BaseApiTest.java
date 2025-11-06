package nbank;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BaseApiTest {
    private static String firstUserToken;
    private static String secondUserToken;

    public static String getFirstUserToken() {
        return firstUserToken;
    }

    public static String getSecondUserToken() {
        return secondUserToken;
    }

    @BeforeAll
    public static void setUp() {
        RestAssured
                .filters(
                        List.of(new RequestLoggingFilter(), new ResponseLoggingFilter()));

        RestAssured.port = 4111;
        // создание пользователя 1
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .body("""
                        {
                          "username": "kate2000111",
                          "password": "Kate2000#",
                          "role": "USER"
                        }
                        """)
                .post("/api/v1/admin/users");

        // получаем токен юзера 1
        firstUserToken = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "username": "kate2000111",
                          "password": "Kate2000#"
                        }
                        """)
                .post("/api/v1/auth/login")
                .then()
                .extract()
                .header("Authorization");

        // создание пользователя 2
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .body("""
                        {
                          "username": "kate20",
                          "password": "Kate2000#",
                          "role": "USER"
                        }
                        """)
                .post("/api/v1/admin/users");

        // получаем токен юзера 2
        secondUserToken = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "username": "kate20",
                          "password": "Kate2000#"
                        }
                        """)
                .post("/api/v1/auth/login")
                .then()
                .extract()
                .header("Authorization");

    }

    public int createAccount(String token) {
        String accountString = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .post("/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .path("accountNumber");

        return Integer.parseInt(accountString.substring(3));
    }

    public void addMoneyToAccount(String token, int accountNumber, int amount) {

        String body = String.format(
                """
                        {
                          "id": %s,
                          "balance": %s
                        }
                        """, accountNumber, amount);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .body(body)
                .when()
                .post("/api/v1/accounts/deposit")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

    }

    public Double getAccountBalance(String token, int accountNumber) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("/api/v1/customer/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getDouble("find { it.id == " + accountNumber + " }?.balance");
    }

}
