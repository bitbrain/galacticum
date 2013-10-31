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
package de.myreality.galacticum.core.player;

import de.myreality.galacticum.core.entities.SpaceShip;

/**
 * Is called when a player gets updated
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public interface PlayerListener {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * 
	 * 
	 * @param player
	 */
	void onCreate(Player player);
	
	/**
	 * 
	 * 
	 * @param player
	 */
	void onDestroy(Player player);
	
	/**
	 * 
	 * 
	 * @param ship
	 * @param player
	 */
	void onAddSpaceShip(SpaceShip ship, Player player);
	
	/**
	 * 
	 * 
	 * @param ship
	 * @param player
	 */
	void onRemoveSpaceShip(SpaceShip ship, Player player);
	
	/**
	 * 
	 * 
	 * @param oldValue
	 * @param newValue
	 * @param player
	 */
	void onChangeMoney(int oldValue, int newValue, Player player);
	
	/**
	 * 
	 * @param oldShip
	 * @param newShip
	 * @param player
	 */
	void onSetCurrentShip(SpaceShip oldShip, SpaceShip newShip, Player player);

}
