package de.myreality.galacticum.shading;

import org.newdawn.slick.geom.Vector2f;

/**
 * 
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since
 * @version
 */
public class StarShader extends SlickShader {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private long seedHash;
	
	private Vector2f resolution;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * @param vert
	 * @param frag
	 */
	public StarShader(String vert, String frag) {
		super(vert, frag);
		resolution = new Vector2f(0, 0);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public Vector2f getResolution() {
		return resolution;
	}
	
	/**
	 * @return the seedHash
	 */
	public long getSeedHash() {
		return seedHash;
	}

	/**
	 * @param seedHash the seedHash to set
	 */
	public void setSeedHash(long seedHash) {
		this.seedHash = seedHash;
	}

	/**
	 * @param resolution the resolution to set
	 */
	public void setResolution(float x, float y) {
		this.resolution.set(x, y);
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.shading.SlickShader#onBind(de.myreality
	 * .dev.galacticum.game.shading.ShaderProgram)
	 */
	@Override
	protected void onBind(ShaderProgram program) {
		program.setUniform2f("resolution", getResolution());
		//program.setUniform1f("time", getSeedHash());
		program.setUniform1f("time", getSeedHash());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
