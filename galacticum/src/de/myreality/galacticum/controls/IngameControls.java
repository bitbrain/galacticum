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

import com.badlogic.gdx.Input.Keys;

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
		
		if (keyCode == Keys.ESCAPE) {
			screen.leave();
			return true;
		}

		return false;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
