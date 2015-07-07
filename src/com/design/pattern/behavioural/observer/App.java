package com.design.pattern.behavioural.observer;

/**
 * Observer Design Pattern
 *
 * An object, called the subject, maintains a list of its dependents, called observers, and notifies them
 * automatically of any state changes, usually by calling one of their methods. It is mainly used to implement
 * distributed event handling systems.
 *
 * The observer pattern can cause memory leaks, known as the lapsed listener problem, because in basic implementation
 * it requires both explicit registration and explicit deregistration, as in the dispose pattern, because the
 * subject holds strong references to the observers, keeping them alive. This can be prevented by the subject
 * holding weak references to the observers.
 */
public class App {

    public static void main(String[] args) {
        Person frankPerson=new Person("Frank");
        Person johnPerson=new Person("John");

        Product samsungMobile=new Product("Samsung", "Mobile", "Not available");

        //When you opt for option "Notify me when product is available".Below registerObserver method
        //get executed
        samsungMobile.registerObserver(frankPerson);
        samsungMobile.registerObserver(johnPerson);

        //Now product is available
        samsungMobile.setAvailability("Available");
    }
}
