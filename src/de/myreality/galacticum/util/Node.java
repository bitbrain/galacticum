package de.myreality.galacticum.util;

import java.util.Collection;

/**
 * Family node which allows to create a simple tree structure
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 * 
 * @param <Type> The target type
 */
public interface Node<Type> {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * @return Returns the parent node
	 */
	Collection<Type> getParents();
	
	/**
	 * @return True when a parent exists
	 */
	boolean hasParent();
	
	/**
	 * @return True when the target parent exixts
	 */
	boolean hasParent(Type node);
	
	/**
	 * Adds a new child
	 * 
	 * @param the new child
	 */
	void addChild(Type child);
	
	/**
	 * Removes an existing child
	 * 
	 * @param the existing child
	 */
	void removeChild(Type child);
	
	/**
	 * Set a new parent
	 */
	void addParent(Type parent);
	
	/**
	 * Removes a parent
	 */
	void removeParent(Type parent);
	
	/**
	 * @return True when children exists
	 */
	boolean hasChildren();
	
	/**
	 * @return True when the child exists
	 */
	boolean hasChild(Type child);
	
	/**
	 * @return Number of children
	 */
	int getNumberOfChildren();
	
	/**
	 * @return Number of parents
	 */
	int getNumberOfParents();
	
	/**
	 * @return The current children
	 */
	Collection<Type> getChildren();
	
	/**
	 * Removes all children
	 */
	void clearChildren();
	
}
