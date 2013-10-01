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
package de.myreality.galacticum.screens;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.core.GameContainerFactory;
import de.myreality.galacticum.core.context.Context;
import de.myreality.galacticum.core.context.ContextLoader;
import de.myreality.galacticum.core.context.SimpleContextLoader;
import de.myreality.galacticum.io.ConfigurationManager;
import de.myreality.galacticum.io.ContextConfiguration;
import de.myreality.galacticum.io.ContextNotFoundException;
import de.myreality.galacticum.io.SharedConfigurationManager;

/**
 * Screen which displays the loading progress of current universe creation.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class LoadingScreen extends MenuScreen {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private TweenManager tweenManager;
	
	private ContextLoader contextLoader;
	
	private ContextConfiguration configuration;
	
	private Future<?> loadingFuture;
	
	private GameLoader loader;

	private GameContainerFactory containerFactory;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public LoadingScreen(String caption, GalacticumGame game, ContextConfiguration configuration) throws ContextNotFoundException {
		super(caption, game);		
		this.contextLoader = new SimpleContextLoader();
		containerFactory = null; // TODO
		this.configuration = configuration;
		
		ConfigurationManager configurationManager = SharedConfigurationManager.getInstance();
		
		if (!configurationManager.hasContext(configuration.getID())) {
			throw new ContextNotFoundException("Context with ID=" + configuration.getID() + " does not exist");
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
	 * @see de.myreality.galacticum.screens.MenuScreen#onCreateUI()
	 */
	@Override
	protected void onCreateUI(Stage stage) {
		tweenManager = new TweenManager();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.screens.MenuScreen#onResizeUI(int, int)
	 */
	@Override
	protected void onResizeUI(int width, int height) {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.screens.MenuScreen#onDraw(com.badlogic.gdx.graphics
	 * .g2d.SpriteBatch, float)
	 */
	@Override
	protected void onDraw(SpriteBatch batch, float delta) {
		tweenManager.update(delta);
		
		if (loadingFuture.isCancelled()) {
			// Go back to creation screen
			getGame().setScreen(new CreationScreen("Create new universe", getGame()));
		} else if (loadingFuture.isDone()) {
			// Loading is done, go to the next screen
			getGame().setScreen(new IngameScreen(getGame(), loader.getContext()));
		}
	}
	
	@Override
	public void show() {		
		super.show();
		
		loader = new GameLoader(contextLoader, containerFactory);
		
		// Load the game
		ExecutorService executor = Executors.newFixedThreadPool(1);
		loadingFuture = executor.submit(loader);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	private class GameLoader implements Runnable {
		
		private Context context;
		
		private ContextLoader contextFactory;
		
		private GameContainerFactory gameFactory;
		
		public GameLoader(ContextLoader contextFactory, GameContainerFactory gameFactory) {
			this.contextFactory = contextFactory;
			this.gameFactory = gameFactory;
		}

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {							
			context = contextFactory.create(configuration, gameFactory);
		}
		
		public Context getContext() {
			return context;
		}
		
	}

}
