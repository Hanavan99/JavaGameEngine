package com.github.hanavan99.javagameengine.matrix;

import com.github.hanavan99.javagameengine.vector.Vector;

public interface Matrix<M extends Matrix<M, V>, V extends Vector<V>> extends Cloneable {

	M clone();

	M translate(V v);

	M setTranslation(V v);

	M rotate(V v);

	M setRotation(V v);

	M scale(double scale);

	M scale(V v);

	M setScale(V v);

	M multiply(M m);

	boolean equals(M other);

}
