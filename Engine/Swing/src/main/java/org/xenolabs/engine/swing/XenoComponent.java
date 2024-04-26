package org.xenolabs.engine.swing;

import static org.lwjgl.opengl.GL40.*;

import javax.swing.*;
import java.awt.*;
import java.nio.ByteBuffer;

public class XenoComponent extends JComponent {

    public interface Handler {
        void glInitialize ();

        void glResize (int width, int height);

        void glPaint ();
    }

    private final Handler handler;

    private boolean initialized = false;


    public XenoComponent(Handler handle) {
        this.handler = handle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        XenoContext.instance().makeCurrent();
        if (!initialized) {
            handler.glInitialize();
            initialized = true;
        }

        if (updateFramebuffer(getWidth(), getHeight())) {
            glBindFramebuffer(GL_FRAMEBUFFER, this.fbo);
            handler.glPaint();
        }
    }


    private int fbo;
    private int fboWidth;
    private int fboHeight;
    private int colorTexture;
    private boolean updateFramebuffer (int width, int height) {
        if (fbo == 0 || fboWidth != width || fboHeight != height) {
            if (fbo != 0) {
                glDeleteFramebuffers(this.fbo);
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
            glFramebufferTexture(GL_RENDERBUFFER, GL_COLOR_ATTACHMENT0, this.colorTexture, 0);


            int depthBuffer = glGenRenderbuffers();
            glBindRenderbuffer(GL_RENDERBUFFER, depthBuffer);
            glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT, width, height);
            glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, depthBuffer);

            int i = glCheckFramebufferStatus(GL_FRAMEBUFFER);

            if (i != GL_FRAMEBUFFER_COMPLETE) {
                String m_log = "";
                switch (i)
                {
                    case GL_FRAMEBUFFER_UNDEFINED:
                        m_log = "Framebuffer undefined";
                        break;
                    case GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT:
                        m_log = "Framebuffer incomplete attachment";
                        break;
                    case GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT:
                        m_log = "Framebuffer incomplete missing attachment";
                        break;
                    case GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER:
                        m_log = "Framebuffer incomplete draw buffer";
                        break;
                    case GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER:
                        m_log = "Framebuffer incomplete read buffer";
                        break;
                    case GL_FRAMEBUFFER_UNSUPPORTED:
                        m_log = "Framebuffer unsupported";
                        break;
                    case GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE:
                        m_log = "Framebuffer incomplete multisample";
                        break;
                    case GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS:
                        m_log = "Framebuffer incomplete layer targets";
                        break;
                }
                System.out.println(m_log);
                return false;
            }
        }
        return true;
    }
}
