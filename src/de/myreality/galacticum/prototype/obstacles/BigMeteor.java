package de.myreality.galacticum.prototype.obstacles;

import org.newdawn.slick.GameContainer;

import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.WorldState;

public class BigMeteor extends Obstacle {

	public BigMeteor(int x, int y, WorldState game) {
		super(x, y, 60, 60, game);
		setSpeed(0.03f);
		setSprite(ResourceManager.getInstance().getImage("SPRITE_METEOR_SMALL"));
		setSprite(ResourceManager.getInstance().getImage("SPRITE_METEOR_BIG"));
	}
	
	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		if (getY() > gc.getHeight()) {
			removeQuery();
		}
		
		setRotation(getRotation() + 0.03f * delta);
		
	}

}
