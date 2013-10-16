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
import java.io.OutputStream;

import de.myreality.galacticum.io.ConfigurationReader;
import de.myreality.galacticum.io.ConfigurationWriter;
import de.myreality.galacticum.io.ContextConfiguration;
import de.myreality.galacticum.io.GDXOutputStreamProvider;
import de.myreality.galacticum.io.OutputStreamProvider;

/**
 * 
 * 
 * @author miguel
 * @since
 * @version
 */
public class XMLConfigurationWriter implements ConfigurationWriter {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private String path;
	
	private OutputStreamProvider provider;
	
	private ConfigurationReader reader;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public XMLConfigurationWriter(String path, ConfigurationReader reader) {
		this.path = path;
		this.reader = reader;
		provider = new GDXOutputStreamProvider();
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
	 * @see de.myreality.galacticum.io.FileProvider#setPath(java.lang.String)
	 */
	@Override
	public void setPath(String path) {
		this.path = path;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.io.FileProvider#getPath()
	 */
	@Override
	public String getPath() {
		return path;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.io.ConfigurationWriter#write(de.myreality.galacticum
	 * .io.ContextConfiguration)
	 */
	@Override
	public void write(ContextConfiguration configuration) {

		ContextConfiguration[] configurations = reader.read();
		OutputStream out = provider.getOutputStream(path);
		XMLHandler xmlHandler = new XMLHandler("contexts");		
		boolean flag = false;
		
		for (ContextConfiguration conf : configurations) {
			
			XMLData data = null;
			
			if (conf.getID().equals(configuration.getID())) {
				data = convert(configuration);
				flag = true;
			} else {
				data = convert(conf);
			}
			
			xmlHandler.addLine(data);
		}
		
		if (!flag) {
			xmlHandler.addLine(convert(configuration));
		}
		
		xmlHandler.writeToStream(out);
		
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.io.ConfigurationWriter#setProvider(de.myreality
	 * .galacticum.io.OutputStreamProvider)
	 */
	@Override
	public void setProvider(OutputStreamProvider provider) {
		this.provider = provider;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private XMLData convert(ContextConfiguration configuration) {
		XMLData data = new XMLData("context");
		data.addAttribute(ContextConfiguration.ID, configuration.getID());
		data.addAttribute(ContextConfiguration.NAME, configuration.getName());
		data.addAttribute(ContextConfiguration.SEED, configuration.getSeed().toString());
		data.addAttribute(ContextConfiguration.TIMESTAMP, "" + configuration.getTimestamp());
		data.addAttribute("root", configuration.getRootPath());
		data.addAttribute("chunks", configuration.getChunkPath());
		data.addAttribute("player", configuration.getPlayerPath());
		
		return data;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
