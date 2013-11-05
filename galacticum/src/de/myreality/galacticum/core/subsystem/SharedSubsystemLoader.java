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
package de.myreality.galacticum.core.subsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.galacticum.core.World;
import de.myreality.galacticum.core.WorldSystem;
import de.myreality.galacticum.core.chunks.ChunkSubsystemFactory;
import de.myreality.galacticum.core.chunks.ChunkTargetAdapter;
import de.myreality.galacticum.core.chunks.ContentProviderAdapter;
import de.myreality.galacticum.core.entities.SharedSpaceShipFactory;
import de.myreality.galacticum.core.player.PlayerSubsystem;
import de.myreality.galacticum.graphics.BackgroundSystem;
import de.myreality.galacticum.graphics.CameraSystem;
import de.myreality.galacticum.graphics.GameCamera;
import de.myreality.galacticum.graphics.LightingSubsystem;
import de.myreality.galacticum.graphics.ViewportAdapter;
import de.myreality.galacticum.io.ContextConfiguration;
import de.myreality.galacticum.physics.PhysicSubsystem;

/**
 * Singleton implementation of a {@see SubsystemLoader}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SharedSubsystemLoader implements SubsystemLoader {
	
	// ===========================================================
	// Constants
	// ===========================================================
	
	private static SharedSubsystemLoader loader;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	
	private SharedSubsystemLoader() { }

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public static SharedSubsystemLoader getInstance() {
		
		if (loader == null) {
			loader = new SharedSubsystemLoader();
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
	public SubsystemList createSubsystems(World world, SpriteBatch batch, ContextConfiguration configuration) {
		
		SubsystemList systems = new SubsystemList();
		
		CameraSystem cameraSystem = new CameraSystem(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), batch);
		
		GameCamera camera = cameraSystem.getCamera();
		ChunkTargetAdapter cameraAdapter = new ChunkTargetAdapter(camera);
		
		ChunkSubsystemFactory chunkFactory = new ChunkSubsystemFactory(cameraAdapter, new ContentProviderAdapter(world));		
		PlayerSubsystem playerSystem = new PlayerSubsystem(configuration, SharedSpaceShipFactory.getInstance(), cameraAdapter);

		systems.add(chunkFactory.create(configuration));
		systems.add(cameraSystem);
		systems.add(playerSystem);
		systems.add(new BackgroundSystem(new ViewportAdapter(cameraSystem.getCamera()), batch));
		systems.add(new PhysicSubsystem());
		systems.add(new WorldSystem(world, batch, camera));
		systems.add(new LightingSubsystem());
		return systems;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
