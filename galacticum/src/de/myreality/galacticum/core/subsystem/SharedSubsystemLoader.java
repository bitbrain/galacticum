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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.chunx.ChunkTarget;
import de.myreality.galacticum.core.GameContainer;
import de.myreality.galacticum.core.chunks.ChunkSystemFactory;
import de.myreality.galacticum.core.chunks.ContentProviderAdapter;
import de.myreality.galacticum.core.entities.SharedSpaceShipFactory;
import de.myreality.galacticum.core.player.PlayerSubsystem;
import de.myreality.galacticum.graphics.BackgroundSystem;
import de.myreality.galacticum.graphics.CameraSystem;
import de.myreality.galacticum.graphics.ViewportAdapter;
import de.myreality.galacticum.io.ContextConfiguration;

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
	public Collection<Subsystem> createSubsystems(GameContainer container, ContextConfiguration configuration) {
		
		List<Subsystem> systems = new ArrayList<Subsystem>();
		
		SpriteBatch batch = new SpriteBatch();
		
		ChunkSystemFactory chunkFactory = new ChunkSystemFactory(new ChunkTarget() {

			private static final long serialVersionUID = 1L;

			@Override
			public float getX() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public float getY() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void setX(float arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setY(float arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}, new ContentProviderAdapter(container));		
		
		CameraSystem cameraSystem = new CameraSystem(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), batch);
		
		systems.add(chunkFactory.create(configuration));
		systems.add(cameraSystem);
		systems.add(new PlayerSubsystem(configuration, SharedSpaceShipFactory.getInstance()));
		systems.add(new BackgroundSystem(new ViewportAdapter(cameraSystem.getCamera()), batch));
		
		return systems;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
