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

import java.util.ArrayList;
import java.util.List;


/**
 * Simple implementation of {@see ConfigurationManager}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SimpleConfigurationManager extends SimpleConfigurationIO implements
		ConfigurationManager {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private List<ConfigurationListener> listeners;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * @param writer
	 * @param reader
	 */
	public SimpleConfigurationManager(ConfigurationWriter writer,
			ConfigurationReader reader, ConfigurationRemover remover) {
		super(writer, reader, remover);
		listeners = new ArrayList<ConfigurationListener>();
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
	 * de.myreality.galacticum.io.ConfigurationManager#load(de.myreality.galacticum
	 * .io.ContextConfiguration)
	 */
	@Override
	public ContextConfiguration load(String id)
			throws ContextNotFoundException {
		
		for (ConfigurationListener l : listeners) {
			l.onLoad(null);
		}
		
		ContextConfiguration[] configurations = getReader().read();
		
		for (ContextConfiguration configuration : configurations) {
			if (configuration.getID().equals(id)) {
				return configuration;
			}
		}
		
		ContextNotFoundException e = new ContextNotFoundException("There is no context with id=" + id);
		for (ConfigurationListener l : listeners) {
			l.onError(null, e);
		}
		
		throw e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.io.ConfigurationManager#save(de.myreality.galacticum
	 * .io.ContextConfiguration)
	 */
	@Override
	public void save(ContextConfiguration context) {		
		ConfigurationWriter writer = getWriter();	
		for (ConfigurationListener l : listeners) {
			l.onSave(null);
		}
		writer.write(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.io.ConfigurationManager#remove(de.myreality.
	 * galacticum.io.ContextConfiguration)
	 */
	@Override
	public void remove(String id)
			throws ContextNotFoundException {
		try {
			for (ConfigurationListener l : listeners) {
				l.onRemove(null);
			}
			getRemover().remove(id);
		} catch (ContextNotFoundException e) {
			for (ConfigurationListener l : listeners) {
				l.onError(null, e);
			}
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.io.ConfigurationManager#hasContext(de.myreality
	 * .galacticum.io.ContextConfiguration)
	 */
	@Override
	public boolean hasContext(String id) {
		ContextConfiguration[] configs = getReader().read();	
		
		for (ContextConfiguration config : configs) {
			if (config.getID().equals(id)) {
				return true;
			}
		}
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.io.ConfigurationManager#addListener(de.myreality.galacticum.io.ConfigurationListener)
	 */
	@Override
	public void addListener(ConfigurationListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}