package com.github.hanavan99.javagameengine.vector;

/**
 * This {@code Vector} class provides an implementation of mathematical vectors.
 * This class allows for both mutable and immutable versions, and can be used
 * interchangeably.
 * 
 * @author Hanavan Kuhn
 *
 * @param <V> the type of vector returned by calls that modify the vector
 */
public interface Vector<V extends Vector<V>> extends Cloneable {

	/**
	 * Creates a copy of this vector.
	 * 
	 * @return the copy
	 */
	V clone();

	/**
	 * Gets the magnitude/length of the vector.
	 * 
	 * @return the magnitude
	 */
	double magnitude();

	/**
	 * Gets the direction of the vector in radians, which should be between -pi and
	 * pi.
	 * 
	 * @return the direction
	 */
	double direction();

	/**
	 * Adds a vector to this vector.
	 * 
	 * @param t the vector to add
	 * @return the new vector (if applicable)
	 */
	V add(V t);

	/**
	 * Subtracts a vector from this vector.
	 * 
	 * @param t the vector to subtract
	 * @return the new vector (if applicable)
	 */
	V subtract(V t);

	/**
	 * Rotates this vector by a certain angle, in radians.
	 * 
	 * @param angle the angle to rotate by
	 * @return the new vector (if applicable)
	 */
	V rotate(double angle);

	/**
	 * Scales this vector by a scalar amount.
	 * 
	 * @param scale the scale
	 * @return the new vector (if applicable)
	 */
	V scale(double scale);

	/**
	 * Scales this vector by another vector. Each vector component will be
	 * multiplied by the corresponding vector component passed in.
	 * 
	 * @param t the vector to scale by
	 * @return the new vector (if applicable)
	 */
	V scale(V t);

	/**
	 * Computes the dot product between two vectors.
	 * 
	 * @param t the other vector
	 * @return the dot product
	 */
	double dot(V t);

	/**
	 * Computes the cross product between two vectors.
	 * 
	 * @param t the other vector
	 * @return the cross product (if applicable)
	 */
	V cross(V t);

	/**
	 * Normalizes this vector so that {@code magnitude()} returns 1. The angle of
	 * the vector is preserved.
	 * 
	 * @return the normalized vector (if applicable)
	 */
	V normalize();

	/**
	 * Determines if two vectors are equal.
	 * 
	 * @param other the other vector
	 * @return if the vectors are equal
	 */
	boolean equals(V other);

}
