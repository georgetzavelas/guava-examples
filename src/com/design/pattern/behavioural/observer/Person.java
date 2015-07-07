package com.design.pattern.behavioural.observer;

import java.util.Observable;
import java.util.Observer;

public class Person implements Observer {

    String personName;


    public Person(String personName) {
        this.personName = personName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        System.out.println("Hello "+personName+", Product is now "+arg1+" on flipkart");
    }
}
