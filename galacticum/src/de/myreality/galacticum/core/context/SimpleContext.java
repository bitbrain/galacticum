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
package de.myreality.galacticum.core.context;

import java.util.Collection;

import de.myreality.galacticum.core.GameContainer;
import de.myreality.galacticum.core.player.Player;
import de.myreality.galacticum.core.subsystem.Subsystem;
import de.myreality.galacticum.graphics.GameCamera;
import de.myreality.galacticum.io.ContextConfiguration;

/**
 * Simple implementation of {@see Context}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
class SimpleContext implements Context {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Collection<Subsystem> subsystems;
	
	private GameContainer container;
	
	private ContextConfiguration configuration;
	
	private Player player;
	
	private GameCamera camera;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleContext(Collection<Subsystem> subsystems, GameContainer container, Player player, GameCamera camera, ContextConfiguration configuration) {
		this.subsystems = subsystems;
		this.container = container;
		this.configuration = configuration;
		this.camera = camera;
		this.player = player;
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
	 * @see de.myreality.galacticum.core.context.Context#getSubsystems()
	 */
	@Override
	public Collection<Subsystem> getSubsystems() {
		return subsystems;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.context.Context#getContainer()
	 */
	@Override
	public GameContainer getContainer() {
		return container;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.context.Context#getConfiguration()
	 */
	@Override
	public ContextConfiguration getConfiguration() {
		return configuration;
	}


	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.context.Context#getPlayer()
	 */
	@Override
	public Player getPlayer() {
		return player;
	}


	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.context.Context#getCamera()
	 */
	@Override
	public GameCamera getCamera() {
		return camera;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
