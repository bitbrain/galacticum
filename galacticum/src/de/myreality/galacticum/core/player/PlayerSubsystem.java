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
package de.myreality.galacticum.core.player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import de.myreality.galacticum.core.subsystem.ProgressListener;
import de.myreality.galacticum.core.subsystem.Subsystem;
import de.myreality.galacticum.core.subsystem.SubsystemException;
import de.myreality.galacticum.io.ContextConfiguration;

/**
 * Handles the current player
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class PlayerSubsystem implements Subsystem {

	// ===========================================================
	// Constants
	// ===========================================================
	
	public static final String PLAYER_FILE = "player.dat";

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Player player;
	
	private ContextConfiguration configuration;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public PlayerSubsystem(ContextConfiguration config) {
		this.configuration = config;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public Player getPlayer() {
		return player;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Nameable#getName()
	 */
	@Override
	public String getName() {
		return "player";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#start()
	 */
	@Override
	public void start() throws SubsystemException {		
		File file = getFile();		
		this.player = loadFromFile(file);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#update(float)
	 */
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#shutdown()
	 */
	@Override
	public void shutdown() {
		if (this.player != null) {
			try {
				File file = getFile();
				saveToFile(file, this.player);
			} catch (SubsystemException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.subsystem.Subsystem#addProgressListener(
	 * de.myreality.galacticum.core.subsystem.ProgressListener)
	 */
	@Override
	public void addProgressListener(ProgressListener listener) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.subsystem.Subsystem#removeProgressListener
	 * (de.myreality.galacticum.core.subsystem.ProgressListener)
	 */
	@Override
	public void removeProgressListener(ProgressListener listener) {
		// TODO Auto-generated method stub

	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private void saveToFile(File file, Player player) throws SubsystemException {	
		
		ObjectOutputStream stream;
		
		try {
			stream = new ObjectOutputStream(new FileOutputStream(file));
			stream.writeObject(player);		
			stream.close();	
		} catch (FileNotFoundException e) {
			throw new SubsystemException(e);
		} catch (IOException e) {
			throw new SubsystemException(e);
		}		
	}
	
	private Player loadFromFile(File file) throws SubsystemException {
		
		try {
		ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));			
		Object player = stream.readObject();
		stream.close();
		
		if (player instanceof Player) {
			return (Player) player;
		} else {
			throw new IOException("Target object is not of type Player");
		}
		} catch (IOException e) {
			throw new SubsystemException(e);
		} catch (ClassNotFoundException e) {
			throw new SubsystemException(e);
		}
	}
	
	private File getFile() throws SubsystemException {
		// Load the player from disk
		FileHandle handle = Gdx.files.external(configuration.getPlayerPath() + PLAYER_FILE);
		File file = handle.file();
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new SubsystemException("Can't create player file: " + e.getMessage());
			}
		}
		
		return file;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
