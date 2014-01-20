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
	
	private static final int DEFAULT_ADDITIONAL_HASH     = 1;
    
    private static final int VALUE_EXPONENT              = 2;
    
    private static final float SHIFTING_MIN              = 2.1f;
    
    private static final float SHIFTING_MAX              = 2.5f;
    
    private static final float MIN_FIRST_WIDTH_DIVIDER   = 2f;
    
    private static final float MAX_FIRST_WIDTH_DIVIDER   = 2.5f;
    
    private static final float MIN_SECOND_WIDTH_DIVIDER  = 1.1f;
    
    private static final float MAX_SECOND_WIDTH_DIVIDER  = 1.2f;
    
    private static final float MIN_FIRST_HEIGHT_DIVIDER  = 1.1f;
    
    private static final float MAX_FIRST_HEIGHT_DIVIDER  = 1.2f;
    
    private static final float MIN_SECOND_HEIGHT_DIVIDER = 2.2f;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public TBodyLayer() {
		super(true);
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
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.rendering.AbstractTextureLayer#draw(com.badlogic.gdx.graphics.Pixmap, int, int, java.lang.Iterable)
	 */
	@Override
	protected void draw(Pixmap map, int width, int height,
			Iterable<TextureLayer> others) {
		// TODO Auto-generated method stub

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
