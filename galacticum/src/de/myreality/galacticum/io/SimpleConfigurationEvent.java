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
package de.myreality.galacticum.io;

/**
 * Event which triggers by the configuration manager
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
class SimpleConfigurationEvent implements ConfigurationEvent {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private float progress;
	
	private String resource;
	
	private ConfigurationManager sender;
	
	private ContextConfiguration configuration;

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
	 * @see de.myreality.galacticum.io.ConfigurationEvent#getProgress()
	 */
	@Override
	public float getProgress() {
		return progress;
	}

	/**
	 * @param progress
	 * @param resource
	 * @param sender
	 * @param configuration
	 */
	public SimpleConfigurationEvent(float progress, String resource,
			ConfigurationManager sender, ContextConfiguration configuration) {
		super();
		this.progress = progress;
		this.resource = resource;
		this.sender = sender;
		this.configuration = configuration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.io.ConfigurationEvent#getResource()
	 */
	@Override
	public String getResource() {
		return resource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.io.ConfigurationEvent#getSender()
	 */
	@Override
	public ConfigurationManager getSender() {
		return sender;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.io.ConfigurationEvent#getConfiguration()
	 */
	@Override
	public ContextConfiguration getConfiguration() {
		return configuration;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
