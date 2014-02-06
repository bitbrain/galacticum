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
package de.myreality.galacticum.tweens;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.Color;

/**
 * Provides tween functionality for GUI actors
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class ColorTween implements TweenAccessor<Color> {

	// ===========================================================
	// Constants
	// ===========================================================
	
	public static final int R = 1;

	public static final int G = 2;
	
	public static final int B = 3;
	
	public static final int A = 4;
	
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see aurelienribon.tweenengine.TweenAccessor#getValues(java.lang.Object,
	 * int, float[])
	 */
	@Override
	public int getValues(Color target, int tweenType, float[] returnValues) {
		
		switch (tweenType) {
			case R:
				returnValues[0] = target.r;
				return 1;
			case G:
				returnValues[0] = target.g;
				return 1;
			case B:
				returnValues[0] = target.b;
				return 1;
			case A:
				returnValues[0] = target.a;
				return 1;
		}
		
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aurelienribon.tweenengine.TweenAccessor#setValues(java.lang.Object,
	 * int, float[])
	 */
	@Override
	public void setValues(Color target, int tweenType, float[] newValues) {
		switch (tweenType) {
			case R:
				target.r = newValues[0];
				break;
			case G:
				target.g = newValues[0];
				break;
			case B:
				target.b = newValues[0];
				break;
			case A:
				target.a = newValues[0];
				break;
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
