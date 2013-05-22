package de.myreality.galacticum.util;

import org.newdawn.slick.Color;

/**
 * Background that can be rendered and updated as well
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface Background extends Updatable, Renderable {

	/**
	 * The current color of the background
	 * 
	 * @return the current filter color
	 */
	Color getFilter();
}
