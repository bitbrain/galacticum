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
package de.myreality.galacticum.core;

import java.io.Serializable;

import de.myreality.chunx.ChunkTarget;
import de.myreality.galacticum.core.chunks.ContentTarget;
import de.myreality.galacticum.core.entities.Entity;
import de.myreality.galacticum.util.GameColor;

/**
 * Game light representation including types
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public interface GameLight extends ChunkTarget, ContentTarget {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	Entity getOwner();
	
	GameColor getColor();
	
	float getRadius();
	
	int getNumberOfRays();	
	
	GameLightType getType();
	
	public static enum GameLightType implements Serializable {
		
		POINT,
		CONE,
		DIRECTIONAL
	}

}
