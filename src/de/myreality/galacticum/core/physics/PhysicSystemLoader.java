package de.myreality.galacticum.core.physics;

import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.core.SubsystemLoader;
import de.myreality.galacticum.core.Universe;

/**
 * 
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since
 * @version
 */
public class PhysicSystemLoader extends SubsystemLoader<PhysicSystem> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public PhysicSystemLoader(StateBasedGame game) {
		super(game);
		setStateMessage("Initialize physics");
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
	 * de.myreality.galacticum.core.SubsystemLoader#load(de.myreality.galacticum
	 * .core.Universe)
	 */
	@Override
	public void load(Universe universe) {
		PhysicSystem system = null;//new Phys2DPhysicSystem(universe);
		setLoadingProgress(50);
		subSystem = system;
		universe.setPhysicSystem(system);
		setLoadingProgress(100);		
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
