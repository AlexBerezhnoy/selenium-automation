package io.react.realworld.api;

import com.hillel.auto.model.User;
import com.hillel.auto.utils.UserData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;


public class RegistrationTest {
    private User user = UserData.randomUser();

    @Test
    public void registrationTest() {

        RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\n"+
                        "\"user\": {\n"+
                        "\"username\": \""+user.getUserName()+"\",\n"+
                        "\"email\": \""+user.getEmail()+"\",\n"+
                        "\"password\": \""+user.getPassword()+"\"\n"+
                        "}\n"+"}")
                .when()
                    .post("https://conduit.productionready.io/api/users")
                .then()
                    .statusCode(200)
                    .body("user.email", equalTo(user.getEmail().toLowerCase()))
                    .body("user.username", equalTo(user.getUserName()))
                .log().all();
    }
}
