package io.react.realworld.api;

import com.hillel.auto.model.User;
import com.hillel.auto.utils.UserData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class NewPostTest {
    private User user = UserData.defaultUser();

    @Test
    public void newArticleTest() {

        RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " +getTokenAfterlogin())
                .body("{\n"+
                        "\"article\": {\n"+
                        "\"title\": \"Test title\",\n"+
                        "\"description\": \"test discription\",\n"+
                        "\"body\": \"Test body\",\n"+
                        "\"tagList\": [\"tags\"]\n"+
                        "}\n"+"}")
                .when()
                    .post("https://conduit.productionready.io/api/articles")
                .then()
                    .statusCode(200)
                    .log().all();
    }

    protected String getTokenAfterlogin () {
        return RestAssured
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
                    .extract()
                    .body().path("user.token", "");

    }
}
