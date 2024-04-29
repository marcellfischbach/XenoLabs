package org.xeno.engine.core.graphics.shading;

import org.xeno.engine.core.math.Color4f;
import org.xeno.engine.core.math.Vector2f;
import org.xeno.engine.core.math.Vector3f;
import org.xeno.engine.core.math.Vector4f;

public interface IShaderAttribute {

    String getName();

    boolean isValid();

    void setArrayIndex(int arrayIndex);

    void bind(float x);

    void bind(float x, float y);

    void bind(float x, float y, float z);

    void bind(float x, float y, float z, float w);

    void bind(int x);

    void bind(int x, int y);

    void bind(int x, int y, int z);

    void bind(int x, int y, int z, int w);

    void bind(Vector2f v);

    void bind(Vector3f v);

    void bind(Vector4f v);

    void bind(Color4f c);
}
