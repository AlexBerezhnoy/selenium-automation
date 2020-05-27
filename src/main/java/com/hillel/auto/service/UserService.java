package com.hillel.auto.service;

import com.hillel.auto.model.User;
import com.hillel.auto.model.UserResponse;
import com.hillel.auto.utils.UserData;
import io.restassured.RestAssured;

public class UserService extends ApiService {

    public User userRegistration() {
        User user = UserData.randomUser();
        UserResponse userResponse = new UserResponse();
        userResponse.setUser(user);

        User newUser = RestAssured
                .given()
                .body(userResponse)
                .when()
                .post("/users")
                .then()
                .statusCode(200)
                .extract().body()
                .as(UserResponse.class)
                .getUser();

        newUser.setPassword(user.getPassword());
        return newUser;
    }

}
