package de.myreality.galacticum.core.background;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.myreality.galacticum.core.Boundable;
import de.myreality.galacticum.util.Background;
import de.myreality.galacticum.util.HashProvider;

public class SpaceParallaxLayer extends AbstractParallaxLayer {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private Background container;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public SpaceParallaxLayer(Boundable boundable, HashProvider hashProvider, Background container, int tileWidth,
			int tileHeight, float distance) {
		super(boundable, hashProvider, tileWidth, tileHeight, distance);
		this.container = container;
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
		try {
			Image image = new Image("res/images/space-far.png");
			image.draw(0, 0, getTileWidth(), getTileHeight());
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.background.AbstractParallaxLayer#getFilter()
	 */
	@Override
	public Color getFilter() {
		return container.getFilter();
	}
	
	

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
