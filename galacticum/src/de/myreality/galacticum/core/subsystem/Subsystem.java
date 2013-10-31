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
package de.myreality.galacticum.core.subsystem;

import de.myreality.galacticum.core.context.Context;
import de.myreality.galacticum.util.Nameable;

/**
 * Provides such functionality for different game sections. A subsystem
 * can be started, shutted down or updated frequently.
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public interface Subsystem extends Nameable {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Starts this subsystem
	 */
	void start() throws SubsystemException;
	
	/**
	 * 
	 * 
	 * @param context
	 */
	void onEnter(Context context);
	
	/**
	 * Updates this subsystem by the given delta
	 * 
	 * @param delta
	 */
	void update(float delta);
	
	/**
	 * Closes this subsystem
	 */
	void shutdown();
	
	/**
	 * 
	 * @param listener
	 */
	void addProgressListener(ProgressListener listener);
	
	/**
	 * 
	 * 
	 * @param listener
	 */
	void removeProgressListener(ProgressListener listener);

}
