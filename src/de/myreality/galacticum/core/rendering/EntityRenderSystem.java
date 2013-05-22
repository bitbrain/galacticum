package de.myreality.galacticum.core.rendering;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.core.Camera;
import de.myreality.galacticum.core.Entity;
import de.myreality.galacticum.core.Universe;
import de.myreality.galacticum.core.lighting.LightingSystem;
import de.myreality.galacticum.util.GameSettings;

/**
 * Basic render system to render only visible parts
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class EntityRenderSystem implements RenderSystem {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// Target universe
	private Universe universe;

	// Lighting system that affects the objects
	private LightingSystem lightingSystem;

	// Target render objects
	private CopyOnWriteArrayList<Entity> renderObjects;

	// ===========================================================
	// Constructors
	// ===========================================================

	public EntityRenderSystem(Universe universe) {
		this.universe = universe;
		renderObjects = new CopyOnWriteArrayList<Entity>();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public List<Entity> getRenderObjects() {
		return renderObjects;
	}

	@Override
	public void update(Entity worldObject, int delta) {
		Camera camera = universe.getCamera();
		if (camera.collidateWith(worldObject)) {
			if (!renderObjects.contains(worldObject)) {
				renderObjects.add(worldObject);
			}
		} else {
			renderObjects.remove(worldObject);
		}

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		for (Entity entity : renderObjects) {
			if (getLightingSystem() != null) {
				Color color = Color.white;

				if (universe.getBackground() != null) {
					color = universe.getBackground().getFilter();
				}

				if (GameSettings.getInstance().isLightingEnabled()) {
					getLightingSystem().update(entity, color);
				} else {
					entity.getSprite().setImageColor(1.0f, 1.0f, 1.0f);
				}
			}
			entity.render(gc, sbg, g);
		}

	}

	@Override
	public void setLightingSystem(LightingSystem lightingSystem) {
		this.lightingSystem = lightingSystem;
	}

	@Override
	public LightingSystem getLightingSystem() {
		return lightingSystem;
	}

	@Override
	public void removeEntities(Collection<? extends Entity> entities) {
		renderObjects.removeAll(entities);
	}

	@Override
	public void removeEntity(Entity entity) {
		renderObjects.remove(entity);
	}

	@Override
	public void init() {
		if (universe != null && universe.getEntities() != null) {
			for (Entity entity : universe.getEntities()) {
				update(entity, 0);
			}
		}
	}

	@Override
	public void shutdown() {
		renderObjects.clear();
		renderObjects = null;
		lightingSystem = null;
		universe = null;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
