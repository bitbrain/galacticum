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
package de.myreality.galacticum.entities;

import java.io.Externalizable;

import de.myreality.chunx.ChunkTarget;
import de.myreality.galacticum.entities.Shape.ShapeListener;
import de.myreality.galacticum.graphics.GameCamera;
import de.myreality.galacticum.util.Observer;

/**
 * Provides collision detection and basic 2D layout
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public interface Shape extends Externalizable, ChunkTarget, Observer<ShapeListener> {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * 
	 * 
	 * @return
	 */
	float getX();
	
	/**
	 * 
	 * 
	 * @return
	 */
	float getY();
	
	/**
	 * 
	 * 
	 * @param x
	 */
	void setX(float x);
	
	/**
	 * 
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
	 * 
	 * @param other
	 * @return
	 */
	boolean collidesWith(Shape other);
	
	/**
	 * 
	 * 
	 * @param camera
	 * @return
	 */
	boolean isOnScreen(GameCamera camera);
	
	/**
	 * 
	 * 
	 * @param rotation
	 */
	void setRotation(float rotation);
	
	/**
	 * 
	 * 
	 * @return
	 */
	float getRotation();
	
	
	public static interface ShapeListener {
		
		void onSetX(Shape shape);
		
		void onSetY(Shape shape);
		
		void onSetRotation(Shape shape);
		
		void onMove(float x, float y, Shape shape);
	}

}
