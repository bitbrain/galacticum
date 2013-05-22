package de.myreality.galacticum.shading;


/**
 * 
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since
 * @version
 */
public class BloomShader extends SlickShader {

	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * @param vert
	 * @param frag
	 */
	public BloomShader(String vert, String frag) {
		super(vert, frag);
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

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
		
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
