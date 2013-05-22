package de.myreality.galacticum.prototype.items;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import de.myreality.galacticum.prototype.BackgroundBattle;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.Shot;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.gui.FlashText;
import de.myreality.galacticum.prototype.ships.Spaceship;

public class InvisiblePowerUp extends PowerUp {
	
	private final static float TARGET_OPACITY = 0.2f;
	private final static int TIME_BASE_VALUE = 9000;
	
	private Spaceship owner;

	public InvisiblePowerUp(int x, int y, WorldState game) {
		super(x, y, TIME_BASE_VALUE, game);
		setIcon(ResourceManager.getInstance().getImage("ICON_ITEM_TRANSMITTER"));
	}


	@Override
	public void setRank(int rank) {
		super.setRank(rank);
		setDuration(TIME_BASE_VALUE + (TIME_BASE_VALUE / 2 * rank));
	}
	
	@Override
	public void handleEffects(Spaceship ship, Shot shot) {
		
	}
	
	
	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		if (owner != null) {
			owner.setOpacity(TARGET_OPACITY);
		}
	}

	@Override
	public void alignToShip(Spaceship owner) {
		owner.setOpacity(TARGET_OPACITY);
		super.alignToShip(owner);
		this.owner = owner;
		if (!(game instanceof BackgroundBattle)) {
			FlashText text = new FlashText(owner, "Invisibility", game);
			text.setFontColor(Color.decode("0x7e00ff"));
		}
	}

	@Override
	public void onOver(Spaceship ship) {
		ship.setOpacity(1.0f);
	}

}
