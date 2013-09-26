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
package de.myreality.galacticum.io;



/**
 * Builder which builds configurations
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SimpleConfigurationBuilder implements ConfigurationBuilder {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private String name;
	
	private String seed;
	
	private String id;
	
	private long timestamp;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.io.Configurable#setName(java.lang.String)
	 */
	@Override
	public Configurable setName(String name) {
		this.name = name;
		
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.io.Configurable#setSeed(java.lang.String)
	 */
	@Override
	public Configurable setSeed(String seed) {
		this.seed = seed;
		
		return this;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.io.Configurable#setID(java.lang.String)
	 */
	@Override
	public Configurable setID(String id) {
		this.id = id;
		
		return this;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.io.ConfigurationBuilder#build()
	 */
	@Override
	public ContextConfiguration build() {
	
		SimpleContextConfiguration configuration = new SimpleContextConfiguration();
		
		configuration.setID(id);
		configuration.setSeed(seed);
		configuration.setName(name);
		configuration.setTimestamp(timestamp);
		
		return configuration;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.io.Configurable#setTimestamp(long)
	 */
	@Override
	public Configurable setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
