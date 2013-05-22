package de.myreality.galacticum.prototype.obstacles;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Sound;

import de.myreality.galacticum.prototype.Animations;
import de.myreality.galacticum.prototype.BackgroundBattle;
import de.myreality.galacticum.prototype.GameObject;
import de.myreality.galacticum.prototype.Material;
import de.myreality.galacticum.prototype.Materialized;
import de.myreality.galacticum.prototype.MovableSpriteObject;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.Shield;
import de.myreality.galacticum.prototype.Shot;
import de.myreality.galacticum.prototype.Vector;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.ships.BossShip;
import de.myreality.galacticum.prototype.ships.Spaceship;

public class Obstacle extends MovableSpriteObject implements Materialized {
	
	private Material material;
	
	private Material reactiveMaterial;

	public Obstacle(int x, int y, int width, int height, WorldState game) {
		super(x, y, width, height, game);
		moveTo((int)(Math.random() * game.getWorldWidth()), game.getWorldHeight());	
		setMaterial(Material.NONE);
		reactiveMaterial = Material.RHADEON;
	}
	
	
	
	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
	}
	
	public void destroy() {
		game.getAnimations().addAnimation(this, Animations.EXPLOSION);
		Sound sound = ResourceManager.getInstance().getSound("SOUND_BIG_EXPLOSION");
		if (game instanceof BackgroundBattle) {
			sound.playAt(0.7f, 0.1f, getCenterX(), getCenterY(), -3.0f);
		} else {
			sound.playAt((float)(1.4 - Math.random() * 0.9), (float)(0.7 - Math.random() * 0.4), getCenterX(), getCenterY(), 0.0f);
		}
		removeQuery();
		
	}


	public void doPhysics(Obstacle other) {
		Vector oldVelocity = getVelocity();
		getVelocity().x = other.getVelocity().x;
		getVelocity().y = other.getVelocity().y;
		other.getVelocity().x = oldVelocity.x;
		other.getVelocity().y = oldVelocity.y;
	}



	@Override
	public void onCollidateWith(GameObject other) {
		super.onCollidateWith(other);
		// Collidate with ships
		if (other instanceof Spaceship) {				   
			   if (other instanceof BossShip) {
				   destroy();
			   } else {
				   Spaceship ship = (Spaceship)other;
				   Shield activeShield = ship.getActiveShield();
				   final int collisionDamage = ship.getMaxLife() / 100;
				   if (activeShield != null) {
					   if (activeShield.getMaterial().equals(reactiveMaterial)) {
						   destroy();
					   } else {
						   ship.addDamage(collisionDamage, this);
					   }
				   } else {
					   ship.addDamage(collisionDamage, this);
				   }
			   }
	    // Collidate with shots
		} else if (other instanceof Shot) {
			   Shot shot = (Shot)other;
			   game.getAnimations().addAnimation(shot, Animations.EXPLOSION_SHORT);
			   Sound sound = ResourceManager.getInstance().getSound("SOUND_SMALL_EXPLOSION");
			   
			   // Check the material of the shot. If it's plasma, the obstacle goes up
			   if (shot.getMaterial().equals(reactiveMaterial)) {
				   destroy();
			   }
			   sound.playAt((float)(0.5 + Math.random() * 0.9), (float)(1.0 - Math.random() * 0.3), shot.getX(), shot.getY(), 0.0f);
			   shot.removeQuery();
	    // Collidate with another obstacle
		} else if (other instanceof Obstacle) {
			   Obstacle otherObstacle = (Obstacle)other;
			   doPhysics(otherObstacle);
		}
	}



	@Override
	public Material getMaterial() {
		return material;
	}



	@Override
	public void setMaterial(Material material) {
		this.material = material;
		
	}
	
	
	public void setReactiveMaterial(Material material) {
		this.reactiveMaterial = material;
	}
	
	
	
}
