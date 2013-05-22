package de.myreality.galacticum.core.background;

import de.myreality.galacticum.util.Background;
import de.myreality.galacticum.util.HashProvider;
import de.myreality.galacticum.util.Indexable;

/**
 * Area in space which has a functional mapping.
 * <p>
 * It calculates a specific value which depends on the current index position. Additionally
 * this class contains a fading mechanism to fade between different zones.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface SpaceZone extends Background, HashProvider {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Sets a new indexable as target
	 */
	void setIndexable(Indexable indexable);
	
	/**
	 * Get the current indexable
	 * 
	 * @return Current indexable
	 */
	Indexable getIndexable();
	
	
	/**
	 * Adds a new listener
	 */
	void addZoneListener(ZoneListener listener);
	
	/**
	 * Removes an existing listener
	 */
	void removeZoneListener(ZoneListener listener);
}
