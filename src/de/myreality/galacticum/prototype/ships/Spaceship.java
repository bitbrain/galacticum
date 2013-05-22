package de.myreality.galacticum.prototype.ships;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

import de.myreality.galacticum.prototype.Animations;
import de.myreality.galacticum.prototype.BackgroundBattle;
import de.myreality.galacticum.prototype.GameObject;
import de.myreality.galacticum.prototype.Material;
import de.myreality.galacticum.prototype.Materialized;
import de.myreality.galacticum.prototype.MovableSpriteObject;
import de.myreality.galacticum.prototype.Point;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.Shield;
import de.myreality.galacticum.prototype.Shield.Segment;
import de.myreality.galacticum.prototype.Vector;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.gui.FlashText;
import de.myreality.galacticum.prototype.items.PowerUp;
import de.myreality.galacticum.prototype.weapons.Weapon;

public abstract class Spaceship extends MovableSpriteObject implements Materialized {

	private ArrayList<Weapon> weapons;
	private int currentLife;
	private int experience;
	private int rank;
	private Spaceship target;
	private CopyOnWriteArrayList<PowerUp> powerUps;
	private Map<Material, Shield> shields;
	protected Shield activeShield;
	
	public Spaceship(int x, int y, Image sprite, WorldState game) {
		super(x, y, sprite.getWidth(), sprite.getHeight(), game);
		// Basic configuration
		setSprite(sprite);
		setSpeed(0.5f);
		weapons = new ArrayList<Weapon>();
		powerUps = new CopyOnWriteArrayList<PowerUp>();
		rank = 1;
		currentLife = getMaxLife();		
		
		shields = new TreeMap<Material, Shield>();
	}	
	
	
	public Shield getShield(Material material) {
		Shield shield = shields.get(material);
		if (shield == null && material == Material.NONE) {
			addShield(Material.NONE);
			return shields.get(material);
		} else {
			return shield;
		}
	}
	
	
	public void addShield(Material material) {
		if (!shields.containsKey(material)) {
			Shield shield = new Shield();
			shields.put(material, shield);
			Shield defaultShield = shield;
			Segment defaultSegment = defaultShield.new Segment(0, 0, material, game);
			defaultSegment.alignToShip(this);
			if (activeShield == null) {
				activeShield = shield;
			}
		}
	}
	
	
	public Shield getActiveShield() {
		return activeShield;
	}
	
	public void setActiveShield(Material material) {
		if (shields.get(material) != null) {
			activeShield = shields.get(material);
		}
	}
	
	public void setActiveShield(int index) {
		int tmpIndex = 0;
		for (Shield shield : shields.values()) {
			if (tmpIndex == index) {
				activeShield = shield;
				break;
			}
			tmpIndex++;
		}
	}
	
	public void addShieldSegment(Shield.Segment segment) {
		if (!shields.containsKey(segment.getMaterial())) {
			addShield(segment.getMaterial());
		} else {
			shields.get(segment.getMaterial()).addSegment(segment);
		}
	}
	
	
	public void addPowerUp(PowerUp powerUp) {
		powerUps.add(powerUp);
	}
	
	public void removePowerUp(PowerUp powerUp) {
		powerUps.remove(powerUp);
	}
	
	public void addLife(int life) {
		currentLife +=life;
		
		if (currentLife > getMaxLife()) {
			life -= currentLife - getMaxLife();
			currentLife = getMaxLife();			
		}
		
		// Game Tracker
		if (!(this instanceof EnemyShip)) {
			game.getGameTracker().addHeal(life);
		}
		
		if (!(game instanceof BackgroundBattle) && life > 0) {
			FlashText text = new FlashText(this, "+" + String.valueOf(life), game);
			text.setFontColor(Color.green);
		}
	}
	
	public int getExperience() {
		return experience;
	}
	
	public void setRank(int newRank) {
		this.rank = newRank;		
		currentLife = getMaxLife();
	}
	
	public void rotateTo(float x, float y) {
		rotateTo((int)x, (int)y);
	}
	
	public void rotateTo(int x, int y) {
		if (sprite != null) {
			if (bounds.getCenterX() != x && bounds.getCenterY() != y) {
				Vector vector = new Vector(new Point(bounds.getCenterX(), bounds.getCenterY()), new Point(x, y));
				
				
				if (bounds.getCenterY() > y) {
					setRotation(360 - vector.getAngle() + 90);
					
				} else {
					setRotation(vector.getAngle() + 90);
				}
			}	
		}
	}
	
	public void rotateTo(GameObject object) {
		rotateTo(object.getCenterX(), object.getCenterY());
	}
	
	public boolean hasTarget() {
		return target != null;
	}
	
	public void setTarget(Spaceship pos) {
		this.target = pos;
	}
	
	public Spaceship getTarget() {
		return target;
	}
	
	
	public Weapon getWeapon(int index) {
		try {
			return weapons.get(index);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public void setCurrentLife(int life) {
		currentLife = life;
		if (currentLife > getMaxLife()) {
			currentLife = getMaxLife();
		}
	}
	
	
	public Weapon getRandomWeapon() {
		if (weapons.isEmpty()) {
			return null;
		}
		
		return weapons.get((int)(Math.random() * weapons.size()));
	}
	
	
	public Shield getRandomShield() {
		if (shields.isEmpty()) {
			return null;
		}
		
		Shield shield = activeShield;		
		int randIndex = (int) (Math.random() * shields.size());
		int index = 0;
		
		for (Entry<Material, Shield> tmpShield : shields.entrySet()) {
			if (index == randIndex) {
				return tmpShield.getValue();
			}
			
			index++;
		}		
		
		return shield;
	}
	
	
	
	public int getCurrentLife() {
		return currentLife;
	}


	public double getLifePercent() {
		return currentLife / getMaxLife();
	}
	
	public void addExperience(int value) {
		experience += value;
		
		if (experience >= getNextExperience()) {
			experience = experience - getNextExperience();
			upgrade();
		}
		
		// Game Tracker
		if (!(this instanceof EnemyShip)) {
			game.getGameTracker().addExperience(experience);
		}
	}
	
	
	public void destroy() {
		currentLife = 0;
		removeQuery();
	}
	
	
	public void upgrade() {
		++rank;
		currentLife = getMaxLife();
		if (rank % 4 == 0) {
			setSpeed(getSpeed() + 0.05f);
		}
		game.getAnimations().addAnimation(this, Animations.EXPLOSION_GREEN);
		Sound sound = ResourceManager.getInstance().getSound("SOUND_UPGRADE");
		sound.playAt(1.0f, 1.0f, getCenterX(), getCenterY(), 0.0f);
		if (!(game instanceof BackgroundBattle)) {
			FlashText text = new FlashText(this, "Rank up!", game);
			text.setFontColor(Color.green);
		}
	}
	
	public ArrayList<Weapon> getAllWeapons() {
		return weapons;
	}
	
	public void addWeapon(Weapon weapon) {
		
		/**
		 * Add a new weapon but only when the same type does not exist in the list
		 */
		Weapon foundResult = null;
		for (Weapon internWeapon : weapons) {
			if (internWeapon.hasSameTypeAs(weapon)) {
				foundResult = internWeapon;
				break;
			}
		}
		
		if (foundResult != null) {
			for (int i = 0; i < weapon.getRank(); ++i) {
				foundResult.upgrade();
			}
		} else {
			weapons.add(weapon);			
			weapon.setLocalY((int)(getHeight() / 2));
			realignWeapons();
		}				
	}
	
	
	public int getRank() {
		return rank;
	}
	
	
	public void addDamage(int value, Materialized materialized) {
		
		if (isDead()) {
			return;
		}
		
		value -= getDamageAbsorbtion();
		
		if (value < 0) {
			value = 0;
		}
		
		if (activeShield != null && !activeShield.isDepleted()) {
			value = activeShield.addDamage(value, materialized);
		}
		
		if (value >= currentLife) {			
			value = currentLife;
			currentLife = 0;
		} else  {
			currentLife -= value;
		}
		
		// Game Tracker
		if (this instanceof EnemyShip) {
			game.getGameTracker().addOwnDamage(value);
		} else {
			game.getGameTracker().addOtherDamage(value);
		}
		
		if (isDead()) {
			onDie();
			removeQuery();						
		}
		
		if (!(game instanceof BackgroundBattle) && value > 0) {
			FlashText text = new FlashText(this, String.valueOf(value), game);
			text.setFontColor(Color.red);
		}
	}
	
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		if (getActiveShield() != null) {
			getActiveShield().drawOn(this, g);
		}
	}


	public void onDie() {
		
	}
	
	public boolean isDead() {
		return currentLife < 1;
	}
	
	
	public void shoot() {
		if (game.getContainer() != null && getY() <= game.getContainer().getHeight()) {		
		
			for (Weapon weapon : weapons) {
				weapon.shoot();
			}
		}
	}
	
	
	/**
	 * Helper function to align weapons to the ship
	 * 
	 * @param gc
	 * @param delta
	 */
	public void realignWeapons() {
	
		float space = getWidth() / (weapons.size() + 1);
		float localX = 0;
		for (Weapon weapon : weapons) {
			localX += space;
			weapon.setLocalX((int)Math.ceil(localX));	
		}
		
	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		
		// Update the weapons
		for (Weapon weapon : weapons) {
			if (weapon.isUsed()) {
				weapon.update(gc, delta);
			}
		}
		
		// Update the power ups		
		for (PowerUp powerUp : powerUps) {
			if (powerUp.isUsed()) {
				powerUp.update(gc, delta);
				if (powerUp.isOver()) {
					powerUp.onOver(this);
					removePowerUp(powerUp);
				}
			}
		}
		
		// Update the shields
		for (Shield shield : shields.values()) {
			shield.update(gc, delta);
		}
		
		if (getY() > game.getWorldHeight()) {
			removeQuery();
		}
	}
	
	public Collection<Shield> getAllShields() {
		return shields.values();
	}
	
	
	public void removeAllWeapons() {
		while (!weapons.isEmpty()) {
			weapons.get(0).removeQuery();
			weapons.remove(0);
		}
	}

	@Override
	public void onRemove(GameContainer gc) {
		super.onRemove(gc);
		if (getY() < gc.getHeight() && getY() + getHeight() > 0) {
			for (Weapon weapon : weapons) {
				weapon.removeQuery();
			}
			game.getAnimations().addAnimation(this, Animations.EXPLOSION);
			Sound sound = ResourceManager.getInstance().getSound("SOUND_BIG_EXPLOSION");
			if (game instanceof BackgroundBattle) {
				sound.playAt(0.7f, 0.1f, getCenterX(), getCenterY(), -3.0f);
			} else {
				sound.playAt((float)(1.4 - Math.random() * 0.9), (float)(0.7 - Math.random() * 0.4), getCenterX(), getCenterY(), 0.0f);
			}
		}
	}
	
	
	
	protected abstract int getRankLife(int rank);
	protected abstract int getRankExperience(int rank);
	protected abstract int getRankGainedExperience(int rank);
	protected abstract int getRankDamageAbsorbtion(int rank);
	protected abstract float getRankCriticalChange(int rank);
	
	public int getMaxLife() {
		return getRankLife(rank);
	}
	
	public float getCriticalChance() {
		return getRankCriticalChange(rank);
	}
	
	public int getDamageAbsorbtion() {
		return getRankDamageAbsorbtion(rank);
	}
	
	public int getNextExperience() {
		return getRankExperience(rank);
	}
	
	public int getGainedExperience() {
		return getRankGainedExperience(rank);
	}

	
	
	public static boolean isProbably(float percent) {
		return Math.random() * 1000 < (percent * 10.0f);
	}


	@Override
	public void onCollidateWith(GameObject other) {
		super.onCollidateWith(other);
		if (this instanceof AlliedShip && 
			      other instanceof Spaceship && !(other instanceof AlliedShip)) {
		   Spaceship object1 = this;
		   Spaceship object2 = (Spaceship)other;
		   object1.addDamage(object2.getCurrentLife() * 30, object2);
		   object2.addDamage(object1.getCurrentLife() * 30, object1);
	   }
	}
	
	
	public boolean hasActiveShield() {
		return activeShield != null;
	}
	

	
	@Override
	public Material getMaterial() {
		return Material.NONE;
	}
	
	@Override
	public void setMaterial(Material material) {
		
	}
}
