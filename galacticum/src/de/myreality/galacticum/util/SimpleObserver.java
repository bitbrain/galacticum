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

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * 
 * @author miguel
 * @since
 * @version
 */
public class SimpleObserver<Type> implements Observer<Type> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private List<Type> listeners;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleObserver() {
		listeners = new CopyOnWriteArrayList<Type>();
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
	 * @see de.myreality.galacticum.util.Observer#addListener(java.lang.Object)
	 */
	@Override
	public void addListener(Type listener) {
		if (!hasListener(listener)) {
			listeners.add(listener);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.util.Observer#removeListener(java.lang.Object)
	 */
	@Override
	public void removeListener(Type listener) {
		listeners.remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Observer#hasListener(java.lang.Object)
	 */
	@Override
	public boolean hasListener(Type listener) {
		return listeners.contains(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Observer#getListeners()
	 */
	@Override
	public Collection<Type> getListeners() {
		return listeners;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
