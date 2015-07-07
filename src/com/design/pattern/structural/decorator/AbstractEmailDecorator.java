package com.design.pattern.structural.decorator;

/**
 *  The decorator pattern (also known as Wrapper) is a design pattern that allows behaviour
 *  to be added to an individual object, either statically or dynamically, without affecting
 *  the behaviour of other objects from the same class.
 *
 *  Typically all the methods of the interface are wrapped and then new functionality can be
 *  introduced in any derived concrete class
 */
public abstract class AbstractEmailDecorator implements IEmail {
    //wrapped component
    IEmail delegate;

    public AbstractEmailDecorator(IEmail delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getContents() {
        return delegate.getContents();
    }
}
