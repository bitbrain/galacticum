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
package de.myreality.galacticum.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import de.myreality.galacticum.core.entities.Entity;
import de.myreality.galacticum.core.player.Player;
import de.myreality.galacticum.screens.IngameScreen;

/**
 * Controls for ingame functionality
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class IngameControls extends GeneralStage {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private IngameScreen screen;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * @param width
	 * @param height
	 * @param keepAspectRatio
	 */
	public IngameControls(float width, float height, boolean keepAspectRatio,
			IngameScreen screen) {
		super(width, height, keepAspectRatio);
		this.screen = screen;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public boolean keyDown(int keyCode) {

		boolean state = super.keyDown(keyCode);

		switch (keyCode) {
		case Keys.ESCAPE:
			screen.leave();
			return true;
		case Keys.F3:
			screen.setDebugEnabled(!screen.isDebugEnabled());
			return true;
		}

		return state;
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		// Control via W,A,S,D
		Player player = screen.getContext().getPlayer();
		Entity target = player.getCurrentShip();
		float speed = 5;
		if (Gdx.input.isKeyPressed(DefaultControls.PLAYER_MOVE_UP)) {
			target.setY(target.getY() - speed);
		}

		if (Gdx.input.isKeyPressed(DefaultControls.PLAYER_MOVE_LEFT)) {
			target.setX(target.getX() - speed);
		}
		if (Gdx.input.isKeyPressed(DefaultControls.PLAYER_MOVE_DOWN)) {
			target.setY(target.getY() + speed);
		}
		if (Gdx.input.isKeyPressed(DefaultControls.PLAYER_MOVE_RIGHT)) {
			target.setX(target.getX() + speed);
		}
	}
	
	

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
