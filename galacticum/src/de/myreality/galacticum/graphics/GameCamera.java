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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import de.myreality.galacticum.core.entities.Entity;
import de.myreality.galacticum.core.entities.Shape;
import de.myreality.galacticum.util.Shakeable;
import de.myreality.galacticum.util.Zoomable;

/**
 * Is provided for contexts in order manipulate the current camera
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public interface GameCamera extends Shape, Shakeable, Zoomable {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Tracks a new entity
	 * 
	 * @param entity new entity to track
	 */
	void focus(Entity entity);
	
	/**
	 * Sets a new position
	 * 
	 * @param x new x position
	 * @param y new y position
	 */
	void setPosition(float x, float y);
	
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
	 * @param x
	 */
	void setX(float x);
	
	/**
	 * 
	 * @param y
	 */
	void setY(float y);
	
	
	/**
	 * 
	 * 
	 * @return
	 */
	float getWidth();
	
	/**
	 * 
	 * 
	 * @return
	 */
	float getHeight();
	
	/**
	 * 
	 * @return
	 */
	Matrix4 getCombinedMatrix();
	
	/**
	 * Updates the camera
	 * 
	 * @param delta time delta
	 * @param batch sprite batch
	 */
	void update(float delta, SpriteBatch batch);
}
