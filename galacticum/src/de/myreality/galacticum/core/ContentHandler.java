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

import de.myreality.chunx.Chunk;
import de.myreality.chunx.ChunkSystem;
import de.myreality.galacticum.chunks.ChunkSystemListenerPrototype;
import de.myreality.galacticum.core.GameLight.GameLightType;
import de.myreality.galacticum.entities.Entity;
import de.myreality.galacticum.entities.EntityType;
import de.myreality.galacticum.entities.SharedSpaceShipFactory;
import de.myreality.galacticum.entities.SimpleEntity;
import de.myreality.galacticum.entities.SpaceShipFactory;
import de.myreality.galacticum.entities.SpaceShipType;
import de.myreality.galacticum.util.GameColor;
import de.myreality.galacticum.util.HashGenerator;

/**
 * Handles the content of the world
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class ContentHandler extends ChunkSystemListenerPrototype {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private HashGenerator hashGenerator;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ContentHandler(HashGenerator generator) {
		this.hashGenerator = generator;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.chunks.ChunkSystemListenerPrototype#afterCreateChunk(de.myreality.chunx.Chunk, de.myreality.chunx.ChunkSystem)
	 */
	@Override
	public void afterCreateChunk(Chunk area, ChunkSystem chunkSystem) {
		SpaceShipFactory f = SharedSpaceShipFactory.getInstance();
		
		if (area.getX() == 0 && area.getY() == 0) {
			System.out.println("Created chunk 0|0");
		}
		
		final int AMOUNT = 20;
		
		for (int i = 0; i < AMOUNT; ++i) {
			
			float x = (float) (area.getX() + Math.random() * area.getWidth());
			float y = (float) (area.getY() + Math.random() * area.getHeight());
			
			Entity entity = f.create(x, y, SpaceShipType.FIGHTER, hashGenerator.generate(x, y));
			area.add(entity);			
		}
		
		// Add planets
		
		if (Math.random() < 0.4) {
			float x = (float) (area.getX() + Math.random() * area.getWidth());
			float y = (float) (area.getY() + Math.random() * area.getHeight());
			
			Planet planet = new Planet(x, y, hashGenerator.generate(x, y), GameColor.WHITE);
			area.add(planet);
		}
		
		// Add lights
		final int LIGHT_COUNT = 1;
		
		for (int i = 0; i < LIGHT_COUNT; ++i) {
			float x = (float) (area.getX() + Math.random() * area.getWidth());
			float y = (float) (area.getY() + Math.random() * area.getHeight());
			GameColor color = new GameColor((float)Math.random() + 0.2f, (float)Math.random() + 0.2f, (float)Math.random() + 0.2f, 0.3f);
			
			GameLight light = new SimpleGameLight(x, y, 100, 800, color, GameLightType.POINT);
			area.add(light);
		}
	}

	public static class Planet extends SimpleEntity {

		/**
		 * @param type
		 * @param width
		 * @param height
		 */
		public Planet(float x, float y, long hash, GameColor color) {
			super(EntityType.PLANET, 50, 50, color, hash);
			setX(x);
			setY(y);
		}
		
	}

}
