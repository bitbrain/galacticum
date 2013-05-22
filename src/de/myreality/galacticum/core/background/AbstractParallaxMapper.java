package de.myreality.galacticum.core.background;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.core.Boundable;
import de.myreality.galacticum.util.HashProvider;

/**
 * Basic implementation of a parallax mapper
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public abstract class AbstractParallaxMapper implements LayerProvider {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	@SuppressWarnings("unused")
	private HashProvider hashProvider;
	
	// Layers
	private List<ParallaxLayer> layers;

	// Target boundable
	private Boundable boundable;

	// ===========================================================
	// Constructors
	// ===========================================================

	public AbstractParallaxMapper(Boundable boundable, HashProvider hashProvider) {
		layers = new ArrayList<ParallaxLayer>();
		this.setBoundable(boundable);
		this.hashProvider = hashProvider;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		for (ParallaxLayer layer : layers) {
			layer.update(gc, sbg, delta);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		for (ParallaxLayer layer : layers) {
			layer.render(gc, sbg, g);
		}
	}

	@Override
	public Boundable getBoundable() {
		return boundable;
	}

	@Override
	public void setBoundable(Boundable boundable) {
		this.boundable = boundable;
	}

	@Override
	public void addLayer(ParallaxLayer layer) {
		layers.add(layer);
		Collections.sort(layers);
	}

	@Override
	public int getLayerCount() {
		return layers.size();
	}
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Background#getFilter()
	 */
	@Override
	public Color getFilter() {
		return Color.white;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
