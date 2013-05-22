package de.myreality.galacticum.states;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.screen.Screen;
import de.myreality.dev.controls.progressbar.ProgressBar;
import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.core.Universe;
import de.myreality.galacticum.core.UniverseLoader;
import de.myreality.galacticum.core.chunks.ChunkSystemLoader;
import de.myreality.galacticum.core.lighting.LightingSystemLoader;
import de.myreality.galacticum.core.physics.PhysicSystemLoader;
import de.myreality.galacticum.core.rendering.RenderSystemLoader;
import de.myreality.galacticum.exceptions.LoadingException;
import de.myreality.galacticum.exceptions.UniverseNotFoundException;
import de.myreality.galacticum.ui.LabelResizer;
import de.myreality.galacticum.util.Renderer;

/**
 * Loading a new or an existing world
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 0.1dev
 * @since 0.1dev
 */
public class LoadingGameState extends GalacticumGameState {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private UniverseLoader loader;

	private ProgressBar progressBar;

	private LabelResizer lblVersion;

	private LabelResizer lblCaption;

	private LabelResizer lblDescription;

	private int id;

	private ExecutorService executor;

	private Future<?> future;

	private StateBasedGame game;

	// ===========================================================
	// Constructors
	// ===========================================================

	public LoadingGameState(int id) {
		this.id = id;
		executor = Executors.newFixedThreadPool(1);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void finalize() {
		executor.shutdownNow();
	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame sbg) {
		super.prepareNifty(nifty, sbg);
		nifty.fromXml("UI/loading_layout.xml", "start", this);
		this.game = sbg;
	}

	@Override
	public int getID() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.states.GalacticumGameState#renderGame
	 * (org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame,
	 * org.newdawn.slick.Graphics)
	 */
	@Override
	protected void renderGame(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		super.renderGame(container, game, g);
		Renderer renderer = Renderer.getInstance();
		renderer.render(container, game, g);
		updateUI();
		if (renderer.isDone() && future != null && future.isDone()
				&& loader.isDone()) {
			PlayingGameState playingState = (PlayingGameState) game
					.getState(GalacticumGame.PLAYING_GAME_STATE);

			try {
				playingState.setUniverse(loader.fetch());
				playingState.start(container, game);
			} catch (LoadingException e) {
				Log.error(e);
				abort(game);
			} catch (UniverseNotFoundException e) {
				Log.error(e);
				abort(game);
			}
		} else if (future == null || future.isCancelled()) {
			abort(game);
		}
	}

	@Override
	public void bind(Nifty nifty, Screen screen) {
		super.bind(nifty, screen);
		progressBar = screen.findControl("pgbProgress", ProgressBar.class);
		lblVersion = new LabelResizer(screen.findNiftyControl("lblVersion",
				Label.class));
		lblCaption = new LabelResizer(screen.findNiftyControl("lblCaption",
				Label.class));
		lblDescription = new LabelResizer(screen.findNiftyControl(
				"lblDescription", Label.class));
		LabelResizer lblDescriptionBonus = new LabelResizer(
				screen.findNiftyControl("lblDescriptionBonus", Label.class));

		// Clear all texts
		lblVersion.setText("");
		lblCaption.setText("");
		lblDescription.setText("");
		lblDescriptionBonus.setText("");
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

	public void loadFromID(String id) {
		try {
			loader = new UniverseLoader(id, game);
			initSubLoaders();
			future = executor.submit(loader);
			updateUI();
		} catch (UniverseNotFoundException e) {
			Log.error(e);
		}
	}

	public void createFromSeed(String name, String seed) {
		try {
		loader = new UniverseLoader(name, game, seed);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		initSubLoaders();
		future = executor.submit(loader);
		updateUI();
		
	}

	private void initSubLoaders() {
		if (loader != null) {
			loader.addSubsystemLoader(new PhysicSystemLoader(game));
			loader.addSubsystemLoader(new ChunkSystemLoader(game));
			loader.addSubsystemLoader(new RenderSystemLoader(game));
			loader.addSubsystemLoader(new LightingSystemLoader(game));
		}
	}

	private void updateUI() {
		if (loader != null) {
			Universe temporary = loader.getTemporary();
			if (temporary != null) {
				lblVersion.setText(loader.getTemporary().getName());
				lblDescription.setText(loader.getStateMessage() + "...");
				progressBar.setProgress(loader.getLoadingProgress());
			}
			lblCaption.setText("Loading ");
		}
	}

	private void abort(StateBasedGame game) {
		loader = null;
		future = null;
		game.enterState(GalacticumGame.NEW_UNIVERSE_GAME_STATE);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
