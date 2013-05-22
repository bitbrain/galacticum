package de.myreality.galacticum.prototype.ships;

import org.newdawn.slick.GameContainer;

import de.myreality.galacticum.prototype.BackgroundBattle;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.items.InvisiblePowerUp;
import de.myreality.galacticum.prototype.weapons.IonWeapon;
import de.myreality.galacticum.prototype.weapons.PlasmaWeapon;


public class AnnihilatorShip extends EnemyShip {

	public AnnihilatorShip(int x, int y, WorldState game) {
		super(x, y, ResourceManager.getInstance().getImage("SPRITE_SHIP_ANNIHILATOR"), game);
				
		addWeapon(new PlasmaWeapon(0, 0, this, game));
		addWeapon(new IonWeapon(0, 0, this, game));
		setSpeed(0.02f);
	}

	@Override
	protected int getRankLife(int rank) {
		return 50 + (rank * 20);
	}

	@Override
	protected int getRankExperience(int rank) {
		return rank * 100;
	}

	@Override
	protected int getRankGainedExperience(int rank) {
		return rank * 50;
	}
	
	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
	}

	@Override
	public void onRemove(GameContainer gc) {
		super.onRemove(gc);
		
		if (isProbably(55) && !(game instanceof BackgroundBattle)) {
			getRandomWeapon().getAsItem();
		}
		
		if (isProbably(20) && !(game instanceof BackgroundBattle)) {
			InvisiblePowerUp shield = new InvisiblePowerUp((int)getCenterX(), (int)getCenterY(), game);
			shield.setRank(getRank());
		}
	}
	
	@Override
	protected int getRankDamageAbsorbtion(int rank) {
		return rank - 1;
	}

	@Override
	protected float getRankCriticalChange(int rank) {
		// TODO Auto-generated method stub
		return 0;
	}

}
