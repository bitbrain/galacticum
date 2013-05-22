package de.myreality.galacticum.core.chunks;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.newdawn.slick.util.Log;

/**
 * A concurrent implementation of a chunk manager which manages
 * a single chunk system with multi-threading.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class ConcurrentChunkManager implements ChunkManager {

	// ===========================================================
	// Constants
	// ===========================================================
	
	public static final long UPDATE_INTERVAL = 10;
	
	public static final int CORE_SIZE = 1;

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ScheduledThreadPoolExecutor chunkExecutor;
	
	private ChunkSystem system;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ConcurrentChunkManager(ChunkSystem system) {
		this.system = system;
		chunkExecutor = new ScheduledThreadPoolExecutor(CORE_SIZE);
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	@Override
	public ChunkSystem getSystem() {
		return system;
	}

	@Override
	public void start() {
		chunkExecutor.remove(system);
		chunkExecutor.scheduleWithFixedDelay(system, 0, UPDATE_INTERVAL, TimeUnit.MILLISECONDS);
	}

	@Override
	public void shutdown() {
		system.shutdown();
		chunkExecutor.shutdown();
		system = null;
		chunkExecutor = null;
		Log.info("ChunkManager shutdown has been executed.");
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
