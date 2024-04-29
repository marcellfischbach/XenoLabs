package org.xeno.engine.core;

import org.xeno.engine.core.graphics.IDevice;
import org.xeno.engine.core.math.Color4f;
import org.xeno.engine.core.module.ModuleCore;
import org.xeno.engine.core.module.ModuleLoader;
import org.xeno.engine.core.resource.VFS;
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

    private Engine() {
    }

    public boolean initialize(String[] args) {
        VFS.instance().initialize(Engine.class.getResourceAsStream("/vfs.config"));

        ModuleCore core = new ModuleCore();
        if (!core.initialize(this, args)) {
            return false;
        }
        return ModuleLoader.load(this, args);
    }

    public void run() {
        while (!window.shouldClose()) {
            window.processEvents();

            processFrame();

            window.swap();
        }
    }

    public void processFrame() {
        this.device.clear(true, new Color4f(0.5f, 0.0f, 0.5f, 1.0f), true, 1.0f, true, 0);
    }

    private static Engine instance = null;

    public static Engine instance() {
        if (instance == null) {
            instance = new Engine();
        }
        return instance;
    }
}
