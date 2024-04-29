package org.xeno.engine.core.math;

public class Matrix3f {

    public float m00;
    public float m01;
    public float m02;

    public float m10;
    public float m11;
    public float m12;

    public float m20;
    public float m21;
    public float m22;

    public Matrix3f() {
        m00 = m11 = m22 = 1.0f;
        m01 = m02 = m10 = m12 = m20 = m21 = 0.0f;
    }

}
