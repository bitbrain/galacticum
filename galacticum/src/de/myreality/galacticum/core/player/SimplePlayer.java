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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.myreality.galacticum.core.entities.SpaceShip;
import de.myreality.galacticum.util.SimpleObserver;

/**
 * Simple implementation of {@see Player}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SimplePlayer extends SimpleObserver<PlayerListener> implements Player {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = -8268769786257566700L;
	
	// ===========================================================
	// Fields
	// ===========================================================
	
	private SpaceShip currentShip;
	
	private Map<String, SpaceShip> ships;


	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimplePlayer(SpaceShip startShip) {
		ships = new HashMap<String, SpaceShip>();
		addSpaceShip(startShip);
		setCurrentShip(startShip.getID());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.player.Player#addSpaceShip(de.myreality.
	 * galacticum.core.entities.SpaceShip)
	 */
	@Override
	public void addSpaceShip(SpaceShip spaceship) {
		ships.put(spaceship.getID(), spaceship);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.player.Player#removeSpaceShip(de.myreality
	 * .galacticum.core.entities.SpaceShip)
	 */
	@Override
	public void removeSpaceShip(SpaceShip spaceship) {
		ships.remove(spaceship.getID());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.player.Player#getSpaceShips()
	 */
	@Override
	public Collection<SpaceShip> getSpaceShips() {
		return ships.values();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.player.Player#getSpaceShip(java.lang.String)
	 */
	@Override
	public SpaceShip getSpaceShip(String id) {
		return ships.get(id);
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.player.Player#setCurrentShip(java.lang.String)
	 */
	@Override
	public boolean setCurrentShip(String id) {
		SpaceShip ship = getSpaceShip(id);
		
		if (ship != null && ship != getCurrentShip()) {
			currentShip = ship;
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.player.Player#getCurrentShip()
	 */
	@Override
	public SpaceShip getCurrentShip() {
		return currentShip;
	}

}
