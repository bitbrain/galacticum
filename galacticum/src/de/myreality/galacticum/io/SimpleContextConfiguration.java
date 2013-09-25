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

import de.myreality.galacticum.util.Seed;

/**
 * Simple implementation of {@see ConfigurationIO}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
class SimpleContextConfiguration implements Configurable,
		ContextConfiguration {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Seed seed;
	
	private String name;
	
	private String rootPath;
	
	private String chunkPath;
	
	private String playerPath;
	
	private String id;
	
	private long timestamp;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleContextConfiguration() {
		setTimestamp(0);
		seed = new Seed();
		name = "";
		rootPath = "";
		chunkPath = "";
		playerPath = "";
		id = "";
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
	 * @see de.myreality.galacticum.util.Seedable#getSeed()
	 */
	@Override
	public Seed getSeed() {
		return seed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.ContextConfiguration#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.ContextConfiguration#getID()
	 */
	@Override
	public String getID() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.ContextConfiguration#getRootPath()
	 */
	@Override
	public String getRootPath() {
		return rootPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.ContextConfiguration#getChunkPath()
	 */
	@Override
	public String getChunkPath() {
		return chunkPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.ContextConfiguration#getPlayerPath()
	 */
	@Override
	public String getPlayerPath() {
		return playerPath;
	}

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
		this.seed = new Seed(seed);
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

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.io.ContextConfiguration#getTimestamp()
	 */
	@Override
	public long getTimestamp() {
		return timestamp;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.io.Configurable#setTimestamp(long)
	 */
	@Override
	public Configurable setTimestamp(long timestamp) {
		
		if (timestamp == 0) {
			timestamp = System.currentTimeMillis();
		}
		
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
