package com.design.pattern.structural.decorator;

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
