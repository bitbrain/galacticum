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

import java.util.Collection;
import java.util.Stack;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.galacticum.core.World;
import de.myreality.galacticum.core.WorldModule;
import de.myreality.galacticum.entities.Entity;
import de.myreality.galacticum.entities.EntityScreenDetector;
import de.myreality.galacticum.graphics.GameCamera;
import de.myreality.galacticum.io.ContextConfiguration;
import de.myreality.galacticum.modules.Module;
import de.myreality.galacticum.modules.ModuleException;
import de.myreality.galacticum.modules.ModuleList;
import de.myreality.galacticum.player.Player;
import de.myreality.galacticum.player.PlayerProvider;
import de.myreality.galacticum.util.Updateable;

/**
 * Simple implementation of {@see Context}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
class SimpleContext implements Context, Updateable, PlayerProvider {

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

	private TweenManager tweenManager;
	
	private EntityScreenDetector entityScreenDetector;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleContext(World container, Player player, GameCamera camera, SpriteBatch batch, TweenManager tweenManager, ContextConfiguration configuration) {
		this.subsystems = new ModuleList();
		subsystemStack = new Stack<Module>();	
		this.container = container;
		this.configuration = configuration;
		this.camera = camera;
		this.player = player;
		this.batch = batch;
		this.tweenManager = tweenManager;
		this.entityScreenDetector = new EntityScreenDetector(this);
	}
	

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public void addModule(Module module) {
		subsystems.add(module);
		subsystemStack.push(module);
		
		// Register our module
		if (module instanceof WorldModule) {
			((WorldModule)module).addListener(entityScreenDetector);
		}
	}
	
	@Override
	public void setPlayer(Player player) {
		this.player = player;
	}


	public void setCamera(GameCamera camera) {
		this.camera = camera;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


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
	public <Type extends Module> Type getModule(Class<Type> subsystemClass) throws ModuleException {
		Type type = subsystems.get(subsystemClass);
		
		if (type != null) {
			return type;
		} else {
			throw new ModuleException("Module of class " + subsystemClass + " not found in context");
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.context.Context#getTweenManager()
	 */
	@Override
	public TweenManager getTweenManager() {
		return tweenManager;
	}


	/* (non-Javadoc)
	 * @see de.myreality.galacticum.context.Context#getVisibleEntities()
	 */
	@Override
	public Collection<Entity> getVisibleEntities() {
		return entityScreenDetector.getVisibleEntities();
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Updateable#update(float)
	 */
	@Override
	public void update(float delta) {
		for (Module system : subsystems) {
			if (system instanceof Updateable) {
				((Updateable)system).update(delta);
			}
		}
		entityScreenDetector.clear();
	}


	/* (non-Javadoc)
	 * @see de.myreality.galacticum.context.Context#dispose()
	 */
	@Override
	public void dispose() {		
		// Free the last element first
		while (!subsystemStack.isEmpty()) {
			Module system = subsystemStack.pop();
			system.dispose();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
