package de.myreality.galacticum.prototype.ships;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

import de.myreality.galacticum.prototype.BackgroundBattle;
import de.myreality.galacticum.prototype.IngameState;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.items.HealPower;
import de.myreality.galacticum.prototype.weapons.Weapon;

public abstract class EnemyShip extends Spaceship {

	public EnemyShip(int x, int y, Image sprite, WorldState game) {
		super(x, y, sprite, game);
		setRotation(180);
		moveTo((int)(Math.random() * game.getWorldWidth()), game.getWorldHeight());	

	}
	

	
	

	@Override
	public void setRank(int newRank) {
		int oldRank = getRank();
		super.setRank(newRank);
		
		int difference = oldRank - newRank;
		
		for (Weapon weapon : getAllWeapons()) {
			for (int i = 0; i < difference; ++i) {
				weapon.upgrade();
			}
		}
	}





	@Override
	public void onDie() {
		super.onDie();
		game.getGameTracker().addKill();
	}


	@Override
	protected int getRankLife(int rank) {
		// TODO Auto-generated method stub
		return 0;
	}








	@Override
	protected int getRankExperience(int rank) {
		// TODO Auto-generated method stub
		return 0;
	}








	@Override
	protected int getRankGainedExperience(int rank) {
		// TODO Auto-generated method stub
		return 0;
	}








	@Override
	protected int getRankDamageAbsorbtion(int rank) {
		// TODO Auto-generated method stub
		return 0;
	}








	@Override
	public void onRemove(GameContainer gc) {
		super.onRemove(gc);
		if (isProbably(10) && !(game instanceof BackgroundBattle)) {
			new HealPower((int)getCenterX(), (int)getCenterY(), 20, game);
		}	
	}


	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		
		if (hasTarget() && getTarget().isDead()) {
			setTarget(null);
		}
		
		if (game instanceof IngameState && hasTarget()) {
			IngameState ingame = (IngameState)game;
			setTarget(ingame.getPlayerShip());
		} else if (game instanceof IngameState) {
			IngameState ingame = (IngameState)game;
			setTarget(ingame.getPlayerShip());
		}
		
		if (hasTarget() && getTarget().isHalfInvisible() && !(this instanceof BossShip)) {
			setTarget(null);
		}
		
		if (hasTarget() && !(this instanceof BossShip)) {
			rotateTo(getTarget().getCenterX(), getTarget().getCenterY());
			shoot();
		}
	}


}
