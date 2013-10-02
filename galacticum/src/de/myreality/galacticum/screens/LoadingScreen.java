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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.myreality.chunx.ChunkTarget;
import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.core.GameContainer;
import de.myreality.galacticum.core.SimpleGameContainer;
import de.myreality.galacticum.core.chunks.ChunkSystemFactory;
import de.myreality.galacticum.core.chunks.ContentProviderAdapter;
import de.myreality.galacticum.core.context.Context;
import de.myreality.galacticum.core.context.ContextException;
import de.myreality.galacticum.core.context.ContextLoader;
import de.myreality.galacticum.core.context.SimpleContextLoader;
import de.myreality.galacticum.io.ConfigurationManager;
import de.myreality.galacticum.io.ContextConfiguration;
import de.myreality.galacticum.io.ContextNotFoundException;
import de.myreality.galacticum.io.SharedConfigurationManager;
import de.myreality.galacticum.ui.LoadingBox;

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

	private GameContainer container;
	
	private LoadingBox box;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public LoadingScreen(String caption, GalacticumGame game, ContextConfiguration configuration) throws ContextNotFoundException {
		super(caption, game);		
		
		// Game setup
		this.contextLoader = new SimpleContextLoader();
		this.container = new SimpleGameContainer();
		this.configuration = configuration;
		
		// Add factory for loading chunks
		contextLoader.addFactory(new ChunkSystemFactory(new ChunkTarget() {

			private static final long serialVersionUID = 1L;

			@Override
			public float getX() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public float getY() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void setX(float arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setY(float arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}, new ContentProviderAdapter(container)));
		
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
		box = new LoadingBox();
		contextLoader.addListener(box);
		stage.addActor(box);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.screens.MenuScreen#onResizeUI(int, int)
	 */
	@Override
	protected void onResizeUI(int width, int height) {
		box.setX(Gdx.graphics.getWidth() / 2f - box.getWidth() /  2f);
		box.setY(Gdx.graphics.getHeight() / 2f - box.getHeight() /  2f);
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
		
		if (loadingFuture == null || loadingFuture.isCancelled()) {
			// Go back to creation screen
			getGame().setScreen(new CreationScreen("Create new universe", getGame(), loader.getMessage()));
		} else if (loadingFuture.isDone()) {
			// Loading is done, go to the next screen
			//getGame().setScreen(new IngameScreen(getGame(), loader.getContext()));
		}
	}
	
	@Override
	public void show() {		
		super.show();
		
		loader = new GameLoader(contextLoader, container);
		
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
		
		private ContextLoader contextLoader;
		
		private GameContainer container;
		
		private String message;
		
		public GameLoader(ContextLoader contextLoader, GameContainer container) {
			this.contextLoader = contextLoader;
			this.container = container;
			message = "";
		}
		
		public void load() {
			try {
				context = contextLoader.load(configuration, container);
			} catch (ContextException e) {
				message = e.getMessage();
				loadingFuture.cancel(true);
			}
		}

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {	
			load();
		}
		
		public Context getContext() {
			return context;
		}
		
		public String getMessage() {
			return message;
		}
		
	}

}
