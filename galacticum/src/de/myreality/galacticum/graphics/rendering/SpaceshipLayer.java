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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

import de.myreality.galacticum.Resources;

/**
 * Generates spaceship textures depending on the hash
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SpaceshipLayer implements TextureLayer {

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.rendering.TextureLayer#build(int, int, int, java.lang.Iterable, com.badlogic.gdx.graphics.Color)
	 */
	@Override
	public Pixmap build(int hash, int width, int height,
			Iterable<TextureLayer> others, Color color) {
		
		Pixmap map = new Pixmap(width, height, Format.RGBA8888);
		map.setColor(color);
		map.fill();
		
		createGradient(map, 0, 0, width, height, color);
		
		return map;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.rendering.TextureLayer#buildEdges(int, int, int, java.lang.Iterable)
	 */
	@Override
	public float[] buildEdges(int hash, int width, int height,
			Iterable<TextureLayer> others) {
		return new float[0];
	}
	
	
	private void createGradient(Pixmap original, int x, int y, int width, int height, Color color) {
		
		Texture gradient = Resources.TEXTURE_GRADIENT;
		TextureData data = gradient.getTextureData();
		data.prepare();
		Pixmap gradientMap = data.consumePixmap();
		original.setColor(color);
		
		float padding = 80;
		
		x -= padding;
		y -= padding;
		width += padding * 2;
		height += padding * 2;
		
        
		if (x + width < y + height) {
			original.drawPixmap(gradientMap, 0, 0, original.getWidth(), original.getHeight(), x, y - (width - height) / 2, width, width);
		} else {
			original.drawPixmap(gradientMap, 0, 0, original.getWidth(), original.getHeight(), x - (height - width) / 2, y, height, height);
		}		
		
		gradientMap.dispose();
	}

}