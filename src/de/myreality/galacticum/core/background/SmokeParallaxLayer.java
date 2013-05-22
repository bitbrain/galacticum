package de.myreality.galacticum.core.background;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.myreality.galacticum.core.Boundable;
import de.myreality.galacticum.util.HashProvider;

public class SmokeParallaxLayer extends AbstractParallaxLayer {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public SmokeParallaxLayer(Boundable boundable, HashProvider hashProvider, int tileWidth,
			int tileHeight, float distance) {
		super(boundable, hashProvider, tileWidth, tileHeight, distance);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void computeSprite(Graphics g) {
		g.clear();
		g.setColor(Color.white);
		try {
			Image image = new Image("res/images/space-middle.png");
			image.draw(0, 0, getTileWidth(), getTileHeight());
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
