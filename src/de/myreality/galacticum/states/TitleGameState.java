package de.myreality.galacticum.states;

import java.util.Locale;

import org.newdawn.slick.state.StateBasedGame;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.elements.events.NiftyMousePrimaryClickedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.myreality.galacticum.GalacticumGame;

/**
 * The title screen state
 * <p>
 * Menu buttons:
 * <ul>
 * <li>New Universe</li>
 * <li>Load Universe</li>
 * <li>Settings</li>
 * <li>Credits</li>
 * <li>Close</li>
 * </ul>
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 0.1dev
 * @since 0.1dev
 */
public class TitleGameState extends GalacticumGameState {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private int id;

	private StateBasedGame game;

	// ===========================================================
	// Constructors
	// ===========================================================

	public TitleGameState(int id) {
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

		nifty.fromXml("UI/title_layout.xml", "start", this);
		this.game = sbg;
		nifty.setLocale(Locale.GERMAN);
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

	}

	@Override
	public void onStartScreen() {

	}

	// ===========================================================
	// Methods
	// ===========================================================

	@NiftyEventSubscriber(pattern = "btn.*")
	public void onClick(String id, NiftyMousePrimaryClickedEvent event) {
		if (id.equals("btnNewUniverse")) {
			game.enterState(GalacticumGame.NEW_UNIVERSE_GAME_STATE);
		} else if (id.equals("btnLoadUniverse")) {
			game.enterState(GalacticumGame.LOAD_UNIVERSE_GAME_STATE);
		} else if (id.equals("btnSettings")) {
			game.enterState(GalacticumGame.SETTINGS_GAME_STATE);
		} else if (id.equals("btnCredits")) {
			game.enterState(GalacticumGame.CREDITS_GAME_STATE);
		} else if (id.equals("btnClose")) {
			game.getContainer().exit();
		}

	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
