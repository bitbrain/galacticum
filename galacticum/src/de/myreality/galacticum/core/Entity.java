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

import de.myreality.chunx.ChunkConfiguration;
import de.myreality.chunx.moving.MoveableChunkTarget;
import de.myreality.chunx.moving.MovementDetector;
import de.myreality.chunx.moving.SimpleMovementDetector;
import de.myreality.galacticum.util.Renderable;

/**
 * Basic implementation of an entity
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public abstract class Entity implements MoveableChunkTarget, Renderable {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 1L;

	// ===========================================================
	// Fields
	// ===========================================================
	
	private float x, y;
	
	private MovementDetector detector;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public Entity(float x, float y, ChunkConfiguration configuration) {
		this.x = x;
		this.y = y;
		detector = new SimpleMovementDetector(this, configuration);
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
	 * @see de.myreality.chunx.util.Updateable#update()
	 */
	@Override
	public void update() {
		update(0f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.chunx.util.Updateable#update(float)
	 */
	@Override
	public void update(float delta) {
		detector.update(delta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.chunx.moving.MoveableChunkTarget#getMovementDetector()
	 */
	@Override
	public MovementDetector getMovementDetector() {
		return detector;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.chunx.moving.MoveableChunkTarget#setMovementDetector(de.
	 * myreality.chunx.moving.MovementDetector)
	 */
	@Override
	public void setMovementDetector(MovementDetector detector) {
		this.detector = detector;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
