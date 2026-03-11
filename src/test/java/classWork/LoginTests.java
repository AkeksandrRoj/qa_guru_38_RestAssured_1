package classWork;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class LoginTests {

    /*
    1. Делаем запрос к (POST)"https://app.reqres.in/api/login"
    2.Передаем в теле {"email": "eve.holt@reqres.in", "password": "cityslicka"}
    3. Получаем ответ.
    4. Проверяем что поле "token" = "QpwL5tke4Pnpja7X4" и "status" = 200
     */


    @Test
    void successfulLoginTest() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        given().
                header("x-api-key", "pro_9041615ddf0bb4947db14951f87780072443c925485003cef8a6e1737494e916")
                .body(authData)
                .contentType(JSON)
                .when()
                .log().uri()
                .post("https://reqres.in/api/login")
                .then()
                .log().status().log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void unsuccessfulLogin403Test() {
        given()
                .log().uri()
                .post("https://app.reqres.in/api/login")
                .then()
                .log().status().log().body()
                .statusCode(403);
    }

    @Test
    void unsuccessfulLoginWithoutContentTypeTest() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        given().
                header("x-api-key", "pro_9041615ddf0bb4947db14951f87780072443c925485003cef8a6e1737494e916")
                .body(authData)

                .when()
                .log().uri()
                .post("https://reqres.in/api/login")

                .then()
                .log().status().log().body()
                .statusCode(415)
                .body("error", is("unsupported_charset"))
                .body("message", is("Only UTF-8 encoded request bodies are supported. Please resend the request using UTF-8 encoding."));

    }


}
