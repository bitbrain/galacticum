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
package de.myreality.galacticum.core.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.chunx.ChunkTarget;
import de.myreality.galacticum.util.IDProvider;

/**
 * Represents a simple entity in a game
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public interface Entity extends IDProvider, Shape, ChunkTarget {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * 
	 * @return
	 */
	float getX();
	
	/**
	 * 
	 * @return
	 */
	float getY();
	
	/**
	 * 
	 * @return
	 */
	float getWidth();
	
	/**
	 * 
	 * @return
	 */
	float getHeight();
	
	/**
	 * 
	 * 
	 * @return
	 */
	EntityType getType();
	
	/**
	 * 
	 * 
	 * @param delta
	 * @param batch
	 */
	void draw(SpriteBatch batch);
	
	/**
	 * 
	 * 
	 * @param delta
	 */
	void update(float delta);

}
