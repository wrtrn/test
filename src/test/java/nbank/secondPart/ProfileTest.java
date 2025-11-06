package nbank.secondPart;

import io.restassured.http.ContentType;
import nbank.BaseApiTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ProfileTest extends BaseApiTest {

    @Test
    public void userCanUpdateProfileName() {
        String body = """
                {
                  "name": "Ivan"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .body(body)
                .when()
                .put("/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK).toString();

        String name = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .when()
                .get("/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getString("name");

        Assertions.assertEquals("Ivan", name);
    }

    @Test
    public void userCanSetEmptyName() {
        String body = """
                {
                  "name": ""
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .body(body)
                .when()
                .put("/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK).toString();

        String name = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", getFirstUserToken())
                .when()
                .get("/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .getString("name");

        Assertions.assertEquals("", name);
    }
}
