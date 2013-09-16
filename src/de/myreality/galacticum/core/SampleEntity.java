package de.myreality.galacticum.core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.util.ImagePack;

public class SampleEntity extends Entity {

	public SampleEntity() {
		setSprite(ImagePack.TEST);
	}

	public SampleEntity(float x, float y) {
		setSprite(ImagePack.TEST);
		setBounds(x, x + 50, y, y + 50);
	}

	@Override
	public boolean isIlluminable() {
		return true;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {

	}
}