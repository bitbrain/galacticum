package de.myreality.galacticum.prototype.weapons;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

import de.myreality.galacticum.prototype.Animations;
import de.myreality.galacticum.prototype.BackgroundBattle;
import de.myreality.galacticum.prototype.Material;
import de.myreality.galacticum.prototype.Shot;
import de.myreality.galacticum.prototype.Timer;
import de.myreality.galacticum.prototype.Vector;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.items.Item;
import de.myreality.galacticum.prototype.ships.Spaceship;

public abstract class Weapon extends Item {
	
	protected Spaceship parentShip;
	
	private Timer timer;
	
	private int localX, localY;

	private Image shootSprite;
	
	private boolean shooted;
	
	protected Material material;
	
	public static int LASER = 0, PLASMA = 1, ION = 2, RADEON = 3;

	
	public Material getMaterial() {
		return this.material;
	}
	
	
	public boolean hasSameTypeAs(Weapon weapon) {
		return getMaterial() == weapon.getMaterial();
	}


	public Weapon(int x, int y, Spaceship ship, WorldState game) {
		super(x, y, game);
		parentShip = ship;
		localX = 0;
		localY = 0;
		timer = new Timer();
		timer.start();
		
		if (parentShip != null) {
			setItemUse(true);			
		} else {
			setItemUse(false);
		}
		game.getAnimations().addAnimation(this, Animations.ORANGE_FLARE, true);
	}
	
	
	public void setShootSprite(Image sprite) {
		shootSprite = sprite;
	}
	
	
	public void setParentShip(Spaceship parent) {
		parentShip = parent;
	}
	
	
	public Weapon(int x, int y, WorldState game) {
		this(x, y, null, game);
	}
	
	
	
	
	
	@Override
	public void alignToShip(Spaceship owner) {		
		if (!isUsed()) {
			setParentShip(owner);
			setRotation(0);
			owner.addWeapon(this);	
			setX(0);
			stop();
		}
		super.alignToShip(owner);
	}

	
	public int getLocalX() {
		return localX;
	}
	
	public int getLocalY() {
		return localY;
	}
	
	public void setLocalX(int x) {
		localX = x;
	}
	
	public void setLocalY(int y) {
		localY = y;
	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		timer.update(delta);
		if (parentShip != null) {
			bounds.setX(parentShip.getBounds().getX() + localX);
			bounds.setY(parentShip.getBounds().getY() + localY);
		}
	
	}	
	
	
	public Weapon getAsItem() {
		Weapon item = getAsNewItem();
		for (int i = 1; i < getRank(); i++) {
			item.upgrade();
		}
		return item;
	}
	
	public Weapon getAsNewItem() {
		Weapon weapon = null;
		
		if (this instanceof IonWeapon) {
			weapon = new IonWeapon((int)getCenterX(), (int)getCenterY(), null, game);
		} else if (this instanceof LaserWeapon) {
			weapon = new LaserWeapon((int)getCenterX(), (int)getCenterY(), null, game);
		} else if (this instanceof PlasmaWeapon) {
			weapon = new PlasmaWeapon((int)getCenterX(), (int)getCenterY(), null, game);
		} else if (this instanceof RadeonWeapon) {
			weapon = new RadeonWeapon((int)getCenterX(), (int)getCenterY(), null, game);
		}
		
		
		
		return weapon;
	}
	

	public boolean hasShooted() {
		return shooted;
	}
	
	public void shoot() {
		if (couldShoot()) {
			shooted = true;
			doShot();	
			timer.reset();
		} else {
			shooted = false;
		}
	}
	
	
	public float getLoadPercent() {
		float percent = (float)timer.getMiliseconds() / (float)getDuration();
		if (percent > 1.0f) {
			percent = 1.0f;
		}
		return percent;
	}
	
	
	public boolean couldShoot() {
		return timer.getMiliseconds() > getDuration() && parentShip != null;
	}
	
	protected void doShot() {

		// In background, no one can shoot
		if (game instanceof BackgroundBattle) {
			return;
		}
		
		// Rotation
		float totalRotation = parentShip.getRotation() + getRotation();
		
		Shot shot = new Shot(material, (int)parentShip.getX() + localX, (int)parentShip.getY() + localY, getShotSize(), parentShip, game);
		shot.setSpeed(getShootSpeed());
		shot.setDamage(getDamage());
		shot.setRotation(totalRotation);

		if (shootSprite != null) {
			shot.setSprite(shootSprite);
		}

		Vector vector = new Vector(360 - (totalRotation - 90));
		shot.move(vector);
	
		if (!(game instanceof BackgroundBattle)) {
			onShoot(shot);
		}
	}
	
	public void onShoot(Shot shot) { }
	
	
	protected abstract int getRankDamage(int rank);	
	protected abstract int getRankDuration(int rank);
	protected abstract float getRankShootSpeed(int rank);
	protected abstract int getRankSize(int rank);
	
	public int getDamage() {
		return getRankDamage(getRank());
	}
	
	public int getDuration() {
		return getRankDuration(getRank());
	}
	
	public float getShootSpeed() {
		return getRankShootSpeed(getRank());
	}
	
	public int getShotSize() {
		return getRankSize(getRank());
	}
	
	

}
