package de.myreality.galacticum;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import de.lessvoid.nifty.slick2d.NiftyStateBasedGame;
import de.myreality.dev.chronos.resource.ResourceManager;
import de.myreality.galacticum.states.CreditsGameState;
import de.myreality.galacticum.states.IntroGameState;
import de.myreality.galacticum.states.LoadUniverseGameState;
import de.myreality.galacticum.states.LoadingGameState;
import de.myreality.galacticum.states.NewUniverseGameState;
import de.myreality.galacticum.states.PlayingGameState;
import de.myreality.galacticum.states.SettingsGameState;
import de.myreality.galacticum.states.TitleGameState;
import de.myreality.galacticum.states.UpdateGameState;
import de.myreality.galacticum.util.Debugger;
import de.myreality.galacticum.util.GameSettings;
import de.myreality.galacticum.util.ImagePack;

/**
 * Game class to start Galacticum at all its game states
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 0.1dev
 * @since 0.1dev
 */
public class GalacticumGame extends NiftyStateBasedGame {
	
	// ===========================================================
	// Constants
	// ===========================================================
	
	/** Game state: {@link IntroGameState} */
	public static final int INTRO_GAME_STATE         = 1;
	
	/** Game state: {@link TitleGameState} */
	public static final int TITLE_GAME_STATE         = 2;
	
	/** Game state: {@link SettingsGameState} */
	public static final int SETTINGS_GAME_STATE      = 3;
	
	/** Game state: {@link CreditsGameState} */
	public static final int CREDITS_GAME_STATE       = 4;
	
	/** Game state: {@link NewUniverseGameState} */
	public static final int NEW_UNIVERSE_GAME_STATE  = 5;
	
	/** Game state: {@link LoadUniverseGameState} */
	public static final int LOAD_UNIVERSE_GAME_STATE = 6;
	
	/** Game state: {@link PlayingGameState} */
	public static final int PLAYING_GAME_STATE       = 7;
	
	/** Game state: {@link UpdateGameState} */
	public static final int UPDATE_GAME_STATE        = 8;
	
	/** Game state: {@link LoadingGameState} */
	public static final int LOADING_GAME_STATE       = 9;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	/**
	 * Class constructor
	 * 
	 * @param name name of the game
	 */
	public GalacticumGame(String name) {
		super(name);
		// Disable nifty output
		java.util.logging.Logger.getAnonymousLogger().getParent().setLevel(java.util.logging.Level.SEVERE);
		java.util.logging.Logger.getLogger("de.lessvoid.nifty.*").setLevel(java.util.logging.Level.SEVERE);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	/**
	 * @param gc game container of the game
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		
		registerResources();
		addState(new UpdateGameState(UPDATE_GAME_STATE));
		addState(new IntroGameState(INTRO_GAME_STATE));
		addState(new TitleGameState(TITLE_GAME_STATE));
		addState(new SettingsGameState(SETTINGS_GAME_STATE));
		addState(new CreditsGameState(CREDITS_GAME_STATE));
		addState(new NewUniverseGameState(NEW_UNIVERSE_GAME_STATE));
		addState(new LoadUniverseGameState(LOAD_UNIVERSE_GAME_STATE));
		addState(new LoadingGameState(LOADING_GAME_STATE));
		addState(new PlayingGameState(PLAYING_GAME_STATE));
		
		
		// Change the title
		if (gc instanceof AppGameContainer) {
			ResourceManager manager = ResourceManager.getInstance();
			String appName = manager.getResource("APP_NAME", String.class).get();
			String appVersion = manager.getResource("APP_VERSION", String.class).get();
			String appPhase = manager.getResource("APP_PHASE", String.class).get();
			
			((AppGameContainer) gc).setTitle(appName + " v. " + appVersion + appPhase);
		}
		
		ImagePack.init(gc, null);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================	
	
	// Load all necessary resources
	private void registerResources() {
		// Register the resource loaders
		ResourceManager manager = ResourceManager.getInstance();
		//manager.addResourceLoader(ImageLoader.getInstance());
		//manager.addResourceLoader(SoundLoader.getInstance());
		//manager.addResourceLoader(ScriptLoader.getInstance());
		//manager.addResourceLoader(MusicLoader.getInstance());

		// Load the resources
		manager.fromXML("res/xml/meta.xml");
		//manager.fromXML("res/xml/images.xml");
		//manager.fromXML("res/xml/sounds.xml");
		//manager.fromXML("res/xml/music.xml");
		//manager.fromXML("res/xml/scripts.xml");
	}
	
	
	/**
	 * @return string game information
	 */
	public static String getInfo() {
		// Build the notification
		ResourceManager manager = ResourceManager.getInstance();
		String gameName = manager.getResource("APP_NAME", String.class).get();
		String version = manager.getResource("APP_VERSION", String.class).get();
		String phase = manager.getResource("APP_PHASE", String.class).get();
		return gameName + " v. " + version + phase + " console";
	}
	
	
	
	// Main Method	
	public static void main(String[] args) throws SlickException {
		Debugger.getInstance().setEnabled(true);

		GameSettings.getInstance().loadFromFile();
		AppGameContainer game = new AppGameContainer(new GalacticumGame("Galacticum"));
		GameSettings.getInstance().apply(game);
		if (!Debugger.getInstance().isEnabled()) {
			game.setShowFPS(false);
		}
		game.start();
	}

}
