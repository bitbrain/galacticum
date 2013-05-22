package de.myreality.galacticum.util;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Fader which fades between two colors over time
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class ColorFader extends AbstractFader<Color> implements Fader<Color> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private Color current;

	// ===========================================================
	// Constructors
	// ===========================================================

	public ColorFader() {
		super();
		current = new Color(0, 0, 0);
	}

	public ColorFader(Color source, Color target) {
		super(source, target);
		current = new Color(source.r, source.g, source.b);
	}

	public ColorFader(Color source) {
		super(source);
		current = new Color(source.r, source.g, source.b);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public Color getCurrent() {
		return current;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.util.AbstractFader#onUpdate(org.newdawn
	 * .slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	protected void onUpdate(GameContainer gc, StateBasedGame sbg, int delta) {
		if (getSource() != null && getTarget() != null) {
			blend(getTarget(), getSource(), getProgress());
		}
	}
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.AbstractFader#setTarget(java.lang.Object)
	 */
	@Override
	public void setTarget(Color target) {
		setSource(new Color(current.r, current.g, current.b));
		super.setTarget(target);
	}

	// ===========================================================
	// Methods
	// ===========================================================


	private void blend(Color source, Color target, float ratio) {

		float ir = (float) 1.0 - ratio;

		current.r = source.r * ratio + target.r * ir;
		current.g = source.g * ratio + target.g * ir;
		current.b = source.b * ratio + target.b * ir;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
