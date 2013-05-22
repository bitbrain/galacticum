package de.myreality.galacticum.ui.console;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Console;
import de.lessvoid.nifty.controls.ConsoleCommands;
import de.lessvoid.nifty.controls.ConsoleCommands.ConsoleCommand;
import de.lessvoid.nifty.tools.Color;
import de.myreality.dev.chronos.resource.ResourceManager;
import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.states.LoadingGameState;

/**
 * Factory that handles all commands of the game
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class CommandFactory {

	// ===========================================================
	// Constants
	// ===========================================================

	public static final Color COLOR_SYSTEM = new Color("#ff00cc");

	public static final Color COLOR_INFO = new Color("#76c6e7");

	public static final Color COLOR_WARN = new Color("#ffc000");

	public static final Color COLOR_SUCCESS = new Color("#c5dc2c");

	// ===========================================================
	// Fields
	// ===========================================================

	private Nifty nifty;

	private StateBasedGame sbg;

	private Console console;

	// ===========================================================
	// Constructors
	// ===========================================================

	public CommandFactory(Nifty nifty, StateBasedGame sbg, Console console) {
		this.nifty = nifty;
		this.sbg = sbg;
		this.console = console;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public ConsoleCommand newExitCommand() {

		return new ConsoleCommand() {

			@Override
			public void execute(String[] args) {
				sbg.getContainer().exit();
			}

		};
	}

	public ConsoleCommand newHelpCommand(final ConsoleCommands commands) {
		return new ConsoleCommand() {

			@Override
			public void execute(String[] arg0) {
				for (String command : commands.getRegisteredCommands()) {
					console.output(command, COLOR_INFO);
				}

			}

		};
	}

	public ConsoleCommand newVersionCommand() {
		return new ConsoleCommand() {

			@Override
			public void execute(String[] args) {
				ResourceManager manager = ResourceManager.getInstance();
				String version = manager.getResource("APP_VERSION",
						String.class).get();
				String phase = manager.getResource("APP_PHASE", String.class)
						.get();
				console.output("Game version: " + version + phase, COLOR_INFO);
				console.output("Nifty build " + nifty.getVersion(), COLOR_INFO);
				console.output(
						"Slick build #" + GameContainer.getBuildVersion(),
						COLOR_INFO);
			}

		};
	}

	public ConsoleCommand newResolutionCommand() {
		return new ConsoleCommand() {

			@Override
			public void execute(String[] args) {
				GameContainer container = sbg.getContainer();
				if (args.length == 3) {
					try {
						int width = Integer.valueOf(args[1]);
						int height = Integer.valueOf(args[2]);

						if (width > container.getScreenWidth()
								|| height > container.getScreenHeight()) {
							throw new IllegalArgumentException(
									"Resolution does not exist");
						} else if (width == container.getWidth()
								&& height == container.getHeight()) {
							console.output(
									"Warning: The display resolution has been already set",
									COLOR_WARN);
						} else {
							if (container instanceof AppGameContainer) {
								((AppGameContainer) container)
										.setDisplayMode(width, height,
												container.isFullscreen());
								nifty.resolutionChanged();
								console.output(
										"Display resolution has been changed to "
												+ width + "x" + height,
										COLOR_SUCCESS);

							}
						}
					} catch (SlickException e) {
						console.outputError("Error occured while setting the display mode");
					} catch (NumberFormatException e) {
						console.outputError("Invalid arguments");
					} catch (IllegalArgumentException e) {
						console.outputError(e.getMessage());
					}
				} else if (args.length == 1) {
					console.output(
							"Current resolution: " + container.getWidth() + "x"
									+ container.getHeight(), COLOR_INFO);
				} else {
					console.outputError("Missing arguments");
				}
			}

		};
	}

	public ConsoleCommand newUpdateCommand() {
		return new ConsoleCommand() {

			@Override
			public void execute(String[] args) {
				if (!(sbg.getCurrentState() instanceof LoadingGameState)) {
					console.output("Update the game...", COLOR_SYSTEM);
					sbg.enterState(GalacticumGame.UPDATE_GAME_STATE);
				} else {
					console.output("Can't update while loading an universe.",
							COLOR_WARN);
				}
			}
		};
	}

	public ConsoleCommand newVSyncCommand() {
		return new ConsoleCommand() {

			private void checkVSync(boolean set) {
				if (!set) {
					if (sbg.getContainer().isVSyncRequested()) {
						console.output("Vertical synchronization is enabled",
								COLOR_INFO);
					} else {
						console.output("Vertical synchronization is disabled",
								COLOR_INFO);
					}
				} else {
					if (sbg.getContainer().isVSyncRequested()) {
						console.output(
								"Vertical synchronization has been successfully enabled",
								COLOR_SUCCESS);
					} else {
						console.output(
								"Vertical synchronization has been successfully disabled",
								COLOR_SUCCESS);
					}
				}
			}

			@Override
			public void execute(String[] args) {

				if (args.length == 1) {
					checkVSync(false);
				} else if (args.length == 2) {
					if (args[1].equals("on")) {
						sbg.getContainer().setVSync(true);
						checkVSync(true);
					} else if (args[1].equals("off")) {
						sbg.getContainer().setVSync(false);
						checkVSync(true);
					} else {
						console.outputError("Invalid argument");
						return;
					}
				} else {
					console.outputError("Too many arguments");
				}
			}
		};
	}

	public ConsoleCommand newFullscreenCommand() {
		return new ConsoleCommand() {

			private void checkFullscreen() {
				if (sbg.getContainer().isFullscreen()) {
					console.output("Fullscreen is enabled", COLOR_INFO);
				} else {
					console.output("Fullscreen is disabled", COLOR_INFO);
				}
			}

			@Override
			public void execute(String[] args) {

				if (args.length == 1) {
					checkFullscreen();
				} else if (args.length == 2) {
					try {
						if (args[1].equals("on")) {
							sbg.getContainer().setFullscreen(true);
						} else if (args[1].equals("off")) {
							sbg.getContainer().setFullscreen(false);
						} else {
							console.outputError("Invalid argument");
							return;
						}
					} catch (SlickException e) {
						console.outputError(e.getMessage());
					}

					checkFullscreen();
				} else {
					console.outputError("Too many arguments");
				}
			}
		};
	}

	public ConsoleCommands newConsoleCommands() {
		ConsoleCommands defaultCommands = new ConsoleCommands(nifty, console);
		defaultCommands.enableCommandCompletion(true);

		// Help
		defaultCommands
				.registerCommand("help", newHelpCommand(defaultCommands));

		// Version
		defaultCommands.registerCommand("ver", newVersionCommand());

		// Exit
		defaultCommands.registerCommand("exit", newExitCommand());

		// Resolution
		defaultCommands.registerCommand("res [width height]",
				newResolutionCommand());

		// Updating
		defaultCommands.registerCommand("update", newUpdateCommand());

		// VSync
		defaultCommands.registerCommand("vsync [on|off]", newVSyncCommand());

		// Fullscreen
		defaultCommands.registerCommand("fullscreen [on|off]",
				newFullscreenCommand());

		return defaultCommands;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
