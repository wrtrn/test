package nBankTests.secondPart;

import io.restassured.http.ContentType;
import nBankTests.BaseApiTest;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AccountListsTest extends BaseApiTest {

    @Test
    public void newUserGetsEmptyAccountListTest() {

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .body("""
                        {
                          "username": "kateHello",
                          "password": "Kate2000#",
                          "role": "USER"
                        }
                        """)
                .post("/api/v1/admin/users");

        String userToken = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "username": "kateHello",
                          "password": "Kate2000#"
                        }
                        """)
                .post("/api/v1/auth/login")
                .then()
                .extract()
                .header("Authorization");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", userToken)
                .when()
                .get("/api/v1/customer/profile")
                .then()
                .assertThat()
                .body("accounts", Matchers.empty());
    }

    @Test
    public void twoAccountsCreationTest() {
        String firstAccountString = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .when()
                .post("/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("balance", Matchers.equalTo(0.0F),
                        "transactions", Matchers.empty(),
                        "accountNumber", Matchers.startsWith("ACC"))
                .extract()
                .path("accountNumber");

        String secondAccountString = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .when()
                .post("/api/v1/accounts")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("balance", Matchers.equalTo(0.0F),
                        "transactions", Matchers.empty(),
                        "accountNumber", Matchers.startsWith("ACC"))
                .extract()
                .path("accountNumber");

        int firstAccountNumber = Integer.parseInt(firstAccountString.substring(3));
        int secondAccountNumber = Integer.parseInt(secondAccountString.substring(3));

        Assertions.assertEquals(firstAccountNumber, secondAccountNumber - 1);
    }
}
