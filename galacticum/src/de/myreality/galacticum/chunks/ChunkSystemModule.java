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
package de.myreality.galacticum.chunks;

import de.myreality.chunx.Chunk;
import de.myreality.chunx.ChunkSystem;
import de.myreality.galacticum.context.Context;
import de.myreality.galacticum.modules.Module;
import de.myreality.galacticum.modules.ModuleException;
import de.myreality.galacticum.modules.ProgressListener;
import de.myreality.galacticum.util.SimpleObserver;
import de.myreality.galacticum.util.Updateable;

/**
 * Adapter to convert {@see ChunkSystem} to {@see Subsystem}
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class ChunkSystemModule extends SimpleObserver<ProgressListener> implements Module, Updateable {

	// ===========================================================
	// Constants
	// ===========================================================
	
	private static final String NAME = "chunk system";

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ChunkSystem chunkSystem;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ChunkSystemModule(ChunkSystem chunkSystem) {
		this.chunkSystem = chunkSystem;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public Chunk getActiveChunk() {
		return chunkSystem.getActiveChunk();
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
	public void load(Context context) throws ModuleException {
		try {
		chunkSystem.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public void dispose() {
		chunkSystem.shutdown();
	}

	@Override
	public void addListener(ProgressListener listener) {
		chunkSystem.addListener(new ChunkSystemListenerAdapter(listener));
	}

	@Override
	public void removeListener(ProgressListener listener) {
		chunkSystem.removeListener(new ChunkSystemListenerAdapter(listener));
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
}
