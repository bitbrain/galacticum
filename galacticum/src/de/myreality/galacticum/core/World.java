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

import java.util.Collection;

import de.myreality.chunx.ContentProvider;
import de.myreality.chunx.util.Observable;

/**
 * Contains all entities of a game which are currently handled.
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public interface World extends Observable<WorldListener>, ContentProvider {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * 
	 * 
	 * @param entity
	 */
	void add(Object entity);
	
	/**
	 * 
	 * 
	 * @param entity
	 */
	void remove(Object entity);
	
	/**
	 * 
	 * 
	 * @return
	 */
	Collection<Object> getContent();
	
	/**
	 * 
	 * 
	 * @param object
	 * @return
	 */
	boolean contains(Object object);
}
