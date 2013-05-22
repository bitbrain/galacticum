package de.myreality.galacticum.prototype.ships;

import org.newdawn.slick.SlickException;

import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.weapons.LaserWeapon;

public class AlliedShip extends Spaceship {

	public AlliedShip(int x, int y, WorldState game) throws SlickException {
		super(x, y, ResourceManager.getInstance().getImage("SPRITE_PLAYER_SHIP"), game);
		addWeapon(new LaserWeapon(0, 0, this, game));
		setRank(1);
	}
	

	@Override
	protected int getRankLife(int rank) {
		return 1500 + rank * 300;
	}

	@Override
	protected int getRankExperience(int rank) {
		return 1400 + rank * 200;
	}

	@Override
	protected int getRankGainedExperience(int rank) {
		return rank * 500;
	}


	@Override
	protected int getRankDamageAbsorbtion(int rank) {
		return rank;
	}


	@Override
	protected float getRankCriticalChange(int rank) {
		return rank * 4.5f;
	}
	
	

}
