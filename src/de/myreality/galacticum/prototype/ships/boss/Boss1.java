package de.myreality.galacticum.prototype.ships.boss;

import org.newdawn.slick.GameContainer;

import de.myreality.galacticum.prototype.EffectFunction;
import de.myreality.galacticum.prototype.GameObject;
import de.myreality.galacticum.prototype.IngameState;
import de.myreality.galacticum.prototype.Material;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.ships.BossShip;
import de.myreality.galacticum.prototype.weapons.IonWeapon;
import de.myreality.galacticum.prototype.weapons.RadeonWeapon;

public class Boss1 extends BossShip {
	
	private EffectFunction phase1 = new EffectFunction() {

		@Override
		public void doEffect(GameObject obj) {			
			if (obj instanceof BossShip) {
				BossShip boss = (BossShip)obj;
				setSpeed(0.05f);
				if (boss.hasTarget()) {
					boss.moveTo((int)boss.getTarget().getCenterX(), (int)boss.getTarget().getCenterY());
					rotateTo(getTarget().getCenterX(), getTarget().getCenterY());
					if (game instanceof IngameState) {
						IngameState ingame = (IngameState)game;
						ingame.generateObstacles(game.getContainer());
						ingame.generateObstacles(game.getContainer());
					}
					shoot();
				}			
			}			
		}
		
	};
	
	private EffectFunction phase2 = new EffectFunction() {

		@Override
		public void doEffect(GameObject obj) {			
			if (obj instanceof BossShip) {
				BossShip boss = (BossShip)obj;
				boss.setSpeed(0.0f);
				boss.setRotation(boss.getRotation() - 1.5f);	
				boss.shoot();
			}			
		}
		
	};
	

	public Boss1(int x, int y, WorldState game) {
		super(x, y, ResourceManager.getInstance().getImage("SPRITE_BOSS_1"), game);
		setSpeed(0.05f);
		addWeapon(new RadeonWeapon(0, 0, this, game));
		addWeapon(new IonWeapon(0, 0, this, game));
		addPhase(phase1);
		addPhase(phase2);
		if (isProbably(80)) {
			addShield(Material.NONE);
		} else {
			addShield(Material.LASER);
		}		
	}
	
	

	@Override
	protected int getRankLife(int rank) {
		return 1900 + rank * 200;
	}

	@Override
	protected int getRankExperience(int rank) {
		return 800 * rank;
	}

	@Override
	protected int getRankGainedExperience(int rank) {
		return 600 * rank;
	}

	@Override
	protected int getRankDamageAbsorbtion(int rank) {
		return 1 * rank;
	}
	
	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);		
	}



	@Override
	public void onNextPhase(int phase) {
		if (phase == 1) {
			addWeapon(new IonWeapon(0, 0, this, game));	
			addWeapon(new RadeonWeapon(0, 0, this, game));	
		}
	}
	
	



	@Override
	public void onRemove(GameContainer gc) {
		super.onRemove(gc);
		if (hasActiveShield()) {
			getRandomShield().getNewSegment((int)getX(), (int)getY(), game);
		}
	}



	@Override
	protected float getRankCriticalChange(int rank) {
		return rank * 2.5f;
	}

}
