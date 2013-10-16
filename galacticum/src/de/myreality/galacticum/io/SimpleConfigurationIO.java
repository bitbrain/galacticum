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
 * Simple implementation of {@see ConfigurationIO}
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
class SimpleConfigurationIO implements ConfigurationIO {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ConfigurationWriter writer;
	
	private ConfigurationReader reader;
	
	private ConfigurationRemover remover;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleConfigurationIO(ConfigurationWriter writer, ConfigurationReader reader, ConfigurationRemover remover) {
		setWriter(writer);
		setReader(reader);
		setRemover(remover);
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
	 * @see
	 * de.myreality.galacticum.io.ConfigurationIO#setWriter(de.myreality.galacticum
	 * .io.ConfigurationWriter)
	 */
	@Override
	public void setWriter(ConfigurationWriter writer) {
		this.writer = writer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.io.ConfigurationIO#setReader(de.myreality.galacticum
	 * .io.ConfigurationReader)
	 */
	@Override
	public void setReader(ConfigurationReader reader) {
		this.reader = reader;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.io.ConfigurationIO#setRemover(de.myreality.galacticum.io.ConfigurationRemover)
	 */
	@Override
	public void setRemover(ConfigurationRemover remover) {
		this.remover = remover;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	protected ConfigurationReader getReader() {
		return reader;
	}
	
	protected ConfigurationWriter getWriter() {
		return writer;
	}
	
	protected ConfigurationRemover getRemover() {
		return remover;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
