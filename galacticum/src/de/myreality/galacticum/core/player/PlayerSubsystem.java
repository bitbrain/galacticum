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

import de.myreality.galacticum.core.subsystem.ProgressListener;
import de.myreality.galacticum.core.subsystem.Subsystem;
import de.myreality.galacticum.core.subsystem.SubsystemException;

/**
 * Handles the current player
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class PlayerSubsystem implements Subsystem {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Player player;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public Player getPlayer() {
		return player;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Nameable#getName()
	 */
	@Override
	public String getName() {
		return "player";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#start()
	 */
	@Override
	public void start() throws SubsystemException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#update(float)
	 */
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#shutdown()
	 */
	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.subsystem.Subsystem#addProgressListener(
	 * de.myreality.galacticum.core.subsystem.ProgressListener)
	 */
	@Override
	public void addProgressListener(ProgressListener listener) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.subsystem.Subsystem#removeProgressListener
	 * (de.myreality.galacticum.core.subsystem.ProgressListener)
	 */
	@Override
	public void removeProgressListener(ProgressListener listener) {
		// TODO Auto-generated method stub

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
