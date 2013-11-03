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
/**
 * Shakes an x and y value at the given time
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class Shaker implements Shakeable {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private float intensX, intensY, startIntensX, startIntensY;
	
	private Timer timer;
	
	private long miliseconds;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public Shaker() {
		timer = new Timer();
		intensX = 0;
		intensY = 0;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public float getX() {
		return (float) intensX;
	}
	
	public float getY() {
		return (float) intensY;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Shakeable#shake(float, long)
	 */
	@Override
	public void shake(float amount, long miliseconds) {
		startIntensX = startIntensY = intensX = intensY = amount;
		timer.start();
		this.miliseconds = miliseconds;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	public void update(float delta) {
		
		if (timer.getTicks() >= miliseconds) {
			timer.stop();
			intensX = 0;
			intensY = 0;
		} else if (timer.isRunning()) {
			intensX /=2;
			intensY /=2;
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
