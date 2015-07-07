package com.design.pattern.structural.proxy;

import java.net.URL;

public class ProxyImage implements IImage {
    private URL url;
    public ProxyImage(URL url) {
        this.url = url;
    }
    //this method delegates to the real image
    @Override
    public void displayImage() {
        RealImage real = new RealImage(url);
        real.displayImage();
    }
}
