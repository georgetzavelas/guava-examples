package com.design.pattern.creational.builder;

/**
 * Builder Design Pattern (Flexible Construction)
 *
 * Allows the creation of objects to avoid the telescoping constructor anti-pattern which occurs
 * when the increase of object constructor parameter combination leads to an exponential list of
 * constructors. Instead of using numerous constructors, the builder pattern uses another object,
 * a builder, that receives each initialization parameter step by step and then returns the resulting
 * constructed object at once.
 *
 * Usage:
 */
public class App {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        User user = new User.UserBuilder("John", "Doe")
                .age(30)
                .phone("1234567")
                .address("Fake address 1234")
                .build();
    }

}
