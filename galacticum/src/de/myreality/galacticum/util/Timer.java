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

import java.util.concurrent.TimeUnit;

/**
 * Simple timer implementation
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class Timer {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private long startTime;

	private boolean running;
	
	private long pauseTime;
	
	private long currentTicks;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	public Timer() {
		reset();
		running = false;
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================
	
	@Override
	public String toString() {
		return convertValue(TimeUnit.MILLISECONDS.toMinutes(getTicks())) + ":" +
		convertValue(TimeUnit.MILLISECONDS.toSeconds(getTicks()) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(getTicks()))
			);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	public void start() {
		running = true;
		
		if (pauseTime > 0) {
			startTime = System.currentTimeMillis() - pauseTime;
			pauseTime = 0;
		}
	}
	
	public void stop() {
		running = false;
		reset();
	}
	
	public void pause() {
		pauseTime = getTicks();
		running = false;
	}
	
	public void reset() {
		startTime = System.currentTimeMillis();
		currentTicks = 0;
	}
	
	public long getTicks() {
		
		if (running) {
			currentTicks = System.currentTimeMillis() - startTime;
		}
		
		return currentTicks;
	}
	
	public boolean isRunning() {
		return running;
	}

	private String convertValue(long time) {
		if (time < 10) {
			return "0" + time;
		} else {
			return "" + time;
		}
	}

	// ===========================================================
	// Inner classes
	// ===========================================================
	
}