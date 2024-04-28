package org.xeno.engine.core;

import org.xeno.engine.core.graphics.IDevice;
import org.xeno.engine.core.window.IWindow;

public class Engine {

    private IWindow window;

    private IDevice device;


    public IWindow getWindow() {
        return window;
    }

    public void setWindow(IWindow window) {
        this.window = window;
    }

    public IDevice getDevice() {
        return device;
    }

    public void setDevice(IDevice device) {
        this.device = device;
    }

    private Engine () {
    }

    private static Engine instance = null;

    public static Engine instance () {
        if (instance == null) {
            instance = new Engine();
        }
        return instance;
    }
}
