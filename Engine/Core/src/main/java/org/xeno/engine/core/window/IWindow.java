package org.xeno.engine.core.window;

public interface IWindow {

    int getWidth ();

    int getHeight ();

    void processEvents ();

    boolean shouldClose();

    void swap ();
}
