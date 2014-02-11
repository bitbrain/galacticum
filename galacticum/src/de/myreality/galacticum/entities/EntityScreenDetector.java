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
package de.myreality.galacticum.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.myreality.galacticum.context.Context;
import de.myreality.galacticum.core.WorldSystemListener;
import de.myreality.galacticum.graphics.GameCamera;

/**
 * Provides a list of entities which is actually on screen
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class EntityScreenDetector implements WorldSystemListener {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private final Set<Entity> entities;
	
	private final Context context;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public EntityScreenDetector(Context context) {
		entities = new HashSet<Entity>();
		this.context = context;
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
	 * de.myreality.galacticum.core.WorldSystemListener#onUpdateEntity(de.myreality
	 * .galacticum.entities.Entity, float)
	 */
	@Override
	public void onUpdateEntity(Entity entity, float delta) {
		GameCamera camera = context.getCamera();
		
		if (entity.isOnScreen(camera)) {
			entities.add(entity);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	public void clear() {
		entities.clear();
	}
	
	public Collection<Entity> getVisibleEntities() {
		return entities;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
