package com.github.hanavan99.javagameengine.graphics;

/**
 * Represents a color stored internally as 8-bit ARGB.
 * 
 * @author Hanavan Kuhn
 *
 */
public final class Color {

	private int argb;

	public Color(int argb) {
		this.argb = argb;
	}

	public Color(int a, int r, int g, int b) {
		setAlpha(a);
		setRed(r);
		setGreen(g);
		setBlue(b);
	}

	public Color(int r, int g, int b) {
		setRed(r);
		setGreen(g);
		setBlue(b);
	}

	public int getAlpha() {
		return (argb & 0xFF) >> 0;
	}

	public void setAlpha(int alpha) {
		argb = (argb & 0xFFFFFF00) | (alpha << 0);
	}

	public int getRed() {
		return (argb & 0xFF00) >> 8;
	}

	public void setRed(int red) {
		argb = (argb & 0xFFFF00FF) | (red << 8);
	}

	public int getGreen() {
		return (argb & 0xFF0000) >> 16;
	}

	public void setGreen(int green) {
		argb = (argb & 0xFF00FFFF) | (green << 16);
	}

	public int getBlue() {
		return (argb & 0xFF000000) >>> 24;
	}

	public void setBlue(int blue) {
		argb = (argb & 0x00FFFFFF) | (blue << 24);
	}

	public int getARGB() {
		return argb;
	}

}
