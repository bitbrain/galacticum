package de.myreality.galacticum.prototype;

import org.newdawn.slick.GameContainer;

public class SpriteObject extends GameObject {

	public SpriteObject(int x, int y, int width, int height, WorldState game) {
		super(x, y, width, height, game);
		game.addRenderObject(this);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub

	}

}
