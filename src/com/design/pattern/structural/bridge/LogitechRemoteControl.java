package com.design.pattern.structural.bridge;

/**
 * The bridge design pattern is used to decouple an abstraction from its implementation so that the two
 * can vary independently.
 *
 * Usage: LogitechRemoteControl remoteControl = new LogitechRemoteControl(new SonyTv());
 */
public class LogitechRemoteControl extends AbstractRemoteControl {

    public LogitechRemoteControl(ITV tv) {
            super(tv);
    }
    public void setChannelKeyboard(int channel){
            setChannel(channel);
            System.out.println("Logitech use keyword to set channel.");
    }
}
