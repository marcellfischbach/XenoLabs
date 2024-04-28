package org.xenolabs.engine.swing;

import org.lwjgl.BufferUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL40.*;

public class XenoGLComponent extends JComponent {

    public interface Handler {
        void glInitialize();

        void glResize(int width, int height);

        void glPaint();
    }

    private final Handler handler;

    private boolean initialized = false;

    private BufferedImage image;

    public XenoGLComponent(Handler handle) {
        this.handler = handle;

        SwingUtilities.invokeLater(this::repaint);
    }

    @Override
    protected void paintComponent(Graphics g) {
        XenoGLContext.instance().makeCurrent();
        if (!initialized) {
            handler.glInitialize();
            initialized = true;
        }

        if (updateBuffers(getWidth(), getHeight())) {
            glBindFramebuffer(GL_FRAMEBUFFER, this.fbo);
            handler.glPaint();
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, this.colorTexture);

            DataBufferInt dataBuffer = (DataBufferInt) this.image.getRaster().getDataBuffer();
            int[] buffer = dataBuffer.getData();
            glGetTexImage(GL_TEXTURE_2D, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
            g.drawImage(this.image, 0, 0, null);
        }

        SwingUtilities.invokeLater(this::repaint);
    }


    private int fbo;
    private int fboWidth;
    private int fboHeight;
    private int colorTexture;

    private boolean updateBuffers(int width, int height) {
        return updateFramebuffer(width, height)
                && updateImage(width, height);
    }

    private boolean updateFramebuffer(int width, int height) {
        if (fbo == 0 || fboWidth != width || fboHeight != height) {
            System.out.println("updateFramebuffer");
            fboWidth = width;
            fboHeight = height;
            if (fbo != 0) {
                glDeleteFramebuffers(this.fbo);
            }
            if (this.colorTexture != 0) {
                glDeleteTextures(this.colorTexture);
            }

            this.fbo = glGenFramebuffers();
            glBindFramebuffer(GL_FRAMEBUFFER, this.fbo);

            this.colorTexture = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, this.colorTexture);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, (ByteBuffer) null);

            glFramebufferTexture(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, this.colorTexture, 0);


            int depthBuffer = glGenRenderbuffers();
            glBindRenderbuffer(GL_RENDERBUFFER, depthBuffer);
            glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT, width, height);
            glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, depthBuffer);

            return glCheckFramebufferStatus(GL_FRAMEBUFFER) == GL_FRAMEBUFFER_COMPLETE;
        }

        return true;
    }

    private boolean updateImage(int width, int height) {
        if (this.image == null || this.image.getWidth() != width || this.image.getHeight() != height) {
            System.out.println("updateImage");
            this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        }
        return true;
    }



}
