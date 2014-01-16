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
import de.myreality.galacticum.Settings;
import de.myreality.galacticum.context.Context;
import de.myreality.galacticum.core.SimpleWorldListener;
import de.myreality.galacticum.modules.ProgressListener;
import de.myreality.galacticum.modules.Module;
import de.myreality.galacticum.modules.ModuleException;
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
public class BackgroundRenderingModule extends SimpleWorldListener implements Module {

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

	public BackgroundRenderingModule(Viewport viewport, SpriteBatch batch) {
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
	public void start() throws ModuleException {

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
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	private void initBackground() {		
		
		final int starLayers = getStarLayerCount();

		for (int i = 0; i < starLayers; ++i) {
			
			float distance = (float) (Math.pow(i, 1.5) + 7f);
			
			LayerTexture texture = new PreprocessedTexture(getStarTetureSize(), getStarTetureSize(), batch,
					new StarfieldCreator(distance));
			LayerConfig config = new LayerConfig(texture);
			mapper.add(distance, config);
		}

		int fogLayers = getFogLayerCount();
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
        config.setFilter(0.5f, 0.3f, 0.4f, 1.0f);
        int size = Math.round(Gdx.graphics.getWidth() / 2);
        config.setTileWidth(size);
        config.setTileHeight(size);
        mapper.add(25f, config);
        
        addDepthLayers();
	}
	
	private int getStarTetureSize() {
		return Math.round(Gdx.graphics.getWidth() / 1.8f);
	}
	
	private int getStarLayerCount() {
		switch (Settings.quality) {
			case EXTREME:
				return 10;
			case HIGH:
				return 7;
			case LOW:
				return 3;
			case MEDIUM:
				return 5;
		}
		
		return 5;
	}
	
	private int getFogLayerCount() {
		switch (Settings.quality) {
		case EXTREME:
			return 5;
		case HIGH:
			return 4;
		case LOW:
			return 1;
		case MEDIUM:
			return 3;
		}
		
		return 5;
	}
	
	private void addDepthLayers() {
		int size = Math.round(Gdx.graphics.getWidth() / 1.2f);
		
		for (int i = 0; i < getDepthLayerCount(); ++i) {
			GdxTexture backgroundTexture = new GdxTexture(Resources.TEXTURE_SPACE_FAR, batch);
			LayerConfig config = new LayerConfig(backgroundTexture);
			config.setFilter(0.5f, 0.3f, 0.4f, 0.5f);
			config.setTileWidth(size);
			config.setTileHeight(size);
			
			float veloX = 1.3f;
			float veloY = 1.5f;
			
			veloX *= i % 2 == 0 ? 1 : -1;
			veloY *= i % 3 == 0 ? 1 : -1;
			
			config.setVelocity(veloX, veloY);
			mapper.add((float) (Math.pow(i, 2) + 8), config);
			
			size *= 1.2f;
		}
	}
	
	private int getDepthLayerCount() {
		switch (Settings.quality) {
			case EXTREME:
				return 5;
			case HIGH:
				return 4;
			case LOW:
				return 1;
			case MEDIUM:
				return 3;
			}
		
		return 5;
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

			int starAmount = (int) (Math.pow(distance, 4) / 45f);

			for (int i = 0; i < starAmount; ++i) {
				drawStar((float) (getStarTetureSize() * Math.random()),
						(float) (getStarTetureSize() * Math.random()), map);
			}
		}

		private void drawStar(float x, float y, Pixmap map) {
			Color color = new Color(255, 255, 255, 255);
			float size = 12f / distance;
			if (Math.random() < 0.05f) {
				size += 0.5f;
			} else if (Math.random() < 0.08f) {
				size += 0.3f;
			} else if (Math.random() < 0.1f) {
				size += 0.2f;
			}
			color.r = (float) (Math.random() * 0.5f + 0.5f);
			color.g = (float) (Math.random() * 0.5f + 0.5f);
			color.b = (float) (Math.random() * 0.5f + 0.5f);
			color.a = (float) (Math.random() * 0.4f + 0.6f);
			
			map.setColor(color);
			map.fillRectangle((int) (x - size / 2f), (int) (y - size / 2f),
					(int) (size * 2f), (int) (size * 2f));

			color.a = 1.0f;
		}

	}
}
