package com.github.hanavan99.javagameengine.util;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {

	private Object[] stack;
	private int size;

	public void push(T t) {
		stack[size++] = t;

		if (size == stack.length) {
			Object[] newStack = new Object[stack.length * 2];
			System.arraycopy(stack, 0, newStack, 0, stack.length);
		}
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index >= 0 && index < size) {
			return (T) stack[index];
		}
		throw new IllegalArgumentException("size: " + size + "; index: " + index);
	}

	public T peek() {
		return get(size);
	}

	public T pop() {
		if (size > 0) {
			return get(size--);
		}
		throw new IllegalStateException("cannot pop when stack is empty");
	}

	@Override
	public Iterator<T> iterator() {
		return new StackIterator();
	}

	private class StackIterator implements Iterator<T> {

		private int curIndex = 0;

		@Override
		public boolean hasNext() {
			return curIndex < size;
		}

		@Override
		public T next() {
			return get(curIndex++);
		}

	}

}
