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
package de.myreality.galacticum.util;

import java.io.Serializable;

import com.badlogic.gdx.graphics.Color;

/**
 * 
 *
 * @author miguel
 * @since
 * @version
 */
public class SerializableColor extends Color implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ===========================================================
	// Constants
	// ===========================================================

	/**
	 * 
	 */
	public SerializableColor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param color
	 */
	public SerializableColor(Color color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 */
	public SerializableColor(float r, float g, float b, float a) {
		super(r, g, b, a);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param rgba8888
	 */
	public SerializableColor(int rgba8888) {
		super(rgba8888);
		// TODO Auto-generated constructor stub
	}

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	
	

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
