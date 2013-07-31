package de.myreality.galacticum.core;

import org.newdawn.slick.geom.Vector2f;

/**
 * Utility class which provides calculation for boundables
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class BoundableUtils {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * Computes if the both bounding objects are colliding.
	 * 
	 * @param a The first boundable
	 * @param b The second boundable
	 * @return True when both boundables are colliding
	 */
	public static boolean collidate(Boundable a, Boundable b) {
		for (Vector2f bound : b.getBounds()) {
			if (contains(a, bound.x, bound.y)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Computes if the given position is inside of the boundable
	 * 
	 * @param source The boundable to compare with
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return True when the point is inside of the boundable
	 */
	public static boolean contains(Boundable source, float x, float y) {
		
		return x >= source.getLeft() && x <= source.getRight() &&
			   y >= source.getTop() && y <= source.getBottom();
		
	}
	
	
	/**
	 * Calculates the bounds of a given point array
	 * 
	 * @param points a given collection of points
	 */
	public static void calculateBounds(Vector2f[] bounds, Vector2f[] points) {
		
		if (bounds.length == Boundable.BOUND_COUNT && points.length > 0) {
		
			// Initialization
			
			float minX = points[0].x, minY = points[0].y;
			float maxX = points[0].x, maxY = points[0].y;
			
			// Calculation
			
			for (Vector2f point : points) {
				minX = Math.min(minX, point.x);
				minY = Math.min(minY, point.y);
				maxX = Math.max(maxX, point.x);
				maxY = Math.max(maxY, point.y);
			}
			
			// Verification
			
			bounds[Boundable.TOP_LEFT].x = minX;
			bounds[Boundable.TOP_LEFT].y = minY;
			
			bounds[Boundable.BOTTOM_RIGHT].x = maxX;
			bounds[Boundable.BOTTOM_RIGHT].y = maxY;
			
			bounds[Boundable.TOP_RIGHT].x = maxX; 
			bounds[Boundable.TOP_RIGHT].y = minY;
			
			
			bounds[Boundable.BOTTOM_LEFT].x = minX;
			bounds[Boundable.BOTTOM_LEFT].y = maxY;
			
		
		}
	}
	
	// ===========================================================
	// Inner classes
	// ===========================================================
}
