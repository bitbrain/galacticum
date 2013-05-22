package de.myreality.galacticum.prototype.items;

import de.myreality.galacticum.prototype.Animations;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.ships.Spaceship;

public class HealPower extends Item {
	
	private int power;

	public HealPower(int x, int y, int power, WorldState game) {
		super(x, y, game);
		this.power = power;
		setIcon(ResourceManager.getInstance().getImage("ICON_ITEM_HEAL"));
		game.getAnimations().addAnimation(this, Animations.GREEN_FLARE, true);
	}

	@Override
	public void alignToShip(Spaceship owner) {
		
		if (!isUsed()) {
			owner.addLife((int)(power / 100.0f * owner.getMaxLife()));
		}
		
		super.alignToShip(owner);
	}
	
	

}
