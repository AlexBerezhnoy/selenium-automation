package io.react.realworld.api;

import com.hillel.auto.model.User;
import com.hillel.auto.utils.UserData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;


public class LoginTest {
    private User user = UserData.defaultUser();

    @Test
    public void loginTest() {

        RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\n"+
                        "\"user\": {\n"+
                        "\"email\": \""+user.getEmail()+"\",\n"+
                        "\"password\": \""+user.getPassword()+"\"\n"+
                        "}\n"+"}")
                .when()
                    .post("https://conduit.productionready.io/api/users/login")
                .then()
                    .statusCode(200)
                    .body("user.email", equalTo(user.getEmail().toLowerCase()))
                    .body("user.username", equalTo(user.getUserName()))
                    .body("user.token", notNullValue())
                .log().all();
    }
}
