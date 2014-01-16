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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.galacticum.chunks.ChunkSystemModuleFactory;
import de.myreality.galacticum.chunks.ChunkTargetAdapter;
import de.myreality.galacticum.chunks.ContentProviderAdapter;
import de.myreality.galacticum.core.World;
import de.myreality.galacticum.core.WorldModule;
import de.myreality.galacticum.entities.SharedSpaceShipFactory;
import de.myreality.galacticum.graphics.BackgroundRenderingModule;
import de.myreality.galacticum.graphics.CameraModule;
import de.myreality.galacticum.graphics.GameCamera;
import de.myreality.galacticum.graphics.LightingModule;
import de.myreality.galacticum.graphics.ViewportAdapter;
import de.myreality.galacticum.io.ContextConfiguration;
import de.myreality.galacticum.physics.Box2DPhysicsModule;
import de.myreality.galacticum.player.PlayerModule;
import de.myreality.galacticum.zones.SpaceZoneModule;

/**
 * Singleton implementation of a {@see SubsystemLoader}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SharedModuleLoader implements ModuleLoader {
	
	// ===========================================================
	// Constants
	// ===========================================================
	
	private static SharedModuleLoader loader;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	
	private SharedModuleLoader() { }

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public static SharedModuleLoader getInstance() {
		
		if (loader == null) {
			loader = new SharedModuleLoader();
		}
		
		return loader;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.subsystem.SubsystemLoader#createSubsystems()
	 */
	@Override
	public ModuleList createSubsystems(World world, SpriteBatch batch, ContextConfiguration configuration) {
		
		ModuleList systems = new ModuleList();
		
		CameraModule cameraSystem = new CameraModule(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), batch);
		
		GameCamera camera = cameraSystem.getCamera();
		ChunkTargetAdapter cameraAdapter = new ChunkTargetAdapter(camera);
		
		ChunkSystemModuleFactory chunkFactory = new ChunkSystemModuleFactory(cameraAdapter, new ContentProviderAdapter(world));		
		PlayerModule playerSystem = new PlayerModule(configuration, SharedSpaceShipFactory.getInstance(), cameraAdapter);
		WorldModule worldSystem = new WorldModule(world, batch, camera);
		SpaceZoneModule zoneModule = new SpaceZoneModule();		
		
		systems.add(chunkFactory.create(configuration));
		systems.add(cameraSystem);
		systems.add(playerSystem);
		systems.add(new BackgroundRenderingModule(new ViewportAdapter(cameraSystem.getCamera()), batch));		
		systems.add(worldSystem);
		systems.add(new Box2DPhysicsModule(worldSystem.getRenderer()));
		systems.add(zoneModule);
		systems.add(new LightingModule(zoneModule));
		return systems;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}