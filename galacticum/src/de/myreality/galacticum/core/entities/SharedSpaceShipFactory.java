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

import java.io.Serializable;

import de.myreality.galacticum.util.Seed;

/**
 * Singleton implementation of (@see SpaceShipFactory}
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SharedSpaceShipFactory implements SpaceShipFactory, Serializable {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 1L;
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
		return new SimpleSpaceShip();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	class SimpleSpaceShip extends SimpleEntity implements SpaceShip {

		private static final long serialVersionUID = 8496116234063566152L;

		/* (non-Javadoc)
		 * @see de.myreality.galacticum.core.entities.SpaceShip#getFaction()
		 */
		@Override
		public Faction getFaction() {
			// TODO Auto-generated method stub
			return null;
		}

		
		
	}

}
