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

import java.util.ArrayList;
import java.util.List;

import de.myreality.galacticum.core.GameContainer;
import de.myreality.galacticum.core.Subsystem;
import de.myreality.galacticum.core.SubsystemException;
import de.myreality.galacticum.core.SubsystemFactory;
import de.myreality.galacticum.io.ContextConfiguration;
import de.myreality.galacticum.util.Nameable;

/**
 * Simple implementation of {@see ContextLoader}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SimpleContextLoader implements ContextLoader {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private List<SubsystemFactory> factories;
	
	private ContextListenerController listenerController;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleContextLoader() {
		listenerController = new ContextListenerController();
		factories = new ArrayList<SubsystemFactory>();
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
	 * de.myreality.galacticum.core.ContextLoader#create(de.myreality.galacticum
	 * .io.ContextConfiguration)
	 */
	@Override
	public Context load(ContextConfiguration configuration, GameContainer container) throws ContextException {		

		listenerController.onStart(new SimpleContextEvent());		
		Subsystem[] subsystems = loadSubsystems(configuration);		
		listenerController.onSuccess(new SimpleContextEvent());
		
		return new SimpleContext(subsystems, container, configuration);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.ContextLoader#addFactory(de.myreality.galacticum
	 * .core.SubsystemFactory)
	 */
	@Override
	public void addFactory(SubsystemFactory factory) {
		factories.add(factory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.ContextLoader#addListener(de.myreality.
	 * galacticum.core.ContextListener)
	 */
	@Override
	public void addListener(ContextListener listener) {
		listenerController.addListener(listener);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private Subsystem[] loadSubsystems(ContextConfiguration configuration) throws ContextException {	
		
		Subsystem[] systems = new Subsystem[factories.size()];		
		for (int index = 0; index < factories.size(); ++index) {

			Subsystem system = loadSubsystem(index, configuration);
			SimpleContextEvent event = new SimpleContextEvent();
			listenerController.onLoad(event, system);	
			
			startSubsystem(system, event);
			
			systems[index] = system;
		}
		
		return systems;
	}
	
	private void startSubsystem(Subsystem system, ContextEvent event) throws ContextException {
		
		try {
			system.start();
		} catch (SubsystemException e) {
			listenerController.onFail(event, e);
			throw new ContextException(e);
		}
	}
	
	private Subsystem loadSubsystem(int factoryIndex, ContextConfiguration configuration) {
		return factories.get(factoryIndex).create(configuration);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	class ContextListenerController implements ContextListener {
		
		private List<ContextListener> listeners;
		
		public ContextListenerController() {
			listeners = new ArrayList<ContextListener>();
		}

		/* (non-Javadoc)
		 * @see de.myreality.galacticum.core.context.ContextListener#onStart(de.myreality.galacticum.core.context.ContextEvent)
		 */
		@Override
		public void onStart(ContextEvent event) {
			for (ContextListener l : listeners) {
				l.onStart(event);
			}
		}

		/* (non-Javadoc)
		 * @see de.myreality.galacticum.core.context.ContextListener#onSuccess(de.myreality.galacticum.core.context.ContextEvent)
		 */
		@Override
		public void onSuccess(ContextEvent event) {
			for (ContextListener l : listeners) {
				l.onSuccess(event);
			}
		}

		/* (non-Javadoc)
		 * @see de.myreality.galacticum.core.context.ContextListener#onFail(de.myreality.galacticum.core.context.ContextEvent, java.lang.Throwable)
		 */
		@Override
		public void onFail(ContextEvent event, Throwable error) {
			for (ContextListener l : listeners) {
				l.onFail(event, error);
			}
		}

		/* (non-Javadoc)
		 * @see de.myreality.galacticum.core.context.ContextListener#onLoad(de.myreality.galacticum.core.context.ContextEvent, de.myreality.galacticum.util.Nameable)
		 */
		@Override
		public void onLoad(ContextEvent event, Nameable target) {
			for (ContextListener l : listeners) {
				l.onLoad(event, target);
			}
		}
		
		public void addListener(ContextListener listener) {
			listeners.add(listener);
		}
		
	}

}
