package de.myreality.galacticum.core.lighting;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.newdawn.slick.Color;

import de.myreality.galacticum.core.BasicBoundable;

/**
 * Light that affects all entities with its color
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class Light extends BasicBoundable implements Externalizable {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 435293169155243218L;

	// ===========================================================
	// Fields
	// ===========================================================

	// Color of the light
	private Color color;

	private float radius;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Light() {

	}

	public Light(float x, float y, float radius) {
		color = new Color(1f, 1f, 1f);
		setBounds(x - radius, x + radius, y - radius, y + radius);
		this.radius = radius;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	@Override
	public void setPosition(float x, float y) {
		setBounds(x - radius, x + radius, y - radius, y + radius);
	}

	public float getRadius() {
		return radius;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.BasicBoundable#writeExternal(java
	 * .io.ObjectOutput)
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		super.writeExternal(out);
		out.writeFloat(radius);
		out.writeObject(color);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.BasicBoundable#readExternal(java
	 * .io.ObjectInput)
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		super.readExternal(in);
		radius = in.readFloat();
		color = (Color) in.readObject();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void darker() {
		color.darker();
	}

	public void darker(float amount) {
		color.darker(amount);
	}

	public void brighter() {
		color.brighter();
	}

	public void brighter(float amount) {
		color.brighter(amount);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
