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

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Provides tween functionality for GUI actors
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class ActorTween implements TweenAccessor<Actor> {

	// ===========================================================
	// Constants
	// ===========================================================
	
	public static final int ALPHA = 1;
	
	public static final int SCALE_X = 2;
	
	public static final int SCALE_Y = 3;
	
	public static final int POS_X = 4;
	
	public static final int POS_Y = 5;

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
	public int getValues(Actor target, int tweenType, float[] returnValues) {
		
		switch (tweenType) {
			case ALPHA:
				returnValues[0] = target.getColor().a;
				return 1;
			case SCALE_X:
				returnValues[0] = target.getScaleX();
				return 1;
			case SCALE_Y:
				returnValues[0] = target.getScaleY();
				return 1;
			case POS_X:
				returnValues[0] = target.getX();
				return 1;
			case POS_Y:
				returnValues[0] = target.getY();
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
	public void setValues(Actor target, int tweenType, float[] newValues) {
		switch (tweenType) {
			case ALPHA:
				target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]);
				break;
			case SCALE_X: case SCALE_Y:
				target.scale(newValues[0], newValues[0]);
				break;
			case POS_X:
				target.setX(newValues[0]);
				break;
			case POS_Y:
				target.setY(newValues[0]);
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
