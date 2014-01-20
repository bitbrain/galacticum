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
package de.myreality.galacticum.zones;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import de.myreality.galacticum.tweens.GameColorTween;
import de.myreality.galacticum.util.ColorProvider;
import de.myreality.galacticum.util.GameColor;
import de.myreality.galacticum.util.MathUtils;
import de.myreality.galacticum.zones.ZoneHandler.ZoneListener;

/**
 * Provides colors for the current zone
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class ZoneColorProvider implements ZoneListener, ColorProvider {

	// ===========================================================
	// Constants
	// ===========================================================
	
	public static final float FADE_TIME = 3f;

	// ===========================================================
	// Fields
	// ===========================================================
	
	private GameColor color;
	
	private TweenManager tweenManager;

	private GameColor targetColor;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ZoneColorProvider(TweenManager tweenManager) {
		this.tweenManager = tweenManager;
		color = new GameColor(1f, 1f, 1f, 1f);
		targetColor = new GameColor(1f, 1f, 1f, 1f);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.ColorProvider#getColor()
	 */
	@Override
	public GameColor getColor() {
		return color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.zones.ZoneHandler.ZoneListener#onEnterZone(long,
	 * de.myreality.galacticum.zones.ZoneTarget)
	 */
	@Override
	public void onEnterZone(long hash, ZoneTarget target) {
		
		targetColor = generateTargetColor(hash);
		
		tweenManager.killTarget(color);
		
		Tween.to(color, GameColorTween.R, FADE_TIME)
			.target(targetColor.g)
			.ease(TweenEquations.easeInOutSine)
			.start(tweenManager);
		Tween.to(color, GameColorTween.G, FADE_TIME)
			.target(targetColor.g)
			.ease(TweenEquations.easeInOutSine)
			.start(tweenManager);		
		Tween.to(color, GameColorTween.B, FADE_TIME)
			.target(targetColor.b)
			.ease(TweenEquations.easeInOutSine)
			.start(tweenManager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.zones.ZoneHandler.ZoneListener#onLeaveZone(long,
	 * de.myreality.galacticum.zones.ZoneTarget)
	 */
	@Override
	public void onLeaveZone(long hash, ZoneTarget target) {
		// TODO Auto-generated method stub

	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private GameColor generateTargetColor(long hash) {
		return new GameColor(getSeedRed(hash), getSeedGreen(hash), getSeedBlue(hash), 1f);
	}

	private float getSeedRed(long hash) {
		return MathUtils.getValue(0f, 0.4f, (long) Math.pow(hash, 3), 2);
	}

	private float getSeedGreen(long hash) {
		return MathUtils.getValue(0f, 0.4f, hash * 100, 3);
	}

	private float getSeedBlue(long hash) {
		return MathUtils.getValue(0f, 0.4f, hash * 50, 5);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
