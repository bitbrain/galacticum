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

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.galacticum.context.Context;
import de.myreality.galacticum.entities.Entity;
import de.myreality.galacticum.entities.EntityType;
import de.myreality.galacticum.graphics.CameraModule;
import de.myreality.galacticum.graphics.rendering.EntityRenderer;
import de.myreality.galacticum.graphics.rendering.PlanetTextureLoader;
import de.myreality.galacticum.graphics.rendering.SimpleEntityRenderer;
import de.myreality.galacticum.graphics.rendering.SpaceshipTextureLoader;
import de.myreality.galacticum.modules.Module;
import de.myreality.galacticum.modules.ModuleException;
import de.myreality.galacticum.util.SimpleObserver;
import de.myreality.galacticum.util.Updateable;

/**
 * Handles the current world
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class WorldModule extends SimpleObserver<WorldSystemListener> implements Module, WorldListener, Updateable {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private World world;
	
	private SpriteBatch batch;
	
	private EntityRenderer renderer;
	
	private List<Entity> entities;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public World getWorld() {
		return world;
	}

	public EntityRenderer getRenderer() {
		return renderer;
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Nameable#getName()
	 */
	@Override
	public String getName() {
		return "world";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#start()
	 */
	@Override
	public void load(Context context) throws ModuleException {
		
		CameraModule cameraModule = context.getModule(CameraModule.class);
		
		this.world = context.getWorld();
		this.batch = context.getSpriteBatch();
		renderer = new SimpleEntityRenderer(cameraModule.getCamera());
		entities = new CopyOnWriteArrayList<Entity>();
		
		renderer.addTextureLoader(EntityType.PLANET, new PlanetTextureLoader());
		renderer.addTextureLoader(EntityType.SPACESHIP, new SpaceshipTextureLoader(5));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#update(float)
	 */
	@Override
	public void update(float delta) {
		
		for (Entity e : entities) {
			
			for (WorldSystemListener l : getListeners()) {
				l.onUpdateEntity(e, delta);
			}
			
			e.update(delta);			
			renderer.render(e, batch);
			batch.flush();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#shutdown()
	 */
	@Override
	public void dispose() {
		
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.WorldListener#onAddEntity(de.myreality.galacticum.core.entities.Entity)
	 */
	@Override
	public void onAddEntity(Entity entity) {
		entities.add(entity);
		renderer.register(entity);
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.WorldListener#onRemoveEntity(de.myreality.galacticum.core.entities.Entity)
	 */
	@Override
	public void onRemoveEntity(Entity entity) {
		entities.remove(entity);
		renderer.dispose(entity);
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.WorldListener#onAddLight(de.myreality.galacticum.core.GameLight)
	 */
	@Override
	public void onAddLight(GameLight light) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.WorldListener#onRemoveLight(de.myreality.galacticum.core.GameLight)
	 */
	@Override
	public void onRemoveLight(GameLight light) {
		// TODO Auto-generated method stub
		
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
