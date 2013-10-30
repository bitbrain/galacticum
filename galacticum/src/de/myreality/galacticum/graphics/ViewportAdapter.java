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
package de.myreality.galacticum.graphics;

import com.badlogic.gdx.graphics.Camera;

import de.myreality.parallax.Viewport;

/**
 * Adapter which maps LibGDX camera implementation to a viewport interface
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class ViewportAdapter implements Viewport {

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Camera camera;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ViewportAdapter(Camera camera) {
		this.camera = camera;
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
	 * @see de.myreality.parallax.Viewport#getBottom()
	 */
	@Override
	public float getBottom() {
		return getTop() + camera.viewportHeight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.parallax.Viewport#getLeft()
	 */
	@Override
	public float getLeft() {
		return camera.position.x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.parallax.Viewport#getRight()
	 */
	@Override
	public float getRight() {
		return getLeft() + camera.viewportWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.parallax.Viewport#getTop()
	 */
	@Override
	public float getTop() {
		return camera.position.y;
	}

	// ===========================================================
	// Constants

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
