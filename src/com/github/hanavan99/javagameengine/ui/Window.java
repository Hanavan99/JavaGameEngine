package com.github.hanavan99.javagameengine.ui;

import com.github.hanavan99.javagameengine.graphics.Graphics;
import com.github.hanavan99.javagameengine.matrix.Matrix;
import com.github.hanavan99.javagameengine.vector.Vector;

public abstract class Window<M extends Matrix<M, V>, V extends Vector<V>> {

	public abstract void render(Graphics<M, V> g);

}
