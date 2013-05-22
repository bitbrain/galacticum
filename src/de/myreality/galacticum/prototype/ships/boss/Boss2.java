package de.myreality.galacticum.prototype.ships.boss;

import org.newdawn.slick.GameContainer;

import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.ships.BossShip;
import de.myreality.galacticum.prototype.weapons.IonWeapon;
import de.myreality.galacticum.prototype.weapons.RadeonWeapon;

public class Boss2 extends BossShip {

	public Boss2(int x, int y, WorldState game) {
		super(x, y, ResourceManager.getInstance().getImage("SPRITE_BOSS_2"), game);
		setSpeed(0.04f);
		addWeapon(new RadeonWeapon(0, 0, this, game));
		addWeapon(new IonWeapon(0, 0, this, game));
		addWeapon(new IonWeapon(0, 0, this, game));
		addWeapon(new IonWeapon(0, 0, this, game));
	}
	
	

	@Override
	protected int getRankLife(int rank) {
		return 3500 + rank * 700;
	}

	@Override
	protected int getRankExperience(int rank) {
		return 1200 * rank;
	}

	@Override
	protected int getRankGainedExperience(int rank) {
		return 1000 * rank;
	}

	@Override
	protected int getRankDamageAbsorbtion(int rank) {
		return 5 * rank;
	}
	
	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		if (hasTarget()) {
			moveTo((int)getTarget().getCenterX(), (int)getTarget().getCenterY());	
		}
	}



	@Override
	public void onNextPhase(int phase) {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected float getRankCriticalChange(int rank) {
		// TODO Auto-generated method stub
		return 0;
	}

}
