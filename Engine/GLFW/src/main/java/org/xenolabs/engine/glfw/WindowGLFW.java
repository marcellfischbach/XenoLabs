package org.xenolabs.engine.glfw;

import static org.lwjgl.glfw.GLFW.*;
import org.xeno.engine.core.window.IWindow;

public class WindowGLFW implements IWindow {

    private long wnd;

    private int width;

    private int height;

    public void show () {
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

        this.wnd = glfwCreateWindow(this.width, this.height, "XenoLabs", 0, 0);
        glfwSetWindowPos(this.wnd, 100, 100);

        glfwMakeContextCurrent(this.wnd);
        glfwSwapInterval(0);
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void processEvents() {
        if (this.wnd == 0) {
            return;
        }

        glfwPollEvents();
    }

    @Override
    public boolean shouldClose() {
        return this.wnd == 0 || glfwWindowShouldClose(this.wnd);
    }

    @Override
    public void swap() {
        if  (this.wnd == 0) {
            return;
        }

        glfwSwapBuffers(this.wnd);
    }
}
