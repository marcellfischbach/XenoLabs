package org.xeno.engine.core.module;

import org.xeno.engine.core.Engine;

import java.util.Set;

public class CoreModule implements IModule {

    @Override
    public Set<Class<?>> declaringTypes() {
        return null;
    }

    @Override
    public Set<Class<?>> dependencies() {
        return null;
    }

    @Override
    public boolean initialize(Engine engine) {

        return false;
    }
}
