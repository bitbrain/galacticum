package de.myreality.galacticum.prototype.ships;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import de.myreality.galacticum.prototype.Material;
import de.myreality.galacticum.prototype.Shield.Segment;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.weapons.IonWeapon;

public class PlayerShip extends AlliedShip {
	
	private int gatheredPoints;

	public PlayerShip(int x, int y, WorldState game) throws SlickException {
		super(x, y, game);
		removeAllWeapons();
		addWeapon(new IonWeapon(0, 0, this, game));
		addShield(Material.NONE);
		Segment newSegment = activeShield.getNewSegment(x, y, game);
		newSegment.alignToShip(this);
	}
	
	public void addGatheredPoints(int points) {
		this.gatheredPoints += points;
	}
	
	public int getGatheredPoints() {
		return gatheredPoints;
	}
	
	@Override
	public void addExperience(int value) {
		super.addExperience(value);
		addGatheredPoints(value);
	}

	public void setGatheredPoints(int points) {
		this.gatheredPoints = points;
	}
	
	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		rotateTo(gc.getInput().getMouseX(), gc.getInput().getMouseY());
	}

}
