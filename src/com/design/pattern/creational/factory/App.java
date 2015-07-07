package com.design.pattern.creational.factory;

/**
 * Factory Method Design Pattern (Class Independent Object Creation)
 *
 * A creational pattern which uses factory methods to deal with the problem of creating objects
 * without specifying the exact class of object that will be created.
 */
public class App {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        ICar myCar = CarFactory.buildCar(CarType.SMALL);
    }

}
