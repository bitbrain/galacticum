package de.myreality.galacticum.ui;

import de.lessvoid.nifty.controls.Console;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.KeyInputHandler;

public class ToolInputHandler implements KeyInputHandler {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private Element consoleLayer;

	// ===========================================================
	// Constructors
	// ===========================================================

	public ToolInputHandler(Element consoleLayer) {
		this.consoleLayer = consoleLayer;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public boolean keyEvent(NiftyInputEvent inputEvent) {

		if (inputEvent == NiftyInputEvent.ConsoleToggle) {
			consoleLayer.setVisible(!consoleLayer.isVisible());
			consoleLayer.setFocus();
			Console console = consoleLayer.findNiftyControl("console",
					Console.class);
			console.setFocus();
			return true;
		}

		return false;
	}

}
