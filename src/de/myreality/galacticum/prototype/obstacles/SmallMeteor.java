package de.myreality.galacticum.prototype.obstacles;

import org.newdawn.slick.GameContainer;

import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.WorldState;

public class SmallMeteor extends Obstacle {

	public SmallMeteor(int x, int y, WorldState game) {
		super(x, y, 30, 30, game);
		setSpeed(0.05f);
		setSprite(ResourceManager.getInstance().getImage("SPRITE_METEOR_SMALL"));
	}
	
	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		if (getY() > gc.getHeight()) {
			removeQuery();
		}
		
		setRotation(getRotation() + 0.05f * delta);
		
	}

}
