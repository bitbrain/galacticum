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

import de.myreality.galacticum.util.Seedable;

/**
 * Contains configuration settings for a context
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public interface ContextConfiguration extends Seedable, Configurable {
	
	// ===========================================================
	// Constants
	// ===========================================================
	
	public static final String NAME = "name";
	
	public static final String SEED = "seed";
	
	public static final String ID = "id";
	
	public static final String TIMESTAMP = "timestamp";
	
	public static final String ROOT_PATH = "data/";
	
	public static final String CHUNK_PATH = "chunks/";

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * 
	 * 
	 * @return
	 */
	String getName();
	
	/**
	 * 
	 * 
	 * @return
	 */
	String getID();
	
	/**
	 * 
	 * 
	 * @return
	 */
	String getRootPath();
	
	/**
	 * 
	 * 
	 * @return
	 */
	String getChunkPath();
	
	/**
	 * 
	 * 
	 * @return
	 */
	String getPlayerPath();
	
	/**
	 * 
	 * 
	 * @return
	 */
	long getTimestamp();
}
