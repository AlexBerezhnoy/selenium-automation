package io.react.realworld.api;

import com.hillel.auto.model.*;
import com.hillel.auto.service.UserService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProfileTest {
    private UserService userService = new UserService();
    private User user;
    private User user2;

    @BeforeClass
    public void setUp () {
        user = userService.userRegistration();
        user2 = userService.userRegistration();
        RestAssured.baseURI = "https://conduit.productionready.io";
        RestAssured.basePath = "/api";
        RestAssured.requestSpecification =
                new RequestSpecBuilder()
                        .setAccept(ContentType.JSON)
                        .setContentType(ContentType.JSON)
                        .addHeader("Authorization", "Token " + user.getToken())
                        .log(LogDetail.ALL)
                        .build();

    }

    @Test ()
    public void getProfile() {

        Profile profile =
                RestAssured
                        .given()
                        .when()
                        .get("/profiles/"+user.getUsername())
                        .then()
                        .statusCode(200)
                        .extract().body()
                        .as(ProfileResponse.class)
                        .getProfile();

        System.out.println(profile);
    }

    @Test
    public void postProfile() {

        Profile profile =
                RestAssured
                        .given()
                        .when()
                        .post("/profiles/"+user2.getUsername()+"/follow")
                        .then()
                        .statusCode(200)
                        .extract().body()
                        .as(ProfileResponse.class)
                        .getProfile();

        System.out.println(profile);
    }


    @Test
    public void deleteProfile() {

        Profile profile =
                RestAssured
                        .given()
                        .when()
                        .delete("/profiles/"+user2.getUsername()+"/follow")
                        .then()
                        .statusCode(200)
                        .extract().body()
                        .as(ProfileResponse.class)
                        .getProfile();

        System.out.println(profile);
    }
}
