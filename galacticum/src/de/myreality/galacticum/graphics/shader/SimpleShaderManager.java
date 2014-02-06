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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private Map<ShaderData, FrameBuffer[]> frameBuffers;
	
	private FrameBuffer initialBuffer;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SimpleShaderManager() {
		data = new ArrayList<ShaderData>();
		frameBuffers = new HashMap<SimpleShaderManager.ShaderData, FrameBuffer[]>();
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
	public void add(ShadeArea shaderTarget, Shader... shaders) {
		ShaderData shaderData = new ShaderData(shaderTarget, shaders);
		data.add(shaderData);
		updateFrameBuffers(shaderData);
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
		
		// New area is required if the next shade area has been entered,
		// we need to consider the last buffer as well for shading
		boolean newArea = false;
		
		// Iterate through each shader data and apply a buffer to it
		for (int dataIndex = 0; dataIndex < data.size(); ++dataIndex) {
			ShaderData shaderData = data.get(dataIndex);
			FrameBuffer[] availableBuffers = frameBuffers.get(shaderData);
			ShadeArea area = shaderData.getTarget();
			Shader[] shaders = shaderData.getShaders();
			
			// Draw the area onto the previous buffer
			previousBuffer.begin();
				drawTo(delta, batch, area);
			previousBuffer.end();
			
			for (int index = 0; index < shaders.length && index < availableBuffers.length; ++index) {
				
				Shader shader = shaders[index];
				FrameBuffer buffer = availableBuffers[index];
				
				// If it's not the last element, draw to the buffer. Otherwise draw to screen
				if (index < shaders.length - 1 || dataIndex < data.size() - 1) {
					buffer.begin();	
						drawToBuffer(batch, area, previousBuffer, shader, delta, newArea);				
					buffer.end();
				} else {
					drawToBuffer(batch, area, previousBuffer, shader, delta, newArea);		
				}
				
				previousBuffer = buffer;				
				newArea = false;
			}
			
			newArea = true;
		}
		
	}
	


	/* (non-Javadoc)
	 * @see de.myreality.galacticum.graphics.shader.ShaderManager#clear()
	 */
	@Override
	public void clear() {
		
		for (FrameBuffer[] buffers : frameBuffers.values()) {
			for (FrameBuffer buffer : buffers) {
				buffer.dispose();
			}
		}
		
		frameBuffers.clear();
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

	// ===========================================================
	// Methods
	// ===========================================================

	private void updateFrameBuffers(ShaderData data) {
		
		Shader[] shaders = data.getShaders();
		ShadeArea target = data.getTarget();		

		FrameBuffer[] buffers = new FrameBuffer[shaders.length];
		
		for (int i = 0; i < buffers.length; ++i) {
			
			if (buffers[i] != null) {
				buffers[i].dispose();
			}
			
			buffers[i] = new FrameBuffer(Format.RGBA8888,
					target.getWidth(), target.getHeight(), false);
		}
		
		frameBuffers.put(data, buffers);
		
		if (initialBuffer != null) {
			initialBuffer.dispose();
		}
		
		initialBuffer = new FrameBuffer(Format.RGBA8888,
				target.getWidth(), target.getHeight(), false);
	}
	
	private void drawToBuffer(SpriteBatch batch, ShadeArea area, FrameBuffer previousBuffer, Shader shader, float delta, boolean newArea) {
			// Draw only the previous buffer and apply the shader
			drawTo(previousBuffer, delta, batch, shader);			
	}
	
	private void drawTo(FrameBuffer buffer, float delta, Batch batch, Shader shader, ShadeArea area) {
		
		// Apply the current shader
		if (shader != null)
			batch.setShader(shader.getProgram());		
		
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
	
	private void drawTo(FrameBuffer buffer, float delta, Batch batch, Shader shader) {
		drawTo(buffer, delta, batch, shader, null);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	private class ShaderData {

		private Shader[] shaders;

		private ShadeArea target;

		/**
		 * @param shaders
		 * @param target
		 */
		public ShaderData(ShadeArea target, Shader[] shaders) {
			super();
			this.shaders = shaders;
			this.target = target;
		}

		/**
		 * @return the shaders
		 */
		public Shader[] getShaders() {
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
