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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * Abstract implementation of {@see Shader}
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public abstract class AbstractShader<Type extends Shader<Type> > implements Shader<Type> {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ShaderProgram program;
	
	private ShaderBehavior<Type> behavior;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public AbstractShader(String vert, String frag) {
		FileHandle vertHandle = Gdx.files.internal(vert);
		FileHandle fragHandle = Gdx.files.internal(frag);
		program = new ShaderProgram(vertHandle, fragHandle);
		System.out.println(program.getLog());
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.shader.Shader#getProgram()
	 */
	@Override
	public ShaderProgram getProgram() {
		return program;
	}
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.shader.Shader#setBehavior(de.myreality.galacticum.graphics.shader.ShaderBehavior)
	 */
	@Override
	public void setBehavior(ShaderBehavior<Type> behavior) {
		this.behavior = behavior;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(float delta) {
		if (behavior != null) {
			behavior.update(delta, (Type) this);
		}
	}	

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
