package de.myreality.galacticum.shading;

import org.newdawn.slick.SlickException;


/**
 * Simple shader implementation for singleton proposals
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public abstract class SlickShader implements Shader {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private ShaderProgram program;

	private boolean valid;

	private boolean enabled;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SlickShader(String vert, String frag) {
		try {
			program = ShaderProgram.loadProgram(vert, frag);
			valid = true;
			enabled = true;
		} catch (SlickException e) {
			e.printStackTrace();
			valid = false;
		}
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
	 * @see de.myreality.galacticum.shading.Shader#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean value) {
		enabled = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.shading.Shader#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean isValid() {
		return valid;
	}

	@Override
	public void bind() {
		if (isValid() && isEnabled()) {
			program.bind();
			onBind(program);
		}
	}

	@Override
	public void unbind() {
		if (isValid() && isEnabled()) {
			program.unbind();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected abstract void onBind(ShaderProgram program);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
