package de.myreality.galacticum.core.rendering;

import java.util.Collection;
import java.util.List;

import de.myreality.galacticum.core.Entity;
import de.myreality.galacticum.core.UniverseSystem;
import de.myreality.galacticum.core.lighting.LightingSystem;
import de.myreality.galacticum.util.Renderable;

/**
 * Basic render system that renders visible world objects
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface RenderSystem extends Renderable, UniverseSystem {

	List<Entity> getRenderObjects();

	void update(Entity worldObject, int delta);

	void removeEntities(Collection<? extends Entity> entities);

	void removeEntity(Entity entity);

	void setLightingSystem(LightingSystem lightingSystem);

	LightingSystem getLightingSystem();

	void init();
}
