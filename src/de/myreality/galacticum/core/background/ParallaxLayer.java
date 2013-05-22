package de.myreality.galacticum.core.background;

import org.newdawn.slick.Color;

import de.myreality.dev.chronos.util.Vector2f;
import de.myreality.galacticum.core.Boundable;
import de.myreality.galacticum.core.rendering.RenderTarget;
import de.myreality.galacticum.util.HashProvider;
import de.myreality.galacticum.util.Renderable;
import de.myreality.galacticum.util.Updatable;

/**
 * Parallax layer that is displayed by the LayerProvider
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 * @see {LayerProvider}
 */
public interface ParallaxLayer extends RenderTarget, Comparable<ParallaxLayer>,
		Renderable, Updatable {

	/**
	 * @return The distance of this layer
	 */
	float getDistance();

	/**
	 * Sets the distance
	 * 
	 * @param distance
	 *            The distance to set
	 */
	void setDistance(float distance);

	/**
	 * @return The current velocity
	 */
	Vector2f getVelocity();

	/**
	 * Sets a new velocity
	 * 
	 * @param velocity
	 *            The velocity to set
	 */
	void setVelocity(Vector2f velocity);

	/**
	 * Sets a new velocity
	 * 
	 * @param velocityX
	 *            The velocity in X direction
	 * @param velocityY
	 *            the velocity in Y direction
	 */
	void setVelocity(float velocityX, float velocityY);

	/**
	 * @return the current tile width
	 */
	int getTileWidth();

	/**
	 * Sets a new tile width
	 * 
	 * @param tileWidth
	 *            the tile width to set
	 */
	void setTileWidth(int tileWidth);

	/**
	 * @return the current tile width
	 */
	int getTileHeight();
	
	Color getFilter();

	/**
	 * Sets a new tile width
	 * 
	 * @param tileWidth
	 *            the tile width to set
	 */
	void setTileHeight(int tileHeight);

	Boundable getBoundable();
	
	HashProvider getHashProvider();
}
