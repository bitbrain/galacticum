package de.myreality.galacticum.prototype.ships;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import de.myreality.galacticum.prototype.BackgroundBattle;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.weapons.LaserWeapon;

public class DestroyerShip extends EnemyShip {

	public DestroyerShip(int x, int y, WorldState game) throws SlickException {
		super(x, y, ResourceManager.getInstance().getImage("SPRITE_SHIP_DESTROYER"), game);	
		addWeapon(new LaserWeapon(0, 0, this, game));
		setSpeed(0.06f);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);		
	}


	@Override
	public void onRemove(GameContainer gc) {
		super.onRemove(gc);
		
		if (isProbably(15) && !(game instanceof BackgroundBattle)) {
			getRandomWeapon().getAsItem();			
		}
	}

	@Override
	protected int getRankLife(int rank) {
		return 6 + (rank * 3);
	}

	@Override
	protected int getRankExperience(int rank) {
		return rank * 500;
	}

	@Override
	protected int getRankGainedExperience(int rank) {
		return rank * 10;
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
