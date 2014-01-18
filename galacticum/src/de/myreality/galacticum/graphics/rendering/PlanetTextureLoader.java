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
import com.badlogic.gdx.graphics.Pixmap.Format;


/**
 * Loads planet textures
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class PlanetTextureLoader extends SimpleLayeredTextureLoader {

	/**
	 * 
	 */
	public PlanetTextureLoader() {
		super();
		addLayer(new PlanetLayer());
	}

	class PlanetLayer implements TextureLayer {

		/* (non-Javadoc)
		 * @see de.myreality.galacticum.graphics.rendering.TextureLayer#build(int, int, int, java.lang.Iterable, com.badlogic.gdx.graphics.Color)
		 */
		@Override
		public Pixmap build(long hash, int width, int height,
				Iterable<TextureLayer> others, Color color) {
			
			Pixmap map = new Pixmap(width, height, Format.RGBA8888);
			
			map.setColor(color);
			map.fillCircle(width / 2, height / 2, width / 2);
			
			return map;
		}

		/* (non-Javadoc)
		 * @see de.myreality.galacticum.graphics.rendering.TextureLayer#buildEdges(int, int, int, java.lang.Iterable)
		 */
		@Override
		public float[] buildEdges(long hash, int width, int height,
				Iterable<TextureLayer> others) {
			return new float[0];
		}
		
	}
	
}
