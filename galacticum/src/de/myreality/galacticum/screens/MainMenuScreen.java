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

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.Resources;
import de.myreality.galacticum.tweens.ActorTween;

/**
 * Main menu of Galacticum
 * 
 * @author Miguel Gonzalez
 * @since 0.1
 * @version 0.1
 */
public class MainMenuScreen extends MenuScreen {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Image logo;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * @param game
	 */
	public MainMenuScreen(GalacticumGame game) {
		super(game);
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
	 * de.myreality.galacticum.screens.MenuScreen#onCreateUI(com.badlogic.gdx
	 * .scenes.scene2d.Stage)
	 */
	@Override
	protected void onCreateUI(Stage stage) {
		
		// Create the logo
		logo = new Image(Resources.TEXTURE_LOGO);	
		stage.addActor(logo);
		
		fadeBackground(0f, 1f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.screens.MenuScreen#onResizeUI(int, int)
	 */
	@Override
	protected void onResizeUI(int width, int height) {

		float logoWidth = getWidth() - getWidth() * PADDING;
		float logoHeight = logo.getPrefHeight() * (logoWidth / (float)logo.getPrefWidth());
		
		float logoX = getWidth() / 2 - logoWidth / 2;
		float logoY = getHeight() - logoHeight - getHeight() * PADDING;
		
		logo.setBounds(logoX, logoHeight + getHeight(), logoWidth, logoHeight);
		
		TweenManager tweenManager = getTweenManager();
		
		Tween.to(logo, ActorTween.POS_Y, 1.3f)
			 .target(logoY)
			 .setCallbackTriggers(TweenCallback.COMPLETE)
			 .setCallback(new TweenCallback() {

				@Override
				public void onEvent(int type, BaseTween<?> source) {
					animateLogo();
				}
				 
			 })
			 .ease(TweenEquations.easeInOutElastic)
			 .start(tweenManager);
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
		
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private void animateLogo() {
		Tween.to(logo, ActorTween.ALPHA, 10f)
		     .target(0.7f)
		     .repeatYoyo(Tween.INFINITY, 0)
		     .setCallbackTriggers(TweenCallback.COMPLETE)
		     .ease(TweenEquations.easeInOutCubic)
		     .start(getTweenManager());
		Tween.to(logo, ActorTween.POS_Y, 5f)
	     .target(Gdx.graphics.getHeight() - logo.getHeight() * 1.5f)
	     .repeatYoyo(Tween.INFINITY, 0)
	     .ease(TweenEquations.easeInOutCubic)
	     .start(getTweenManager());
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
