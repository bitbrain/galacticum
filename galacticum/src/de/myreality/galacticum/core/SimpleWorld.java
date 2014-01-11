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
package de.myreality.galacticum.core;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import de.myreality.chunx.util.SimpleObservable;
import de.myreality.galacticum.core.chunks.ContentTargetAdapter;
import de.myreality.galacticum.core.entities.Entity;

/**
 * Simple implementation of {@see GameContainer}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SimpleWorld extends SimpleObservable<WorldListener> implements	World {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 6687408260630855504L;
	
	// ===========================================================
	// Fields
	// ===========================================================
	
	private CopyOnWriteArrayList<Object> entities;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SimpleWorld() {
		entities = new CopyOnWriteArrayList<Object>();
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
	 * de.myreality.galacticum.core.GameContainer#add(de.myreality.galacticum
	 * .core.Entity)
	 */
	@Override
	public void add(Object entity) {
		if (!entities.contains(entity)) {
			entities.add(entity);
			
			if (entity instanceof GameLight) {
				
				GameLight light = deriveEntity(entity, GameLight.class);
				
				for (WorldListener l : getListeners()) {
					l.onAddLight(light);
				}
			} else {
				Entity realEntity = deriveEntity(entity, Entity.class);
				
				if (realEntity != null) {
					for (WorldListener l : getListeners()) {
						l.onAddEntity(realEntity);
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.GameContainer#remove(de.myreality.galacticum
	 * .core.Entity)
	 */
	@Override
	public void remove(Object entity) {
		entities.remove(entity);
		
		if (entity instanceof GameLight) {
			
			GameLight light = deriveEntity(entity, GameLight.class);
			
			for (WorldListener l : getListeners()) {
				l.onRemoveLight(light);
			}
		} else {
			Entity realEntity = deriveEntity(entity, Entity.class);
			
			if (realEntity != null) {
				for (WorldListener l : getListeners()) {
					l.onRemoveEntity(realEntity);
				}
			}
		} 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.GameContainer#getEntities()
	 */
	@Override
	public Collection<Object> getEntities() {
		return entities;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	@SuppressWarnings("unchecked")
	private <T> T deriveEntity(Object object, Class<T> typeClass) {
		
		if (object instanceof Entity) {
			return (T)object;
		} else if (object instanceof ContentTargetAdapter) {
			ContentTargetAdapter a = (ContentTargetAdapter)object;
			return deriveEntity(a.getTarget(), typeClass);
		} else {
			return null;
		}
	}
	
	

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
