package org.xeno.engine.opengl.graphics.gl4;

import static org.lwjgl.opengl.GL46.*;

import org.lwjgl.opengl.GL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xeno.engine.core.graphics.IDevice;
import org.xeno.engine.core.math.Color4f;



public class DeviceGL4 implements IDevice {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceGL4.class);

    public DeviceGL4() {
        GL.createCapabilities();
    }

    @Override
    public void clear(boolean clearColor,
                      Color4f color,
                      boolean clearDepth,
                      float depth,
                      boolean clearStencil,
                      int stencil) {
        int mask = 0;
        if (clearColor) {
            mask |= GL_COLOR_BUFFER_BIT;
            glClearColor(color.r, color.g, color.b, color.a);
        }
        if (clearDepth) {
            mask |= GL_DEPTH_BUFFER_BIT;
            glClearDepth(depth);
        }
        if (clearStencil) {
            mask |= GL_STENCIL_BUFFER_BIT;
            glClearStencil(stencil);
        }
        glClear(mask);
    }
}
