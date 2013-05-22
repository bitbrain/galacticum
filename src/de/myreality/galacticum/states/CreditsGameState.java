package de.myreality.galacticum.states;

import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;

/**
 * Game state to show the credits of Galacticum
 * <p>
 * All producers, used software and other information are displayed in an
 * infinite interval.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 0.1dev
 * @since 0.1dev
 */
public class CreditsGameState extends GalacticumGameState {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private int id;

	// ===========================================================
	// Constructors
	// ===========================================================

	public CreditsGameState(int id) {
		this.id = id;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame sbg) {
		super.prepareNifty(nifty, sbg);
		nifty.fromXml("UI/credits_layout.xml", "start", this);
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public void bind(Nifty nifty, Screen screen) {
		super.bind(nifty, screen);
	}

	@Override
	public void onEndScreen() {
		Log.info("Credits Screen has been leaved.");
	}

	@Override
	public void onStartScreen() {
		Log.info("Credits Screen has been entered.");
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
