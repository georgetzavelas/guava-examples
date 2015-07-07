package com.design.pattern.creational.singleton;

/**
 * Singleton Design Pattern (Single Instance)
 *
 * Restricts the instantiation of a class to one object.
 */
public class App {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
    }

}
