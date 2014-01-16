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
package de.myreality.galacticum.chunks;

import de.myreality.chunx.ChunkTarget;
import de.myreality.galacticum.entities.SpaceShip;
import de.myreality.galacticum.graphics.GameCamera;
import de.myreality.galacticum.player.Player;
import de.myreality.galacticum.player.PlayerListener;
import de.myreality.galacticum.player.SimplePlayerListener;

/**
 * Maps camera position to chunx in order to update the chunk system properly
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class ChunkTargetAdapter extends SimplePlayerListener implements ChunkTarget, PlayerListener {

	
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 1L;

	// ===========================================================
	// Fields
	// ===========================================================
	
	private GameCamera camera;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ChunkTargetAdapter(GameCamera camera) {
		this.camera = camera;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Positionable#getX()
	 */
	@Override
	public float getX() {
		return camera.getX();
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Positionable#getY()
	 */
	@Override
	public float getY() {
		return camera.getY();
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Positionable#setX(float)
	 */
	@Override
	public void setX(float x) {
		camera.setX(x);
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Positionable#setY(float)
	 */
	@Override
	public void setY(float y) {
		camera.setY(y);
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.player.PlayerListener#onSetCurrentShip(de.myreality.galacticum.core.entities.SpaceShip, de.myreality.galacticum.core.entities.SpaceShip, de.myreality.galacticum.core.player.Player)
	 */
	@Override
	public void onSetCurrentShip(SpaceShip oldShip, SpaceShip newShip,
			Player player) {
		camera.focus(newShip);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
