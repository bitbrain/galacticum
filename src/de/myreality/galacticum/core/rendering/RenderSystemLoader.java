package de.myreality.galacticum.core.rendering;

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
public class RenderSystemLoader extends SubsystemLoader<RenderSystem> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public RenderSystemLoader(StateBasedGame game) {
		super(game);
		setStateMessage("Initialize rendersystem");
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void load(Universe universe) {
		EntityRenderSystem system = new EntityRenderSystem(universe);
		subSystem = system;
		setLoadingProgress(50);
		subSystem.init();
		universe.setRenderSystem(subSystem);
		setLoadingProgress(100);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
