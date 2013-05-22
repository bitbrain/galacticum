package de.myreality.galacticum.core.chunks;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.Log;

import de.myreality.dev.chronos.util.Point2f;
import de.myreality.galacticum.core.BasicBoundable;
import de.myreality.galacticum.core.Boundable;
import de.myreality.galacticum.core.Universe;
import de.myreality.galacticum.exceptions.EntryNotFoundException;
import de.myreality.galacticum.util.MatrixList;

/**
 * Cached implementation of a chunk system that controls the loading of single
 * chunks. When the cache has been leaved and the pre-cache will be entered all
 * current chunks will be reordered:
 * <p>
 * Old chunks will be removed and missing chunks will be loaded from file.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class CachedChunkSystem implements ChunkSystem {

	// ===========================================================
	// Constants
	// ===========================================================

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 939837129674039235L;

	// Default cache size
	private static final int CACHE_SIZE = 1;

	// Default pre cache size
	private static final int PRECACHE_SIZE = 1;

	// ===========================================================
	// Fields
	// ===========================================================

	// Target universe
	private Universe universe;

	// All current chunks
	private MatrixList<Chunk> chunks;

	// Request variables
	private boolean saveRequested;

	// Cache
	private Cache updateCache, preCache;

	private int currentChunkCount;

	private boolean cacheRequest;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * Basic constructor that creates the system
	 * 
	 * @param universe
	 *            target universe
	 * @param renderer
	 *            target renderer
	 */
	public CachedChunkSystem(Universe universe) {
		this.universe = universe;
		saveRequested = false;
		chunks = new MatrixList<Chunk>();
		updateCache = new Cache(CACHE_SIZE);
		preCache = new Cache(CACHE_SIZE + PRECACHE_SIZE);
		currentChunkCount = 0;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public Chunk getActiveChunk() throws EntryNotFoundException {
		int indexX = 0;
		int indexY = 0;
		try {
			indexX = getIndexX();
			indexY = getIndexY();
			return chunks.get(getIndexX(), getIndexY());
		} catch (EntryNotFoundException e) {
			throw new EntryNotFoundException(indexX, indexY);
		}
	}

	@Override
	public int getIndexX() {
		return ChunkUtils.translateToIndexX(universe.getCamera());
	}

	@Override
	public int getIndexY() {
		return ChunkUtils.translateToIndexY(universe.getCamera());
	}

	@Override
	public Chunk getChunk(int indexX, int indexY) throws EntryNotFoundException {
		return chunks.get(indexX, indexY);
	}
	
	@Override
	public Chunk getChunk(Boundable boundable) throws EntryNotFoundException {
		int indexX = ChunkUtils.translateToIndexX(boundable);
		int indexY = ChunkUtils.translateToIndexY(boundable);
		return getChunk(indexX, indexY);
	}

	@Override
	public Universe getUniverse() {
		return universe;
	}

	@Override
	public void save() {
		saveRequested = true;
	}

	@Override
	public void run() {

		// Compute only chunks, when the cache has been leaved
		if (cacheRequested() || cacheRequest) {
			cacheRequest = false;
			computeChunks();
		}

		checkSaving();
	}

	@Override
	public void shutdown() {
		save();
		checkSaving();
		for (Chunk chunk : chunks) {
			chunk.clear();
		}
		chunks.clear();
		universe = null;
		chunks = null;
		Log.info("Chunksystem shutdown has been executed.");

	}

	@Override
	public MatrixList<Chunk> getChunks() {
		return chunks;
	}

	@Override
	public void debug(GameContainer gc, Graphics g) {
		g.setColor(Color.green);
		g.fillRect(preCache.getIndexTopLeft().x * CHUNK_SIZE,
				preCache.getIndexTopLeft().y * CHUNK_SIZE, preCache.getSize()
						* CHUNK_SIZE, preCache.getSize() * CHUNK_SIZE);

		g.setColor(Color.gray);
		g.fillRect(updateCache.getIndexTopLeft().x * CHUNK_SIZE,
				updateCache.getIndexTopLeft().y * CHUNK_SIZE,
				updateCache.getSize() * CHUNK_SIZE, updateCache.getSize()
						* CHUNK_SIZE);
	}

	@Override
	public int getCurrentChunkCount() {
		return currentChunkCount;
	}

	@Override
	public int getTotalChunkCount() {
		return (int) Math.pow(CACHE_SIZE * 2 + 1 + PRECACHE_SIZE, 2);
	}

	@Override
	public boolean isLoading() {
		return getCurrentChunkCount() != getTotalChunkCount();
	}

	@Override
	public float getProgress() {
		return ((float) getCurrentChunkCount() / (float) getTotalChunkCount()) * 100;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.chunks.ChunkSystem#getBounds()
	 */
	@Override
	public Boundable getBounds() {
		return preCache;
	}

	// ===========================================================
	// Methods
	// ===========================================================


	private void checkSaving() {
		if (saveRequested) {
			saveRequested = false;
			for (Chunk chunk : chunks) {
				saveChunk(chunk);
			}
		}
	}

	public void cacheRequest() {
		cacheRequest = true;
	}

	private boolean cacheRequested() {
		return !updateCache.containsIndex(getIndexX(), getIndexY());
	}

	private void alignCache() {

		preCache.align(getIndexX(), getIndexY());
		updateCache.align(getIndexX(), getIndexY());
	}

	private void loadChunk(int x, int y, MatrixList<Chunk> target)
			throws EntryNotFoundException {
		ChunkUtils.loadChunk(x, y, universe, target);
	}

	private void saveChunk(Chunk chunk, boolean remove) {
		ChunkUtils.saveChunk(chunk, universe, chunks, remove);
	}

	private void saveChunk(Chunk chunk) {
		saveChunk(chunk, false);
	}

	private void createChunk(int x, int y, MatrixList<Chunk> target) {
		Chunk chunk = new BasicChunk(x, y, this);
		target.add(chunk);
		universe.addEntities(chunk.retrieveEntities());
		universe.addLights(chunk.retrieveLights());
		saveChunk(chunk);
	}

	/**
	 * Removes the given chunk from the system. The method saves all containing
	 * entities inside the chunk and removes them from the universe.
	 * 
	 * @param chunk
	 *            target chunk to remove
	 */
	private void removeChunk(Chunk chunk) {
		saveChunk(chunk, true);
	}
	
	private void handleChunk(int indexX, int indexY, MatrixList<Chunk> removeList) {
		if (chunks.contains(indexX, indexY)) {
			removeList.remove(indexX, indexY);
			
			// Add remaining entities and lights to the world
			// when the chunk is inside of the update cache
			try {
				Chunk chunk = chunks.get(indexX, indexY);
				
				if (updateCache.containsIndex(indexX, indexY)) {
					universe.addEntities(chunk.retrieveEntities());
					universe.addLights(chunk.retrieveLights());
				}
			} catch (EntryNotFoundException e) { }
			
		} else {
			try {
				loadChunk(indexX, indexY, chunks);
			} catch (EntryNotFoundException e) {
				createChunk(indexX, indexY, chunks);
			}
		}
	}

	private void computeChunks() {

		alignCache();

		synchronized (chunks) {
			MatrixList<Chunk> removeChunks = chunks.copy();

			// Go through the entire new pre cache
			currentChunkCount = 0;
			for (int indexX = (int) preCache.getIndexTopLeft().x; indexX <= preCache
					.getIndexBottomRight().x; indexX++) {
				for (int indexY = (int) preCache.getIndexTopLeft().y; indexY <= preCache
						.getIndexBottomRight().y; indexY++) {
					handleChunk(indexX, indexY, removeChunks);
					currentChunkCount++;
				}
			}

			// Remove the old chunks
			for (Chunk chunk : removeChunks) {
				removeChunk(chunk);
			}
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	/**
	 * Chunk cache
	 */
	class Cache extends BasicBoundable implements Boundable {

		private Point2f indexTopLeft;
		private Point2f indexBottomLeft;
		private Point2f indexTopRight;
		private Point2f indexBottomRight;
		private int size;

		// Cache Size
		private int cacheSize;

		public Cache(int cacheSize) {
			setCacheSize(cacheSize);
			indexTopLeft = new Point2f(0, 0);
			indexBottomLeft = new Point2f(0, 0);
			indexTopRight = new Point2f(0, 0);
			indexBottomRight = new Point2f(0, 0);
			size = cacheSize * 2 + 1;
			alignBounds();
		}

		public int getCacheSize() {
			return cacheSize;
		}

		public void setCacheSize(int cacheSize) {
			this.cacheSize = cacheSize;
		}

		public boolean containsIndex(int indexX, int indexY) {
			boolean topLeftRange = indexX < indexTopLeft.x
					|| indexY < indexTopLeft.y;
			boolean bottomRightRange = indexX > indexBottomRight.x
					|| indexY > indexBottomRight.y;

			return !(topLeftRange || bottomRightRange);
		}

		public int getSize() {
			return size;
		}

		/**
		 * Align the cache to the new center
		 * 
		 * @param indexX
		 *            x index of the new chunk center
		 * @param indexY
		 *            y index of the new chunk center
		 */
		public void align(int indexX, int indexY) {
			// TOP-LEFT
			indexTopLeft.x = indexX - cacheSize;
			indexTopLeft.y = indexY - cacheSize;

			// TOP-RIGHT
			indexTopRight.x = indexX + cacheSize;
			indexTopRight.y = indexY - cacheSize;

			// BOTTOM-LEFT
			indexBottomLeft.x = indexX - cacheSize;
			indexBottomLeft.y = indexY + cacheSize;

			// BOTTOM-RIGHT
			indexBottomRight.x = indexX + cacheSize;
			indexBottomRight.y = indexY + cacheSize;

			alignBounds();
		}

		/**
		 * @return the indexTopLeft
		 */
		public Point2f getIndexTopLeft() {
			return indexTopLeft;
		}

		/**
		 * @return the indexBottomLeft
		 */
		public Point2f getIndexBottomLeft() {
			return indexBottomLeft;
		}

		/**
		 * @return the indexTopRight
		 */
		public Point2f getIndexTopRight() {
			return indexTopRight;
		}

		/**
		 * @return the indexBottomRight
		 */
		public Point2f getIndexBottomRight() {
			return indexBottomRight;
		}

		private void alignBounds() {
			float left = indexTopLeft.x * ChunkSystem.CHUNK_SIZE;
			float top = indexTopLeft.y * ChunkSystem.CHUNK_SIZE;
			float right = (indexBottomRight.x + 1) * ChunkSystem.CHUNK_SIZE;
			float bottom = (indexBottomRight.y + 1) * ChunkSystem.CHUNK_SIZE;
			setBounds(left, right, top, bottom);
		}

	}

}
