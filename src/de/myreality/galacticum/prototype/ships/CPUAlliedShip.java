package de.myreality.galacticum.prototype.ships;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import de.myreality.galacticum.prototype.WorldState;

public class CPUAlliedShip extends AlliedShip {

	public CPUAlliedShip(int x, int y, WorldState game) throws SlickException {
		super(x, y, game);
		GameContainer gc = game.getContainer();
		if (gc != null) {
			moveTo((int)(Math.random() * game.getWorldWidth()), (int)-getHeight());
		}
		setSpeed(0.08f);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		if (hasTarget()) {
			rotateTo(getTarget().getCenterX(), getTarget().getCenterY());
			shoot();
		}
	}
	
	

}
