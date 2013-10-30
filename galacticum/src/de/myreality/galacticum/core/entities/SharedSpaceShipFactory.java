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

import de.myreality.galacticum.util.Seed;

/**
 * 
 * 
 * @author miguel
 * @since
 * @version
 */
public class SharedSpaceShipFactory implements SpaceShipFactory {

	// ===========================================================
	// Constants
	// ===========================================================
	
	private static SharedSpaceShipFactory instance;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	
	static {
		instance = new SharedSpaceShipFactory();
	}
	
	private SharedSpaceShipFactory() { }

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public static SharedSpaceShipFactory getInstance() {
		return instance;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.entities.SpaceShipFactory#create(float,
	 * float, de.myreality.galacticum.core.entities.SpaceShipType,
	 * de.myreality.galacticum.util.Seed)
	 */
	@Override
	public SpaceShip create(float x, float y, SpaceShipType type, Seed seed) {
		return null;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
