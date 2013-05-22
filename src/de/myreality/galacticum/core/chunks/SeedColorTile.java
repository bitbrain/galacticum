package de.myreality.galacticum.core.chunks;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.core.rendering.RenderTarget;
import de.myreality.galacticum.util.ImagePack;
import de.myreality.galacticum.util.Renderable;
import de.myreality.galacticum.util.Seed;

class SeedColorTile implements Renderable, Externalizable, RenderTarget {

	private Color seedColor;
	private Image sprite;
	private boolean rendered;

	private Seed seed;

	private int indexX, indexY;

	public SeedColorTile(Chunk chunk, ChunkSystem system) {
		seed = system.getUniverse().getSeed();

		indexX = chunk.getIndexX();
		indexY = chunk.getIndexX();

		seedColor = seedColor();
	}

	public SeedColorTile() {
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		sprite = ImagePack.SPACE;
		if (sprite != null && seedColor != null) {
			sprite.draw(indexX * ChunkSystem.CHUNK_SIZE, indexY
					* ChunkSystem.CHUNK_SIZE, ChunkSystem.CHUNK_SIZE,
					ChunkSystem.CHUNK_SIZE, Color.gray);
		}

	}

	private Color seedColor() {
		int hash = seed.hashCode();
		double value = hash / 100000f / (indexX + 500 + indexY)
				+ (0.1f * Math.random());
		return new Color(getValue(0.6f, 1.0f, value * 2),
				getValue(0.6f, 1f, value * 3), getValue(0.6f, 1f, value / 2));
	}

	private float getValue(float min, float max, double x) {
		float difference = (max - min) / 2.0f;
		return (float) (difference * Math.sin(x) + max - difference);
	}

	@Override
	public void beforeRender() {
		// TODO Auto-generated method stub

	}

	@Override
	public void computeRendering(GameContainer gc, Graphics g) {
		/*
		 * try { sprite = new Image(100, 100); Graphics g2 =
		 * sprite.getGraphics(); g2.setColor(Color.green); g2.fillRect(0, 0,
		 * 100, 100); g2.flush(); } catch (SlickException e) {
		 * e.printStackTrace(); }
		 */
	}

	@Override
	public void afterRender() {
		rendered = true;
	}

	@Override
	public boolean hasRendered() {
		return rendered;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(seed);
		out.writeInt(indexX);
		out.writeInt(indexY);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		seed = (Seed) in.readObject();
		indexX = in.readInt();
		indexY = in.readInt();
		seedColor = seedColor();
	}
}
