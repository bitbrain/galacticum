package de.myreality.galacticum.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.elements.events.NiftyMousePrimaryClickedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.util.XMLData;

/**
 * Creating a new universe
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 0.1dev
 * @since 0.1dev
 */
public class NewUniverseGameState extends GalacticumGameState {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private int id;

	private StateBasedGame game;

	private TextField txName;

	private TextField txSeed;

	// ===========================================================
	// Constructors
	// ===========================================================

	public NewUniverseGameState(int id) {
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
		nifty.fromXml("UI/new_universe_layout.xml", "start", this);
		this.game = sbg;
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public void bind(Nifty nifty, Screen screen) {
		super.bind(nifty, screen);

		txName = screen.findNiftyControl("txfName", TextField.class);
		txSeed = screen.findNiftyControl("txfSeed", TextField.class);
	}

	@Override
	public void onEndScreen() {
	}

	@Override
	public void onStartScreen() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.states.GalacticumGameState#enterState
	 * (org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void enterState(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enterState(container, game);
		if (txName != null) {
			txName.setText("New Universe");
			txName.setCursorPosition(txName.getDisplayedText().length());
		}

		if (txSeed != null) {
			txSeed.setText("");
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * Create a new universe
	 */
	private void enterLoadingState() {
		GameState gameState = game.getState(GalacticumGame.LOADING_GAME_STATE);

		if (gameState instanceof LoadingGameState) {
		
			LoadingGameState loadingState = (LoadingGameState) gameState;

			// Filter the current data
			// TODO: Show error message on screen to prevent bad names
			String seedString = XMLData.filter(txSeed.getRealText());
			String nameString = XMLData.filter(txName.getRealText());

			// Transfer the data to the loading state
			loadingState.createFromSeed(nameString, seedString);
			game.enterState(GalacticumGame.LOADING_GAME_STATE);
		}
	}

	@NiftyEventSubscriber(pattern = "btn.*")
	public void onClick(String id, NiftyMousePrimaryClickedEvent event) {

		if (id.equals("btnCreateWorld")) {
			enterLoadingState();
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
