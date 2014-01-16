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
package de.myreality.galacticum.zones;

import de.myreality.galacticum.util.GameColor;
import de.myreality.galacticum.util.Observer;
import de.myreality.galacticum.zones.Zone.ZoneListener;

/**
 * Zone which covers an area in space
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public interface Zone extends Observer<ZoneListener> {
	
	// ===========================================================
	// Constants
	// ===========================================================
	
	GameColor getAmbientColor();
	
	long getHash();

	// ===========================================================
	// Methods
	// ===========================================================
	
	public interface ZoneListener {
		
		void onEnterZone(long hash, ZoneTarget target);
		
		void onLeaveZone(long hash, ZoneTarget target);
	}
}
