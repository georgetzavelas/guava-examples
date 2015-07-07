package com.design.pattern.structural.composite;

/**
 * Composite Design Pattern (Tree Structure)
 *
 * Describes that a group of objects is to be treated in the same way as a single instance of an object.
 * The intent of a composite is to "compose" objects into tree structures to represent part-whole hierarchies.
 */
public class App {

    public static void main(String[] args) {
        IEmployee emp1=new Developer("John", 10000);
        IEmployee emp2=new Developer("David", 15000);
        IEmployee manager1=new Manager("Daniel",25000);
        manager1.add(emp1);
        manager1.add(emp2);
        IEmployee emp3=new Developer("Michael", 20000);
        Manager generalManager=new Manager("Mark", 50000);
        generalManager.add(emp3);
        generalManager.add(manager1);
        generalManager.print();
    }
}
