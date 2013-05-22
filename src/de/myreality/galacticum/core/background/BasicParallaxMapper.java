package de.myreality.galacticum.core.background;

import de.myreality.galacticum.core.Boundable;

/**
 * Basic implementation of an universe mapper
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class BasicParallaxMapper extends AbstractParallaxMapper {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private SpaceZone zone;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public BasicParallaxMapper(Boundable boundable, SpaceZone zone) {
		super(boundable, zone);
		this.zone = zone;	
		ParallaxLayer smokeLayer1 = new SmokeParallaxLayer(getBoundable(), zone, 512, 512, 10.0f);
		smokeLayer1.setVelocity((float)(Math.random() * 0.2f), (float)(Math.random() * 0.2f));
		addLayer(smokeLayer1);
		ParallaxLayer smokeLayer2 = new SmokeParallaxLayer(getBoundable(), zone, 512, 512, 30.0f);
		smokeLayer2.setVelocity((float)(Math.random() * 0.2f), (float)(Math.random() * 0.2f));
		addLayer(smokeLayer2);

		generateStarLayers(10);
		addLayer(new SpaceParallaxLayer(getBoundable(), zone, zone, 1024, 1024, 150.0f));
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================	

	// ===========================================================
	// Methods
	// ===========================================================

	private void generateStarLayers(int count) {
		
		final float offset = 7.0f;
		
		for (float x = 0; x < count / 2f; x += 0.5f) {
			addLayer(new StarParallaxLayer(getBoundable(), zone, 512, 512, (float) Math.pow(Math.E, x) + offset));
		}
	}
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
