package de.myreality.galacticum.core.lighting;

import java.util.ArrayList;
import java.util.List;

import net.phys2d.math.Vector2f;

import org.newdawn.slick.Color;

import de.myreality.galacticum.core.Boundable;
import de.myreality.galacticum.core.Entity;
import de.myreality.galacticum.core.Universe;
import de.myreality.galacticum.util.Quadtree;

/**
 * Vector based lighting system
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class VectorLightingSystem implements LightingSystem {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final int LEVEL = 1;

	// ===========================================================
	// Fields
	// ===========================================================

	private Universe universe;

	private List<Boundable> activeLights;

	// Quad tree in order to compare only near lights
	private Quadtree collisionTree;

	// ===========================================================
	// Constructors
	// ===========================================================

	public VectorLightingSystem(Universe universe) {
		this.universe = universe;
		activeLights = new ArrayList<Boundable>();
		collisionTree = new Quadtree(LEVEL, universe.getCamera());
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void update(Entity entity, Color ambientColor) {
		// Calculate only for allowed entities
		if (entity.isIlluminable()) {
			calculateNearLights(entity);

			for (int index = 0; index < entity.getBounds().length; index++) {

				// Initiate new bound color
				Color boundColor = new Color(0, 0, 0);
				Vector2f bound = entity.getBounds()[index];

				for (Boundable boundable : activeLights) {
					if (boundable instanceof Light) {
						Color lightColor = calculateBoundLighting(
								(Light) boundable, bound, ambientColor,
								activeLights.size());
						boundColor.r += lightColor.r;
						boundColor.g += lightColor.g;
						boundColor.b += lightColor.b;
					}

				}

				// Set the new calculated color
				if (entity.getSprite() != null) {
					
					entity.getSprite().setColor(
							transformTextureIndex(index, entity.getBounds()),
							boundColor.r, boundColor.g, boundColor.b);
				}
			}
		}
	}

	@Override
	public void init() {

	}

	@Override
	public List<Boundable> getActiveLights() {
		return activeLights;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private int transformTextureIndex(int index, Vector2f[] bounds) {
		int transformedIndex = bounds.length - (index);
		if (index < 1) {
			transformedIndex = 0;
		}
		return transformedIndex;
	}

	private void calculateNearLights(Entity target) {
		// Calculate the quad tree
		collisionTree.clear();
		activeLights.clear();

		for (Light light : universe.getLights()) {
			if (universe.getCamera().collidateWith(light)) {
				collisionTree.insert(light);
			}
		}

		collisionTree.retrieve(activeLights, target);
	}

	private Color calculateBoundLighting(Light light, Vector2f bound,
			Color ambientColor, int lightCount) {
		// Calculate the distance
		float deltaX = light.getLeft() + light.getWidth() / 2 - bound.x;
		float deltaY = light.getTop() + light.getHeight() / 2 - bound.y;
		float distance = (float) Math.sqrt(Math.pow(deltaX, 2)
				+ Math.pow(deltaY, 2));

		float shadowAmount = distance / light.getRadius() * 2;

		if (shadowAmount > 1) {
			shadowAmount = 1;
		}

		return blend(ambientColor.r / lightCount, ambientColor.g / lightCount,
				ambientColor.b / lightCount, light.getColor().r,
				light.getColor().g, light.getColor().b, shadowAmount);
	}

	private Color blend(float r1, float g1, float b1, float r2, float g2,
			float b2, float ratio) {

		float ir = (float) 1.0 - ratio;

		return new Color(r1 * ratio + r2 * ir,
				g1 * ratio + g2 * ir, b1 * ratio + b2 * ir);
	}

	@Override
	public void shutdown() {
		activeLights.clear();
		activeLights = null;
		collisionTree = null;
		universe = null;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
