package com.design.pattern.structural.composite;

public interface IEmployee {
    public void add(IEmployee employee);
    public void remove(IEmployee employee);
    public IEmployee getChild(int i);
    public String getName();
    public double getSalary();
    public void print();
}
