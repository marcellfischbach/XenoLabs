package org.xenolabs.engine.demo;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL40;
import org.xeno.engine.core.Engine;
import org.xeno.engine.core.resource.ResourceLocator;
import org.xeno.engine.core.resource.VFS;
import org.xeno.engine.glfw.window.WindowGLFW;
import org.xenolabs.engine.swing.XenoGLCanvas;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class Demo {


    private static void mainGLFW(String[] args) {


        Engine engine = Engine.instance();
        if (engine.initialize(args)) {

            engine.run();
        }
    }

    private void narf () {

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

    private static void mainSwing (String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(new XenoGLCanvas(), BorderLayout.CENTER);
            frame.setSize(1024, 768);
            frame.setVisible(true);
        });
    }


    public static void main(String[] args) {
        mainGLFW(args);
    }

}
