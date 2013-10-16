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
package de.myreality.galacticum.core.context;

/**
 * Simple implementation of {@see ContextEvent}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
class SimpleContextEvent implements ContextEvent {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private Object sender;

	private int currentCount;

	private int totalCount;

	private float progress;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * @param sender
	 * @param currentCount
	 * @param totalCount
	 * @param progress
	 */
	public SimpleContextEvent(Object sender, int currentCount, int totalCount,
			float progress) {
		this.sender = sender;
		this.currentCount = currentCount;
		this.totalCount = totalCount;
		this.progress = progress;
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
	 * @see de.myreality.galacticum.core.context.ContextEvent#getProgress()
	 */
	@Override
	public float getProgress() {
		return progress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.context.ContextEvent#getSender()
	 */
	@Override
	public Object getSender() {
		return sender;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.context.ContextEvent#getTotalCount()
	 */
	@Override
	public int getTotalCount() {
		return totalCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.context.ContextEvent#getCurrentCount()
	 */
	@Override
	public int getCurrentCount() {
		return currentCount;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
