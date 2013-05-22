package de.myreality.galacticum.util;

import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.renderer.SGL;

/**
 * Extended Image that provides a new warping method
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class ExtendedImage extends Image {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public ExtendedImage() {
	}

	public ExtendedImage(Image other) {
		super(other);
	}

	public ExtendedImage(Texture texture) {
		super(texture);
	}

	public ExtendedImage(String ref) throws SlickException {
		super(ref);
	}

	public ExtendedImage(ImageData data) {
		super(data);
	}

	public ExtendedImage(String ref, Color trans) throws SlickException {
		super(ref, trans);
	}

	public ExtendedImage(String ref, boolean flipped) throws SlickException {
		super(ref, flipped);
	}

	public ExtendedImage(int width, int height) throws SlickException {
		super(width, height);
	}

	public ExtendedImage(ImageData data, int f) {
		super(data, f);
	}

	public ExtendedImage(String ref, boolean flipped, int filter)
			throws SlickException {
		super(ref, flipped, filter);
	}

	public ExtendedImage(int arg0, int arg1, int arg2) throws SlickException {
		super(arg0, arg1, arg2);
	}

	public ExtendedImage(InputStream in, String ref, boolean flipped)
			throws SlickException {
		super(in, ref, flipped);
	}

	public ExtendedImage(String ref, boolean flipped, int f, Color transparent)
			throws SlickException {
		super(ref, flipped, f, transparent);
	}

	public ExtendedImage(InputStream in, String ref, boolean flipped, int filter)
			throws SlickException {
		super(in, ref, flipped, filter);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void drawWarped(float x1, float y1, float x2, float y2, float x3,
			float y3, float x4, float y4) {
		if (corners == null) {
			Color.white.bind();
		}
		texture.bind();

		GL.glTranslatef(x1, y1, 0);
		if (angle != 0) {
			GL.glTranslatef(centerX, centerY, 0.0f);
			GL.glRotatef(angle, 0.0f, 0.0f, 1.0f);
			GL.glTranslatef(-centerX, -centerY, 0.0f);
		}

		GL.glBegin(SGL.GL_QUADS);
		init();

		if (corners != null) {
			corners[TOP_LEFT].bind();
		}
		GL.glTexCoord2f(textureOffsetX, textureOffsetY);
		GL.glVertex3f(0, 0, 0);
		if (corners != null) {
			corners[TOP_RIGHT].bind();
		}
		GL.glTexCoord2f(textureOffsetX, textureOffsetY + textureHeight);
		GL.glVertex3f(x2 - x1, y2 - y1, 0);
		if (corners != null) {
			corners[BOTTOM_RIGHT].bind();
		}
		GL.glTexCoord2f(textureOffsetX + textureWidth, textureOffsetY
				+ textureHeight);
		GL.glVertex3f(x3 - x1, y3 - y1, 0);
		if (corners != null) {
			corners[BOTTOM_LEFT].bind();
		}
		GL.glTexCoord2f(textureOffsetX + textureWidth, textureOffsetY);
		GL.glVertex3f(x4 - x1, y4 - y1, 0);
		GL.glEnd();

		if (angle != 0) {
			GL.glTranslatef(centerX, centerY, 0.0f);
			GL.glRotatef(-angle, 0.0f, 0.0f, 1.0f);
			GL.glTranslatef(-centerX, -centerY, 0.0f);
		}
		GL.glTranslatef(-x1, -y1, 0);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
