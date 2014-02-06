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
