package de.myreality.galacticum.core.chunks;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.util.Log;

import de.myreality.galacticum.core.SampleEntity;
import de.myreality.galacticum.core.lighting.Light;
import de.myreality.galacticum.exceptions.OutOfChunkException;

/**
 * A basic implementation of a chunk which defines entities for a specific chunk
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class BasicChunk extends AbstractChunk implements Chunk {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * Default constructor
	 */
	public BasicChunk() {
	}

	/**
	 * @param x
	 *            horizontal index of the chunk
	 * @param y
	 *            vertical index of the chunk
	 * @param system
	 *            target chunk system
	 */
	public BasicChunk(int x, int y, ChunkSystem system) {
		super(x, y, system);
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

	private void addRandomLight() {
		Random random = new Random();
		Light light = new Light(getRandomX(), getRandomY(), 500);
		light.setColor(new Color(random.nextFloat(), random.nextFloat(), random
				.nextFloat()));
		try {
			add(light);
		} catch (OutOfChunkException e) {
			Log.error(e.getMessage());
		}
	}

	private void addRandomEntity() {
		try {
			add(new SampleEntity(getRandomX(50), getRandomY(50)));
		} catch (OutOfChunkException e) {
			Log.error(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.chunks.AbstractChunk#initChunk()
	 */
	@Override
	protected void initChunk() {

		final int ENTITY_COUNT = 80;
		final int LIGHT_COUNT = 1;

		for (int i = 0; i < ENTITY_COUNT; ++i) {
			addRandomEntity();
		}

		for (int i = 0; i < LIGHT_COUNT; ++i) {
			if (Math.random() < 0.3) {
				addRandomLight();
			}
		}
	}

	private float getRandomX() {
		return getRandomX(0);
	}

	private float getRandomY() {
		return getRandomY(0);
	}

	private float getRandomY(float delta) {
		return (float) (getTop() + Math.random()
				* (ChunkSystem.CHUNK_SIZE - delta));
	}

	private float getRandomX(float delta) {
		return (float) (getLeft() + Math.random()
				* (ChunkSystem.CHUNK_SIZE - delta));
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
