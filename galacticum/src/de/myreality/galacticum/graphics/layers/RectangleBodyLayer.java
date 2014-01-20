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
package de.myreality.galacticum.graphics.layers;

import com.badlogic.gdx.graphics.Pixmap;

import de.myreality.galacticum.graphics.rendering.AbstractTextureLayer;
import de.myreality.galacticum.graphics.rendering.TextureLayer;

/**
 * Generates a rectangle formed body layer
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class RectangleBodyLayer extends AbstractTextureLayer {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * @param shadingEnabled
	 */
	public RectangleBodyLayer(boolean shadingEnabled) {
		super(shadingEnabled);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.rendering.TextureLayer#buildEdges(long, int, int, java.lang.Iterable)
	 */
	@Override
	public float[] buildEdges(long hash, int width, int height,
			Iterable<TextureLayer> others) {
		return new float[0];
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.rendering.AbstractTextureLayer#draw(com.badlogic.gdx.graphics.Pixmap, int, int, java.lang.Iterable)
	 */
	@Override
	protected void draw(Pixmap map, int width, int height,
			Iterable<TextureLayer> others, long hash) {
		
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
