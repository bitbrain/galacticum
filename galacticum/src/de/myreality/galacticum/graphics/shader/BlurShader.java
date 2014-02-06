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
 * Blur shader which supports vertical and horizontal shading. It is also possible to set
 * the strength of the blur effect.
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class BlurShader implements Shader {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public void setHorizontal(boolean horizontal) {
		// TODO
	}
	
	public void setBlurSize(float blurSize) {
		// TODO
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.shader.Shader#getProgram()
	 */
	@Override
	public ShaderProgram getProgram() {
		return null;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.shader.Shader#update(float)
	 */
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub		
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
