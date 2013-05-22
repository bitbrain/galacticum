package de.myreality.galacticum.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.CheckBox;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.elements.events.NiftyMousePrimaryClickedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.myreality.galacticum.util.CameraDistance;
import de.myreality.galacticum.util.GameDifficulty;
import de.myreality.galacticum.util.GameSettings;
import de.myreality.galacticum.util.Language;
import de.myreality.galacticum.util.Resolution;

/**
 * Game state to change the game settings
 * <p>
 * Possible game settings are:
 * <ul>
 * <li>Vertical Synchronization (Vsync)</li>
 * <li>Resolution</li>
 * <li>Sound volume</li>
 * <li>Music volume</li>
 * <li>Language</li>
 * </ul>
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 0.1dev
 * @since 0.1dev
 */
public class SettingsGameState extends GalacticumGameState {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private int id;

	private StateBasedGame game;

	private Nifty nifty;

	private Screen screen;

	private DropDown<Resolution> drpResolution;

	private DropDown<GameDifficulty> drpDifficulty;

	private DropDown<CameraDistance> drpCameraDistance;

	private DropDown<Language> drpLanguage;

	private CheckBox cbxVsync;

	private CheckBox cbxFullscreen;

	private CheckBox cbxParticles;

	private CheckBox cbxLighting;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SettingsGameState(int id) {
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
		this.nifty = nifty;
		nifty.fromXml("UI/settings_layout.xml", "start", this);
	}

	@Override
	public int getID() {
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void bind(Nifty nifty, Screen screen) {
		super.bind(nifty, screen);
		this.screen = screen;
		drpResolution = screen.findNiftyControl(
				"drpResolution", DropDown.class);
		drpDifficulty = screen.findNiftyControl(
				"drpDifficulty", DropDown.class);
		drpCameraDistance = screen.findNiftyControl(
				"drpCameraDistance", DropDown.class);
		drpLanguage = screen.findNiftyControl(
				"drpLanguage", DropDown.class);
		cbxVsync = screen.findNiftyControl("cbxVsync", CheckBox.class);
		cbxFullscreen = screen
				.findNiftyControl("cbxFullscreen", CheckBox.class);
		cbxParticles = screen.findNiftyControl("cbxParticles", CheckBox.class);
		cbxLighting = screen.findNiftyControl("cbxLighting", CheckBox.class);
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
		applySettingsOnGUI();
	}

	@Override
	public void leaveState(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.leaveState(container, game);
		applySettingsOnGUI();
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

		Input input = container.getInput();

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(getLastGameState().getID());
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void applySettingsOnGUI() {
		GameSettings settings = GameSettings.getInstance();

		drpDifficulty.selectItem(settings.getDifficulty());
		drpResolution.selectItem(settings.getDisplayResolution());
		drpLanguage.selectItem(settings.getLanguage());
		drpCameraDistance.selectItem(settings.getDistance());
		cbxLighting.setChecked(settings.isLightingEnabled());
		cbxParticles.setChecked(settings.areParticlesEnabled());
		cbxFullscreen.setChecked(settings.isFullscreenEnabled());
		cbxVsync.setChecked(settings.isVsyncEnabled());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.states.GalacticumGameState#onBackClick
	 * (java.lang.String,
	 * de.lessvoid.nifty.elements.events.NiftyMousePrimaryClickedEvent)
	 */
	@NiftyEventSubscriber(pattern = "btnApply")
	public void onApply(String id, NiftyMousePrimaryClickedEvent event) {
		GameSettings settings = GameSettings.getInstance();
		settings.setDifficulty(drpDifficulty.getSelection());
		settings.setDisplayResolution(drpResolution.getSelection());
		settings.setLanguage(drpLanguage.getSelection());
		settings.setDistance(drpCameraDistance.getSelection());
		settings.setLightingEnabled(cbxLighting.isChecked());
		settings.setParticlesEnabled(cbxParticles.isChecked());
		settings.setFullscreenEnabled(cbxFullscreen.isChecked());
		settings.setVsyncEnabled(cbxVsync.isChecked());
		settings.apply(game.getContainer(), game, nifty, screen);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
