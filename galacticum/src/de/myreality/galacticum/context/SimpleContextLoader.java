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

import java.util.ArrayList;
import java.util.List;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.galacticum.core.World;
import de.myreality.galacticum.graphics.CameraModule;
import de.myreality.galacticum.graphics.GameCamera;
import de.myreality.galacticum.io.ContextConfiguration;
import de.myreality.galacticum.modules.Module;
import de.myreality.galacticum.modules.ModuleException;
import de.myreality.galacticum.modules.ModuleList;
import de.myreality.galacticum.modules.ProgressListener;
import de.myreality.galacticum.player.Player;
import de.myreality.galacticum.player.PlayerModule;
import de.myreality.galacticum.util.Nameable;

/**
 * Simple implementation of {@see ContextLoader}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SimpleContextLoader implements ContextLoader {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private ModuleList subsystems;

	private ContextListenerController listenerController;

	private SubsystemListener subsystemListener;

	private int currentIndex;

	private Player player;
	
	private GameCamera camera;
	
	private SpriteBatch batch;
	
	private TweenManager tweenManager;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SimpleContextLoader(SpriteBatch batch, TweenManager tweenManager, ModuleList subsystems) {
		listenerController = new ContextListenerController();
		this.subsystems = subsystems;
		subsystemListener = new SubsystemListener(this);
		this.batch = batch;
		this.tweenManager = tweenManager;
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
	 * @see
	 * de.myreality.galacticum.core.ContextLoader#create(de.myreality.galacticum
	 * .io.ContextConfiguration)
	 */
	@Override
	public Context load(ContextConfiguration configuration,
			World world) throws ContextException {
		listenerController.onStart(new SimpleContextEvent(this, 0, subsystems
				.size(), 0.0f));
		loadSubsystems(configuration, world);
		listenerController.onSuccess(new SimpleContextEvent(this, subsystems
				.size(), subsystems.size(), 1.0f));

		return new SimpleContext(subsystems, world, player, camera, batch, tweenManager, configuration);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.ContextLoader#addListener(de.myreality.
	 * galacticum.core.ContextListener)
	 */
	@Override
	public void addListener(ContextListener listener) {
		listenerController.addListener(listener);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void loadSubsystems(
			ContextConfiguration configuration, World world) throws ContextException {

		int index = 0;
		
		for (Module system : subsystems) {
			world.addListener(system);
		}
		
		Gdx.app.log("LOAD", "Loading subsystems..");

		for (Module system : subsystems) {
			
			Gdx.app.log("LOAD", "Load " + system.getName() + " system..");
			
			currentIndex = index;
			system.addProgressListener(subsystemListener);
			SimpleContextEvent event = new SimpleContextEvent(this, index,
					subsystems.size(), (float) index
							/ (float) subsystems.size());
			listenerController.onLoad(event, system);
			startSubsystem(system, event);
			system.removeProgressListener(subsystemListener);

			loadPlayer(system);
			loadCamera(system);
			
			Gdx.app.log("LOAD", "Success!");
		}
	}

	private void startSubsystem(Module system, ContextEvent event)
			throws ContextException {

		try {
			system.start();
		} catch (ModuleException e) {
			listenerController.onFail(event, e);
			throw new ContextException(e);
		}
	}

	private void loadPlayer(Module system) {
		// Fetch the current player if available
		if (system instanceof PlayerModule) {
			player = ((PlayerModule) system).getPlayer();
		}
	}
	
	private void loadCamera(Module system) {
		// Fetch the current player if available
		if (system instanceof CameraModule) {
			camera = ((CameraModule) system).getCamera();
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	private class ContextListenerController implements ContextListener {

		private List<ContextListener> listeners;

		public ContextListenerController() {
			listeners = new ArrayList<ContextListener>();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * de.myreality.galacticum.core.context.ContextListener#onStart(de.myreality
		 * .galacticum.core.context.ContextEvent)
		 */
		@Override
		public void onStart(ContextEvent event) {
			for (ContextListener l : listeners) {
				l.onStart(event);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * de.myreality.galacticum.core.context.ContextListener#onSuccess(de
		 * .myreality.galacticum.core.context.ContextEvent)
		 */
		@Override
		public void onSuccess(ContextEvent event) {
			for (ContextListener l : listeners) {
				l.onSuccess(event);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * de.myreality.galacticum.core.context.ContextListener#onFail(de.myreality
		 * .galacticum.core.context.ContextEvent, java.lang.Throwable)
		 */
		@Override
		public void onFail(ContextEvent event, Throwable error) {
			for (ContextListener l : listeners) {
				l.onFail(event, error);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * de.myreality.galacticum.core.context.ContextListener#onLoad(de.myreality
		 * .galacticum.core.context.ContextEvent,
		 * de.myreality.galacticum.util.Nameable)
		 */
		@Override
		public void onLoad(ContextEvent event, Nameable target) {
			for (ContextListener l : listeners) {
				l.onLoad(event, target);
			}
		}

		public void addListener(ContextListener listener) {
			listeners.add(listener);
		}

	}

	private class SubsystemListener implements ProgressListener {

		private ContextLoader parent;

		public SubsystemListener(ContextLoader parent) {
			this.parent = parent;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * de.myreality.galacticum.core.subsystem.ProgressListener#onProgress
		 * (float, int, int)
		 */
		@Override
		public void onProgress(float progress, int current, int total,
				Module sender) {

			progress /= (float) subsystems.size();
			progress += (float) currentIndex / (float) subsystems.size();

			ContextEvent event = new SimpleContextEvent(parent, current, total,
					progress);
			listenerController.onLoad(event, sender);
		}

	}

}
