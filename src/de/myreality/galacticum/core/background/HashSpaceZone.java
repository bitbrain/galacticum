package de.myreality.galacticum.core.background;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.core.Universe;
import de.myreality.galacticum.util.ColorFader;

/**
 * 
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since
 * @version
 */
public class HashSpaceZone extends AbstractSpaceZone implements ZoneListener {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private LayerProvider background;

	private ColorFader fader;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * @param indexable
	 */
	public HashSpaceZone(Universe universe) {
		super(universe.getChunkSystem());
		background = new BasicParallaxMapper(universe.getCamera(), this);
		fader = new ColorFader(super.getFilter(), super.getFilter());
		fader.setInterval(3000);
		addZoneListener(this);
		universe.addChild(this);
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
	 * de.myreality.galacticum.util.AbstractHashProvider#calculateHash
	 * ()
	 */
	@Override
	protected long calculateHash() {
		int x = getIndexable().getIndexX();
		int y = getIndexable().getIndexY();
		return Math.round(Math.pow(x, 2) + Math.pow(y, 2));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.background.AbstractSpaceZone#update
	 * (org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame,
	 * int)
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		super.update(gc, sbg, delta);
		background.update(gc, sbg, delta);
		fader.update(gc, sbg, delta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.background.AbstractSpaceZone#render
	 * (org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame,
	 * org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.render(gc, sbg, g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.background.ZoneListener#onZoneLeaved
	 * (int, int)
	 */
	@Override
	public void onZoneLeaved(int indexX, int indexY) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.background.ZoneListener#onZoneEntered
	 * (int, int)
	 */
	@Override
	public void onZoneEntered(int indexX, int indexY, long hash) {
		fader.setTarget(getColor(hash));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.background.AbstractParallaxMapper
	 * #getFilter()
	 */
	@Override
	public Color getFilter() {
		return fader.getCurrent();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
