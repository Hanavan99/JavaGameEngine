package com.github.hanavan99.javagameengine.graphics;

import com.github.hanavan99.javagameengine.matrix.Matrix;
import com.github.hanavan99.javagameengine.vector.Vector;

/**
 * This class provides graphics functionality that is independent of any
 * graphics library.
 * 
 * @author Hanavan Kuhn
 *
 * @param <M> the type of matrix this graphics object uses
 * @param <V> the type of vector this graphics object uses
 */
public interface Graphics<M extends Matrix<M, V>, V extends Vector<V>> {

	M getViewMatrix();

	void setViewMatrix(M m);

	void pushViewMatrix();

	void popViewMatrix();

	M getModelMatrix();

	void setModelMatrix(M m);

	void pushModelMatrix();

	void popModelMatrix();

	void drawLine(V point1, V point2);

	void drawTriangle(V point1, V point2, V point3);

	void fillTriangle(V point1, V point2, V point3);

	void drawRect(V position, V size);

	void fillRect(V position, V size);

	void drawCircle(V position, double size);

	void fillCircle(V position, double size);

	void drawOval(V position, V size);

	void fillOval(V position, V size);

	void drawString(V position, String text);

	void drawBitmap(V position, Texture bitmap);

	void drawBitmap(V position, Texture bitmap, Color colorMask);

	void drawBitmap(V position, V size, Texture bitmap);

	void drawBitmap(V position, V size, Texture bitmap, Color colorMask);

	void drawBitmap(V position, V size, V srcPosition, V srcSize, Texture bitmap);

	void drawBitmap(V position, V size, V srcPosition, V srcSize, Texture bitmap, Color color);

	Color getColor();

	void setColor(Color color);

	// void setLineThickness();

}
