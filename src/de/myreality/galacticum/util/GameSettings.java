package de.myreality.galacticum.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.myreality.galacticum.exceptions.LanguageNotSupportedException;

/**
 * Basic game setting class that contains all necessary game settings
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class GameSettings implements Serializable {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 6066605091727580440L;

	// File path
	private static final String FOLDER = "config";

	// File name
	private static final String FILE = "settings.dat";

	private static final String PATH = FOLDER + "/" + FILE;

	// ===========================================================
	// Fields
	// ===========================================================

	private boolean vsyncEnabled;

	private Resolution displayResolution;

	private boolean fullscreenEnabled, lightingEnabled, particlesEnabled;

	private CameraDistance distance;

	private Language language;

	// Private instance for singleton usage
	private static GameSettings instance;

	private GameDifficulty difficulty;

	// ===========================================================
	// Constructors
	// ===========================================================

	static {
		instance = new GameSettings();
	}

	/**
	 * Private Constructor to prevent instance creation
	 */
	private GameSettings() {
		displayResolution = new Resolution(800, 600);
		vsyncEnabled = false;
		fullscreenEnabled = false;
		try {
			language = new Language(Locale.ENGLISH);
		} catch (LanguageNotSupportedException e) {
			e.printStackTrace();
		}
		difficulty = GameDifficulty.NORMAL;
		distance = CameraDistance.NORMAL;
		loadFromFile();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	/**
	 * @return the lightingEnabled
	 */
	public boolean isLightingEnabled() {
		return lightingEnabled;
	}

	/**
	 * @param lightingEnabled
	 *            the lightingEnabled to set
	 */
	public void setLightingEnabled(boolean lightingEnabled) {
		this.lightingEnabled = lightingEnabled;
	}

	/**
	 * @return the particlesEnabled
	 */
	public boolean areParticlesEnabled() {
		return particlesEnabled;
	}

	/**
	 * @param particlesEnabled
	 *            the particlesEnabled to set
	 */
	public void setParticlesEnabled(boolean particlesEnabled) {
		this.particlesEnabled = particlesEnabled;
	}

	public boolean isVsyncEnabled() {
		return vsyncEnabled;
	}

	public void setVsyncEnabled(boolean vsyncEnabled) {
		this.vsyncEnabled = vsyncEnabled;
		saveToFile();
	}

	public Resolution getDisplayResolution() {
		return displayResolution;
	}

	public void setDisplayResolution(Resolution displayResolution) {
		this.displayResolution = displayResolution;
		saveToFile();
	}

	public boolean isFullscreenEnabled() {
		return fullscreenEnabled;
	}

	public void setFullscreenEnabled(boolean fullscreenEnabled) {
		this.fullscreenEnabled = fullscreenEnabled;
		saveToFile();
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
		saveToFile();
	}

	public GameDifficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(GameDifficulty difficulty) {
		this.difficulty = difficulty;
		saveToFile();
	}

	/**
	 * @return the distance
	 */
	public CameraDistance getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(CameraDistance distance) {
		this.distance = distance;
		saveToFile();
	}

	/**
	 * @return Singleton instance
	 */
	public static GameSettings getInstance() {
		return instance;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	private void saveToFile() {
		FileOutputStream fileOut;
		try {
			File file = new File(PATH);

			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}

			fileOut = new FileOutputStream(PATH);
			@SuppressWarnings("resource")
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
		} catch (FileNotFoundException e) {
			Log.error("Can't save game settings: " + e.getMessage());
		} catch (IOException e) {
			Log.error("Can't save game settings: " + e.getMessage());
		}

	}

	public void loadFromFile() {
		try {
			File file = new File(PATH);
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);

			GameSettings settings = (GameSettings) in.readObject();

			// Apply changes
			this.vsyncEnabled = settings.vsyncEnabled;
			this.displayResolution = settings.displayResolution;
			this.fullscreenEnabled = settings.fullscreenEnabled;
			this.lightingEnabled = settings.lightingEnabled;
			this.language = settings.language;
			this.distance = settings.distance;
			this.difficulty = settings.difficulty;

			fileIn.close();
			in.close();
		} catch (FileNotFoundException e) {
			Log.error("Can't load game settings: " + e.getMessage());
		} catch (IOException e) {
			Log.error("Can't load game settings: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			Log.error("Can't load game settings: " + e.getMessage());
		}
	}

	public void apply(GameContainer gc, StateBasedGame sbg, Nifty nifty,
			Screen screen) {
		if (gc instanceof AppGameContainer) {
			apply((AppGameContainer) gc);
			nifty.resolutionChanged();
		}
		Locale.setDefault(language.getLocale());
		nifty.setLocale(language.getLocale());

	}

	public void apply(AppGameContainer game) {
		try {
			game.setDisplayMode(displayResolution.getWidth(),
					displayResolution.getHeight(), isFullscreenEnabled());
		} catch (SlickException e) {
			Log.error(e);
		}
		game.setVSync(isVsyncEnabled());
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
