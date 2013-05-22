package de.myreality.galacticum.util;

/**
 * Debugger that enables or disables debugging
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class Debugger {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static Debugger instance;

	private boolean enabled;

	// ===========================================================
	// Constructors
	// ===========================================================

	static {
		instance = new Debugger();
	}

	private Debugger() {
		enabled = false;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public static Debugger getInstance() {
		return instance;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public boolean isEnabled() {
		return enabled;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
