package org.xenolabs.engine.demo;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL40;
import org.xeno.engine.core.Engine;
import org.xenolabs.engine.glfw.WindowGLFW;
import org.xenolabs.engine.swing.XenoComponent;

import javax.swing.*;
import java.awt.*;

public class Demo {

    private static void mainGLFW(String[] args) {

        WindowGLFW window = new WindowGLFW();

        window.setWidth(1024);
        window.setHeight(768);
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
            JFrame frame = new JFrame("Demo");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(new XenoComponent(new XenoComponent.Handler() {
                @Override
                public void glInitialize() {
                    GL.createCapabilities();
                }

                @Override
                public void glResize(int width, int height) {

                }

                @Override
                public void glPaint() {
                    System.out.println("paint");
                    GL40.glClearColor(0.0f, 0.0f, 0.5f, 1.0f);
                    GL40.glClear(GL40.GL_COLOR_BUFFER_BIT);
                }
            }));
            frame.setSize(1024, 768);
            frame.setVisible(true);


        });
    }

    public static void main(String[] args) {
        mainSwing(args);
    }

}
