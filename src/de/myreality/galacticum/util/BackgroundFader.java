package de.myreality.galacticum.util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Implementation of a fader
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class BackgroundFader extends AbstractFader<Background> implements
		Fader<Background> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private Image sourceBuffer, targetBuffer;

	// ===========================================================
	// Constructors
	// ===========================================================

	public BackgroundFader() {
		super();
	}

	public BackgroundFader(Background source, Background target) {
		super(source, target);
	}

	public BackgroundFader(Background source) {
		super(source);
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
	 * de.myreality.galacticum.util.Renderable#render(org.newdawn.slick
	 * .GameContainer, org.newdawn.slick.state.StateBasedGame,
	 * org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		if (sourceBuffer == null) {
			sourceBuffer = createBuffer(gc);
		}

		if (targetBuffer == null) {
			targetBuffer = createBuffer(gc);
		}

		updateBuffers(gc, sbg);

		if (isBuffered()) {
			if (!isDone()) {
				sourceBuffer.setAlpha(1.0f - getProgress());
				sourceBuffer.draw(0, 0, gc.getWidth(), gc.getHeight());
			}
			targetBuffer.setAlpha(getProgress());
			targetBuffer.draw(0, 0, gc.getWidth(), gc.getHeight());
		}
	}	

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.AbstractFader#onUpdate(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	protected void onUpdate(GameContainer gc, StateBasedGame sbg, int delta) {
		if (!isDone()) {
			if (getSource() != null) {
				getSource().update(gc, sbg, delta);
			}
		}

		if (getTarget() != null) {
			getTarget().update(gc, sbg, delta);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private Image createBuffer(GameContainer gc) throws SlickException {
		return new Image(gc.getWidth(), gc.getHeight());
	}

	private void updateBuffers(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		Background source = getSource();
		Background target = getTarget();
		
		if (isInitialized() && isBuffered()) {
			if (!isDone() && source != null) {
				Graphics sourceG = sourceBuffer.getGraphics();
				sourceG.clear();
				sourceG.clearAlphaMap();
				source.render(gc, sbg, sourceG);
				sourceG.flush();

			}

			if (target != null) {
				Graphics targetG = targetBuffer.getGraphics();
				targetG.clear();
				targetG.clearAlphaMap();
				target.render(gc, sbg, targetG);
				targetG.flush();
			}
		}
	}

	public boolean isBuffered() {
		return sourceBuffer != null && targetBuffer != null;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
