package org.xeno.engine.core.graphics.mesh;

import java.nio.ByteBuffer;

public interface IVertexBuffer {

    default void createForRendering (int size) {
        createForRendering(size, EBufferUsage.Static);
    }

    void createForRendering (int size, EBufferUsage usage);

    void bind ();

    void unbind ();

    void copy (ByteBuffer buffer, int size, int offset);

}
