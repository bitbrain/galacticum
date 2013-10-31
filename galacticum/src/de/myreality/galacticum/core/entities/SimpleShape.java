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
package de.myreality.galacticum.core.entities;

/**
 * Simple implementation of {@see Shape}
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SimpleShape implements Shape {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float width, height, x, y;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Shape#getX()
	 */
	@Override
	public float getX() {
		return x;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Shape#getY()
	 */
	@Override
	public float getY() {
		return y;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Shape#setX(float)
	 */
	@Override
	public void setX(float x) {
		this.x = x;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Shape#setY(float)
	 */
	@Override
	public void setY(float y) {
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Shape#getWidth()
	 */
	@Override
	public float getWidth() {
		return width;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Shape#getHeight()
	 */
	@Override
	public float getHeight() {
		return height;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Shape#collidesWith(de.myreality.galacticum.core.entities.Shape)
	 */
	@Override
	public boolean collidesWith(Shape other) {
		return true;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
