package org.xeno.engine.glfw.module;

import org.xeno.engine.core.Engine;
import org.xeno.engine.core.module.IModule;
import org.xeno.engine.core.window.IWindow;
import org.xeno.engine.glfw.window.WindowGLFW;

import java.util.HashSet;
import java.util.Set;

public class ModuleGLFW implements IModule {

    @Override
    public Set<Class<?>> declaringTypes() {
        return Set.of(IWindow.class);
    }

    @Override
    public Set<Class<?>> dependencies() {
        return Set.of();
    }

    @Override
    public boolean initialize(Engine engine, String[] args) {

        WindowGLFW window = new WindowGLFW();
        if (!window.initialize ()) {
            return false;
        }

        engine.setWindow(window);
        window.show();
        return true;
    }
}
