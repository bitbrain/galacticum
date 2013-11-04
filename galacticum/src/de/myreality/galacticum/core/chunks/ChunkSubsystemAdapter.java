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
package de.myreality.galacticum.core.chunks;

import de.myreality.chunx.Chunk;
import de.myreality.chunx.ChunkSystem;
import de.myreality.chunx.ChunkSystemListener;
import de.myreality.galacticum.core.context.Context;
import de.myreality.galacticum.core.entities.Entity;
import de.myreality.galacticum.core.entities.SharedSpaceShipFactory;
import de.myreality.galacticum.core.entities.SpaceShipFactory;
import de.myreality.galacticum.core.entities.SpaceShipType;
import de.myreality.galacticum.core.subsystem.ProgressListener;
import de.myreality.galacticum.core.subsystem.Subsystem;
import de.myreality.galacticum.core.subsystem.SubsystemException;
import de.myreality.galacticum.util.Seed;

/**
 * Adapter to convert {@see ChunkSystem} to {@see Subsystem}
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class ChunkSubsystemAdapter implements Subsystem {

	// ===========================================================
	// Constants
	// ===========================================================
	
	private static final String NAME = "chunk system";

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ChunkSystem chunkSystem;
	
	private Seed seed;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ChunkSubsystemAdapter(ChunkSystem chunkSystem, Seed seed) {
		this.chunkSystem = chunkSystem;
		this.seed = seed;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public Chunk getActiveChunk() {
		return chunkSystem.getActiveChunk();
	}
	
	public void addListener(ChunkSystemListener listener) {
		chunkSystem.addListener(listener);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Nameable#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.Subsystem#start()
	 */
	@Override
	public void start() throws SubsystemException {
		addListener(new ContentProvider(seed));
		chunkSystem.start();
	}
	


	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#onEnter(de.myreality.galacticum.core.context.Context)
	 */
	@Override
	public void onEnter(Context context) {
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.Subsystem#update(float)
	 */
	@Override
	public void update(float delta) {
		chunkSystem.update(delta);
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.Subsystem#shutdown()
	 */
	@Override
	public void shutdown() {
		chunkSystem.shutdown();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#addProgressListener(de.myreality.galacticum.core.subsystem.ProgressListener)
	 */
	@Override
	public void addProgressListener(ProgressListener listener) {
		chunkSystem.addListener(new ChunkSystemListenerAdapter(listener));
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#removeProgressListener(de.myreality.galacticum.core.subsystem.ProgressListener)
	 */
	@Override
	public void removeProgressListener(ProgressListener listener) {
		chunkSystem.removeListener(new ChunkSystemListenerAdapter(listener));
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	class ContentProvider implements ChunkSystemListener {
		
		private Seed seed;
		
		public ContentProvider(Seed seed) {
			this.seed = seed;
		}

		/* (non-Javadoc)
		 * @see de.myreality.chunx.ChunkSystemListener#afterCreateChunk(de.myreality.chunx.Chunk, de.myreality.chunx.ChunkSystem)
		 */
		@Override
		public void afterCreateChunk(Chunk chunk, ChunkSystem system) {
			
			SpaceShipFactory f = SharedSpaceShipFactory.getInstance();
			
			final int ENTITIES = 20;
			
			for (int i = 0; i < ENTITIES; ++i) {
				
				float x = (float) (chunk.getX() + Math.random() * chunk.getWidth());
				float y = (float) (chunk.getY() + Math.random() * chunk.getHeight());
				
				Entity e = f.create(x, y, SpaceShipType.FIGHTER, seed);
				chunk.add(e);
			}
			
		}

		/* (non-Javadoc)
		 * @see de.myreality.chunx.ChunkSystemListener#afterLoadChunk(de.myreality.chunx.Chunk, de.myreality.chunx.ChunkSystem)
		 */
		@Override
		public void afterLoadChunk(Chunk arg0, ChunkSystem arg1) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see de.myreality.chunx.ChunkSystemListener#afterRemoveChunk(int, int, de.myreality.chunx.ChunkSystem)
		 */
		@Override
		public void afterRemoveChunk(int arg0, int arg1, ChunkSystem arg2) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see de.myreality.chunx.ChunkSystemListener#afterSaveChunk(de.myreality.chunx.Chunk, de.myreality.chunx.ChunkSystem)
		 */
		@Override
		public void afterSaveChunk(Chunk arg0, ChunkSystem arg1) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see de.myreality.chunx.ChunkSystemListener#beforeCreateChunk(int, int, de.myreality.chunx.ChunkSystem)
		 */
		@Override
		public void beforeCreateChunk(int arg0, int arg1, ChunkSystem arg2) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see de.myreality.chunx.ChunkSystemListener#beforeLoadChunk(int, int, de.myreality.chunx.ChunkSystem)
		 */
		@Override
		public void beforeLoadChunk(int arg0, int arg1, ChunkSystem arg2) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see de.myreality.chunx.ChunkSystemListener#beforeRemoveChunk(de.myreality.chunx.Chunk, de.myreality.chunx.ChunkSystem)
		 */
		@Override
		public void beforeRemoveChunk(Chunk arg0, ChunkSystem arg1) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see de.myreality.chunx.ChunkSystemListener#beforeSaveChunk(de.myreality.chunx.Chunk, de.myreality.chunx.ChunkSystem)
		 */
		@Override
		public void beforeSaveChunk(Chunk arg0, ChunkSystem arg1) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see de.myreality.chunx.ChunkSystemListener#onEnterChunk(de.myreality.chunx.Chunk, de.myreality.chunx.ChunkSystem)
		 */
		@Override
		public void onEnterChunk(Chunk arg0, ChunkSystem arg1) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see de.myreality.chunx.ChunkSystemListener#onLeaveChunk(de.myreality.chunx.Chunk, de.myreality.chunx.ChunkSystem)
		 */
		@Override
		public void onLeaveChunk(Chunk arg0, ChunkSystem arg1) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
