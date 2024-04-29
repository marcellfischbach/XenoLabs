package org.xeno.engine.core.graphics.shading;

import org.xeno.engine.core.graphics.EShaderAttribute;

public interface IShader {

    void registerAttribute(String attribute);

    int indexOf(String attribute);

    IShaderAttribute getAttribute(String attribute);

    IShaderAttribute getAttribute(int index);

    IShaderAttribute getAttribute (EShaderAttribute attribute);

}
