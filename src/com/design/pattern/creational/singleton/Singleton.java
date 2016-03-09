package com.design.pattern.creational.singleton;

public class Singleton {
    // Private constructor prevents instantiation from other classes
    private Singleton() {
    }

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     *
     * https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom#Example_Java_Implementation
     *
     * Despite the elegance of this approach any failure to initialize renders the class unusable
     * which means the holder pattern can only be used when the programmer is certain the initialization will not fail.
     */
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
