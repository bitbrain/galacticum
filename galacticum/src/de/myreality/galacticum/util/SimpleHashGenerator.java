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
package de.myreality.galacticum.util;


/**
 * Simple implementation of @{see SubSeedGenerator}
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de
 * @since 0.1
 * @version 0.1
 */
public class SimpleHashGenerator implements HashGenerator {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Seed seed;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleHashGenerator(Seed seed) {
		this.seed = seed;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.SubSeedGenerator#generate(de.myreality.galacticum.util.Seed, float, float)
	 */
	@Override
	public long generate(float x, float y) {		
		return (long) (seed.get() * x * y);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
