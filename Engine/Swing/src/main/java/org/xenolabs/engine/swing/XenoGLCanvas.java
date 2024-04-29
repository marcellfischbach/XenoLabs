package org.xenolabs.engine.swing;

import static org.lwjgl.opengl.GL40.*;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.awt.AWTGLCanvas;
import org.lwjgl.opengl.awt.GLData;

import javax.swing.*;

public class XenoGLCanvas extends AWTGLCanvas {

    public XenoGLCanvas() {
        super(glData());

        Runnable renderLoop = new Runnable() {
            @Override
            public void run() {
                if (!isValid()) {
                    GL.setCapabilities(null);
                    return;
                }
                render();
                SwingUtilities.invokeLater(this);
            }
        };
        SwingUtilities.invokeLater(renderLoop);
    }

    @Override
    public void initGL() {
        GL.createCapabilities();
    }


    long count = 0;
    long lastTime = System.currentTimeMillis();
    @Override
    public void paintGL() {
        count++;
        long now = System.currentTimeMillis();
        if (now > lastTime + 1000) {
            System.out.println("FPS: " + count);
            lastTime = now;
            count = 0;
        }
        glClearColor(0.0f, 0.5f, 0.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        swapBuffers();
    }

    private static GLData glData() {
        GLData data = new GLData();
        data.majorVersion = 4;
        data.minorVersion = 4;
        data.profile = GLData.Profile.CORE;
        data.swapInterval = 0;
        return data;
    }
}
