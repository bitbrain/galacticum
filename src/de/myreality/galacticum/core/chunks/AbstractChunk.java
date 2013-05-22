package de.myreality.galacticum.core.chunks;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.util.Log;

import de.myreality.galacticum.core.BasicBoundable;
import de.myreality.galacticum.core.Boundable;
import de.myreality.galacticum.core.Entity;
import de.myreality.galacticum.core.lighting.Light;
import de.myreality.galacticum.exceptions.OutOfChunkException;
import de.myreality.galacticum.util.Seed;

public abstract class AbstractChunk extends BasicBoundable implements Chunk {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private Seed seed;

	private int indexX;

	private int indexY;

	private List<Entity> entities;

	private List<Light> lights;

	// ===========================================================
	// Constructors
	// ===========================================================

	public AbstractChunk() {

	}

	public AbstractChunk(int x, int y, ChunkSystem system) {
		seed = system.getUniverse().getSeed();
		indexX = x;
		indexY = y;
		entities = new CopyOnWriteArrayList<Entity>();
		lights = new CopyOnWriteArrayList<Light>();

		initBounds();
		initChunk();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(seed);
		out.writeInt(indexX);
		out.writeInt(indexY);
		out.writeObject(entities);
		out.writeObject(lights);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		seed = (Seed) in.readObject();
		indexX = in.readInt();
		indexY = in.readInt();
		this.entities = (CopyOnWriteArrayList<Entity>) in.readObject();
		this.lights = (CopyOnWriteArrayList<Light>) in.readObject();
		// system.getRenderer().addTarget(rendable);
		initBounds();
	}

	@Override
	public void addEntities(Collection<? extends Entity> entities) {
		synchronized (entities) {
			for (Entity entity : entities) {
				try {
					add(entity);
				} catch (OutOfChunkException e) {
					Log.error(e.getMessage());
				}
			}
		}
	}

	@Override
	public void addLights(Collection<? extends Light> lights) {
		synchronized (lights) {
			for (Light light : lights) {
				try {
					add(light);
				} catch (OutOfChunkException e) {
					Log.error(e.getMessage());
				}
			}
		}
	}

	@Override
	public Collection<Light> retrieveLights() {
		List<Light> result = new ArrayList<Light>();
		result.addAll(lights);
		lights.clear();

		return result;
	}

	@Override
	public Collection<Entity> retrieveEntities() {
		List<Entity> result = new ArrayList<Entity>();
		result.addAll(entities);
		entities.clear();

		return result;
	}

	@Override
	public void clear() {
		lights.clear();
		entities.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Indexable#getCurrentIndexX()
	 */
	@Override
	public int getIndexX() {
		return indexX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Indexable#getCurrentIndexY()
	 */
	@Override
	public int getIndexY() {
		return indexY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Index: " + getIndexX() + "|" + getIndexY() + ", Bounds: " + super.toString();
	}


	/**
	 * Adds a new entity to the chunk. The entity must be at least inside of
	 * this chunk. Otherwise an exception will be thrown.
	 * 
	 * @param entity
	 *            Target entity to add
	 * @exception OutOfChunkException
	 *                Is thrown when the entity is outside of the chunk
	 */
	@Override
	public void add(Entity entity) throws OutOfChunkException {
		if (checkBoundable(entity)) {
			if (!entities.contains(entity)) {
				entities.add(entity);
			}
		} else {
			throw new OutOfChunkException(this, entity);
		}
	}

	/**
	 * Adds a new light to the chunk. The light must be at least inside of this
	 * chunk. Otherwise an exception will be thrown.
	 * 
	 * @param light
	 *            Target light to add
	 * @exception OutOfChunkException
	 *                Is thrown when the light is outside of the chunk
	 */
	@Override
	public void add(Light light) throws OutOfChunkException {
		if (checkBoundable(light)) {
			if (!lights.contains(light)) {
				lights.add(light);
			}
		} else {
			throw new OutOfChunkException(this, light);
		}
	}
	// ===========================================================
	// Methods
	// ===========================================================


	private boolean checkBoundable(Boundable boundable) {
		return contains(boundable.getCenterX(), boundable.getCenterY());
	}

	protected abstract void initChunk();
	
	private void initBounds() {

		float left = indexX * ChunkSystem.CHUNK_SIZE;
		float right = left + ChunkSystem.CHUNK_SIZE - 1;
		float top = indexY * ChunkSystem.CHUNK_SIZE;
		float bottom = top + ChunkSystem.CHUNK_SIZE - 1;

		setBounds(left, right, top, bottom);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
