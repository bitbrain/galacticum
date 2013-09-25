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

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.io.ConfigurationManager;
import de.myreality.galacticum.io.ContextNotFoundException;

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
	
	private ConfigurationManager configurationManager;
	
	private String contextID;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public LoadingScreen(String caption, GalacticumGame game, String contextID, ConfigurationManager configurationManager) throws ContextNotFoundException {
		super(caption, game);		
		this.configurationManager = configurationManager;
		this.contextID = contextID;
		
		if (!configurationManager.hasContext(contextID)) {
			throw new ContextNotFoundException("Context with ID=" + contextID + " does not exist");
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
	}
	
	@Override
	public void show() {		
		super.show();
		
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
