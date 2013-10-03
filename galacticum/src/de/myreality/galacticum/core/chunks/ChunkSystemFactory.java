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

import de.myreality.chunx.ChunkSystem;
import de.myreality.chunx.ChunkTarget;
import de.myreality.chunx.ContentProvider;
import de.myreality.chunx.caching.CachedChunkConfiguration;
import de.myreality.chunx.caching.SimpleCachedChunkConfiguration;
import de.myreality.chunx.caching.SimpleCachedChunkSystem;
import de.myreality.chunx.io.ChunkLoader;
import de.myreality.chunx.io.ChunkSaver;
import de.myreality.galacticum.core.Subsystem;
import de.myreality.galacticum.core.SubsystemFactory;
import de.myreality.galacticum.io.ContextConfiguration;
import de.myreality.galacticum.io.GDXInputStreamProvider;
import de.myreality.galacticum.io.GDXOutputStreamProvider;

/**
 * Factory which creates a subsystem for creating chunks
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class ChunkSystemFactory implements SubsystemFactory {
	
	// ===========================================================
	// Constants
	// ===========================================================
	
	private static final int CHUNK_SIZE = 2048;

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ContentProvider contentProvider;
	
	private ChunkTarget chunkTarget;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ChunkSystemFactory(ChunkTarget chunkTarget, ContentProvider contentProvider) {
		this.chunkTarget = chunkTarget;
		this.contentProvider = contentProvider;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.SubsystemFactory#create()
	 */
	@Override
	public Subsystem create(ContextConfiguration configuration) {

		CachedChunkConfiguration chunkConfiguration = new SimpleCachedChunkConfiguration();		
		ChunkSystem chunkSystem = new SimpleCachedChunkSystem(chunkConfiguration);		
		ChunkSaver saver = chunkSystem.getSaver();
		ChunkLoader loader = chunkSystem.getLoader();
		loader.setPath(configuration.getChunkPath());
		saver.setPath(configuration.getChunkPath());
		
		chunkConfiguration.setContentProvider(contentProvider);
		chunkConfiguration.setFocused(chunkTarget);
		chunkConfiguration.setChunkSize(CHUNK_SIZE);
		
		// TODO: Decrease number, its only for testing purposes
		chunkConfiguration.setCacheSize(25);
		
		// Align adapters for LibGDX
		saver.setProvider(new OutputProviderAdapter(new GDXOutputStreamProvider()));
		loader.setProvider(new InputProviderAdapter(new GDXInputStreamProvider()));
		
		return new ChunkSubsystemAdapter(chunkSystem);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}