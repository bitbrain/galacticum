package de.myreality.galacticum.prototype;

import org.newdawn.slick.GameContainer;

public class GUIObject extends GameObject {

	public GUIObject(int x, int y, int width, int height, WorldState game) {
		super(x, y, width, height, game);
		game.addGUIObject(this);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
	}

}
