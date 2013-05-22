package de.myreality.galacticum.util;

/**
 * Pattern to display a correct chunk file name
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class ChunkFilePattern {

	// ===========================================================
	// Constants
	// ===========================================================

	// File name extension
	public static final String FILE_EXTENSION = "chunk";

	// File name pattern
	public static final String FILE_PATTERN = "ch_X_Y";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public ChunkFilePattern() {

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

	/**
	 * Translates a given coordinate into a chunk file string
	 */
	public String convert(int x, int y) {
		String result = FILE_PATTERN;

		result = result.replace("X", String.valueOf(x));
		result = result.replace("Y", String.valueOf(y));

		return result + '.' + FILE_EXTENSION;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
