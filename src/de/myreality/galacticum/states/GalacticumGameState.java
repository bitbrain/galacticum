package de.myreality.galacticum.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.controls.Console;
import de.lessvoid.nifty.controls.console.builder.ConsoleBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.events.NiftyMousePrimaryClickedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.NiftyBasicGameState;
import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.ui.BasicInputMapping;
import de.myreality.galacticum.ui.ToolInputHandler;
import de.myreality.galacticum.ui.console.CommandFactory;

/**
 * Provides general game state control
 * <p>
 * Extending from this game state provides taking screenshots and getting
 * information about the last game state
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 0.1dev
 * @since 0.1dev
 */
public abstract class GalacticumGameState extends NiftyBasicGameState implements
		ScreenController {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// Game this game state is part of
	private StateBasedGame sbg;

	private Nifty nifty;

	// Last game state
	private static BasicGameState lastGameState;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame sbg) {
		this.sbg = sbg;
		this.nifty = nifty;
	}

	@Override
	public void bind(Nifty nifty, Screen screen) {
		initConsole(nifty, screen);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private LayerBuilder newConsoleLayer() {
		return new LayerBuilder("console-layer") {
			{
				childLayoutVertical();
				visible(false);
				control(new ConsoleBuilder("console") {
					{
						lines(10);
						alignCenter();
						valignCenter();
					}
				});
			}
		};
	}

	@Override
	public void leaveState(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.leaveState(container, game);
		lastGameState = this;
	}

	@NiftyEventSubscriber(pattern = "btnAbort")
	public void onBackClick(String id, NiftyMousePrimaryClickedEvent event) {
		sbg.enterState(getLastGameState().getID());
	}

	@Override
	public void onEndScreen() {
	}

	@Override
	public void onStartScreen() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterState(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enterState(container, game);
		nifty.resolutionChanged();
	}

	public static BasicGameState getLastGameState() {
		return lastGameState;
	}

	public static void setLastGameState(BasicGameState state) {
		lastGameState = state;
	}

	@Override
	protected void renderGame(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {

		super.renderGame(container, game, g);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	private void initConsole(Nifty nifty, Screen screen) {
		// Add the console
		LayerBuilder consoleLayerBuilder = newConsoleLayer();
		consoleLayerBuilder.build(nifty, screen, screen.getRootElement());
		Element consoleLayer = screen.findElementByName("console-layer");
		screen.addKeyboardInputHandler(new BasicInputMapping(),
				new ToolInputHandler(consoleLayer));
		Console console = consoleLayer.findNiftyControl("console",
				Console.class);

		// Generate a new factory
		CommandFactory factory = new CommandFactory(nifty, sbg, console);
		factory.newConsoleCommands();

		// Show first console output
		console.output(GalacticumGame.getInfo(), CommandFactory.COLOR_SYSTEM);
		console.output("Type 'help' for more information",
				CommandFactory.COLOR_INFO);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
