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
package de.myreality.galacticum.io.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.badlogic.gdx.files.FileHandle;

import de.myreality.galacticum.MetaData;
import de.myreality.galacticum.io.SimpleMetaData;

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
		
		JsonReader reader = new JsonReader();
		InputStream input = handle.read();
		JsonObject object = reader.parse(getJson(input));
		
		String name = (String) object.getProperty("name");
		String author = (String) object.getProperty("author");
		String url = (String) object.getProperty("url");
		String version = (String) object.getProperty("version");
		String phase = (String) object.getProperty("phase");
		int progress = Integer.valueOf((String) object.getProperty("progress"));
		
		return new SimpleMetaData(name, version, phase, author, url, progress);
	}
	
	private String getJson(InputStream input) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String result  = "";
		String line  = "";
		
		while ((line = reader.readLine()) != null) {
			
			if (line.contains("url")) {
				continue;
			}
			
			result += line;
		}
		
		reader.close();
		
		return result;		
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
