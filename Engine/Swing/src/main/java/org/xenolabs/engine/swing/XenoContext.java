package org.xenolabs.engine.swing;

import static org.lwjgl.glfw.GLFW.*;

public class XenoContext {

    private long wnd;

    private void createContext() {
        glfwInit();

        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_DECORATED, GLFW_TRUE);

        glfwWindowHint(GLFW_RED_BITS, 8);
        glfwWindowHint(GLFW_GREEN_BITS, 8);
        glfwWindowHint(GLFW_BLUE_BITS, 8);
        glfwWindowHint(GLFW_ALPHA_BITS, 8);
        glfwWindowHint(GLFW_DEPTH_BITS, 24);
        glfwWindowHint(GLFW_STENCIL_BITS, 8);
        glfwWindowHint(GLFW_DOUBLEBUFFER, 1);

        glfwWindowHint(GLFW_CLIENT_API, GLFW_OPENGL_API);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 4);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        this.wnd = glfwCreateWindow(100, 100, "", 0, 0);
        glfwMakeContextCurrent(this.wnd);
        glfwSwapInterval(0);
        glfwHideWindow(this.wnd);
    }


    public void makeCurrent () {
        glfwMakeContextCurrent(this.wnd);
    }

    private XenoContext() {
        createContext ();
    }

    private static XenoContext instance = null;

    public static XenoContext instance() {
        if (instance == null) {
            instance = new XenoContext();
        }
        return instance;
    }


}
