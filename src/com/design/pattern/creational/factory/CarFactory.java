package com.design.pattern.creational.factory;

/**
 *  The factory or factory method design pattern is a creational pattern which uses factory methods to deal
 *  with the problem of creating objects without specifying the exact class of object that will be created.
 *
 * Usage: Car myCar = CarFactory.buildCar(CarType.SMALL);
 */
public class CarFactory {
    public static ICar buildCar(CarType model) {
        ICar car = null;
        switch (model) {
            case SMALL:
                car = new SmallCar();
                break;
            case SEDAN:
                car = new SedanCar();
                break;
            case LUXURY:
                car = new LuxuryCar();
                break;
            default:
                throw new IllegalArgumentException("No such Car!");
        }
        return car;
    }
}
