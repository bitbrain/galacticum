package de.myreality.galacticum.core;

import static org.junit.Assert.assertTrue;
import net.phys2d.math.Vector2f;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for a boundable implementation
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class BoundableTest {
	
	static final float SIZE = 100;
	
	Boundable boundable;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		Vector2f[] points = {new Vector2f(10, 10), new Vector2f(SIZE, SIZE), new Vector2f(SIZE / 2f, 10)};
		
		boundable = new BasicBoundable(points);
	}

	// ===========================================================
	// Constants
	// ===========================================================

	@Test
	public void testGetLeft() {
		assertTrue("Wrong left coordinate -> " + boundable.getLeft(), boundable.getLeft() == 10);
	}
	
	@Test
	public void testGetTop() {
		assertTrue("Wrong top coordinate -> " + boundable.getTop(), boundable.getTop() == 10);
	}
	
	@Test
	public void testGetRight() {
		assertTrue("Wrong right coordinate -> " + boundable.getRight(), boundable.getRight() == SIZE);
	}
	
	@Test
	public void testGetBottom() {
		assertTrue("Wrong bottom coordinate -> " + boundable.getBottom(), boundable.getBottom() == SIZE);
	}

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

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
