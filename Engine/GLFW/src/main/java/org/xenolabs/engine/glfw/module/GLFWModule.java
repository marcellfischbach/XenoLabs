package org.xenolabs.engine.glfw.module;

import org.xeno.engine.core.Engine;
import org.xeno.engine.core.module.IModule;
import org.xeno.engine.core.window.IWindow;
import org.xenolabs.engine.glfw.window.WindowGLFW;

import java.util.HashSet;
import java.util.Set;

public class GLFWModule implements IModule {

    @Override
    public Set<Class<?>> declaringTypes() {
        Set<Class<?>> declaringTypes = new HashSet<>();
        declaringTypes.add(IWindow.class);
        return declaringTypes;
    }

    @Override
    public Set<Class<?>> dependencies() {
        Set<Class<?>> dependencies = new HashSet<>();
        return dependencies;
    }

    @Override
    public boolean initialize(Engine engine) {

        WindowGLFW window = new WindowGLFW();
        if (!window.initialize ()) {
            return false;
        }
        engine.setWindow(window);
        return true;
    }
}
