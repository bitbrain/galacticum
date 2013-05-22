package de.myreality.galacticum.prototype.ships;

import org.newdawn.slick.GameContainer;

import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.WorldState;

public class SeekerShip extends EnemyShip {

	public SeekerShip(int x, int y, WorldState game) {
		super(x, y, ResourceManager.getInstance().getImage("SPRITE_SHIP_SEEKER"), game);
		setSpeed(0.2f);
	}

	@Override
	protected int getRankLife(int rank) {
		return rank * 10;
	}

	@Override
	protected int getRankExperience(int rank) {
		return rank * 200;
	}

	@Override
	protected int getRankGainedExperience(int rank) {
		return 5 * rank;
	}

	@Override
	protected int getRankDamageAbsorbtion(int rank) {
		return 0;
	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		if (hasTarget()) {
			moveTo((int)getTarget().getCenterX(), (int)getTarget().getCenterY());	
		}
	}

	@Override
	protected float getRankCriticalChange(int rank) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
