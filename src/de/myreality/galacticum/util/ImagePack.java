package de.myreality.galacticum.util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ImagePack {

	public static ExtendedImage TEST;
	public static ExtendedImage SPACE;
	public static ExtendedImage GRADIENT;

	public static void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		TEST = new ExtendedImage("res/images/test.png");
		SPACE = new ExtendedImage("res/images/space-far.png");
		GRADIENT =  new ExtendedImage("res/images/black-gradient.png");
	}
}
