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

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;

import de.myreality.galacticum.player.Player;
import de.myreality.galacticum.screens.IngameScreen;
import de.myreality.galacticum.util.Moveable;

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
	
	private Touchpad touchpad;

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
		
		boolean runsOnAndroid = Gdx.app.getType().equals(ApplicationType.Android);
		boolean runsOniOS = Gdx.app.getType().equals(ApplicationType.iOS);
		
		if (runsOnAndroid || runsOniOS) {			 
			TouchpadStyle style = new TouchpadStyle();
			touchpad = new Touchpad(8, style);
			touchpad.setBounds(30, 30, 220, 220);
			addActor(touchpad);
		}
		
		Gdx.input.setCatchBackKey(true);
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
		case Keys.ESCAPE: case Keys.BACK: 
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
		Moveable target = player.getCurrentShip();
		float speed = 500f;
		if (Gdx.input.isKeyPressed(DefaultControls.PLAYER_MOVE_UP)) {			
			target.move(0, -speed);
		}

		if (Gdx.input.isKeyPressed(DefaultControls.PLAYER_MOVE_LEFT)) {
			target.move(-speed, 0);
		}
		if (Gdx.input.isKeyPressed(DefaultControls.PLAYER_MOVE_DOWN)) {
			target.move(0, speed);
		}
		if (Gdx.input.isKeyPressed(DefaultControls.PLAYER_MOVE_RIGHT)) {
			target.move(speed, 0f);
		}
		
		if (touchpad != null) {
			//target.setX(target.getX() + touchpad.getKnobPercentX() * speed);
			//target.setY(target.getY() - touchpad.getKnobPercentY() * speed);
		}
		
		if (Gdx.input.isKeyPressed(Keys.F5)) {
			screen.getContext().getCamera().shake(25f, 2500);
		}
		

		
		if (Gdx.input.isKeyPressed(Keys.F6)) {
			screen.getContext().getCamera().zoom(-0.01f);
		}
		
		if (Gdx.input.isKeyPressed(Keys.F7)) {
			screen.getContext().getCamera().zoom(0.01f);
		}
	}
	
	

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
