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
package de.myreality.galacticum.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.galacticum.context.Context;
import de.myreality.galacticum.modules.Module;
import de.myreality.galacticum.modules.ModuleException;
import de.myreality.galacticum.player.PlayerModule;
import de.myreality.galacticum.util.Updateable;

/**
 * Is responsible for providing a camera
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class CameraModule implements Module, Updateable {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private GameCamera camera;

	private float viewportWidth;

	private float viewportHeight;
	
	private SpriteBatch batch;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public GameCamera getCamera() {
		return camera;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Nameable#getName()
	 */
	@Override
	public String getName() {
		return "Camera system";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#start()
	 */
	@Override
	public void load(Context context) throws ModuleException {		
		
		PlayerModule playerModule = context.getModule(PlayerModule.class);

		this.viewportWidth = Gdx.graphics.getWidth();
		this.viewportHeight = Gdx.graphics.getHeight();
		camera = new SimpleGameCamera(viewportWidth, viewportHeight);
		this.batch = context.getSpriteBatch();

		if (viewportWidth <= 0 || viewportHeight <= 0) {
			throw new ModuleException("Viewport of " + viewportWidth + "x"
					+ viewportHeight + " is not allowed.");
		}		
		
		camera.focus(playerModule.getPlayer().getCurrentShip());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#update(float)
	 */
	@Override
	public void update(float delta) {		
		camera.update(delta, batch);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#shutdown()
	 */
	@Override
	public void dispose() {
		camera = null;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
