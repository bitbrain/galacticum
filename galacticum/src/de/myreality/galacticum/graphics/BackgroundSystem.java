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
import de.myreality.galacticum.core.context.Context;
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
	


	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#onEnter(de.myreality.galacticum.core.context.Context)
	 */
	@Override
	public void onEnter(Context context) {
		// TODO Auto-generated method stub
		
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
	


	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#afterUpdate()
	 */
	@Override
	public void afterUpdate() {
		// TODO Auto-generated method stub
		
	}


	// ===========================================================
	// Methods
	// ===========================================================
	
	private void initBackground() {
		final int starLayers = 8;

		for (int i = 0; i < starLayers; ++i) {
			
			float distance = (float) (Math.pow(i, 1.5) + 7f);
			
			LayerTexture texture = new PreprocessedTexture(512, 512, batch,
					new StarfieldCreator(distance));
			LayerConfig config = new LayerConfig(texture);
			mapper.add(distance, config);
		}

		int fogLayers = 5;
		float veloX = 4f;
		float veloY = 3f;

		LayerTexture fogTexture = new GdxTexture(Resources.TEXTURE_FOG_MEDIUM, batch);

		for (int i = 0; i < fogLayers; ++i) {
			LayerConfig config = new LayerConfig(fogTexture);
			
			veloX *= (i % 2 == 0) ? 1 : -1;
			veloY *= (i % 3 == 0) ? 1 : -1;
			
			config.setVelocity(veloX, veloY);
			config.setTileWidth(450 + i * 20);
			config.setTileHeight(450 + i * 20);
			mapper.add((float) (Math.pow(i, 3) / 5f) + 5, config);
		}

		// Add the background
		LayerTexture backgroundTexture = new GdxTexture(Resources.TEXTURE_SPACE_FAR, batch);
		LayerConfig config = new LayerConfig(backgroundTexture);
		config.setFilter(0.3f, 0.1f, 0.4f, 1.0f);
		config.setTileWidth(256);
		config.setTileHeight(256);
		mapper.add(20f, config);
		
		backgroundTexture = new GdxTexture(Resources.TEXTURE_SPACE_FAR, batch);
		config = new LayerConfig(backgroundTexture);
		config.setFilter(0.3f, 0.1f, 0.4f, 0.3f);
		config.setTileWidth(450);
		config.setTileHeight(450);
		mapper.add(15f, config);
		
		backgroundTexture = new GdxTexture(Resources.TEXTURE_SPACE_FAR, batch);
		config = new LayerConfig(backgroundTexture);
		config.setFilter(0.3f, 0.1f, 0.4f, 0.4f);
		mapper.add(12f, config);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	class StarfieldCreator implements GdxTextureProcessor {
		
		private float distance;
		
		public StarfieldCreator(float distance) {
			this.distance = distance;
		}

		@Override
		public void process(Pixmap map) {

			int starAmount = (int) (Math.pow(distance, 3) / 10f);

			for (int i = 0; i < starAmount; ++i) {
				drawStar((float) (512 * Math.random()),
						(float) (512 * Math.random()), map);
			}
		}

		private void drawStar(float x, float y, Pixmap map) {
			Color color = new Color(255, 255, 255, 255);
			float size = 10f / distance;
			if (Math.random() < 0.05f) {
				size += 0.5f;
			} else if (Math.random() < 0.08f) {
				size += 0.3f;
			} else if (Math.random() < 0.1f) {
				size += 0.2f;
			}
			color.r = (float) (Math.random() * 0.4f + 0.6f);
			color.g = (float) (Math.random() * 0.4f + 0.6f);
			color.b = (float) (Math.random() * 0.4f + 0.6f);
			color.a = (float) (Math.random() * 0.3f + 0.7f);
			
			map.setColor(color);
			map.fillRectangle((int) (x - size / 2f), (int) (y - size / 2f),
					(int) (size * 2f), (int) (size * 2f));

			color.a = 1.0f;
		}

	}
}
