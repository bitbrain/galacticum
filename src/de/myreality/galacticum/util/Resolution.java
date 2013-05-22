/**
 * This file was written by Miguel Gonzalez and is part of the
 * game "LittleWars". For more information mailto info@my-reality.de
 * or visit the game page: http://dev.my-reality.de/littlewars
 * 
 * Class that contains information of display resolution 
 * 
 * @version 	0.1.5
 * @author 		Miguel Gonzalez		
 */

package de.myreality.galacticum.util;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Screen resolution
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class Resolution implements Serializable {

	// ===========================================================
	// Constants
	// ===========================================================

	// ID for serialization
	private static final long serialVersionUID = 1L;

	// ===========================================================
	// Fields
	// ===========================================================

	// x-coordinate, y-coordinate
	private int width, height;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * Constructor of Resolution
	 * 
	 * @param x
	 *            x-coordinate on the screen
	 * @param y
	 *            y-coordinate on the screen
	 */
	public Resolution(int width, int height) {
		this.width = width;
		this.height = height;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	/**
	 * @return x-position on the screen
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Setter of the x-coordinate
	 * 
	 * @param x
	 *            new x-coordinate on the screen
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return y-position on the screen
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Setter of the y-coordinate
	 * 
	 * @param y
	 *            new y-coordinate on the screen
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public String toString() {
		return width + "x" + height;
	}

	@Override
	public boolean equals(Object obj) {
		return width == ((Resolution) obj).width
				&& height == ((Resolution) obj).height;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * Function that returns all available system display modes
	 * 
	 * @return List containing all available resolutions
	 * @todo Fix resolution bug on Linux systems
	 */
	public static List<Resolution> getAvailableResolutions() {
		List<Resolution> modes = new ArrayList<Resolution>();
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice display = ge.getDefaultScreenDevice();
		DisplayMode[] availableModes = display.getDisplayModes();

		for (DisplayMode mode : availableModes) {
			boolean alreadyInserted = false;
			for (Resolution res : modes) {
				if (res.getWidth() == mode.getWidth()
						&& res.getHeight() == mode.getHeight()) {
					alreadyInserted = true;
					break;
				}
			}

			if (!alreadyInserted) {
				modes.add(new Resolution(mode.getWidth(), mode.getHeight()));
			}
		}

		return modes;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
