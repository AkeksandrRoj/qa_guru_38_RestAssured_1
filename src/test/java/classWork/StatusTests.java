package classWork;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;


public class StatusTests {
    /*
    1. Сделать запрос по: https://selenoid.autotests.cloud/status
    2. Получить ответ вида: {"total":5,"used":0,"queued":0,"pending":0,"browsers":{"chrome":{"127.0":{},"128.0":{}},"firefox":{"124.0":{},"125.0":{}},"opera":{"108.0":{},"109.0":{}}}}
    3. Проверить, что поле total = 5.

     */

    @Test
    void checkTotal5() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(5));
    }

    @Test
    void checkTotalResponseWithLog() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithAllLog() {
        given()
                .log().all()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithSomeLog() {
        given()
                .log().method().log().uri()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .body("total", is(5));
    }
}
