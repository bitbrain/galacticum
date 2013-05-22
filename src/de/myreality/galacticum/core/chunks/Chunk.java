package de.myreality.galacticum.core.chunks;

import java.io.Externalizable;
import java.util.Collection;

import de.myreality.galacticum.core.Boundable;
import de.myreality.galacticum.core.Entity;
import de.myreality.galacticum.core.lighting.Light;
import de.myreality.galacticum.exceptions.OutOfChunkException;
import de.myreality.galacticum.util.Indexable;

/**
 * Single chunk that is managed by a chunk system
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface Chunk extends Externalizable, Indexable, Boundable {

	void addEntities(Collection<? extends Entity> entities);

	void addLights(Collection<? extends Light> lights);
	
	void add(Entity entity) throws OutOfChunkException;
	
	void add(Light light) throws OutOfChunkException;

	Collection<Light> retrieveLights();

	Collection<Entity> retrieveEntities();
	
	void clear();
}
