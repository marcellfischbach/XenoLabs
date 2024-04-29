package org.xeno.engine.opengl.module;

import org.xeno.engine.core.Engine;
import org.xeno.engine.core.graphics.IDevice;
import org.xeno.engine.core.module.IModule;
import org.xeno.engine.core.window.IWindow;
import org.xeno.engine.opengl.graphics.gl4.DeviceGL4;

import java.util.HashSet;
import java.util.Set;

public class ModuleOpenGL implements IModule {
    @Override
    public Set<Class<?>> declaringTypes() {
        return Set.of(IDevice.class);
    }

    @Override
    public Set<Class<?>> dependencies() {
        return Set.of(IWindow.class);
    }

    @Override
    public boolean initialize(Engine engine, String[] args) {

        DeviceGL4 device = new DeviceGL4();
        engine.setDevice(device);

        return true;
    }
}
