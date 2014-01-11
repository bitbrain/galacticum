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
package de.myreality.galacticum.graphics.rendering.spaceships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

import de.myreality.galacticum.Resources;
import de.myreality.galacticum.graphics.rendering.AbstractTextureLoader;

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
		
		Pixmap map = new Pixmap(width, height, Format.RGBA8888);
		Texture gradient = Resources.TEXTURE_GRADIENT;
		
		Gdx.gl.glEnable(GL10.GL_BLEND);
        Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        
        int scaleFactor = 60;
        int padding = 20;
        
        TextureData data = gradient.getTextureData();
		data.prepare();
        Pixmap gradientMap = data.consumePixmap();
        
		map.setColor(1f, 1f, 1f, 1f);
		map.fill();
		
		map.drawPixmap(gradientMap, 0, 0, gradient.getWidth(), gradient.getHeight(), -scaleFactor, -scaleFactor, width + scaleFactor * 2, height + scaleFactor * 2);
		
		map.setColor(0.5f, 0.5f, 0.5f, 0.4f);
		map.fillRectangle(padding, padding, width - padding * 2, height - padding * 2);
		
		Texture texture = new Texture(map);
		map.dispose();
		gradientMap.dispose();
		Gdx.gl.glDisable(GL10.GL_BLEND);
		
		return texture;		
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
