package de.myreality.galacticum.core.chunks;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import de.myreality.galacticum.core.Boundable;
import de.myreality.galacticum.core.Universe;
import de.myreality.galacticum.core.UniverseSystem;
import de.myreality.galacticum.exceptions.EntryNotFoundException;
import de.myreality.galacticum.util.Indexable;
import de.myreality.galacticum.util.MatrixList;

/**
 * System that manages all chunks
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface ChunkSystem extends Runnable, Indexable, UniverseSystem {

	/**
	 * Target chunk size
	 */
	static final int CHUNK_SIZE = 1024;

	/**
	 * @return The current chunk that is active
	 */
	Chunk getActiveChunk() throws EntryNotFoundException;

	
	/** 
	 * @return A list of all current chunks
	 */
	MatrixList<Chunk> getChunks();

	/**
	 * @param indexX
	 *            x index of the chunk
	 * @param indexY
	 *            y index of the chunk
	 * @return chunk at the given position
	 * @throws EntryNotFoundException
	 */
	Chunk getChunk(int indexX, int indexY) throws EntryNotFoundException;
	
	Chunk getChunk(Boundable boundable) throws EntryNotFoundException;

	/**
	 * @return the universe tis system refers to
	 */
	Universe getUniverse();

	/**
	 * @return the amount of currently loaded chunks
	 */
	int getCurrentChunkCount();

	/**
	 * @return the amount of maximum available chunks
	 */
	int getTotalChunkCount();
	
	/**
	 * @return an area which defines the location of the system
	 */
	Boundable getBounds();

	/**
	 * @return True when the system is currently loading chunks
	 */
	boolean isLoading();

	
	/**
	 * @return the current loading progress (between 0.0 and 1.0)
	 */
	float getProgress();

	/**
	 * Saves the current chunk state 
	 */
	void save();

	void debug(GameContainer gc, Graphics g);
}
