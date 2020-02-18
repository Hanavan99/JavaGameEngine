package com.github.hanavan99.javagameengine.ui;

import com.github.hanavan99.javagameengine.graphics.Graphics;
import com.github.hanavan99.javagameengine.matrix.Matrix;
import com.github.hanavan99.javagameengine.vector.Vector;

public interface Renderable<M extends Matrix<M, V>, V extends Vector<V>> {

	public Bounds getBounds();
	
	public void setBounds(Bounds bounds);
	
	public void render(Graphics<M, V> graphics);

}
