package org.xenolabs.engine.glfw.window;

import static org.lwjgl.glfw.GLFW.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xeno.engine.core.resource.ResourceLocator;
import org.xeno.engine.core.resource.VFS;
import org.xeno.engine.core.window.IWindow;

import java.io.File;
import java.io.InputStream;

public class WindowGLFW implements IWindow {

    private static final Logger LOGGER = LoggerFactory.getLogger(WindowGLFW.class);

    private long wnd;

    private int width;

    private int height;

    private int posX;

    private int posY;

    public boolean initialize () {
        try {
            InputStream stream = VFS.instance().open("window.config");
            ObjectMapper OM = new ObjectMapper();
            WindowConfig windowConfig = OM.readValue(stream, WindowConfig.class);
            this.width = windowConfig.width;
            this.height = windowConfig.height;
            this.posX = windowConfig.posX;
            this.posY = windowConfig.posY;
            return true;
        }
        catch (Exception e) {
            LOGGER.error("", e);
        }
        return false;
    }

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
        glfwSetWindowPos(this.wnd, this.posX, this.posY);

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
