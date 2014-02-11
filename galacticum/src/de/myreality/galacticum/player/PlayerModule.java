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
package de.myreality.galacticum.player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import de.myreality.galacticum.context.Context;
import de.myreality.galacticum.core.World;
import de.myreality.galacticum.entities.SpaceShip;
import de.myreality.galacticum.entities.SpaceShipFactory;
import de.myreality.galacticum.entities.SpaceShipType;
import de.myreality.galacticum.io.ContextConfiguration;
import de.myreality.galacticum.modules.Module;
import de.myreality.galacticum.modules.ModuleException;
import de.myreality.galacticum.util.HashGenerator;

/**
 * Handles the current player
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class PlayerModule implements Module {

	// ===========================================================
	// Constants
	// ===========================================================
	
	public static final String PLAYER_FILE = "player.dat";

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Player player;
	
	private ContextConfiguration configuration;
	
	private PlayerFactory playerFactory;
	
	private SpaceShipFactory spaceShipFactory;
	
	private PlayerListener listener;
	
	private Context context;
	
	private HashGenerator generator;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public PlayerModule(ContextConfiguration config, SpaceShipFactory factory, HashGenerator hashGenerator, PlayerListener listener) {
		this.configuration = config;
		spaceShipFactory = factory;
		playerFactory = new SimplePlayerFactory();
		this.listener = listener;
		this.generator = hashGenerator;
	}
	
	public PlayerModule(ContextConfiguration config, SpaceShipFactory factory, HashGenerator hashGenerator) {
		this(config, factory, hashGenerator, null);
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
	public void load(Context context) throws ModuleException {	
		
		File file = getFile();	
		
		this.player = loadFromFile(file);
		
		if (this.player == null) {
			SpaceShip startShip = spaceShipFactory.create(0, 0, SpaceShipType.FIGHTER, generator.generate(0, 0));
			this.player = playerFactory.create(startShip);
			
			if (listener != null) {
				this.player.addListener(listener);
			}
		}
		
		this.context = context;
		
		World world = context.getWorld();
		world.add(player.getCurrentShip());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#shutdown()
	 */
	@Override
	public void dispose() {
		if (this.player != null) {
			
			if (context != null) {
				context.getWorld().remove(player.getCurrentShip());
			}
			
			if (listener != null) {
				player.removeListener(listener);
			}
			
			try {
				File file = getFile();
				saveToFile(file, this.player);
			} catch (ModuleException e) {
				e.printStackTrace();
			}
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private void saveToFile(File file, Player player) throws ModuleException {	
		
		ObjectOutputStream stream;
		
		try {
			stream = new ObjectOutputStream(new FileOutputStream(file));
			stream.writeObject(player);		
			stream.close();	
		} catch (FileNotFoundException e) {
			throw new ModuleException(e);
		} catch (IOException e) {
			throw new ModuleException(e);
		}		
	}
	
	private Player loadFromFile(File file) {
		
		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));			
			Object player = stream.readObject();
			stream.close();
			
			if (player != null && player instanceof Player) {
				return (Player) player;
			} else {
				return null;
			}
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	private File getFile() throws ModuleException {
		// Load the player from disk
		FileHandle handle = Gdx.files.external(configuration.getPlayerPath() + PLAYER_FILE);
		File file = handle.file();
		
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e) {				
				throw new ModuleException("Can't create player file at '" + file.getPath() + "': " + e.getMessage());
			}
		}
		
		return file;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
