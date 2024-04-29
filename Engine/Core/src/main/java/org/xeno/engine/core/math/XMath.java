package org.xeno.engine.core.math;

public class XMath {

    public static Vector3f add (Vector3f a, Vector3f b, Vector3f r) {
        r.x = a.x + b.x;
        r.y = a.y + b.y;
        r.z = a.z + b.z;
        return r;
    }
}
