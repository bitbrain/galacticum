package de.myreality.galacticum.shading;

/**
 * Simple shader
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface Shader {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	boolean isValid();
	
	void bind();
	
	void unbind();
	
	void setEnabled(boolean value);
	
	boolean isEnabled();
}
