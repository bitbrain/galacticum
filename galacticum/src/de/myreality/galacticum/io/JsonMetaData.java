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
package de.myreality.galacticum.io;

import java.io.IOException;

import com.badlogic.gdx.files.FileHandle;

/**
 * JSON implementation of {@see MetaData}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class JsonMetaData implements MetaData {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private MetaData data;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public JsonMetaData(FileHandle handle) throws IOException {
		data = createData(handle);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.io.MetaData#getName()
	 */
	@Override
	public String getName() {
		return data.getName();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.io.MetaData#getVersion()
	 */
	@Override
	public String getVersion() {
		return data.getVersion();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.io.MetaData#getPhase()
	 */
	@Override
	public String getPhase() {
		return data.getPhase();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.io.MetaData#getAuthor()
	 */
	@Override
	public String getAuthor() {
		return data.getAuthor();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.io.MetaData#getUrl()
	 */
	@Override
	public String getUrl() {
		return data.getUrl();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.io.MetaData#getProgress()
	 */
	@Override
	public int getProgress() {
		return data.getProgress();
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private MetaData createData(FileHandle handle) throws IOException {
		
		
		
		return null;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
