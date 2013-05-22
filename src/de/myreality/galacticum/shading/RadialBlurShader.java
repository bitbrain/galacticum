package de.myreality.galacticum.shading;

import org.newdawn.slick.geom.Vector2f;

import de.myreality.galacticum.core.Boundable;

/**
 * A shader to compute radial blurring on a specific target
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class RadialBlurShader extends SlickShader {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final String RADIAL_BLUR = "radial_blur";
	private static final String RADIAL_BRIGHT = "radial_bright";
	private static final String RADIAL_SIZE = "radial_size";
	private static final String RADIAL_ORIGIN = "radial_origin";

	// ===========================================================
	// Fields
	// ===========================================================

	private Boundable focus;

	private float blurFactor;

	private float brightFactor;

	private Vector2f size;

	private Vector2f origin;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * @param vert
	 *            Vertex shader file
	 * @param frag
	 *            Fragment shader File
	 */
	public RadialBlurShader(String vert, String frag) {
		super(vert, frag);
		origin = new Vector2f(0, 0);
		size = new Vector2f(0, 0);
		blurFactor = 0.0f;
		brightFactor = 0.0f;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	/**
	 * @return the blurFactor
	 */
	public float getBlurFactor() {
		return blurFactor;
	}

	/**
	 * @return the brightFactor
	 */
	public float getBrightFactor() {
		return brightFactor;
	}

	/**
	 * @param blurFactor
	 *            the blurFactor to set
	 */
	public void setBlurFactor(float blurFactor) {
		this.blurFactor = blurFactor;
	}

	/**
	 * @param brightFactor
	 *            the brightFactor to set
	 */
	public void setBrightFactor(float brightFactor) {
		this.brightFactor = brightFactor;
	}

	/**
	 * @return the size
	 */
	public Vector2f getSize() {
		return size;
	}

	/**
	 * @return the origin
	 */
	public Vector2f getOrigin() {
		return origin;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(float size) {
		this.size.x = size;
		this.size.y = size;
	}

	/**
	 * @param origin
	 *            the origin to set
	 */
	public void setOrigin(float x, float y) {
		this.origin.set(x, y);
	}

	public void setTarget(Boundable target) {
		this.focus = target;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.shading.SlickShader#onBind()
	 */
	@Override
	protected void onBind(ShaderProgram program) {

		if (focus != null) {
			origin.x = focus.getLeft();
			origin.y = focus.getTop();
		}
		program.setUniform1f(RADIAL_BLUR, getBlurFactor());
		program.setUniform1f(RADIAL_BRIGHT, getBrightFactor());
		program.setUniform2f(RADIAL_SIZE, getSize());
		program.setUniform2f(RADIAL_ORIGIN, getOrigin());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
