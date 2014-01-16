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
package de.myreality.galacticum.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author miguel
 * @since
 * @version
 */
public class ModuleList implements Iterable<Module> {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Map<Class<?>, Module>  subsystems;
	
	private List<Module> systems;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ModuleList() {
		subsystems = new HashMap<Class<?>, Module>();
		systems = new ArrayList<Module>();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public void add(Module subsystem) {
		subsystems.put(subsystem.getClass(), subsystem);
		
		if (!systems.contains(subsystem)) {
			systems.add(subsystem);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <Type extends Module> Type get(Class<Type> subsystemClass) {
		return (Type) subsystems.get(subsystemClass);
	}
	
	public int size() {
		return subsystems.size();
	}
	
	public boolean isEmpty() {
		return subsystems.isEmpty();
	}
	
	public void clear() {
		subsystems.clear();
		systems.clear();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Module> iterator() {
		return systems.iterator();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
