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

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Stage which delegates general control functionality
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class GeneralStage extends Stage {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private InputProcessor controls;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * 
	 */
	public GeneralStage() {
		super();
		initControls();
	}

	/**
	 * @param width
	 * @param height
	 * @param keepAspectRatio
	 * @param batch
	 */
	public GeneralStage(float width, float height, boolean keepAspectRatio,
			SpriteBatch batch) {
		super(width, height, keepAspectRatio, batch);
		initControls();
	}

	/**
	 * @param width
	 * @param height
	 * @param keepAspectRatio
	 */
	public GeneralStage(float width, float height, boolean keepAspectRatio) {
		super(width, height, keepAspectRatio);
		initControls();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return super.touchDown(screenX, screenY, pointer, button) ^
			   controls.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return super.touchDragged(screenX, screenY, pointer) ^
				controls.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return super.touchUp(screenX, screenY, pointer, button) ^
				controls.touchUp(screenX, screenY, pointer, button);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return super.mouseMoved(screenX, screenY) ^
				controls.mouseMoved(screenX, screenY);
	}

	@Override
	public boolean scrolled(int amount) {
		return super.scrolled(amount) ^
				controls.scrolled(amount);
	}

	@Override
	public boolean keyDown(int keyCode) {
		return super.keyDown(keyCode) ^
				controls.keyDown(keyCode);
	}

	@Override
	public boolean keyUp(int keyCode) {
		return super.keyUp(keyCode) ^
				controls.keyUp(keyCode);
	}

	@Override
	public boolean keyTyped(char character) {
		return super.keyTyped(character) ^
				controls.keyTyped(character);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private void initControls() {
		this.controls = new GeneralControls();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
