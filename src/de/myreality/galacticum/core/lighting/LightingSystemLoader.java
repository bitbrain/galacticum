package de.myreality.galacticum.core.lighting;

import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.core.SubsystemLoader;
import de.myreality.galacticum.core.Universe;

/**
 * Loads a chunk system
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class LightingSystemLoader extends SubsystemLoader<LightingSystem> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public LightingSystemLoader(StateBasedGame game) {
		super(game);
		setStateMessage("Initialize lighting");
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void load(Universe universe) {
		LightingSystem system = new VectorLightingSystem(universe);
		subSystem = system;
		setLoadingProgress(50);
		subSystem.init();
		universe.setLightingSystem(subSystem);
		setLoadingProgress(100);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
