package de.myreality.galacticum.prototype;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

import de.myreality.galacticum.prototype.items.Item;

public class Animations {

	private ParticleSystem system;
	
	private WorldState game;
	
	public static final int EXPLOSION = 1, BLUE_FLARE = 2, GREEN_FLARE = 3, ORANGE_FLARE = 4, EXPLOSION_GREEN = 5, EXPLOSION_SHORT = 6;
	
	public Animations(WorldState game) {
		try {
			system = ParticleIO.loadConfiguredSystem("prototype-res/particles/default.xml");
			system.setRemoveCompletedEmitters(true);
			system.setBlendingMode(ParticleSystem.BLEND_COMBINE);			
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.game = game;
	}
	
	public void update(GameContainer gc, int delta) {
		system.update(delta);
	}
	
	public void render(Graphics g) {
		system.render();
	}
	
	public ParticleSystem getSystem() {
		return system;
	}
	
	public void addAnimation(MovableSpriteObject obj, int id, boolean isEndless) {
		try {
			ConfigurableEmitter emitter = null;
			MovableEmitter moveEmitter = null;
			
			switch (id) {
				case EXPLOSION:				
					emitter = ParticleIO.loadEmitter("prototype-res/particles/explosion.xml");						
					system.addEmitter(emitter);	
					moveEmitter = new MovableEmitter((int)obj.getCenterX(), (int)obj.getCenterY(), emitter, game);
					moveEmitter.setSpeed(obj.getSpeed());
					moveEmitter.move(obj.getVelocity());
					break;
				case BLUE_FLARE:
					if (obj instanceof Item) {
						Item objItem = (Item)obj;
						if (!objItem.isUsed()) {
							emitter = ParticleIO.loadEmitter("prototype-res/particles/blueflare.xml");		
							moveEmitter = new MovableEmitter((int)obj.getCenterX(), (int)obj.getCenterY(), emitter, game, obj);
							system.addEmitter(emitter);	
						}
					} else {
						emitter = ParticleIO.loadEmitter("res/particles/blueflare.xml");		
						moveEmitter = new MovableEmitter((int)obj.getCenterX(), (int)obj.getCenterY(), emitter, game, obj);
						system.addEmitter(emitter);	
					}
					break;
				case GREEN_FLARE:
					if (obj instanceof Item) {
						Item objItem = (Item)obj;
						if (!objItem.isUsed()) {
							emitter = ParticleIO.loadEmitter("prototype-res/particles/greenflare.xml");		
							moveEmitter = new MovableEmitter((int)obj.getCenterX(), (int)obj.getCenterY(), emitter, game, obj);
							system.addEmitter(emitter);	
						}
					} else {
						emitter = ParticleIO.loadEmitter("prototype-res/particles/greenflare.xml");		
						moveEmitter = new MovableEmitter((int)obj.getCenterX(), (int)obj.getCenterY(), emitter, game, obj);
						system.addEmitter(emitter);	
					}
					break;
				case ORANGE_FLARE:
					if (obj instanceof Item) {
						Item objItem = (Item)obj;
						if (!objItem.isUsed()) {
							emitter = ParticleIO.loadEmitter("prototype-res/particles/orangeflare.xml");		
							moveEmitter = new MovableEmitter((int)obj.getCenterX(), (int)obj.getCenterY(), emitter, game, obj);
							system.addEmitter(emitter);	
						}
					} else {
						emitter = ParticleIO.loadEmitter("prototype-res/particles/orangeflare.xml");		
						moveEmitter = new MovableEmitter((int)obj.getCenterX(), (int)obj.getCenterY(), emitter, game, obj);
						system.addEmitter(emitter);	
					}
					break;
				case EXPLOSION_GREEN:
					emitter = ParticleIO.loadEmitter("prototype-res/particles/explosion-green.xml");						
					system.addEmitter(emitter);	
					moveEmitter = new MovableEmitter((int)obj.getCenterX(), (int)obj.getCenterY(), emitter, game);
					if (!isEndless) {
						moveEmitter.setNonEndlessTarget(obj);
					}
					break;
				case EXPLOSION_SHORT:
					emitter = ParticleIO.loadEmitter("prototype-res/particles/explosion-short.xml");						
					system.addEmitter(emitter);	
					moveEmitter = new MovableEmitter((int)obj.getCenterX(), (int)obj.getCenterY(), emitter, game);
					if (!isEndless) {
						moveEmitter.setNonEndlessTarget(obj);
					}
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void addAnimation(MovableSpriteObject obj, int id) {
		addAnimation(obj, id, false);
	}
	
	public void clear() {
		system.removeAllEmitters();
	}
	
	
	class MovableEmitter extends MovableSpriteObject {
		
		private ConfigurableEmitter emitter;
		
		private GameObject endlessTarget;
		
		private GameObject nonEndlessTarget;

		public MovableEmitter(int x, int y, ConfigurableEmitter emitter,
				WorldState game, GameObject endlessTarget) {
			super(x, y, 0, 0, game);
			this.emitter = emitter;
			this.emitter.setPosition(getX(), getY());
			this.endlessTarget = endlessTarget;
		}
		
		public MovableEmitter(int x, int y, ConfigurableEmitter emitter,
				WorldState game) {
			this(x, y, emitter, game, null);
		}
		
		public void setNonEndlessTarget(GameObject object) {
			nonEndlessTarget = object;
		}

		@Override
		public void update(GameContainer gc, int delta) {
			super.update(gc, delta);
			if (endlessTarget != null) {
				emitter.setPosition(endlessTarget.getCenterX(), endlessTarget.getCenterY(), false);
			} else if (nonEndlessTarget != null) {
				emitter.setPosition(nonEndlessTarget.getCenterX(), nonEndlessTarget.getCenterY(), false);
			} else {
				emitter.setPosition(getX(), getY(), false);
			}
			
			if (endlessTarget != null && endlessTarget instanceof Item) {
				Item item = (Item)endlessTarget;
				if (item.isUsed()) {
					removeQuery();
					system.removeEmitter(emitter);
				}
			}
			
			if (emitter.completed() && endlessTarget == null) {
				removeQuery();
			}
			
			if (endlessTarget != null && endlessTarget.isRemoveQuery()) {
				removeQuery();
				system.removeEmitter(emitter);
			}
		}
		
		
		
	}
	
}
