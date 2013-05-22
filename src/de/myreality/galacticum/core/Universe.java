package de.myreality.galacticum.core;

import java.util.Collection;
import java.util.List;

import org.newdawn.slick.GameContainer;

import de.myreality.galacticum.core.chunks.ChunkSystem;
import de.myreality.galacticum.core.lighting.Light;
import de.myreality.galacticum.core.lighting.LightingSystem;
import de.myreality.galacticum.core.physics.PhysicSystem;
import de.myreality.galacticum.core.rendering.RenderSystem;
import de.myreality.galacticum.util.Background;
import de.myreality.galacticum.util.HashProvider;
import de.myreality.galacticum.util.Renderable;
import de.myreality.galacticum.util.Seed;
import de.myreality.galacticum.util.Updatable;

/**
 * Universe interface with basic setup
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface Universe extends Renderable, Updatable, HashProvider {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	Camera getCamera();

	List<Entity> getEntities();

	void addEntity(Entity entity);

	void addEntities(Collection<? extends Entity> entities);

	void removeEntity(Entity entity);

	void removeEntities(Collection<? extends Entity> entities);

	void addLights(Collection<? extends Light> lights);

	void addLight(Light light);

	void removeLight(Light light);

	void removeLights(Collection<? extends Light> lights);

	List<Light> getLights();

	void setPhysicSystem(PhysicSystem physicSystem);

	PhysicSystem getPhysicSystem();

	void setChunkSystem(ChunkSystem chunkSystem);

	ChunkSystem getChunkSystem();

	void setRenderSystem(RenderSystem renderSystem);

	RenderSystem getRenderSystem();

	void setLightingSystem(LightingSystem lightingSystem);

	LightingSystem getLightingSystem();

	String getName();

	void setCamera(Camera camera);

	String getID();

	String getPath();

	void start(GameContainer gc);

	void shutdown();

	Seed getSeed();

	Background getBackground();

	void setBackground(Background background);
}
