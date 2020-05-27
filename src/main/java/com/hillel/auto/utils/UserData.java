package com.hillel.auto.utils;

import com.github.javafaker.Faker;
import com.hillel.auto.model.User;

public class UserData {

    public static User defaultUser() {
        return new User("UserAleks1", "useraleks1@gmail.com", "qwerty123");
    }

    public static User randomUser() {
        Faker faker = new Faker();
        User user = new User();
        user.setUsername(faker.name().username());
        user.setEmail(faker.name().lastName() + "@mail.com");
        user.setPassword("qwerty123");
        return user;
    }
}