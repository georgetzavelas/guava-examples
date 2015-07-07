package com.design.pattern.structural.decorator;

/**
 *  Decorator/Wrapper Design Pattern (Wrap + Add Features)
 *  Allows behaviour to be added to an individual object, either statically or dynamically,
 *  without affecting the behaviour of other objects from the same class.
 *
 *  Typically all the methods of the interface are wrapped and then new functionality can be
 *  introduced in any derived concrete class
 */
public class App {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        IEmail decorator = new ExternalEmailDecorator(new Email("This is the content of the email"), "Signature - Me");
    }

}
