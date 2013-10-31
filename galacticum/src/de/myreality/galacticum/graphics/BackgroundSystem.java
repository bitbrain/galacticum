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
package de.myreality.galacticum.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.galacticum.Resources;
import de.myreality.galacticum.core.subsystem.ProgressListener;
import de.myreality.galacticum.core.subsystem.Subsystem;
import de.myreality.galacticum.core.subsystem.SubsystemException;
import de.myreality.parallax.LayerConfig;
import de.myreality.parallax.LayerTexture;
import de.myreality.parallax.ParallaxMapper;
import de.myreality.parallax.Viewport;
import de.myreality.parallax.libgdx.GdxTexture;
import de.myreality.parallax.libgdx.GdxTextureProcessor;
import de.myreality.parallax.libgdx.PreprocessedTexture;

/**
 * Provides background rendering of the world
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class BackgroundSystem implements Subsystem {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private ParallaxMapper mapper;

	private Viewport viewport;

	private SpriteBatch batch;

	// ===========================================================
	// Constructors
	// ===========================================================

	public BackgroundSystem(Viewport viewport, SpriteBatch batch) {
		this.viewport = viewport;
		this.batch = batch;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Nameable#getName()
	 */
	@Override
	public String getName() {
		return "background";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#start()
	 */
	@Override
	public void start() throws SubsystemException {

		mapper = new ParallaxMapper(viewport);

		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				initBackground();
			}

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#update(float)
	 */
	@Override
	public void update(float delta) {
		mapper.updateAndRender(delta + 0.5f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#shutdown()
	 */
	@Override
	public void shutdown() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.subsystem.Subsystem#addProgressListener(
	 * de.myreality.galacticum.core.subsystem.ProgressListener)
	 */
	@Override
	public void addProgressListener(ProgressListener listener) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.subsystem.Subsystem#removeProgressListener
	 * (de.myreality.galacticum.core.subsystem.ProgressListener)
	 */
	@Override
	public void removeProgressListener(ProgressListener listener) {

	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private void initBackground() {
		final int starLayers = 8;

		for (int i = 0; i < starLayers; ++i) {
			LayerTexture texture = new PreprocessedTexture(256, 256, batch,
					new StarfieldCreator());
			LayerConfig config = new LayerConfig(texture);
			mapper.add((float) (Math.pow(i, 1.2) + 5f), config);
		}

		int fogLayers = 5;
		float veloX = 6f;
		float veloY = 8f;

		LayerTexture fogTexture = new GdxTexture(Resources.TEXTURE_FOG_MEDIUM, batch);

		for (int i = 0; i < fogLayers; ++i) {
			LayerConfig config = new LayerConfig(fogTexture);
			config.setVelocity(veloX, veloY);
			mapper.add((float) (Math.sin(i) + 5), config);
		}

		// Add the background
		LayerTexture backgroundTexture = new GdxTexture(Resources.TEXTURE_SPACE_FAR, batch);
		LayerConfig config = new LayerConfig(backgroundTexture);
		config.setFilter(0.3f, 0.1f, 0.4f, 1.0f);
		mapper.add(20f, config);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	class StarfieldCreator implements GdxTextureProcessor {

		@Override
		public void process(Pixmap map) {

			int starAmount = (int) (3 * 40);

			for (int i = 0; i < starAmount; ++i) {
				drawStar((float) (400 * Math.random()),
						(float) (400 * Math.random()), map);
			}
		}

		private void drawStar(float x, float y, Pixmap map) {
			Color color = new Color(255, 255, 255, 255);
			float size = 0.5f;
			if (Math.random() < 0.03f) {
				size += 0.5f;
			} else if (Math.random() < 0.05f) {
				size += 0.6f;
			} else if (Math.random() < 0.08f) {
				size += 0.2f;
			}
			color.r = (float) (Math.random() * 0.4f + 0.6f);
			color.g = (float) (Math.random() * 0.4f + 0.6f);
			color.b = (float) (Math.random() * 0.4f + 0.6f);

			map.setColor(color);
			map.fillRectangle((int) (x - size / 2f), (int) (y - size / 2f),
					(int) (size * 2f), (int) (size * 2f));

			color.a = 1.0f;
		}

	}

}
