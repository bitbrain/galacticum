package de.myreality.galacticum.prototype.weapons;

import org.newdawn.slick.Color;
import org.newdawn.slick.Sound;

import de.myreality.galacticum.prototype.EffectFunction;
import de.myreality.galacticum.prototype.GameObject;
import de.myreality.galacticum.prototype.Material;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.Shot;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.ships.Spaceship;

public class RadeonWeapon extends Weapon {
	
	private EffectFunction function = new EffectFunction() {

		/**
		 * Shoot multiple times near to the radius
		 */
		@Override
		public void doEffect(GameObject obj) {			
			if (obj instanceof Weapon) {
				Weapon weapon = (Weapon)obj;
				final float angle = 80.0f + (float) (80.0f * Math.random());
				float currentAngle = 0.0f;
				if (hasShooted()) {
					for (int i = 1; i < 6 + getRank(); ++i) {
						
						currentAngle = -(i * angle);
						
						currentAngle += Math.random() * (angle * 2);
						
						if (i % 2 == 0) {
							currentAngle = -currentAngle;
						}
						
						setRotation(currentAngle);
						
						weapon.doShot();
					}
				}
			}			
		}
		
	};

	public RadeonWeapon(int x, int y, Spaceship ship, WorldState game) {
		super(x, y, ship, game);	
		this.setColor(new Color(160, 0, 255));
		setIcon(ResourceManager.getInstance().getImage("ICON_WEAPON_RADEON"));	
		this.material = Material.RHADEON;
		setEffectFunction(function);
	}
	
	@Override
	public void onShoot(Shot shot) {
		Sound sound = ResourceManager.getInstance().getSound("SOUND_SHOT_1");
		sound.playAt((float)(1.4 + Math.random() * 0.9), (float)(0.7 - Math.random() * 0.4), getX(), getY(), 0.0f);
	}
	
	

	@Override
	protected int getRankDamage(int rank) {
		return 20 + 5 * rank;
	}

	@Override
	protected int getRankDuration(int rank) {
		int duration = 1000 - rank * 40;
		if (duration < 100) {
			duration = 100;
		}
		return duration;
	}

	@Override
	protected float getRankShootSpeed(int rank) {
		return 0.8f + 0.03f * rank;
	}
	
	
	@Override
	protected int getRankSize(int rank) {
		return (int) (3 + rank * 0.2f);
	}

}
