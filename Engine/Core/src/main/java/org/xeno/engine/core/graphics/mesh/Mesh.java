package org.xeno.engine.core.graphics.mesh;

import org.xeno.engine.core.graphics.IDevice;
import org.xeno.engine.core.math.Color4f;
import org.xeno.engine.core.math.Vector2f;
import org.xeno.engine.core.math.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Mesh {


    private final List<Vector3f> vertices = new ArrayList<>();
    private final List<Vector3f> normals = new ArrayList<>();
    private final List<Vector3f> tangents = new ArrayList<>();
    private final List<Color4f> colors = new ArrayList<>();
    private final List<Vector2f> uv0_2 = new ArrayList<>();
    private final List<Vector3f> uv0_3 = new ArrayList<>();

    private boolean invalid = true;


    public void render(IDevice device) {
        if (invalid) {
            rebuild(device);
        }
    }


    public List<Vector3f> getVertices() {
        return new ArrayList<>(vertices);
    }

    public void setVertices(List<Vector3f> vertices) {
        this.vertices.clear();
        this.vertices.addAll(vertices);
        this.invalid = true;
    }

    public List<Vector3f> getNormals() {
        return new ArrayList<>(normals);
    }

    public void setNormals(List<Vector3f> normals) {
        this.normals.clear();
        this.normals.addAll(normals);
        this.invalid = true;
    }

    public List<Vector3f> getTangents() {
        return new ArrayList<>(tangents);
    }

    public void setTangents(List<Vector3f> tangents) {
        this.tangents.clear();
        this.tangents.addAll(tangents);
        this.invalid = true;
    }

    public List<Color4f> getColors() {
        return new ArrayList<>(colors);
    }

    public void setColors(List<Color4f> colors) {
        this.colors.clear();
        this.colors.addAll(colors);
        this.invalid = true;
    }

    public List<Vector2f> getUV0() {
        return new ArrayList<>(uv0_2);
    }

    public void setUV0(List<Vector2f> uv0) {
        this.uv0_2.clear();
        this.uv0_2.addAll(uv0);
        this.invalid = true;
    }

    public List<Vector3f> getUV0_3() {
        return new ArrayList<>(uv0_3);
    }

    public void setUV0_3(List<Vector3f> uv0_3) {
        this.uv0_3.clear();
        this.uv0_3.addAll(uv0_3);
        this.invalid = true;
    }

    private void rebuild (IDevice device) {
        invalid = false;
    }
}
