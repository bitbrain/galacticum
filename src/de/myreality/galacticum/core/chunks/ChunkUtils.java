package de.myreality.galacticum.core.chunks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

import org.newdawn.slick.util.Log;

import de.myreality.galacticum.core.Boundable;
import de.myreality.galacticum.core.Entity;
import de.myreality.galacticum.core.EntityMode;
import de.myreality.galacticum.core.Universe;
import de.myreality.galacticum.core.lighting.Light;
import de.myreality.galacticum.exceptions.EntryNotFoundException;
import de.myreality.galacticum.exceptions.OutOfChunkException;
import de.myreality.galacticum.util.ChunkFilePattern;
import de.myreality.galacticum.util.MatrixList;

/**
 * Util class which provides chunk calculations, saving and loading
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class ChunkUtils {
	

	// File pattern
	private static ChunkFilePattern filePattern = new ChunkFilePattern();
	
	public static void loadChunk(int x, int y, Universe universe, MatrixList<Chunk> target)
			throws EntryNotFoundException {
		
		synchronized (target) {

			FileInputStream fileStream = null;
			try {
				fileStream = new FileInputStream(universe.getPath()
						+ filePattern.convert(x, y));
	
				ObjectInputStream objectStream = new ObjectInputStream(fileStream);
	
				// Load the Chunk
				Chunk chunk = (Chunk) objectStream.readObject();
	
				// Fetch the entities and add them to the current universe
				universe.addEntities(chunk.retrieveEntities());
	
				// Fetch the lights and add them to the current universe
				universe.addLights(chunk.retrieveLights());
	
				target.add(chunk);
	
				objectStream.close();
				fileStream.close();
			} catch (FileNotFoundException e) {
				throw new EntryNotFoundException(x, y);
			} catch (IOException e) {
				Log.error(e);
				throw new EntryNotFoundException(x, y);
			} catch (ClassNotFoundException e) {
				if (fileStream != null) {
					try {
						fileStream.close();
					} catch (IOException e1) {
						
					}
				}
				throw new EntryNotFoundException(x, y);
			}
		}
	}

	public static void saveChunk(Chunk chunk, Universe universe, MatrixList<Chunk> chunks, boolean remove) {
		
		synchronized (chunks) {
		
			// Remove all entities that are inside this chunk and save them
			HashSet<Entity> savedEntities = new HashSet<Entity>();
			HashSet<Light> savedLights = new HashSet<Light>();
	
			for (Entity boundable : universe.getEntities()) {
				int entityIndexX = translateToIndexX(boundable);
				int entityIndexY = translateToIndexY(boundable);
	
				// Check if the entity is NOT a player
				if (!(boundable.getMode() == EntityMode.PLAYER)) {			
					// Check, if the current entity is inside the chunk
					if (chunk.getIndexX() == entityIndexX
							&& chunk.getIndexY() == entityIndexY) {
						savedEntities.add(boundable);
					}
				}
	
			}
			
			for (Light boundable : universe.getLights()) {
				int entityIndexX = translateToIndexX(boundable);
				int entityIndexY = translateToIndexY(boundable);
	
				// Check, if the current entity is inside the chunk
				if (chunk.getIndexX() == entityIndexX
						&& chunk.getIndexY() == entityIndexY) {
					savedLights.add(boundable);
				}
	
			}
	
			// Add the entities to the chunk
			chunk.addEntities(savedEntities);
	
			// Add the lights to the chunk
			chunk.addLights(savedLights);
	
			FileOutputStream fileStream = null;
			try {
				File file = new File(universe.getPath()
						+ filePattern.convert(chunk.getIndexX(), chunk.getIndexY()));
				fileStream = new FileOutputStream(file);
				ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
	
				// Save the Chunk
				objectStream.writeObject(chunk);
	
				objectStream.close();
				fileStream.close();
	
				if (remove) {
					// Remove the entities from the universe
					universe.removeEntities(savedEntities);
	
					// Remove the lights from the universe
					universe.removeLights(savedLights);
	
					// Remove the chunk
					chunks.remove(chunk.getIndexX(), chunk.getIndexY());
	
				}
				
				// Clear the chunk after saving to avoid the existance of multiple entities
				chunk.clear();
			} catch (FileNotFoundException e) {
				Log.error(e);
			} catch (IOException e) {
				Log.error(e);
			}
		}
	}
	
	public static int translateToIndex(int globalPosition) {    
		return (int) Math.floor((float) globalPosition / (float) ChunkSystem.CHUNK_SIZE);
	}

	public static int translateToIndexX(Boundable boundable) {
		int centerX = (int) Math.round(boundable.getLeft() + boundable.getWidth() / 2);
		return translateToIndex(centerX);
	}

	public static int translateToIndexY(Boundable boundable) {
		int centerY = (int) Math.round(boundable.getTop() + boundable.getHeight() / 2);
		return translateToIndex(centerY);
	}
	
	
	/**
	 * Checks if a specific boundable attempts to leave the chunk cache and manages the boundable
	 * afterwards.
	 */
	public static void computeBoundableCache(Boundable boundable, Universe universe) {
		
		ChunkSystem chunkSystem = universe.getChunkSystem();
		
		if (chunkSystem != null) {
			
			// Check if the boundable wants to leave the cache
			// If so, store it and remove it from the universe
			if (!chunkSystem.getBounds().containsCompletely(boundable)) {
				
				try {
					Chunk chunk = chunkSystem.getChunk(boundable);
					
					if (boundable instanceof Entity) {
						Entity entity = (Entity)boundable;
						chunk.add(entity);
						universe.removeEntity(entity);
					} else if (boundable instanceof Light) {
						Light light = (Light)boundable;
						chunk.add((Light)boundable);
						universe.removeLight(light);
					}
				} catch (EntryNotFoundException e) {
					e.printStackTrace();
				} catch (OutOfChunkException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
