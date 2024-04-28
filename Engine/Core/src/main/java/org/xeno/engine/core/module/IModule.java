package org.xeno.engine.core.module;

import org.xeno.engine.core.Engine;

import java.util.Set;

public interface IModule {

    Set<Class<?>> declaringTypes ();

    Set<Class<?>> dependencies ();

    boolean initialize (Engine engine);

}
