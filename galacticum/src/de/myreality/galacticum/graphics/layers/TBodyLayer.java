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
import com.badlogic.gdx.math.Rectangle;

import de.myreality.galacticum.graphics.rendering.AbstractTextureLayer;
import de.myreality.galacticum.graphics.rendering.TextureLayer;

/**
 * Generates a T-formed body layer
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class TBodyLayer extends AbstractTextureLayer {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public TBodyLayer(boolean shaded) {
		super(shaded);
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
	 * @see
	 * de.myreality.galacticum.graphics.rendering.TextureLayer#buildEdges(long,
	 * int, int, java.lang.Iterable)
	 */
	@Override
	public float[] buildEdges(long hash, int width, int height,
			Iterable<TextureLayer> others) {
		Rectangle first = createHorizontal(width, height, hash);
		//Rectangle second = createVertical(width, height, hash);
		
		float[] edges = new float[8];
		
		edges[0] = first.x;
		edges[1] = first.y;
		
		edges[2] = first.x + first.width;
		edges[3] = first.y;
		
		edges[4] = first.x + first.width;
		edges[5] = first.y + first.height;
		
		edges[6] = first.x;
		edges[7] = first.y + first.height;
		
		return edges;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.graphics.rendering.AbstractTextureLayer#draw(
	 * com.badlogic.gdx.graphics.Pixmap, int, int, java.lang.Iterable)
	 */
	@Override
	protected void draw(Pixmap map, int width, int height,
			Iterable<TextureLayer> others, long hash) {
		Rectangle first = createHorizontal(width, height, hash);
		@SuppressWarnings("unused")
		Rectangle second = createVertical(width, height, hash);
		
		map.fillRectangle((int)first.x, (int)first.y, (int)first.width, (int)first.height);
		map.fillRectangle(0, 0, width, height);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private Rectangle createHorizontal(int width, int height, long hash) {
		return new Rectangle(0, 0, 100, 50);
	}
	
	private Rectangle createVertical(int width, int height, long hash) {
		return new Rectangle(0, 0, 50, 100);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
