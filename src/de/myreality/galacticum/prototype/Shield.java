package de.myreality.galacticum.prototype;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.myreality.galacticum.prototype.items.Item;
import de.myreality.galacticum.prototype.ships.Spaceship;


/**
 * General shield class for a spaceship. 
 * 
 * A shield has (depends on level) one or more
 * segments. Each segment has an own cooldown. When a shield gets damage, the last segment
 * reduces its value. If the value is empty, the next segment will be used. While a segment
 * is empty, it regenerate its value until it is completely full. After that, the segment is
 * ordered to the first position. Are all shields down, the target ship gets damage directly.
 * 
 * A shield can have different materials (or types). To activate a type, the player has to press 
 * the key 1 - 9. After switching to another shield, a cooldown affects and the player has to 
 * wait until he can switch again to another shield.
 * 
 * Enemies can drop single segments (gather them will upgrade the shield by 1). Furthermore an 
 * boss opponent can have a shield as well. For instance, fighting against an opponent with 
 * an Ion shield won't be very effective with an ion gun.
 * 
 * Idea by Kevin Zywitzki
 * 
 * @author Miguel Gonzalez
 * @version 1.0
 * @since 08-10-2012
 */
public class Shield {
	
	private ArrayList<Segment> segments;
	
	public static final float RESISTANCE_RATE = 20;
	
	private Image shieldSprite;
	
	public Shield() {
		//SegmentComperator segmentComperator
		segments = new ArrayList<Segment>();
		shieldSprite = ResourceManager.getInstance().getImage("SPRITE_SHIELD");
	}
	
	public Material getMaterial() {
		if (segments.size() > 0) {
			return segments.get(0).getMaterial();
		} else  {
			return Material.NONE;
		}
	}
	
	public boolean isDepleted() {
		if (segments.isEmpty()) {
			return true;
		} else {
			return segments.get(0).isRestoring();
		}
	}
	
	public ArrayList<Segment> getSegments() {
		return segments;
	}
	
	public void restore(int amount) {
		for (int index = 0; index < segments.size(); index++) {			
			amount = segments.get(index).restore(amount);			
			if (amount < 1) {
				break;
			}
		}
	}
	
	public int addDamage(int damage, Materialized materialized) {
		
		if (materialized.getMaterial().equals(getMaterial())) {
			damage -= RESISTANCE_RATE * getRank();
			if (damage < 0 && !getMaterial().equals(Material.NONE)) {
				// Restore the shield by the negative amount
				restore(-damage);
				return 0;
			}
		}

		for (int index = segments.size() - 1; index > -1; index--) {
			if (!segments.get(index).addDamage(damage)) {
				if (!segments.get(index).isRestoring()) {
				    damage -= segments.get(index).getMaxPower();
				}
			} else {
				damage = 0;
				break;
			}
		}
		
		if (damage < 0) {
			damage = 0;
		}
		
		return damage;
	}
	
	public void addSegment(Segment segment) {
		segments.add(segment);
		Collections.sort(segments);
	}
	
	public int getRank() {
		return segments.size();
	}
	
	public void update(GameContainer gc, int delta) {
		for (Segment segment : segments) {
			segment.update(gc, delta);
			if (segment.onPowerRestored()) {
				Collections.sort(segments);
			}
		}
	}
	
	
	public void drawOn(Spaceship spaceship, Graphics g) {
		if (!isDepleted()) {		
			int padding = 20;
			g.rotate(spaceship.getCenterX(), spaceship.getCenterY(), spaceship.getRotation());
			Color materialColor = getMaterial().getColor();
			Color shieldColor = new Color(materialColor.r, materialColor.g, materialColor.b);
			shieldSprite.draw(spaceship.getX() - padding, spaceship.getY() - padding, spaceship.getWidth() + padding * 2, spaceship.getHeight() + padding * 2, shieldColor);
			//g.drawImage(shieldSprite, spaceship.getCenterX(), spaceship.getY(), getMaterial().getColor());
			g.rotate(spaceship.getCenterX(), spaceship.getCenterY(), -spaceship.getRotation());
		}
	}
	
	
	public void drawGUI(int x, int y, int width, int height, Graphics g) {
		int tileWidth = width / getRank();		
		for (int i = 0; i < getRank(); ++i) {
			Segment segment = segments.get(i);
			segment.drawGUI(x + i * tileWidth, y, tileWidth, height, g);			
		}
	}
	
	
	
	public Segment getNewSegment(int x, int y, WorldState game) {
		Segment newSegment = new Segment(x, y, getMaterial(), game);
		return newSegment;
	}
	
	public class Segment extends Item implements Comparable<Segment> {
		
		private Timer cooldownTimer;
		
		private Material material;
		
		private int duration;
		
		private int power, maxPower;
		
		private float regenerateRatio;
		
		private boolean powerRestored;
		
		public static final int DEFAULT_DURATION = 100;		
		public static final int DEFAULT_POWER = 100;

		public Segment(int x, int y, Material material, WorldState game) {
			super(x, y, game);
			cooldownTimer = new Timer();
			this.material = material;
			duration = DEFAULT_DURATION;
			maxPower = DEFAULT_POWER;
			power = maxPower;
			regenerateRatio = 0.01f; // 1% default ratio
			setIcon(ResourceManager.getInstance().getImage("ICON_ITEM_SHIELD"));
			setFadeColor(material.getColor());
			game.getAnimations().addAnimation(this, Animations.ORANGE_FLARE, true);
		}
		
		public void setRegenerateRatio(float ratio) {
			this.regenerateRatio = ratio;
		}
		
		public float getRegenerateRatio() {
			return regenerateRatio;
		}
		
		public float getPercent() {
			return (float) power / (float) maxPower;
		}
		
		public int getCurrentPower() {
			return power;
		}
		
		public int getMaxPower() {
			return maxPower;
		}
		
		public boolean onPowerRestored() {
			return powerRestored;
		}
		
		public int getDuration() {
			return duration;
		}
		
		public boolean addDamage(int damage) {
			
			if (isRestoring()) {
				return false;
			}
			
			power -= damage;
			
			if (power <= 0) {
				power = 0;		
				cooldownTimer.start();
				return false;
			}
			
			return true;
		}
		
		
		public void drawGUI(int x, int y, int width, int height, Graphics g) {
			int padding = 2;
			g.setColor(Color.black);
			g.fillRect(x, y, width, height);
			if (!isRestoring()) {
				g.setColor(getMaterial().getColor());
			} else {
				g.setColor(Color.gray);
			}
			g.fillRect(x + padding, y + padding, (width - padding * 2)  * getPercent(), height - padding * 2);
			Image shaderImage = ResourceManager.getInstance().getImage("IMG_GLASS");
			shaderImage.draw(x + padding, y + padding, (width - padding * 2)  * getPercent(), height - padding * 2);
		}
		
		
		public int restore(int amount) {
			power += amount;
			
			if (power > maxPower) {
				int difference = power - maxPower;
				power = maxPower;
				return difference;
			}
			
			return 0;
		}
		
		private void regeneratePower() {
			power += maxPower * regenerateRatio;
			
			if (isPowerRestored()) {
				power = maxPower;
				powerRestored = true;
				cooldownTimer.stop();
			}
		}
		
		public boolean isPowerRestored() {
			return power >= maxPower;
		}
		
		public void setDuration(int duration) {
			this.duration = duration;
		}

		@Override
		public void alignToShip(Spaceship owner) {
			super.alignToShip(owner);
			owner.addShieldSegment(this);
		}
		
		public Material getMaterial() {
			return material;
		}
		
		
		public boolean isRestoring() {
			return cooldownTimer.isRunning();
		}

		@Override
		public void update(GameContainer gc, int delta) {
			super.update(gc, delta);
			cooldownTimer.update(delta);			
			powerRestored = false;
			if (isUsed()) {
				if (cooldownTimer.getMiliseconds() > duration) {
					cooldownTimer.reset();
					regeneratePower();
				}
			}
		}

		@Override
		public int compareTo(Segment other) {
			if (onPowerRestored()) {
				return -1;
			} else if (getCurrentPower() > other.getCurrentPower()) {
				return -1;				
			} 
			
			return 0;
		}
				
	}
	
}
