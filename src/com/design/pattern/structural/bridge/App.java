package com.design.pattern.structural.bridge;

/**
 * Bridge Design Pattern (Interface/Implementation + Abstract/Implementation)
 *
 * Decouples an abstraction from its implementation so that the two can vary independently.
 */
public class App {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        LogitechRemoteControl remoteControl = new LogitechRemoteControl(new SonyTV());
    }

}
