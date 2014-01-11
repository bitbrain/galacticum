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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Internal texture loader for texture rendering
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public interface TextureLoader {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Returns the internal texture for this seed. If not texture was found,
	 * a new one will be created
	 * 
	 * @param hash hash of the texture
	 * @return texture object
	 */
	Texture getTexture(int hash);
	
	/**
	 * Returns the internal texture for this seed as sprite.
	 * 
	 * @param hash
	 * @return
	 */
	Sprite getSprite(int hash);
	
	/**
	 * Disposes the texture which belongs to the hash
	 * 
	 * @param hash
	 */
	void dispose(int hash);
	
	/**
	 * Registers a new hash
	 * 
	 * @param hash
	 */
	void register(int hash);

}
