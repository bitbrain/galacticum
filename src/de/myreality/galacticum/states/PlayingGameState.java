package de.myreality.galacticum.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.renderer.lwjgl.time.LWJGLTimeProvider;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.NiftyOverlayBasicGameState;
import de.lessvoid.nifty.slick2d.input.PlainSlickInputSystem;
import de.lessvoid.nifty.slick2d.input.SlickSlickInputSystem;
import de.lessvoid.nifty.slick2d.render.SlickRenderDevice;
import de.lessvoid.nifty.slick2d.sound.SlickSoundDevice;
import de.lessvoid.nifty.spi.input.InputSystem;
import de.lessvoid.nifty.spi.render.RenderDevice;
import de.lessvoid.nifty.spi.sound.SoundDevice;
import de.lessvoid.nifty.spi.time.TimeProvider;
import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.GameUtils;
import de.myreality.galacticum.core.Universe;
import de.myreality.galacticum.exceptions.UniverseNotFoundException;
import de.myreality.galacticum.shading.RadialBlurShader;
import de.myreality.galacticum.shading.ShaderManager;
import de.myreality.galacticum.shading.ShaderProgram;
import de.myreality.galacticum.shading.ShaderType;
import de.myreality.galacticum.util.Debugger;
import de.myreality.galacticum.util.Renderer;

/**
 * Ingame state in order to show the current universe and the entire game logic.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 0.1dev
 * @since 0.1dev
 */
public class PlayingGameState extends NiftyOverlayBasicGameState implements
		ScreenController {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	//private Nifty gui;
	private Image scanLines;
	private ShaderManager shaderManager;
	ShaderProgram blur;

	private int id;

	private Universe universe;

	// ===========================================================
	// Constructors
	// ===========================================================

	public PlayingGameState(int id) {
		this.id = id;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void setUniverse(Universe universe) {
		this.universe = universe;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void initGameAndGUI(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		shaderManager = ShaderManager.getInstance();
		RenderDevice renderDevice = new SlickRenderDevice(gc);
		SoundDevice soundDevice = new SlickSoundDevice();
		InputSystem inputSystem = new SlickSlickInputSystem(new PlainSlickInputSystem());
		TimeProvider timeProvider = new LWJGLTimeProvider();
		new Nifty(renderDevice, soundDevice, inputSystem, timeProvider);
		initNifty(gc, sbg);
		if (shaderManager.isSupported()) {
			scanLines = getStripedLineImage(gc);
		}

	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame sbg) {
		nifty.fromXml("UI/ingame_layout.xml", "start", this);
	}

	@Override
	protected void renderGame(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		if (universe != null) {
			
			// 1. Preprocess render jobs
			Renderer.getInstance().render(gc, sbg, g);

			// 2. Draw universe
			universe.render(gc, sbg, g);
			
			
			// 3. Apply scan lines if not supported by shading
			if (shaderManager.isSupported()) {
				scanLines.draw(0, 0, gc.getWidth(), gc.getHeight());
			}
			
			// Apply shaders	
			//shaderManager.apply(buffer);
		}
	}

	@Override
	protected void updateGame(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if (universe != null) {
			universe.update(gc, sbg, delta);
		}

		Input input = gc.getInput();

		/* ESCAPE -> Leave game */
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			shutdown();
			sbg.enterState(GalacticumGame.TITLE_GAME_STATE);
		}
		
		/* F1 -> Enable/Disable Debugging */
		if (input.isKeyPressed(Input.KEY_F1)) {
			Debugger.getInstance().setEnabled(!Debugger.getInstance().isEnabled());
			gc.setShowFPS(Debugger.getInstance().isEnabled());
		}
		
		/* F2 -> Settings */
		if (input.isKeyPressed(Input.KEY_F2)) {
			GalacticumGameState.setLastGameState(this);
			sbg.enterState(GalacticumGame.SETTINGS_GAME_STATE);
		}
		
		/* F12 -> Take screenshot */
		if (input.isKeyPressed(Input.KEY_F12)) {
			GameUtils.takeScreenshot(gc);
		}
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public void bind(Nifty nifty, Screen screen) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStartScreen() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEndScreen() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void enterState(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		// Add shaders - RADIAL BLURRING
		RadialBlurShader radialShader = (RadialBlurShader) ShaderType.RADIAL_BLUR
				.getShader();
		radialShader.setBlurFactor(0.001f);
		radialShader.setBrightFactor(1.0f);
		radialShader.setSize(0.01f);
		//shaderManager.addShader(ShaderType.RADIAL_BLUR);
		
		shaderManager.addShader(ShaderType.BLOOM);
		
		
	}

	@Override
	protected void leaveState(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// clear shaders
		shaderManager.clear();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void start(GameContainer gc, StateBasedGame game)
			throws UniverseNotFoundException {
		if (universe != null) {
			universe.start(gc);
			game.enterState(getID());
		} else {
			throw new UniverseNotFoundException(
					"Can't start game. Universe is not initialized.");
		}
	}

	public void shutdown() {
		if (universe != null) {
			universe.shutdown();
			this.universe = null;
		}
	}

	private Image getStripedLineImage(GameContainer gc) throws SlickException {
		Image buffer = new Image(gc.getWidth(), gc.getHeight());

		Graphics g = buffer.getGraphics();

		g.setColor(new Color(250, 250, 250, 66));
		for (int y = 0; y < buffer.getHeight(); y += 5) {
			g.fillRect(0, y, buffer.getWidth(), 2);
		}

		g.flush();

		return buffer;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
