package de.myreality.galacticum.core;

import net.phys2d.math.Vector2f;

/**
 * Contains bounding information like coordinates. Calculates the bounds
 * automatically.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface Boundable {

	// ===========================================================
	// Constants
	// ===========================================================

	public static final int TOP_LEFT = 0;
	public static final int TOP_RIGHT = 1;
	public static final int BOTTOM_RIGHT = 2;
	public static final int BOTTOM_LEFT = 3;
	public static final int BOUND_COUNT = 4;

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * @return The left X coordinate of the object
	 */
	float getLeft();

	/**
	 * @return the top Y coordinate of the object
	 */
	float getTop();

	/**
	 * @return The right hand X coordinate of the object
	 */
	float getRight();

	/**
	 * @return The bottom Y coordinate of the object
	 */
	float getBottom();

	/**
	 * @return Position of the top left bound
	 */
	Vector2f getTopLeft();

	/**
	 * @return Position of the top right bound
	 */
	Vector2f getTopRight();

	/**
	 * @return Position of the bottom left bound
	 */
	Vector2f getBottomLeft();

	/**
	 * @return Position of the bottom right bound
	 */
	Vector2f getBottomRight();

	/**
	 * Returns the bounds
	 * 
	 * @return array of bounds
	 */
	Vector2f[] getBounds();
	
	/**
	 * Returns contained points
	 * 
	 * @return array of points
	 */
	Vector2f[] getPoints();

	/**
	 * @return Width of the bounding object
	 */
	int getWidth();

	/**
	 * @return Height of the bounding object
	 */
	int getHeight();
	
	/**
	 * @return horizontal center position
	 */
	float getCenterX();
	
	/**
	 * @return vertical center position
	 */
	float getCenterY();
	
	/**
	 * Set new bounds. When points have been set they will be replaced
	 * by the new bounds
	 */
	void setBounds(float left, float right, float top, float bottom);
	
	/**
	 * Sets a new position for the boundable
	 * 
	 * @param x horizontal position
	 * @param y vertical position
	 */
	void setPosition(float x, float y);
	
	/**
	 * Set points which will represent this boundable
	 */
	void setPoints(Vector2f[] points);
	
	/**
	 * @return True when this boundable collides with another
	 */
	boolean collidateWith(Boundable boundable);
	
	/**
	 * @return True when this boundable contains the given coordinate
	 */
	boolean contains(float x, float y);
	
	boolean contains(Vector2f point);
	
	/**
	 * Checks if an boundably is completely contained
	 */
	boolean containsCompletely(Boundable other);
}
