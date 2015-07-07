package com.design.pattern.structural.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Manager implements IEmployee {
    private String name;
    private double salary;
    private List<IEmployee> employees = new ArrayList<IEmployee>();

    public Manager(String name,double salary){
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void add(IEmployee employee) {
       employees.add(employee);
    }

    @Override
    public IEmployee getChild(int i) {
        return employees.get(i);
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

        Iterator<IEmployee> employeeIterator = employees.iterator();
        while(employeeIterator.hasNext()){
            IEmployee employee = employeeIterator.next();
            employee.print();
        }
    }

    @Override
    public void remove(IEmployee employee) {
        employees.remove(employee);
    }
}
