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
package de.myreality.galacticum.core;

import de.myreality.galacticum.entities.Entity;
import de.myreality.galacticum.util.GameColor;

/**
 * Game light representation including types
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SimpleGameLight implements GameLight {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 1L;

	// ===========================================================
	// Fields
	// ===========================================================

	private float x, y, radius;

	private int numberOfRays;

	private Entity owner;

	private GameColor color;
	
	private GameLightType type;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * @param x
	 * @param y
	 * @param radius
	 * @param numberOfRays
	 * @param owner
	 * @param color
	 */
	public SimpleGameLight(float x, float y, float radius, int numberOfRays,
			Entity owner, GameColor color, GameLightType type) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.numberOfRays = numberOfRays;
		this.owner = owner;
		this.color = color;
		this.type = type;
	}
	
	/**
	 * @param x
	 * @param y
	 * @param radius
	 * @param numberOfRays
	 * @param owner
	 * @param color
	 */
	public SimpleGameLight(float x, float y, float radius, int numberOfRays, GameColor color, GameLightType type) {
		this(x, y, radius, numberOfRays, null, color, type);
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
	 * @see de.myreality.chunx.util.Positionable#getX()
	 */
	@Override
	public float getX() {
		return x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.chunx.util.Positionable#getY()
	 */
	@Override
	public float getY() {
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.chunx.util.Positionable#setX(float)
	 */
	@Override
	public void setX(float x) {
		this.x = x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.chunx.util.Positionable#setY(float)
	 */
	@Override
	public void setY(float y) {
		this.y = y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.GameLight#getOwner()
	 */
	@Override
	public Entity getOwner() {
		return owner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.GameLight#getColor()
	 */
	@Override
	public GameColor getColor() {
		return color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.GameLight#getRadius()
	 */
	@Override
	public float getRadius() {
		return radius;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.GameLight#getNumberOfRays()
	 */
	@Override
	public int getNumberOfRays() {
		return numberOfRays;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.GameLight#getType()
	 */
	@Override
	public GameLightType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "SimpleGameLight [x=" + x + ", y=" + y + ", radius=" + radius
				+ ", numberOfRays=" + numberOfRays + ", owner=" + owner
				+ ", color=" + color + ", type=" + type + "]";
	}
	
	

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
