package de.myreality.galacticum.ui;

import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.input.NiftyInputMapping;
import de.lessvoid.nifty.input.keyboard.KeyboardInputEvent;

public class BasicInputMapping implements NiftyInputMapping {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

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
	public NiftyInputEvent convert(KeyboardInputEvent inputEvent) {

		if (inputEvent.isKeyDown()) {
			if (inputEvent.getKey() == KeyboardInputEvent.KEY_F1) {
				return NiftyInputEvent.ConsoleToggle;
			} else if (inputEvent.getKey() == KeyboardInputEvent.KEY_RETURN) {
				return NiftyInputEvent.Activate;
			} else if (inputEvent.getKey() == KeyboardInputEvent.KEY_SPACE) {
				return NiftyInputEvent.Activate;
			} else if (inputEvent.getKey() == KeyboardInputEvent.KEY_TAB) {
				if (inputEvent.isShiftDown()) {
					return NiftyInputEvent.PrevInputElement;
				} else {
					return NiftyInputEvent.NextInputElement;
				}
			}
		}
		return null;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
