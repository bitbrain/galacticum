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
package de.myreality.galacticum.entities;

import de.myreality.galacticum.util.Shakeable;
import de.myreality.galacticum.util.Shaker;

/**
 * 
 *
 * @author miguel
 * @since
 * @version
 */
public class ShakeableShape extends SimpleShape implements Shakeable {

	// ===========================================================
	// Constants
	// ===========================================================
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Shaker shaker;

	// ===========================================================
	// Constructors
	// ===========================================================


	/**
	 * @param width
	 * @param height
	 */
	public ShakeableShape(float width, float height) {
		super(width, height);
		shaker = new Shaker();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Shakeable#shake(float, long)
	 */
	@Override
	public void shake(float amount, long miliseconds) {
		shaker.shake(amount, miliseconds);
	}


	@Override
	public float getX() {
		return super.getX() + shaker.getX();
	}


	@Override
	public float getY() {
		return super.getY() + shaker.getY();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Shakeable#isShaking()
	 */
	@Override
	public boolean isShaking() {
		return shaker.isShaking();
	}
		

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * 
	 */
	public void update(float delta) {
		shaker.update(delta);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
