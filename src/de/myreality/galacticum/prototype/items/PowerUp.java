package de.myreality.galacticum.prototype.items;

import org.newdawn.slick.GameContainer;

import de.myreality.galacticum.prototype.Animations;
import de.myreality.galacticum.prototype.Shot;
import de.myreality.galacticum.prototype.Timer;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.ships.Spaceship;

public abstract class PowerUp extends Item {
	
	private Timer timer;
	private int duration;
	
	public PowerUp(int x, int y, int miliseconds, WorldState game) {
		super(x, y, game);
		timer = new Timer();
		duration = miliseconds;
		game.getAnimations().addAnimation(this, Animations.BLUE_FLARE, true);
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
		timer.reset();
	}
	
	public int getDuration() {
		return duration;
	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);	
		timer.update(delta);
	}
	
	public boolean isOver() {
		return timer.getMiliseconds() > duration;
	}
	
	public abstract void handleEffects(Spaceship ship, Shot shot);
	public abstract void onOver(Spaceship ship);

	@Override
	public void alignToShip(Spaceship owner) {
		super.alignToShip(owner);
		owner.addPowerUp(this);
		timer.start();
	}
	
	
	
	
	
	

}
