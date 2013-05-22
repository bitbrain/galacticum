package de.myreality.galacticum.core.physics;

import net.phys2d.raw.Body;
import de.myreality.galacticum.core.UniverseSystem;
import de.myreality.galacticum.util.Updatable;

/**
 * Basic physic system
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface PhysicSystem extends Updatable, UniverseSystem {
    
	void addBody(Body body);
	
	void removeBody(Body body);
	
	void setWorldGravity(float x, float y);
}
