package com.design.pattern.creational.factory;

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
