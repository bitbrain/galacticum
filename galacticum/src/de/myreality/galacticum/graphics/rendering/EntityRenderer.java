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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.galacticum.core.entities.Entity;
import de.myreality.galacticum.core.entities.EntityType;
import de.myreality.galacticum.util.VerticesProvider;

/**
 * Renders entities which are focused by the current game camera
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public interface EntityRenderer extends VerticesProvider {
	
	/**
	 * Registers a new entity
	 * 
	 * @param entity
	 */
	void register(Entity entity);
	
	/**
	 * Disposes an existing entity 
	 * 
	 * @param entity
	 */
	void dispose(Entity entity);
	
	/**
	 * Renders an entity
	 * 
	 * @param entity
	 * @param batch
	 */
	void render(Entity entity, SpriteBatch batch);
	
	/**
	 * Adds a new texture loader
	 * 
	 * @param type
	 * @param loader
	 */
	void addTextureLoader(EntityType type, TextureLoader loader);

}
