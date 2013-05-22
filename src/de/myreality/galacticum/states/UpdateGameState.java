package de.myreality.galacticum.states;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.screen.Screen;
import de.myreality.dev.chronos.updating.GameUpdater;
import de.myreality.dev.chronos.updating.OpenGameUpdater;
import de.myreality.dev.chronos.updating.UpdatePhase;
import de.myreality.dev.chronos.updating.UpdatingListener;
import de.myreality.dev.chronos.util.FileHelper;
import de.myreality.dev.controls.progressbar.ProgressBar;
import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.ui.LabelResizer;
import de.myreality.galacticum.util.GameSettings;

/**
 * Game state to show the intro of Galacticum
 * <p>
 * All used software banners are displayed.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 0.1dev
 * @since 0.1dev
 */
public class UpdateGameState extends GalacticumGameState implements
		UpdatingListener {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private int id;

	private GameUpdater gameUpdater;

	private ProgressBar progressBar;

	private LabelResizer lblVersion;

	private LabelResizer lblCaption;

	private LabelResizer lblDescription;

	private LabelResizer lblDescriptionBonus;

	private Nifty nifty;

	private StateBasedGame game;

	// ===========================================================
	// Constructors
	// ===========================================================

	public UpdateGameState(int id) {
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
		game = sbg;
		nifty.fromXml("res/UI/update_layout.xml", "start", this);
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public void bind(Nifty nifty, Screen screen) {
		super.bind(nifty, screen);
		this.nifty = nifty;
		GameSettings.getInstance().apply(game.getContainer(), game, nifty,
				screen);
		progressBar = screen.findControl("pgbProgress", ProgressBar.class);
		// progressBar.setScanning(true);
		lblVersion = new LabelResizer(screen.findNiftyControl("lblVersion",
				Label.class));
		lblCaption = new LabelResizer(screen.findNiftyControl("lblCaption",
				Label.class));
		lblDescription = new LabelResizer(screen.findNiftyControl(
				"lblDescription", Label.class));
		lblDescriptionBonus = new LabelResizer(screen.findNiftyControl(
				"lblDescriptionBonus", Label.class));

		// Clear all texts
		lblVersion.setText("");
		lblCaption.setText("");
		lblDescription.setText("");
		lblDescriptionBonus.setText("");
	}

	@Override
	protected void renderGame(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		super.renderGame(container, game, g);
		if (gameUpdater.isDone() || gameUpdater.isAborted()) {
			if (progressBar != null) {
				progressBar.setProgress(0);
			}

			if (getLastGameState() != null) {
				game.enterState(getLastGameState().getID());
			} else {
				game.enterState(GalacticumGame.TITLE_GAME_STATE);
			}
		}
	}

	@Override
	public void onEndScreen() {
	}

	@Override
	public void onStartScreen() {
	}

	@Override
	public void onUpdate(String file, long currentSize, long totalSize,
			float percentage, float bytesPerSecond, UpdatePhase phase) {

		FileHelper helper = FileHelper.getInstance();

		progressBar.setProgress(percentage);
		String currentSizeString = helper.getFileSizeString(currentSize);
		String totalSizeString = helper.getFileSizeString(totalSize);
		String perSecondString = helper.getFileSizeString(bytesPerSecond);
		lblDescription.setText(currentSizeString + "/" + totalSizeString);
		lblDescriptionBonus.setText("@" + perSecondString + "/s");
	}

	@Override
	public void onStartPhase(UpdatePhase phase) {

		switch (phase) {
		case PREPARING:
			progressBar.setScanning(true);
			lblCaption.setText(getPreparingText());
			break;
		case LOADING:
			progressBar.setScanning(false);
			lblCaption.setText(getUpdateText());
			lblVersion.setText("v. " + gameUpdater.getNewerVersion());
			break;
		default:
			break;
		}
	}

	@Override
	public void enterState(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enterState(container, game);
		gameUpdater = new OpenGameUpdater(
				"https://galacticum.googlecode.com/svn/trunk/");
		gameUpdater.setMetaFile("res/xml/meta.xml");
		gameUpdater.addUpdatingListener(this);
		ExecutorService service = Executors.newFixedThreadPool(1);
		gameUpdater.start(service);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private String getPreparingText() {
		Map<String, ResourceBundle> bundles = nifty.getResourceBundles();
		ResourceBundle bundle = bundles.get("dialog");
		return bundle.getString("game-preparing") + "...";
	}

	private String getUpdateText() {
		Map<String, ResourceBundle> bundles = nifty.getResourceBundles();
		ResourceBundle bundle = bundles.get("dialog");
		return bundle.getString("game-updating");
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
