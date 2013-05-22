package de.myreality.galacticum.prototype.ships;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import de.myreality.galacticum.prototype.EffectFunction;
import de.myreality.galacticum.prototype.GameObject;
import de.myreality.galacticum.prototype.MovableSpriteObject;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.items.InvisiblePowerUp;
import de.myreality.galacticum.prototype.weapons.PlasmaWeapon;

public class BigDestroyerShip extends EnemyShip {
	
	private EffectFunction effectFunction = new EffectFunction() {

		@Override
		public void doEffect(GameObject obj) {
			float velocityFactor = 0.08f;
			
			MovableSpriteObject sprite = (MovableSpriteObject)obj;
			if (isProbably(50)) {
				sprite.getVelocity().x += velocityFactor;
				if (sprite.getX() + obj.getWidth() > game.getWorldWidth()) {
					sprite.getVelocity().x -= velocityFactor;
				}
			} else {				
				sprite.getVelocity().x -= velocityFactor;
				if (sprite.getX() < 0) {
					sprite.getVelocity().x += velocityFactor;
				}
			}
		}
		
	};

	public BigDestroyerShip(int x, int y, WorldState game) throws SlickException {
		super(x, y, ResourceManager.getInstance().getImage("SPRITE_SHIP_BIG_DESTROYER"), game);	
		addWeapon(new PlasmaWeapon(0, 0, this, game));
		if (isProbably(20)) {
			addWeapon(new PlasmaWeapon(0, 0, this, game));
		}
		setSpeed(0.07f);
		setEffectFunction(effectFunction);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
	}

	@Override
	public void onRemove(GameContainer gc) {
		super.onRemove(gc);
		
		if (isProbably(30)) {
			getRandomWeapon().getAsItem();
		}
		
		if (isProbably(5)) {
			InvisiblePowerUp shield = new InvisiblePowerUp((int)getCenterX(), (int)getCenterY(), game);
			shield.setRank(getRank());
		}
	}

	@Override
	protected int getRankLife(int rank) {
		return 20 + (rank * 8);
	}

	@Override
	protected int getRankExperience(int rank) {
		return rank * 800;
	}

	@Override
	protected int getRankGainedExperience(int rank) {
		return rank * 20;
	}
	
	@Override
	protected int getRankDamageAbsorbtion(int rank) {
		return rank;
	}

	@Override
	protected float getRankCriticalChange(int rank) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	

}
