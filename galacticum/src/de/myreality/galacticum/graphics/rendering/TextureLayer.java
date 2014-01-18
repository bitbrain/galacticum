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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

/**
 * Miguel Gonzalez <miguel-gonzalez@gmx.de>
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 
 * @version
 */
public interface TextureLayer {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Builds a new texture layer
	 * 
	 * @param hash
	 * @param width
	 * @param height
	 * @param others
	 * @param color
	 * @return
	 */
	Pixmap build(long hash, int width, int height, Iterable<TextureLayer> others, Color color);
	
	/**
	 * Builds the edges of this layer
	 * 
	 * @param hash
	 * @param width
	 * @param height
	 * @param others
	 * @return
	 */
	float[] buildEdges(long hash, int width, int height, Iterable<TextureLayer> others);

}
