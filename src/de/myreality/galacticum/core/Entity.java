package de.myreality.galacticum.core;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.DynamicShape;
import net.phys2d.raw.shapes.Polygon;
import net.phys2d.raw.shapes.Shape;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.core.chunks.ChunkUtils;
import de.myreality.galacticum.util.ExtendedImage;
import de.myreality.galacticum.util.Renderable;
import de.myreality.galacticum.util.Updatable;

/**
 * Basic world object that exists in an universe
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public abstract class Entity extends Body implements Boundable, Externalizable,
		Updatable, Renderable {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// Target sprite to render
	private ExtendedImage sprite;

	// The color of the sprite
	private Color color;

	// ID of the target factory
	@SuppressWarnings("unused")
	private String factoryID;

	private Boundable boundable;

	private EntityMode mode;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Entity() {
		super(new Box(0, 0), 10);
	}

	public Entity(DynamicShape shape, float m) {
		super(shape, m);
		initialize();
	}

	public Entity(Shape shape, float m) {
		super(shape, m);
		initialize();
	}

	public Entity(String name, DynamicShape shape, float m) {
		super(name, shape, m);
		initialize();
	}

	public Entity(String name, Shape shape, float m) {
		super(name, shape, m);
		initialize();
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	public void setMode(EntityMode mode) {
		this.mode = mode;
	}

	public EntityMode getMode() {
		return mode;
	}

	public ExtendedImage getSprite() {
		return sprite;
	}

	public void setSprite(ExtendedImage sprite) {
		this.sprite = sprite;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;

		if (color == null) {
			this.color = Color.white;
		}
	}

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	@Override
	public void setBounds(float left, float right, float top, float bottom) {
		boundable.setBounds(left, right, top, bottom);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		Image s = getSprite();

		if (s != null) {
			s.drawWarped(getLeft(), getTop(), getLeft(), getBottom(),
					getRight(), getBottom(), getRight(), getTop());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.phys2d.raw.Body#setPosition(float, float)
	 */
	@Override
	public strictfp void setPosition(float x, float y) {
		super.setPosition(x, y);
		boundable.setPosition(x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(boundable);
		out.writeUTF(mode.name());
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
		boundable = (BasicBoundable) in.readObject();
		mode = Enum.valueOf(EntityMode.class, in.readUTF());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#getLeft()
	 */
	@Override
	public float getLeft() {
		return boundable.getLeft();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#getTop()
	 */
	@Override
	public float getTop() {
		return boundable.getTop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#getRight()
	 */
	@Override
	public float getRight() {
		return boundable.getRight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#getBottom()
	 */
	@Override
	public float getBottom() {
		return boundable.getBottom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#getTopLeft()
	 */
	@Override
	public Vector2f getTopLeft() {
		return boundable.getTopLeft();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#getTopRight()
	 */
	@Override
	public Vector2f getTopRight() {
		return boundable.getTopRight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#getBottomLeft()
	 */
	@Override
	public Vector2f getBottomLeft() {
		return boundable.getBottomLeft();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#getBottomRight()
	 */
	@Override
	public Vector2f getBottomRight() {
		return boundable.getBottomRight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#getWidth()
	 */
	@Override
	public int getWidth() {
		return (int) (getRight() - getLeft());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#getHeight()
	 */
	@Override
	public int getHeight() {
		return (int) (getBottom() - getTop());
	}

	@Override
	public Vector2f[] getBounds() {
		return boundable.getBounds();
	}

	@Override
	public Vector2f[] getPoints() {
		return boundable.getPoints();
	}

	@Override
	public void setPoints(Vector2f[] points) {
		boundable.setPoints(points);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#getCenterX()
	 */
	@Override
	public float getCenterX() {
		return boundable.getCenterX();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#getCenterY()
	 */
	@Override
	public float getCenterY() {
		return boundable.getCenterY();
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
		return boundable.collidateWith(boundable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Boundable#contains(float, float)
	 */
	@Override
	public boolean contains(float x, float y) {
		return boundable.contains(x, y);
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
		return boundable.contains(point);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.Boundable#containsCompletely(de.myreality
	 * .galacticum.core.Boundable)
	 */
	@Override
	public boolean containsCompletely(Boundable other) {
		return boundable.containsCompletely(other);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void initialize() {
		this.color = Color.white;
		boundable = new BasicBoundable();
		mode = EntityMode.NPC;
	}

	public abstract boolean isIlluminable();
	
	private void alignShape(Shape shape) {
		if (shape instanceof Polygon) {
			Polygon p = (Polygon) getShape();
			Vector2f[] newPoints = p.getVertices(getPosition(), getRotation());
			setPoints(newPoints);
		} else if (shape instanceof Box) {
			//Box b = (Box) getShape();
			//Vector2f[] newPoints = b.getPoints(getPosition(), getRotation());

			// TODO: Fix display bug
			//setPoints(newPoints);
		}
	}

	public void onAdjust(Universe universe) {
		alignShape(getShape());		
		ChunkUtils.computeBoundableCache(this, universe);
	}
}
