package de.myreality.galacticum.prototype;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Sound;

import de.myreality.galacticum.prototype.ships.AlliedShip;
import de.myreality.galacticum.prototype.ships.Spaceship;


public class Shot extends MovableSpriteObject implements Materialized {
	
	private Spaceship owner;
	private int damage;
	private Material material;

	public Shot(Material material, int x, int y, int size, Spaceship owner, WorldState game) {
		super(x, y, size + 1, size + 1, game);
		setColor(material.getColor());
		this.material = material;
		int speed = 2;
		setSpeed(speed);
		this.owner = owner;
	}
	
	
	
	@Override
	public Material getMaterial() {
		return material;
	}
	
	
	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		if (isDone()) {
			removeQuery();
		}
		
		if (sprite == null) {
			setRotation(getRotation() - 0.15f * delta);
		}
	}



	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public Spaceship getOwner() {
		return owner;
	}
	
	public boolean isDone() {
		
		return (bounds.getX() < 0 || bounds.getX() + bounds.getWidth() > game.getContainer().getWidth() ||
			bounds.getY() < 0 || bounds.getY() + bounds.getHeight() > game.getContainer().getHeight());
	}



	@Override
	public void onRemove(GameContainer gc) {
		super.onRemove(gc);
	}



	@Override
	public void onCollidateWith(GameObject other) {
		super.onCollidateWith(other);
		if (other instanceof Spaceship) {
		   Spaceship owner = getOwner();
		   Spaceship enemy = (Spaceship)other;	
		   
		   boolean isEnemyOfAllied = owner instanceof AlliedShip && !(enemy instanceof AlliedShip);
		   boolean isEnemyOfEnemy = owner instanceof Spaceship && !(owner instanceof AlliedShip) && enemy instanceof AlliedShip;

		   // Enemy is NOT allied
		   if (isEnemyOfAllied || isEnemyOfEnemy) {
			   
	   			 if (Math.random() * 100 <= owner.getCriticalChance()) {
	   				enemy.addDamage(getDamage() * 2, this);
	   			 } else {
	   				enemy.addDamage(getDamage(), this);
	   			 }
	   			 
		         if (enemy.isDead()) {
					owner.addExperience(enemy.getGainedExperience());
			     }
		         
		         // Do some graphical stuff
		         game.getAnimations().addAnimation(this, Animations.EXPLOSION_SHORT);
		         Sound sound = ResourceManager.getInstance().getSound("SOUND_SMALL_EXPLOSION");
			     sound.playAt((float)(0.7 + Math.random() * 0.9), (float)(1.0 - Math.random() * 0.3), getX(), getY(), 0.0f);
			     removeQuery();

	       }
		}
	}



	@Override
	public void setMaterial(Material material) {
		this.material = material;
	}
	
}
