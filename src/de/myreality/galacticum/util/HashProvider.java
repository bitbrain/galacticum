package de.myreality.galacticum.util;

/**
 * A HashProver provides a hash number. This number depends on the
 * implementation. 
 * <p>
 * Additionally, a hash provider can have multiple
 * child providers which also affects on the current seed.
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface HashProvider extends Node<HashProvider> {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * @return a hash which depends on the implementation
	 */
	long getHash();
}
