package de.myreality.galacticum.core.lighting;

import java.util.List;

import org.newdawn.slick.Color;

import de.myreality.galacticum.core.Boundable;
import de.myreality.galacticum.core.Entity;
import de.myreality.galacticum.core.UniverseSystem;

/**
 * Lighting system that illuminates render objects
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface LightingSystem extends UniverseSystem {

	void update(Entity entity, Color ambientColor);

	void init();

	List<Boundable> getActiveLights();
}
