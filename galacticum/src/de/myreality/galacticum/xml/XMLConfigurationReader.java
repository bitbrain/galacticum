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
package de.myreality.galacticum.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import de.myreality.galacticum.io.ConfigurationBuilder;
import de.myreality.galacticum.io.ConfigurationReader;
import de.myreality.galacticum.io.ContextConfiguration;
import de.myreality.galacticum.io.GDXInputStreamProvider;
import de.myreality.galacticum.io.InputStreamProvider;
import de.myreality.galacticum.io.SimpleConfigurationBuilder;

/**
 * 
 *
 * @author miguel
 * @since
 * @version
 */
public class XMLConfigurationReader implements ConfigurationReader {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private String path;
	
	private InputStreamProvider provider;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public XMLConfigurationReader(String path) {
		this.path = path;
		provider = new GDXInputStreamProvider();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.io.FileProvider#setPath(java.lang.String)
	 */
	@Override
	public void setPath(String path) {
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.io.FileProvider#getPath()
	 */
	@Override
	public String getPath() {
		return path;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.io.ConfigurationReader#read()
	 */
	@Override
	public ContextConfiguration[] read() {
		
		InputStream in = provider.getInputStream(path);		
		XMLHandler xmlHandler = new XMLHandler("contexts");
		xmlHandler.readFromStream(in);
		ArrayList<XMLData> data = xmlHandler.getLines();
		ContextConfiguration[] result = new ContextConfiguration[data.size()];
		ConfigurationBuilder builder = new SimpleConfigurationBuilder();
		int index = 0;
		
		for (XMLData element : data) {
			
			builder.setID(element.getAttribute(ContextConfiguration.ID).value)
				   .setName(element.getAttribute(ContextConfiguration.NAME).value)
				   .setSeed(element.getAttribute(ContextConfiguration.SEED).value)
				   .setTimestamp(Long.valueOf(element.getAttribute(ContextConfiguration.TIMESTAMP).value))
				   .setChunkPath(element.getAttribute("chunks").value)
				   .setRootPath(element.getAttribute("root").value)
				   .setPlayerPath(element.getAttribute("player").value);
				   
			result[index++] = builder.build();			       
		}
		
		try {
			in.close();
		} catch (IOException e) {
			// TO NOTHING
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.io.ConfigurationReader#setProvider(de.myreality.galacticum.io.InputStreamProvider)
	 */
	@Override
	public void setProvider(InputStreamProvider provider) {
		this.provider = provider;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
