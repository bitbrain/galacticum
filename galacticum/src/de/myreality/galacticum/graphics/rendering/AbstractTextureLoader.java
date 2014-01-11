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
package de.myreality.galacticum.graphics.rendering;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Abstract texture loader which handles basic logic
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public abstract class AbstractTextureLoader implements TextureLoader {

	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Map<Integer, Texture> textures;
	
	private Map<Integer, Sprite> sprites;
	
	private Map<Integer, Integer> entities;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public AbstractTextureLoader() {
		textures = new HashMap<Integer, Texture>();
		sprites = new HashMap<Integer, Sprite>();
		entities = new HashMap<Integer, Integer>();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.rendering.TextureLoader#getTexture(int)
	 */
	@Override
	public Texture getTexture(int hash, int width, int height) {
		
		Texture texture = textures.get(hash);
		
		if (texture == null) {
			texture = createTexture(hash, width, height);
			textures.put(hash, texture);
			Sprite sprite = new Sprite(texture);
			sprites.put(hash, sprite);
		}
		
		return texture;		
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.rendering.TextureLoader#getSprite(int)
	 */
	@Override
	public Sprite getSprite(int hash, int width, int height) {		
		getTexture(hash, width, height);		
		return sprites.get(hash);
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.rendering.TextureLoader#dispose(int)
	 */
	@Override
	public void dispose(int hash) {
		
		Integer entityCount = entities.get(hash);
		
		if (entityCount != null) {
			
			entityCount--;
			
			if (entityCount < 1) {
				entities.remove(hash);
				Texture texture = textures.remove(hash);
				entities.remove(hash);
				sprites.remove(hash);
				
				if (texture != null) {
					texture.dispose();
				}
			} else {
				entities.put(hash, entityCount);
			}
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.rendering.TextureLoader#register(int)
	 */
	@Override
	public void register(int hash) {
		
		Integer entityCount = entities.get(hash);
		
		if (entityCount == null) {
			entityCount = 1;
		} else {
			entityCount++;
		}
		
		entities.put(hash, entityCount);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	protected abstract Texture createTexture(int hash, int width, int height);
	
	protected abstract float[] createVertices(int hash, int width, int height);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
