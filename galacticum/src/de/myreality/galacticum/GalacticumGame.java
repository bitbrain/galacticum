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
package de.myreality.galacticum;

import java.io.IOException;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;

import de.myreality.galacticum.screens.CreationScreen;
import de.myreality.galacticum.tweens.ActorTween;

/**
 * Main game class which provides game functionality
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class GalacticumGame extends Game {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 */
	@Override
	public void create() {
		
		Resources.unloadTextures();
		Resources.unloadTextures();
		
		Resources.loadTextures();
		Resources.loadFonts();
		Resources.loadStyles();
		try {
			Resources.loadMetaData();
			initTweenEngine();			
			setScreen(new CreationScreen(this));			
			MetaData data = Resources.META_DATA;
			Gdx.graphics.setTitle(data.getName() + " " + data.getVersion() + data.getPhase());
		} catch (IOException e) {
			e.printStackTrace();
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		Resources.unloadTextures();
		Resources.unloadFonts();
	}
	
	

	// ===========================================================
	// Methods
	// ===========================================================
	
	private void initTweenEngine() {
		Tween.registerAccessor(Actor.class, new ActorTween());
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
