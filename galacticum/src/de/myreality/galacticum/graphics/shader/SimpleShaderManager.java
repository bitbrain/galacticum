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
package de.myreality.galacticum.graphics.shader;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * Simple implementation of {@see ShaderManager}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SimpleShaderManager implements ShaderManager {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Map<Shader, FrameBuffer> shaders;
	
	private SpriteBatch batch;
	
	private ShaderTarget target;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleShaderManager(ShaderTarget target) {
		shaders = new HashMap<Shader, FrameBuffer>();
		batch = new SpriteBatch();
		this.target = target;
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
	 * de.myreality.galacticum.graphics.shader.ShaderManager#add(com.badlogic
	 * .gdx.graphics.glutils.ShaderProgram,
	 * de.myreality.galacticum.graphics.shader.ShaderBehavior)
	 */
	@Override
	public void add(Shader shader) {
		shaders.put(shader, new FrameBuffer(Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.graphics.shader.ShaderManager#update(float)
	 */
	@Override
	public void updateAndRender(float delta) {
		
		FrameBuffer previousBuffer = null;
		
		for (Entry<Shader, FrameBuffer> entry : shaders.entrySet()) {
			
			Shader shader = entry.getKey();
			FrameBuffer buffer = entry.getValue();
			ShaderProgram program = shader.getShaderProgram();
			
			buffer.begin();
			
				if (program != null) {
					batch.setShader(program);
				}
				
				batch.begin();
				
					if (program != null) {
						shader.update(program, batch, delta);
					}
					
					if (previousBuffer == null) {
						target.draw(batch, delta);
					} else {
						TextureRegion region = new TextureRegion(previousBuffer.getColorBufferTexture());
						batch.draw(region, 0f, 0f);
					}
				
				batch.end();
			
			buffer.end();
			
			previousBuffer = buffer;
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.shader.ShaderManager#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		
		for (FrameBuffer buffer : shaders.values()) {
			buffer.dispose();
		}
		
		for (Shader program : shaders.keySet()) {
			shaders.put(program, new FrameBuffer(Format.RGBA8888, width, height, false));
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.shader.ShaderManager#getBatch()
	 */
	@Override
	public Batch getBatch() {
		return batch;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
