/**
 * This file was written by Miguel Gonzalez and is part of the
 * game "LittleWars". For more information mailto info@my-reality.de
 * or visit the game page: http://dev.my-reality.de/littlewars
 * 
 * A thread bases timer for counting in miliseconds
 * 
 * @version 	0.0.3
 * @author 		Miguel Gonzalez		
 */

package de.myreality.galacticum.prototype;

public class Timer {

	// Milliseconds
	int ms;
	
	// State of running or not
	boolean running;
	
	
	/**
	 * Constructor of TimeCounter
	 */
	public Timer() {
		ms = 0;
	}
	
	
	public boolean isRunning() {
		return running;
	}
	
	/**
	 * Starts the timer
	 */
	public void start() {
		if (!running) {
			running = true;
		}
	}
	
	
	public void update(int delta) {
		if (running) {
			ms += delta;
		}
	}
	
	
	
	/**
	 * Stops the timer
	 */
	public void stop() {
		running = false;
	}
	
	
	
	/**
	 * Resets the timer
	 */
	public void reset() {
		ms = 0;
	}
	
	
	/**
	 * @return Gathered milliseconds
	 */
	public int getMiliseconds() {
		return ms;
	}		

}
