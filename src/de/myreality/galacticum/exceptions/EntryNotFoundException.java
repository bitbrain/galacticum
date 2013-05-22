package de.myreality.galacticum.exceptions;

/**
 * Is thrown when a chunk does not exist
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class EntryNotFoundException extends Exception {

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

	public EntryNotFoundException(int indexX, int indexY) {
		super("Entry " + indexX + "|" + indexY + " can not be found");
	}
	
	public EntryNotFoundException() {
		super("There exist no entry yet");
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
