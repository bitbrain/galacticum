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

import de.myreality.galacticum.util.Seed;

/**
 * Configuration for {@see Universe}
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class UniverseConfiguration {
	
	// ===========================================================
	// Constants
	// ===========================================================
	
	public static final String DEFAULT_NAME = "New Universe";

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Seed seed;
	
	private String name;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public UniverseConfiguration() {
		seed = new Seed();
		name = DEFAULT_NAME;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public Seed getSeed() {
		return seed;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSeed(Seed seed) {
		this.seed = seed;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
