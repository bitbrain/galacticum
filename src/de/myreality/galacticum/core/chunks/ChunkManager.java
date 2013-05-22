package de.myreality.galacticum.core.chunks;

/**
 * Manages a single chunk system.
 * <p>
 * This manager can be used to manage the chunk loading on an abstract level.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface ChunkManager {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * @return The current chunk system
	 */
	ChunkSystem getSystem();
	
	/**
	 * Starts the manager
	 */
	void start();
	
	/**
	 * Shuts the manager down
	 */
	void shutdown();
}
