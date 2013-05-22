package de.myreality.galacticum.core;

import org.newdawn.slick.Graphics;

import de.myreality.dev.chronos.util.Vector2f;
import de.myreality.galacticum.util.Updatable;

/**
 * Camera that is in a world. It controls the entire scaling of the screen.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface Camera extends Boundable, Updatable {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * Sets the scale ratio
     * 
     * @param scale
     *            scale factor
     */
    void setScale(float scale);

    /**
     * Zooms the camera in
     * 
     * @param factor
     *            zoom factor
     */
    void zoomIn(float factor);

    /**
     * Zooms the camera out
     * 
     * @param factor
     *            zoom factor
     */
    void zoomOut(float factor);

    /**
     * Returns true if the camera collidates with the given entity
     * 
     * @param entity
     *            Given entity
     */
    boolean collidateWith(Boundable entity);

    /**
     * Sets a new position for the camera
     * 
     * @param x
     * @param y
     */
    void setPosition(float x, float y);

    /**
     * Sets the velocity ratio
     * 
     * @param velocity
     *            new velocity ratio
     */
    void setVelocity(float velocityX, float velocityY);

    Vector2f getVelocity();

    /**
     * Sets the entity to focus on. The camera will move straight to the given
     * entity.
     * 
     * @param focusEntity
     *            entity to focus on
     */
    void setFocusBoundable(Boundable boundable);

    /**
     * Sets the dimension of the camera
     * 
     * @param width
     *            width of the camera
     * @param height
     *            height of the camera
     */
    void setDimension(int width, int height);

    /**
     * Binds the camera to the graphics
     * 
     * @param g
     *            target graphics
     */
    void bind(Graphics g);

    /**
     * Unbinds the camera to the graphics
     * 
     * @param g
     *            target graphics
     */
    void unbind(Graphics g);

    void alignToBoundable(Boundable b);
}
