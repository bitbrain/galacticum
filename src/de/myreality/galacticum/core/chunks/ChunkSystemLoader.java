package de.myreality.galacticum.core.chunks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.core.SubsystemLoader;
import de.myreality.galacticum.core.Universe;

/**
 * Loads a chunk system
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class ChunkSystemLoader extends SubsystemLoader<ChunkSystem> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public ChunkSystemLoader(StateBasedGame game) {
		super(game);
		setStateMessage("Loading chunks");
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void load(Universe universe) {

		CachedChunkSystem system = new CachedChunkSystem(universe);
		subSystem = system;

		// Load the chunk system
		ExecutorService executor = Executors.newFixedThreadPool(1);
		system.cacheRequest();
		Future<?> future = executor.submit(subSystem);
		while (!future.isCancelled() && !future.isDone()
				&& subSystem.isLoading()) {
			setLoadingProgress(subSystem.getProgress());
		}

		executor.shutdown();
		universe.setChunkSystem(subSystem);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
