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

import de.myreality.galacticum.util.GameColor;
import de.myreality.galacticum.util.Moveable;

/**
 * 
 * 
 * @author Miguel Gonzalez
 * @since
 * @version
 */
public class MoveableEntity extends SimpleEntity implements Moveable {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * @param type
	 * @param width
	 * @param height
	 * @param color
	 * @param hash
	 */
	public MoveableEntity(EntityType type, float width, float height,
			GameColor color, long hash) {
		super(type, width, height, color, hash);
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
	 * @see de.myreality.galacticum.util.Moveable#move(float, float)
	 */
	@Override
	public void move(float x, float y) {
		for (ShapeListener l : getListeners()) {
			l.onMove(x, y, this);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
