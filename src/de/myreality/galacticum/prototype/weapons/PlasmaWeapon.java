package de.myreality.galacticum.prototype.weapons;

import org.newdawn.slick.Color;
import org.newdawn.slick.Sound;

import de.myreality.galacticum.prototype.EffectFunction;
import de.myreality.galacticum.prototype.GameObject;
import de.myreality.galacticum.prototype.Material;
import de.myreality.galacticum.prototype.MovableSpriteObject;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.Shot;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.ships.Spaceship;

public class PlasmaWeapon extends Weapon {
	
	private EffectFunction moveFunction = new EffectFunction() {

		@Override
		public void doEffect(GameObject obj) {
			
			MovableSpriteObject sprite = (MovableSpriteObject)obj;
			final float factor = 0.1f;
			if (Spaceship.isProbably(50)) {
				sprite.getVelocity().x += factor;
				sprite.getVelocity().normalize();
			} else {
				sprite.getVelocity().x -= factor;
				sprite.getVelocity().normalize();
			}
			
			if (Spaceship.isProbably(50)) {
				sprite.getVelocity().y += factor / 3;
				sprite.getVelocity().normalize();
			} else {
				sprite.getVelocity().y -= factor / 3;
				sprite.getVelocity().normalize();
			}
			sprite.addRotation((float) (Math.random() * 5));
			sprite.setOpacity(obj.getOpacity() - 0.001f);
			if (sprite.getOpacity() < 0.4f) {
				sprite.removeQuery();
			}
		}
		
	};

	public PlasmaWeapon(int x, int y, Spaceship ship, WorldState game) {
		super(x, y, ship, game);		
		this.setColor(new Color(0, 100, 255));
		setIcon(ResourceManager.getInstance().getImage("ICON_WEAPON_PLASMA"));
		setShootSprite(ResourceManager.getInstance().getImage("SHOT_PLASMA"));
		this.material = Material.PLASMA;
	}
	
	@Override
	public void onShoot(Shot shot) {
		shot.setEffectFunction(moveFunction);
		Sound sound = ResourceManager.getInstance().getSound("SOUND_SHOT_1");
		sound.playAt((float)(1.4 + Math.random() * 0.9), (float)(0.7 - Math.random() * 0.4), getX(), getY(), 0.0f);
	}

	@Override
	protected int getRankDamage(int rank) {
		return 10 + 5 * rank;
	}

	@Override
	protected int getRankDuration(int rank) {
		int duration = 500 - rank * 20;
		if (duration < 100) {
			duration = 100;
		}
		return duration;
	}

	@Override
	protected float getRankShootSpeed(int rank) {
		return 0.15f + 0.05f * rank;
	}
	
	
	@Override
	protected int getRankSize(int rank) {
		return (int) (5 + rank * 0.5f);
	}

}
