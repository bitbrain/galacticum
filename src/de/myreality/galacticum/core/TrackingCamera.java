package de.myreality.galacticum.core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import de.myreality.dev.chronos.util.Vector2f;

/**
 * Camera implementation that follows the target
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class TrackingCamera extends BasicBoundable implements Camera {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	@SuppressWarnings("unused")
	private float scale;

	private Vector2f velocity;

	private Boundable target;

	// ===========================================================
	// Constructors
	// ===========================================================

	public TrackingCamera(GameContainer gc) {
		scale = 1.0f;
		setBounds(0, gc.getWidth(), 0, gc.getHeight());
		velocity = new Vector2f(0.0f, 0.0f);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void setScale(float scale) {
		this.scale = scale;
	}

	@Override
	public void zoomIn(float factor) {
		scale += factor;
	}

	@Override
	public void zoomOut(float factor) {
		scale -= factor;
	}

	@Override
	public boolean collidateWith(Boundable entity) {
		boolean topLeftRange = entity.getBottomRight().x < getTopLeft().x
				|| entity.getBottomRight().y < getTopLeft().y;
		boolean bottomRightRange = entity.getTopLeft().x > getBottomRight().x
				|| entity.getTopLeft().y > getBottomRight().y;
		return !(topLeftRange || bottomRightRange);
	}

	@Override
	public void alignToBoundable(Boundable b) {
		
		setPosition(b.getLeft() + (float) Math.floor(b.getWidth() / 2.0f)
				- (float) Math.floor(getWidth() / 2.0f),
				b.getTop() + (float) Math.floor(b.getHeight() / 2.0f)
						- (float) Math.floor(getHeight() / 2.0f));

	}

	@Override
	public void setFocusBoundable(Boundable boundable) {
		target = boundable;
	}

	@Override
	public void setDimension(int width, int height) {

	}

	@Override
	public void bind(Graphics g) {
		g.pushTransform();
		g.translate(-getLeft(), -getTop());
		// g.scale(scale, scale);
		// g.translate(-getLeft(), -getTop());
		// GL11.glOrtho(getLeft(), getRight(), getBottom(), getTop(), 1, -1);
	}

	@Override
	public void unbind(Graphics g) {
		g.popTransform();
	}

	@Override
	public void setVelocity(float velocityX, float velocityY) {
		velocity.x = velocityX;
		velocity.y = velocityY;
	}

	@Override
	public Vector2f getVelocity() {
		return velocity;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		if (target != null) {

			// Create a direction vector
			velocity.x = (float) (target.getLeft() + Math.floor(target.getWidth() / 2.0f)
					- (getLeft() + Math.floor(getWidth() / 2.0f)));
			velocity.y = (float) (target.getTop() + Math.floor(target.getHeight() / 2.0f)
					- (getTop() + Math.floor(getHeight() / 2.0f)));

			float distance = velocity.getLength();

			velocity = velocity.normalize();
			if (distance <= 1.0f) {				
				alignToBoundable(target);
			} else {
				double speed = (delta * distance) / (getWidth() / 4.0);
				
				// Round it up to prevent camera shaking
				float newXPos = (float) Math.ceil(getLeft() + velocity.x * speed);
				float newYPos = (float) Math.ceil(getTop() + velocity.y * speed);
	
				setPosition(newXPos, newYPos);
			}
		}
	}
}
