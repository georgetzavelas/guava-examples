package com.design.pattern.example.decorator;

/**
 * Concreate class that adds new functionality
 *
 */
public class ExternalEmailDecorator extends AbstractEmailDecorator {

    private String externalInfo;

    public ExternalEmailDecorator(IEmail delegate, String externalInfo) {
        super(delegate);
    }

    public String getExternalInfo() {
        return externalInfo;
    }
}
