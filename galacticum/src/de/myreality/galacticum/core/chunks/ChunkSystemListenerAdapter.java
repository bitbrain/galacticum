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
package de.myreality.galacticum.core.chunks;

import de.myreality.chunx.Chunk;
import de.myreality.chunx.ChunkSystemListener;
import de.myreality.galacticum.core.subsystem.ProgressListener;

/**
 * Adapter to convert {@see GameContainer} to content providers
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class ChunkSystemListenerAdapter implements ChunkSystemListener {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ProgressListener listener;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ChunkSystemListenerAdapter(ProgressListener listener) {
		this.listener = listener;
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
	 * de.myreality.chunx.ChunkSystemListener#afterCreateChunk(de.myreality.
	 * chunx.Chunk)
	 */
	@Override
	public void afterCreateChunk(Chunk arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.chunx.ChunkSystemListener#afterLoadChunk(de.myreality.chunx
	 * .Chunk)
	 */
	@Override
	public void afterLoadChunk(Chunk arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.chunx.ChunkSystemListener#afterRemoveChunk(int, int)
	 */
	@Override
	public void afterRemoveChunk(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.chunx.ChunkSystemListener#afterSaveChunk(de.myreality.chunx
	 * .Chunk)
	 */
	@Override
	public void afterSaveChunk(Chunk arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.chunx.ChunkSystemListener#beforeCreateChunk(int, int)
	 */
	@Override
	public void beforeCreateChunk(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.chunx.ChunkSystemListener#beforeLoadChunk(int, int)
	 */
	@Override
	public void beforeLoadChunk(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.chunx.ChunkSystemListener#beforeRemoveChunk(de.myreality
	 * .chunx.Chunk)
	 */
	@Override
	public void beforeRemoveChunk(Chunk arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.chunx.ChunkSystemListener#beforeSaveChunk(de.myreality.chunx
	 * .Chunk)
	 */
	@Override
	public void beforeSaveChunk(Chunk arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.chunx.ChunkSystemListener#onEnterChunk(de.myreality.chunx
	 * .Chunk)
	 */
	@Override
	public void onEnterChunk(Chunk arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.chunx.ChunkSystemListener#onLeaveChunk(de.myreality.chunx
	 * .Chunk)
	 */
	@Override
	public void onLeaveChunk(Chunk arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((listener == null) ? 0 : listener.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChunkSystemListenerAdapter other = (ChunkSystemListenerAdapter) obj;
		if (listener == null) {
			if (other.listener != null)
				return false;
		} else if (!listener.equals(other.listener))
			return false;
		return true;
	}
	
	

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
