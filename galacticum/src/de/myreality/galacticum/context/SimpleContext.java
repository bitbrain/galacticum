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
package de.myreality.galacticum.context;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.galacticum.core.World;
import de.myreality.galacticum.graphics.GameCamera;
import de.myreality.galacticum.io.ContextConfiguration;
import de.myreality.galacticum.modules.Module;
import de.myreality.galacticum.modules.ModuleList;
import de.myreality.galacticum.player.Player;

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
	
	private Stack<Module> subsystemStack;
	
	private ModuleList subsystems;
	
	private World container;
	
	private ContextConfiguration configuration;
	
	private Player player;
	
	private GameCamera camera;
	
	private SpriteBatch batch;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleContext(ModuleList subsystems, World container, Player player, GameCamera camera, SpriteBatch batch, ContextConfiguration configuration) {
		this.subsystems = subsystems;
		this.container = container;
		this.configuration = configuration;
		this.camera = camera;
		this.player = player;
		this.batch = batch;
		
		subsystemStack = new Stack<Module>();
		
		for (Module system : subsystems) {
			subsystemStack.push(system);
		}
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
	public Stack<Module> getSubsystems() {
		return subsystemStack;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.context.Context#getContainer()
	 */
	@Override
	public World getWorld() {
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


	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.context.Context#getSpriteBatch()
	 */
	@Override
	public SpriteBatch getSpriteBatch() {
		return batch;
	}


	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.context.Context#getSubsystem(java.lang.Class)
	 */
	@Override
	public <Type extends Module> Type getSubsystem(Class<Type> subsystemClass) {
		return subsystems.get(subsystemClass);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
