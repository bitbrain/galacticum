package de.myreality.galacticum.util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Implementation of a fader
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public abstract class AbstractFader<Type> implements Fader<Type> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private Type source;

	private Type target;

	private int interval;

	private int currentInterval;

	private float filter;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * Creates a new object with the given types
	 * 
	 * @param source
	 *            The new source type
	 * @param target
	 *            The new target type
	 */
	public AbstractFader(Type source, Type target) {
		this.source = source;
		this.target = target;
		filter = 0.0f;
		interval = DEFAULT_INTERVAL;
		currentInterval = 0;
	}

	/**
	 * Creates a new fader with an initial source
	 * 
	 * @param source
	 *            The new source type
	 */
	public AbstractFader(Type source) {
		this(source, null);
	}
	
	/**
	 * Creates an empty fader
	 */
	public AbstractFader() {
		this(null);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.util.Fader#setSource(java.lang.Object)
	 */
	@Override
	public void setSource(Type source) {
		this.source = source;
		currentInterval = 0;
		setProgress(0.0f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.util.Fader#setTarget(java.lang.Object)
	 */
	@Override
	public void setTarget(Type target) {
		this.target = target;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Fader#getSource()
	 */
	@Override
	public Type getSource() {
		return source;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Fader#getTarget()
	 */
	@Override
	public Type getTarget() {
		return target;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Fader#setProgress(float)
	 */
	@Override
	public void setProgress(float progress) {
		if (progress >= 0.0f && progress <= 1.0f) {
			filter = progress;
		} else if (progress > 1.0f) {
			progress = 1.0f;
		} else {
			progress = 0.0f;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Fader#setProgress(int)
	 */
	@Override
	public void setProgress(int progress) {
		setProgress(progress / 100.0f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Fader#getProgress()
	 */
	@Override
	public float getProgress() {
		return filter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Fader#setInterval(int)
	 */
	@Override
	public void setInterval(int interval) {
		if (interval > 0) {
			this.interval = interval;

			if (currentInterval > interval) {
				currentInterval = interval;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Fader#getInterval()
	 */
	@Override
	public int getInterval() {
		return interval;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Fader#isDone()
	 */
	@Override
	public boolean isDone() {
		return filter >= 1.0f;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Renderable#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.util.Updatable#update(org.newdawn.slick
	 * .GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		if (!isDone()) {
			updateInterval(delta);
			setProgress(getIntervalAmount());
		}
		onUpdate(gc, sbg, delta);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void updateInterval(int delta) {
		if (currentInterval < interval) {
			currentInterval += delta;
		} else {
			currentInterval = interval;
		}
	}
	
	protected abstract void onUpdate(GameContainer gc, StateBasedGame sbg, int delta);

	private float getIntervalAmount() {
		return currentInterval / (float) interval;
	}

	public boolean isInitialized() {
		return (source != null && target != null) ||
			   source != null;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
