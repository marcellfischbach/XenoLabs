package org.xeno.engine.core.graphics;

import org.xeno.engine.core.math.Color4f;

public interface IDevice {


    void clear (boolean clearColor, Color4f color, boolean clearDepth, float depth, boolean clearStencil, int stencil);

}
