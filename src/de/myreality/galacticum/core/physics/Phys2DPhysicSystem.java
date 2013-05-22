package de.myreality.galacticum.core.physics;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.BodyList;
import net.phys2d.raw.World;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.core.Universe;

/**
 * Implementation of a Phys2D physic system
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class Phys2DPhysicSystem implements PhysicSystem {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final int ITERATIONS = 1;
	private static final int LEVELS = 8;
	private static final int MAX_IN_LEVEL = 50;

	// ===========================================================
	// Fields
	// ===========================================================

	private World world;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * Basic constructor
	 */
	public Phys2DPhysicSystem(Universe universe) {
		QuadSpaceStrategy strategy = new QuadSpaceStrategy(MAX_IN_LEVEL, LEVELS);
		
		// TODO: Solve performance bug
		world = new World(new Vector2f(0f, 0f), ITERATIONS, strategy, universe);
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
	 * @see
	 * de.myreality.galacticum.util.Updatable#update(org.newdawn.slick.GameContainer
	 * , org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		// Don't use frame delta -> Phys2D bug
		//world.step(delta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.UniverseSystem#shutdown()
	 */
	@Override
	public void shutdown() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.physics.PhysicSystem#addBody(net.phys2d.
	 * raw.Body)
	 */
	@Override
	public void addBody(Body body) {
		BodyList bodies = world.getBodies();
		
		if (!bodies.contains(body)) {
			world.add(body);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.physics.PhysicSystem#removeBody(net.phys2d
	 * .raw.Body)
	 */
	@Override
	public void removeBody(Body body) {
		world.remove(body);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.physics.PhysicSystem#setWorldGravity(float,
	 * float)
	 */
	@Override
	public void setWorldGravity(float x, float y) {
		world.setGravity(x, y);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
