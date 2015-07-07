package com.design.pattern.behavioural.chain;

/**
 * Chain of Responsibility Design Pattern
 *
 * Consists of a source of command objects and a series of processing objects. Each processing
 * object contains logic that defines the types of command objects that it can handle; the rest
 * are passed to the next processing object in the chain.
 */
public class App {

    public static void main(String[] args) {
        // first logger in the chain
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        // second logger in the chain
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        // last logger in the chain
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        AbstractLogger loggerChain = errorLogger;

        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");
        loggerChain.logMessage(AbstractLogger.DEBUG, "This is an debug level information.");
        loggerChain.logMessage(AbstractLogger.ERROR, "This is an error information.");
    }
}
