package de.myreality.dev.galacticum.game.ui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Console;
import de.lessvoid.nifty.controls.ConsoleCommands;
import de.lessvoid.nifty.controls.ConsoleCommands.ConsoleCommand;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.slick2d.NiftyBasicGame;
import de.lessvoid.nifty.tools.Color;

/**
 * Test case class for nifty default controls
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 0.1dev
 * @since 0.1dev
 */
public class DefaultControlsTest extends NiftyBasicGame implements
		ScreenController {

	// Console
	private Console console;

	protected DefaultControlsTest(String gameTitle) {
		super(gameTitle);
	}

	@Override
	protected void prepareNifty(Nifty nifty) {
		nifty.fromXml("xml/default-controls.xml", "start", this);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer game = new AppGameContainer(new DefaultControlsTest(
				"UI Test: Default Controls"));
		game.setDisplayMode(800, 600, false);
		game.setShowFPS(false);
		game.start();
	}

	@Override
	public void bind(Nifty nifty, final Screen screen) {
		@SuppressWarnings("unchecked")
		DropDown<String> dropDown1 = screen.findNiftyControl("dpdDefault",
				DropDown.class);
		dropDown1.addItem("Entry1");
		dropDown1.addItem("Entry2");
		dropDown1.addItem("Entry3");
		dropDown1.addItem("Entry4");
		dropDown1.addItem("Entry5");
		console = screen.findNiftyControl("conDefault", Console.class);

		ConsoleCommands consoleCommands = new ConsoleCommands(nifty, console);
		consoleCommands.enableCommandCompletion(true);
		consoleCommands.registerCommand("hello", new ConsoleCommand() {

			@Override
			public void execute(String[] args) {
				if (args.length == 2) {
					System.out.println("Hello, " + args[1] + "!");
				}
			}

		});

		consoleCommands.registerCommand("add item to id", new ConsoleCommand() {

			private Color successColor = new Color("#00ff00");

			private Color failColor = new Color("#ff0000");

			@Override
			public void execute(String[] args) {
				if (args.length == 4) {
					@SuppressWarnings("unchecked")
					DropDown<String> dropDown = screen.findNiftyControl(
							args[3], DropDown.class);
					if (dropDown != null) {
						dropDown.addItem(args[1]);
						console.output(args[1]
								+ " has been successfully added to " + args[3],
								successColor);
					} else {
						console.output("Error: " + args[3] + " does not exist",
								failColor);
					}
				} else {
					console.output("Error: Missing arguments", failColor);
				}
			}

		});
	}

	@Override
	public void onEndScreen() {

	}

	@Override
	public void onStartScreen() {

	}

}
