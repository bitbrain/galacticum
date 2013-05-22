package de.myreality.galacticum.exceptions;

/**
 * Is thrown when the loader does not have done the work yet
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class UniverseNotFoundException extends Exception {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 5520414323244892398L;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public UniverseNotFoundException() {
		super();
	}

	public UniverseNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UniverseNotFoundException(String message) {
		super(message);
	}

	public UniverseNotFoundException(Throwable cause) {
		super(cause);
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

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
