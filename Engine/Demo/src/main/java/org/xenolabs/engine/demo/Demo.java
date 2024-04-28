package org.xenolabs.engine.demo;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL40;
import org.xeno.engine.core.resource.ResourceLocator;
import org.xeno.engine.core.resource.VFS;
import org.xenolabs.engine.glfw.window.WindowGLFW;

import java.io.InputStream;

public class Demo {


    private static void mainGLFW(String[] args) {

        VFS.instance().initialize(Demo.class.getResourceAsStream("/vfs.config"));

        InputStream is = VFS.instance().open(new ResourceLocator("/data/window.config"));

        try {
            int length = is.available();
            byte[] buffer = new byte[length];
            int l = is.read(buffer);
            String text = new String(buffer, 0, l);
            System.out.println(text);
        }
        catch (Exception e) {

        }


        WindowGLFW window = new WindowGLFW();
        if (!window.initialize()) {
            return;
        }
        window.show();

        GL.createCapabilities();

        while (!window.shouldClose()) {
            window.processEvents();

            GL40.glClearColor(0.5f, 0.0f, 0.0f, 1.0f);
            GL40.glClear (GL40.GL_COLOR_BUFFER_BIT);

            window.swap();
        }
    }


    public static void main(String[] args) {
        mainGLFW(args);
    }

}
