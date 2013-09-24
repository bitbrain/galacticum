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
package de.myreality.galacticum.ui;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import de.myreality.galacticum.Resources;
import de.myreality.galacticum.tweens.ActorTween;

/**
 * 
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class ErrorLabel extends Label {
	
	// ===========================================================
	// Constants
	// ===========================================================
	
	public static final float DEFAULT_DELAY = 2f;
	
	public static final float DEFAULT_FADE = 1f;

	// ===========================================================
	// Fields
	// ===========================================================
	
	private TweenManager manager;
	
	private float delay, fade;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ErrorLabel(TweenManager manager) {
		super(" ", Resources.STYLE_LABEL_ERROR);
		this.manager = manager;
		fade = DEFAULT_FADE;
		delay = DEFAULT_DELAY;		
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	void setDelay(float delay) {
		this.delay = delay;
	}
	
	void setFade(float fade) {
		this.fade = fade;
	}
	
	
	
	@Override
	public void setText(CharSequence text) {
		super.setText(text);
		
		getColor().a = 1f;
		
		manager.killTarget(this);
		
		Tween.to(this, ActorTween.ALPHA, fade)
		.delay(delay)
	 	.target(0f)
		.ease(TweenEquations.easeInOutQuad)
		.start(manager);
	}

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
