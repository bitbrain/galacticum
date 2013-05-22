package de.myreality.galacticum.states;

import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;

/**
 * Game state to show the intro of Galacticum
 * <p>
 * All used software banners are displayed.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 0.1dev
 * @since 0.1dev
 */
public class IntroGameState extends GalacticumGameState {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	int id;

	// ===========================================================
	// Constructors
	// ===========================================================

	public IntroGameState(int id) {
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
		nifty.fromXml("UI/intro_layout.xml", "start", this);
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
		Log.info("Intro Screen has been leaved.");
	}

	@Override
	public void onStartScreen() {
		Log.info("Intro Screen has been entered.");
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
