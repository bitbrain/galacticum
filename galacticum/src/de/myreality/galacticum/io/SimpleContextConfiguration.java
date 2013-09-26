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
		chunkPath = DEFAULT_CHUNK_DIR;
		playerPath = DEFAULT_PLAYER_DIR;
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
		return getRootPath() + chunkPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.ContextConfiguration#getPlayerPath()
	 */
	@Override
	public String getPlayerPath() {
		return getRootPath() + playerPath;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chunkPath == null) ? 0 : chunkPath.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((playerPath == null) ? 0 : playerPath.hashCode());
		result = prime * result
				+ ((rootPath == null) ? 0 : rootPath.hashCode());
		result = prime * result + ((seed == null) ? 0 : seed.hashCode());
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleContextConfiguration other = (SimpleContextConfiguration) obj;
		if (chunkPath == null) {
			if (other.chunkPath != null)
				return false;
		} else if (!chunkPath.equals(other.chunkPath))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (playerPath == null) {
			if (other.playerPath != null)
				return false;
		} else if (!playerPath.equals(other.playerPath))
			return false;
		if (rootPath == null) {
			if (other.rootPath != null)
				return false;
		} else if (!rootPath.equals(other.rootPath))
			return false;
		if (seed == null) {
			if (other.seed != null)
				return false;
		} else if (!seed.equals(other.seed))
			return false;
		if (timestamp != other.timestamp)
			return false;
		return true;
	}
	
	

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
