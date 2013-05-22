package de.myreality.galacticum.exceptions;

import de.myreality.galacticum.core.Boundable;
import de.myreality.galacticum.core.chunks.Chunk;

/**
 * Is called when an object is outside of a given chunk.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class OutOfChunkException extends Exception {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = -3143819781425664537L;

	// ===========================================================
	// Fields
	// ===========================================================

	private String customMessage;

	// ===========================================================
	// Constructors
	// ===========================================================

	public OutOfChunkException(Chunk chunk, Boundable boundable) {

		customMessage = "Entity at position " + boundable.getCenterX() + "|" + boundable.getCenterY()
				+ " is outside of chunk " + chunk;
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	@Override
	public String getMessage() {
		return customMessage;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
