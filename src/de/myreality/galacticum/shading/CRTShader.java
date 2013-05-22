package de.myreality.galacticum.shading;


/**
 * 
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since
 * @version
 */
public class CRTShader extends SlickShader {

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
	public CRTShader(String vert, String frag) {
		super(vert, frag);
		// TODO Auto-generated constructor stub
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/**
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.shading.SlickShader#onBind(de.myreality.galacticum.shading.ShaderProgram)
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
