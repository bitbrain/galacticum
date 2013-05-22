package de.myreality.galacticum.core.background;

import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.util.AbstractHashProvider;
import de.myreality.galacticum.util.Indexable;

/**
 * Abstract implementation of a space zone
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public abstract class AbstractSpaceZone extends AbstractHashProvider implements SpaceZone {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private Indexable indexable;
	
	private Set<ZoneListener> listeners;
	
	private long lastHash;
	
	private int lastIndexX, lastIndexY;
	
	private Color currentColor;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public AbstractSpaceZone(Indexable indexable) {
		listeners = new HashSet<ZoneListener>();
		this.indexable = indexable;
		lastHash = getHash();
		lastIndexX = indexable.getIndexX();
		lastIndexY = indexable.getIndexY();
		currentColor = new Color(0.0f, 0.0f, 0.0f);
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
	 * de.myreality.galacticum.util.Updatable#update(org.newdawn.slick
	 * .GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {

		if (lastHash != getHash()) {
			computeListeners();
		}
	}
	
	private void computeListeners() {
		for (ZoneListener listener : listeners) {
			listener.onZoneLeaved(lastIndexX, lastIndexY);
			int newX = getIndexable().getIndexX();
			int newY = getIndexable().getIndexY();
			listener.onZoneEntered(newX, newY, getHash());
		}
		
		lastIndexX = getIndexable().getIndexX();
		lastIndexY = getIndexable().getIndexY();
		lastHash = getHash();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.SpaceArea#setIndexable(de.myreality
	 * .dev.galacticum.game.util.Indexable)
	 */
	@Override
	public void setIndexable(Indexable indexable) {
		this.indexable = indexable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.SpaceArea#getIndexable()
	 */
	@Override
	public Indexable getIndexable() {
		return indexable;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.background.SpaceZone#addZoneListener(de.myreality.galacticum.core.background.ZoneListener)
	 */
	@Override
	public void addZoneListener(ZoneListener listener) {
		listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.background.SpaceZone#removeZoneListener(de.myreality.galacticum.core.background.ZoneListener)
	 */
	@Override
	public void removeZoneListener(ZoneListener listener) {
		listeners.remove(listener);
	}
	
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Background#getFilter()
	 */
	@Override
	public Color getFilter() {
		return getColor(getHash());	
	}
	
	
	

	// ===========================================================
	// Methods
	// ===========================================================

	protected Color getColor(long hash) {
		
		int indexX = getIndexable().getIndexX();
		int indexY = getIndexable().getIndexY();
		
		double value = hash / 100f + (indexX + 10 + indexY);
		
		currentColor.r = getValue(0.0f, 0.15f, Math.pow(value, 2));
		currentColor.g = getValue(0.0f, 0.15f, value);
		currentColor.b = getValue(0.0f, 0.15f, Math.sqrt(value));
		
		return currentColor;
		
		
	}

	private float getValue(float min, float max, double x) {
		float difference = (max - min) / 2.0f;
		return (float) (difference * Math.sin(x) + max - difference);
	}
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
