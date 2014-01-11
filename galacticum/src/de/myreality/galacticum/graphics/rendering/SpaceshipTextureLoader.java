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

import de.myreality.galacticum.Resources;

/**
 * Loads spaceship textures
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SpaceshipTextureLoader extends AbstractTextureLoader {

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.rendering.AbstractTextureLoader#createTexture(int)
	 */
	@Override
	protected Texture createTexture(int hash, int width, int height) {
		return Resources.TEXTURE_BOX;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.rendering.AbstractTextureLoader#createVertices(int, int, int)
	 */
	@Override
	protected float[] createVertices(int hash, int width, int height) {
		float[] vertices = new float[] { 0, 0, width, 0,
				width, height, 0, height };		
		return vertices;
	}
	
	
}
