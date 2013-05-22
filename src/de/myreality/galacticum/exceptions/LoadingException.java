package de.myreality.galacticum.exceptions;

/**
 * Is thrown when something can't be loaded completely.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class LoadingException extends Exception {

	// ===========================================================
	// Constants
	// ===========================================================

	/**
     * 
     */
	private static final long serialVersionUID = 5123407856393735518L;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public LoadingException() {
		super();
	}

	public LoadingException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoadingException(String message) {
		super(message);
	}

	public LoadingException(Throwable cause) {
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
