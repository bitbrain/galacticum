package de.myreality.galacticum.shading;

import org.newdawn.slick.geom.Vector2f;



/**
 * Shader implementation for blurring
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since
 * @version
 */
public class BlurShader extends SlickShader {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final String SHIFT = "uShift";
	
	// ===========================================================
	// Fields
	// ===========================================================

	private Vector2f factor;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public BlurShader(String vert, String frag) {
		super(vert, frag);
		factor = new Vector2f(0, 0);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void setFactor(float factor) {
		this.factor.set(factor, factor);
	}
	
	public float getFactor() {
		return factor.getX();		
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.shading.SlickShader#onBind(de.myreality
	 * .dev.galacticum.game.util.ShaderProgram)
	 */
	@Override
	protected void onBind(ShaderProgram program) {
		program.setUniform2f(SHIFT, factor);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
