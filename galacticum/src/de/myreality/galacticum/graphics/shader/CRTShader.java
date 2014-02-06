/* Galacticum space game for Android, PC and browser.
 * Copyright (C) 2013  Miguel Gonzalez
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.myreality.galacticum.graphics.shader;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * 
 *
 * @author miguel
 * @since
 * @version
 */
public class CRTShader extends AbstractShader {
	
	// ===========================================================
	// Constants
	// ===========================================================
	
	public static final String VERTEX = "shaders/crt.vert";
	
	public static final String FRAGMENT = "shaders/crt.frag";

	// ===========================================================
	// Fields
	// ===========================================================
	
	private float noiseFactor, frequency, lineSpeed, intensity;
	
	private float time;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	/**
	 * @param vert
	 * @param frag
	 */
	public CRTShader(float noiseFactor, float frequency, float lineSpeed, float intensity) {
		super(VERTEX, FRAGMENT);
		this.noiseFactor = noiseFactor;
		this.frequency = frequency;
		this.lineSpeed = lineSpeed;
		this.intensity = intensity;
		time = 0;
	}
	
	public CRTShader() {
		this(0.1f, 100.0f, 0.8f, 0.9f);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.shader.Shader#update(float)
	 */
	@Override
	public void update(float delta) {
		
		time += delta;
		
		ShaderProgram p = getProgram();
		
		p.setUniformf("time", time);
		p.setUniformf("frequency", frequency);
		p.setUniformf("noiseFactor", noiseFactor);
		p.setUniformf("intensity", intensity);
		p.setUniformf("lineSpeed", lineSpeed);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
