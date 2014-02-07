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

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

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

	private List<ShaderData> data;
	
	private FrameBuffer initialBuffer, bufferA, bufferB;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SimpleShaderManager(int width, int height) {
		data = new ArrayList<ShaderData>();
		initBuffers(width, height);
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
	 * de.myreality.galacticum.graphics.shader.ShaderManager#add(de.myreality
	 * .galacticum.graphics.shader.ShadeArea,
	 * de.myreality.galacticum.graphics.shader.Shader[])
	 */
	@Override
	public void add(ShadeArea shaderTarget, Shader<?> ... shaders) {
		ShaderData shaderData = new ShaderData(shaderTarget, shaders);
		data.add(shaderData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.graphics.shader.ShaderManager#updateAndRender
	 * (com.badlogic.gdx.graphics.g2d.SpriteBatch, float)
	 */
	@Override
	public void updateAndRender(SpriteBatch batch, float delta) {

		FrameBuffer previousBuffer = initialBuffer;
		FrameBuffer currentBuffer = bufferA;
		
		// Iterate through each shader data and apply a buffer to it
		for (int dataIndex = 0; dataIndex < data.size(); ++dataIndex) {
			ShaderData shaderData = data.get(dataIndex);
			ShadeArea area = shaderData.getTarget();
			Shader<?>[] shaders = shaderData.getShaders();
			
			// Draw the area onto the previous buffer
			if (shaders.length > 0) {
				previousBuffer.begin();
			}
			drawTo(delta, batch, area);
			if (shaders.length > 0) {
				previousBuffer.end();
			}
			
			for (int index = 0; index < shaders.length; ++index) {
				
				Shader<?> shader = shaders[index];
				currentBuffer = flipBuffer(currentBuffer);
				
				// If it's not the last element, draw to the buffer. Otherwise draw to screen
				if (index < shaders.length - 1 || dataIndex < data.size() - 1) {
					currentBuffer.begin();	
						drawTo(previousBuffer, delta, batch, shader);				
						currentBuffer.end();
				} else {
					drawTo(previousBuffer, delta, batch, shader);
				}
				
				previousBuffer = currentBuffer;
			}
		}
	}
	


	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.shader.ShaderManager#clear()
	 */
	@Override
	public void clear() {
		data.clear();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.shader.ShaderManager#size()
	 */
	@Override
	public int size() {
		return data.size();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.shader.ShaderManager#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}
	


	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.shader.ShaderManager#dispose()
	 */
	@Override
	public void dispose() {
		initialBuffer.dispose();
		bufferA.dispose();
		bufferB.dispose();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.shader.ShaderManager#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		initialBuffer.dispose();
		bufferA.dispose();
		bufferB.dispose();
		initBuffers(width, height);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private void drawTo(FrameBuffer buffer, float delta, Batch batch, Shader<?> shader, ShadeArea area) {
		
		// Apply the current shader
		if (shader != null) {
			batch.setShader(shader.getProgram());
		} else {
			batch.setShader(null);
		}
		
		batch.begin();		
			if (shader != null) // Update shader
				shader.update(delta);			
			if (buffer != null) // Draw buffer
				batch.draw(buffer.getColorBufferTexture(), 0f, 0f);						
			if (area != null)   // Draw area
				area.draw(batch, delta);	
		batch.end();
		
		// Send the data directly through the graphics pipeline
		batch.flush();
		
		// Reset the current shader
		batch.setShader(null);
	}
	
	private void drawTo(float delta, Batch batch, ShadeArea area) {
		drawTo(null, delta, batch, null, area);
	}
	
	private void drawTo(FrameBuffer buffer, float delta, Batch batch, Shader<?> shader) {
		drawTo(buffer, delta, batch, shader, null);
	}
	
	private FrameBuffer flipBuffer(FrameBuffer current) {
		return current.equals(bufferA) ? bufferB : bufferA;
	}
	
	private void initBuffers(int width, int height) {
		initialBuffer = new FrameBuffer(Format.RGBA4444, width, height, false);
		bufferA = new FrameBuffer(Format.RGBA4444, width, height, false);
		bufferB = new FrameBuffer(Format.RGBA4444, width, height, false);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	private class ShaderData {

		private Shader<?>[] shaders;

		private ShadeArea target;

		/**
		 * @param shaders
		 * @param target
		 */
		public ShaderData(ShadeArea target, Shader<?>[] shaders) {
			super();
			this.shaders = shaders;
			this.target = target;
		}

		/**
		 * @return the shaders
		 */
		public Shader<?>[] getShaders() {
			return shaders;
		}

		/**
		 * @return the target
		 */
		public ShadeArea getTarget() {
			return target;
		}

	}

}
