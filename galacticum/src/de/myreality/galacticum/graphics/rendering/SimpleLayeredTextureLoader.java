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

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;

/**
 * Simple implementation of {@see SpaceshipTextureLoader}
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since
 * @version
 */
public class SimpleLayeredTextureLoader  extends AbstractTextureLoader implements LayeredTextureLoader {
	
	private List<TextureLayer> layers;
	
	public SimpleLayeredTextureLoader() {
		layers = new ArrayList<TextureLayer>();
	}
	
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.rendering.AbstractTextureLoader#createTexture(int)
	 */
	@Override
	protected Texture createTexture(int hash, int width, int height) {
		
		Pixmap map = new Pixmap(width, height, Format.RGBA8888);
        
		Gdx.gl.glEnable(GL10.GL_BLEND);
        Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        
        for (TextureLayer layer : layers) {
        	Pixmap layerMap = layer.build(hash, width, height, layers, Color.WHITE);
        	map.drawPixmap(layerMap, 0, 0, 0, 0, width, height);
        	layerMap.dispose();
        }
        
        Texture texture = new Texture(map);
        
		Gdx.gl.glDisable(GL10.GL_BLEND);
		
		return texture;		
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.rendering.AbstractTextureLoader#createVertices(int, int, int)
	 */
	@Override
	protected float[] createVertices(int hash, int width, int height) {
		
		List<float[]> verticesList = new ArrayList<float[]>();
		
		for (TextureLayer layer : layers) {
			float[] vertices = layer.buildEdges(hash, width, height, layers);
			verticesList.add(vertices);
		}
		
		return createBounds(verticesList, width, height);
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.rendering.spaceships.LayeredTextureLoader#addLayer(de.myreality.galacticum.graphics.rendering.spaceships.TextureLayer)
	 */
	@Override
	public void addLayer(TextureLayer layer) {
		layers.add(layer);
	}
	
	
	private float[] createBounds(List<float[]> verticesList, float width, float height) {
		float[] vertices = new float[] { 0, 0, width, 0,
                width, height, 0, height };                
		return vertices;
	}

}
