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

import de.myreality.galacticum.core.entities.Shape.ShapeListener;
import de.myreality.galacticum.util.SimpleObserver;

/**
 * Simple implementation of {@see Shape}
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SimpleShape extends SimpleObserver<ShapeListener> implements Shape {

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
	private float rotation;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleShape(float width, float height) {
		this.width = width;
		this.height = height;
	}

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
		
		for (ShapeListener l : getListeners()) {
			l.onSetX(this);
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Shape#setY(float)
	 */
	@Override
	public void setY(float y) {
		this.y = y;
		
		for (ShapeListener l : getListeners()) {
			l.onSetY(this);
		}
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
	 * @see de.myreality.galacticum.core.entities.Entity#setRotation(float)
	 */
	@Override
	public void setRotation(float rotation) {
		this.rotation = rotation;
		
		for (ShapeListener l : getListeners()) {
			l.onSetRotation(this);
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Entity#getRotation()
	 */
	@Override
	public float getRotation() {
		return rotation;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Shape#collidesWith(de.myreality.galacticum.core.entities.Shape)
	 */
	@Override
	public boolean collidesWith(Shape other) {
		
		float right = getX() + getWidth();
		float bottom = getY() + getHeight();
		
		float otherRight = other.getX() + other.getWidth();
		float otherBottom = other.getY() + other.getHeight();
		
		boolean collisionX = otherRight >= getX() && other.getX() <= right;
		boolean collisionY = otherBottom >= getY() && other.getY() <= bottom;
		
		return collisionX && collisionY;
		
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
