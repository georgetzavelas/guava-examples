package com.design.pattern.structural.composite;

/**
 * In this class,there are many methods which are not applicable to developer because
 * it is a leaf node.
 */
public class Developer implements IEmployee{

    private String name;
    private double salary;

    public Developer(String name,double salary){
        this.name = name;
        this.salary = salary;
    }
    @Override
    public void add(IEmployee employee) {
        //this is leaf node so this method is not applicable to this class.
    }

    @Override
    public IEmployee getChild(int i) {
        //this is leaf node so this method is not applicable to this class.
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void print() {
        System.out.println("-------------");
        System.out.println("Name ="+getName());
        System.out.println("Salary ="+getSalary());
        System.out.println("-------------");
    }

    @Override
    public void remove(IEmployee employee) {
        //this is leaf node so this method is not applicable to this class.
    }
}


