package de.myreality.galacticum.util;


/**
 * Interface to calculate a current index
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface Indexable {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * @return The current X index
	 */
	int getIndexX();
	
	/**
	 * @return The current Y index
	 */
	int getIndexY();
}
