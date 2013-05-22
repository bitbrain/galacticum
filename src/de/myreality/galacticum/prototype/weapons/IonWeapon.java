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

public class IonWeapon extends Weapon {
	
	private EffectFunction function = new EffectFunction() {

		/**
		 * Shoot multiple times near to the radius
		 */
		@Override
		public void doEffect(GameObject obj) {			
			if (obj instanceof Weapon) {
				Weapon weapon = (Weapon)obj;
				final float angle = 5.0f;
				float currentAngle = 0.0f;
				if (hasShooted()) {
					for (int i = 1; i < getRank(); ++i) {
						
						currentAngle = -(i * angle);
						
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
	
	public IonWeapon(int x, int y, Spaceship ship, WorldState game) {
		super(x, y, ship, game);		
		this.setColor(new Color(130, 255, 0));
		setShootSprite(ResourceManager.getInstance().getImage("SHOT_ION"));
		setIcon(ResourceManager.getInstance().getImage("ICON_WEAPON_ION"));
		this.material = Material.ION;
		this.setEffectFunction(function);
	}

	@Override
	public void onShoot(Shot shot) {
		Sound sound = ResourceManager.getInstance().getSound("SOUND_SHOT_1");
		sound.playAt((float)(1.4 + Math.random() * 0.9), (float)(1.0 - Math.random() * 0.1), getX(), getY(), 0.0f);
	}

	@Override
	protected int getRankDamage(int rank) {
		return 10 + 5 * rank;
	}

	@Override
	protected int getRankDuration(int rank) {
		int duration = 350 - rank * 20;
		if (duration < 100) {
			duration = 100;
		}
		return duration;
	}

	@Override
	protected float getRankShootSpeed(int rank) {
		return 0.6f + 0.05f * rank;
	}

	@Override
	protected int getRankSize(int rank) {
		return (int) (7 + rank * 0.5f);
	}
	
	

}
