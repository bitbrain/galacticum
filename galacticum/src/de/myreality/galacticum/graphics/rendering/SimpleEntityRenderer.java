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

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.galacticum.entities.Entity;
import de.myreality.galacticum.entities.EntityType;
import de.myreality.galacticum.graphics.GameCamera;
import de.myreality.galacticum.util.GameColor;

/**
 * Simple implementation of {@see EntityRenderer}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SimpleEntityRenderer implements EntityRenderer {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private GameCamera camera;

	private Map<EntityType, TextureLoader> textureLoaders;
	
	private float[] nullVertices = new float[0];

	// ===========================================================
	// Constructors
	// ===========================================================

	public SimpleEntityRenderer(GameCamera camera) {
		this.camera = camera;
		textureLoaders = new HashMap<EntityType, TextureLoader>();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void render(Entity entity, SpriteBatch batch) {
		
		if (camera.collidesWith(entity)) {
			
			TextureLoader loader = textureLoaders.get(entity.getType());
			
			if (loader != null) {
				GameColor c = entity.getColor();
				Sprite sprite = loader.getSprite(entity.getHash(), Math.round(entity.getWidth()), Math.round(entity.getHeight()));				
				batch.setColor(c.r, c.g, c.b, c.a);
				batch.draw(sprite, entity.getX(), entity.getY(), 0, 0, entity.getWidth(), entity.getHeight(), 1f, 1f, entity.getRotation());
			}
        }
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.graphics.rendering.EntityRenderer#addTextureLoader
	 * (de.myreality.galacticum.core.entities.EntityType,
	 * de.myreality.galacticum.graphics.rendering.TextureLoader)
	 */
	@Override
	public void addTextureLoader(EntityType type, TextureLoader loader) {
		textureLoaders.put(type, loader);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.graphics.rendering.EntityRenderer#register(de
	 * .myreality.galacticum.core.entities.Entity)
	 */
	@Override
	public void register(Entity entity) {
		TextureLoader loader = textureLoaders.get(entity.getType());
		
		if (loader != null) {
			loader.register(entity.getHash());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.graphics.rendering.EntityRenderer#dispose(de.
	 * myreality.galacticum.core.entities.Entity)
	 */
	@Override
	public void dispose(Entity entity) {
		TextureLoader loader = textureLoaders.get(entity.getType());
		
		if (loader != null) {
			loader.dispose(entity.getHash());
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.rendering.EntityRenderer#getVerticesFor(de.myreality.galacticum.core.entities.Entity)
	 */
	@Override
	public float[] getVerticesFor(Entity entity) {
		TextureLoader loader = textureLoaders.get(entity.getType());
		
		if (loader != null) {
			return loader.getVertices(entity.getHash(), Math.round(entity.getWidth()), Math.round(entity.getHeight()));
		} else {
			return nullVertices;
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
