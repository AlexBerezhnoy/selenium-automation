package com.hillel.auto.utils;

import com.github.javafaker.Faker;
import com.hillel.auto.User;

import java.util.Random;

/**
 * Created by alpa on 5/7/20
 */
public class UserData {

    public static User defaultUser() {
        return new User("UserAleks1", "useraleks1@gmail.com", "qwerty123");
    }

    public static User randomUser() {
        Faker faker = new Faker();
        User user = new User();
        user.setUserName(faker.name().username());
        user.setEmail(faker.name().lastName() + "@mail.com");
        user.setPassword("qwerty123");
        return user;
    }
}
