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
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.chunx.ChunkTarget;
import de.myreality.chunx.util.SimpleObservable;
import de.myreality.chunx.util.Updateable;
import de.myreality.galacticum.util.Renderable;
import de.myreality.galacticum.util.Seed;

/**
 * 
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SimpleUniverse extends SimpleObservable<UniverseListener>
		implements Universe {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private List<ChunkTarget> entities;
	
	private Seed seed;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleUniverse(Seed seed) {
		this.seed = seed;
		entities = new CopyOnWriteArrayList<ChunkTarget>();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.chunx.ContentProvider#add(de.myreality.chunx.ChunkTarget)
	 */
	@Override
	public void add(ChunkTarget target) {
		if (entities.contains(target)) {
			entities.add(target);
			for (UniverseListener l : getListeners()) {
				l.onRemove(target, this);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.chunx.ContentProvider#getContent()
	 */
	@Override
	public Collection<ChunkTarget> getContent() {
		return entities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.chunx.ContentProvider#remove(de.myreality.chunx.ChunkTarget)
	 */
	@Override
	public void remove(ChunkTarget target) {
		if (entities.remove(target)) {
			for (UniverseListener l : getListeners()) {
				l.onRemove(target, this);
			}
		}
	}

	@Override
	public void updateAndRender(float delta, SpriteBatch batch) {		
		
		// Render entities at last
		for (ChunkTarget entity : entities) {
			
			if (entity instanceof Updateable) {
				((Updateable) entity).update(delta);
			}
			
			if (entity instanceof Renderable) {
				((Renderable) entity).render(batch);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Universe#getSeed()
	 */
	@Override
	public Seed getSeed() {
		return seed;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
