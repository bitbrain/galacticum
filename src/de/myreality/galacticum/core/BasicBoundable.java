package de.myreality.galacticum.core;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.newdawn.slick.geom.Vector2f;

public class BasicBoundable implements Boundable, Externalizable {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 8632858808738317772L;

	// ===========================================================
	// Fields
	// ===========================================================

	protected Vector2f bounds[];

	protected Vector2f points[];

	// ===========================================================
	// Constructors
	// ===========================================================

	public BasicBoundable() {
		if (bounds == null) {
			bounds = initBounds();
		}

		if (points == null) {
			points = initBounds();
		}
	}

	public BasicBoundable(Vector2f[] points) {
		setPoints(points);
	}

	public BasicBoundable(float left, float right, float top, float bottom) {
		setBounds(left, right, top, bottom);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void setBounds(float left, float right, float top, float bottom) {

		if (bounds == null) {
			bounds = initBounds();
		}

		bounds[TOP_LEFT].x = bounds[BOTTOM_LEFT].x = left;
		bounds[TOP_RIGHT].x = bounds[BOTTOM_RIGHT].x = right;
		bounds[TOP_LEFT].y = bounds[TOP_RIGHT].y = top;
		bounds[BOTTOM_LEFT].y = bounds[BOTTOM_RIGHT].y = bottom;

		if (points == null || points.length != BOUND_COUNT) {
			points = initBounds();
		}

		points[TOP_LEFT].x = bounds[TOP_LEFT].x;
		points[TOP_LEFT].y = bounds[TOP_LEFT].y;
		points[TOP_RIGHT].x = bounds[TOP_RIGHT].x;
		points[TOP_RIGHT].y = bounds[TOP_RIGHT].y;
		points[TOP_LEFT].x = bounds[TOP_LEFT].x;
		points[TOP_LEFT].y = bounds[TOP_LEFT].y;
		points[BOTTOM_LEFT].x = bounds[BOTTOM_LEFT].x;
		points[BOTTOM_LEFT].y = bounds[BOTTOM_LEFT].y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.Boundable#setPoints(de.myreality.dev.chronos
	 * .util.Point2f[])
	 */
	@Override
	public void setPoints(Vector2f[] points) {
		this.points = points;

		if (bounds == null) {
			bounds = initBounds();
		}

		BoundableUtils.calculateBounds(bounds, points);
	}

	@Override
	public float getLeft() {
		return bounds[TOP_LEFT].x;
	}

	@Override
	public float getTop() {
		return bounds[TOP_LEFT].y;
	}

	@Override
	public float getRight() {
		return bounds[BOTTOM_RIGHT].x;
	}

	@Override
	public float getBottom() {
		return bounds[BOTTOM_RIGHT].y;
	}

	@Override
	public Vector2f getTopLeft() {
		return bounds[TOP_LEFT];
	}

	@Override
	public Vector2f getTopRight() {
		return bounds[TOP_RIGHT];
	}

	@Override
	public Vector2f getBottomLeft() {
		return bounds[BOTTOM_LEFT];
	}

	@Override
	public Vector2f getBottomRight() {
		return bounds[BOTTOM_RIGHT];
	}

	@Override
	public int getWidth() {
		return (int) (getRight() - getLeft());
	}

	@Override
	public int getHeight() {
		return (int) (getBottom() - getTop());
	}

	@Override
	public Vector2f[] getBounds() {
		return bounds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(bounds);
		out.writeObject(points);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		// this.factoryID = in.readLine();
		bounds = (Vector2f[]) in.readObject();
		points = (Vector2f[]) in.readObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#getPoints()
	 */
	@Override
	public Vector2f[] getPoints() {
		return points;
	}

	@Override
	public void setPosition(float x, float y) {
		float diffX = x - bounds[TOP_LEFT].x;
		float diffY = y - bounds[TOP_LEFT].y;

		for (Vector2f point : points) {
			point.x += diffX;
			point.y += diffY;
		}

		for (Vector2f bound : bounds) {
			bound.x += diffX;
			bound.y += diffY;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.Boundable#collidateWith(de.myreality.galacticum
	 * .core.Boundable)
	 */
	@Override
	public boolean collidateWith(Boundable boundable) {
		return BoundableUtils.collidate(this, boundable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#contains(float, float)
	 */
	@Override
	public boolean contains(float x, float y) {
		return BoundableUtils.contains(this, x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.Boundable#contains(de.myreality.dev.chronos
	 * .util.Point2f)
	 */
	@Override
	public boolean contains(Vector2f point) {
		return contains(point.x, point.y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#getCenterX()
	 */
	@Override
	public float getCenterX() {
		return getLeft() + getWidth() / 2f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#getCenterY()
	 */
	@Override
	public float getCenterY() {
		return getTop() + getHeight() / 2f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.Boundable#containsCompletely(de.myreality
	 * .galacticum.core.Boundable)
	 */
	@Override
	public boolean containsCompletely(Boundable boundable) {
		return !(boundable.getLeft() <= getLeft()
				|| boundable.getRight() >= getRight()
				|| boundable.getTop() <= getTop() || boundable.getBottom() >= getBottom());
	}
	
	
	@Override
	public String toString() {
		return getTopLeft() + ", " + getBottomRight();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private Vector2f[] initBounds() {
		Vector2f[] bounds = new Vector2f[4];

		for (int index = 0; index < BOUND_COUNT; ++index) {
			bounds[index] = new Vector2f(0f, 0f);
		}

		return bounds;
	}
}
