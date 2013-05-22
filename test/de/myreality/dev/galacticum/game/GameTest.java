package de.myreality.dev.galacticum.game;

import org.newdawn.slick.BasicGame;

/**
 * 
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since
 * @version
 */
public abstract class GameTest extends BasicGame {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final String TITLE = "Galacticum Test - ";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * @param title
	 */
	public GameTest(Class<?> target) {
		super(TITLE + target.getSimpleName());
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
