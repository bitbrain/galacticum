package de.myreality.galacticum.states;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.elements.events.NiftyMousePrimaryClickedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.core.UniverseManager;
import de.myreality.galacticum.exceptions.UniverseNotFoundException;
import de.myreality.galacticum.ui.UniverseModel;
import de.myreality.galacticum.util.XMLData;

/**
 * Loading an existing universe
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 0.1dev
 * @since 0.1dev
 */
public class LoadUniverseGameState extends GalacticumGameState {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private int id;

	private ListBox<UniverseModel> worldList;

	private StateBasedGame game;

	// ===========================================================
	// Constructors
	// ===========================================================

	public LoadUniverseGameState(int id) {
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
		nifty.fromXml("UI/load_universe_layout.xml", "start", this);
		this.game = sbg;
	}

	@Override
	public int getID() {
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(Nifty nifty, Screen screen) {
		super.bind(nifty, screen);
		worldList = screen.findNiftyControl(
				"worldList", ListBox.class);
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
		initializeListBox();
	}

	@Override
	public void onEndScreen() {
	}

	@Override
	public void onStartScreen() {
	}

	@NiftyEventSubscriber(pattern = "btn.*")
	public void onClick(String id, NiftyMousePrimaryClickedEvent event) {
		List<UniverseModel> selection = worldList.getSelection();

		if (!selection.isEmpty()) {
			UniverseModel model = selection.get(0);

			if (id.equals("btnLoadWorld")) {
				enterLoadingState(model.getUniverseID());
			}
			if (id.equals("btnRemoveWorld")) {
				try {
					UniverseManager.getInstance().removeUniverseData(
							model.getUniverseID());
					initializeListBox();
				} catch (UniverseNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void initializeListBox() {
		if (worldList != null) {
			worldList.clear();

			try {
				List<XMLData> data = UniverseManager.getInstance()
						.getUniverseData();

				for (XMLData universe : data) {
					worldList.addItem(new UniverseModel(universe));
				}

			} catch (UniverseNotFoundException e) {
				// Universe not exist!
			}
		}
	}

	/**
	 * Create a new universe
	 */
	private void enterLoadingState(String id) {
		GameState gameState = game.getState(GalacticumGame.LOADING_GAME_STATE);
		if (gameState instanceof LoadingGameState) {

			LoadingGameState loadingState = (LoadingGameState) gameState;

			// Transfer the data to the loading state
			loadingState.loadFromID(id);
			game.enterState(GalacticumGame.LOADING_GAME_STATE);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
