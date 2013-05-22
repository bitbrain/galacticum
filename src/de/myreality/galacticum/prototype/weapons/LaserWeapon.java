package de.myreality.galacticum.prototype.weapons;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Sound;

import de.myreality.galacticum.prototype.Material;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.Shot;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.ships.Spaceship;

public class LaserWeapon extends Weapon {	
	

	public LaserWeapon(int x, int y, Spaceship ship, WorldState game) {
		super(x, y, ship, game);
		this.setColor(new Color(255, 0, 0));
		setIcon(ResourceManager.getInstance().getImage("ICON_WEAPON_LASER"));
		//setShootSprite(ResourceManager.getInstance().getImage("SHOT_LASER"));
		this.material = Material.LASER;
	}
	
	@Override
	public void onShoot(Shot shot) {
		Sound sound = ResourceManager.getInstance().getSound("SOUND_SHOT_1");
		sound.playAt((float)(1.4 + Math.random() * 0.9), (float)(0.5 - Math.random() * 0.1), getX(), getY(), 0.0f);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
	}

	@Override
	protected int getRankDamage(int rank) {
		return 5 + 3 * rank;
	}

	@Override
	protected int getRankDuration(int rank) {
		int duration = 1500 - rank * 20;
		if (duration < 300) {
			duration = 100;
		}
		return duration;
	}

	@Override
	protected float getRankShootSpeed(int rank) {
		return 0.2f + 0.03f * rank;
	}
	
	@Override
	protected int getRankSize(int rank) {
		return (int) (2 + rank * 0.2f);
	}

}
