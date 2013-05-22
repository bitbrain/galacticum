package de.myreality.galacticum.prototype;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This file was written by Miguel Gonzalez and is part of the
 * game "Galacticum". For more information mailto info@my-reality.de
 * or visit the game page: http://dev.my-reality.de/littlewars
 * 
 * Main class containing the main function as well as the GameContainer
 * 
 * @version 	1.0
 * @author 		Miguel Gonzalez		
 */


/**
 * Main game class
 * 
 * @version 1.0
 * @author Miguel Gonzalez
 *
 */
public class Game extends StateBasedGame {
	
	public static final int MENU_STATE = 0;
	public static final int INGAME_STATE = 1;
	public static final int STATISTIC_STATE = 2;
	
	public static final int TIME_DELTA = 10;
	
	public Game() {
		super("Galacticum Prototype");		
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new MenuState(Game.MENU_STATE));
		this.addState(new IngameState(Game.INGAME_STATE));
	}
	
	/**
	 * Main method of the game
	 * 
	 * @param args application arguments
	 * @throws SlickException
	 * @throws IOException
	 * @throws LWJGLException
	 */
	public static void main(String[] args) throws SlickException, IOException, LWJGLException {		
		
		AppGameContainer container = new AppGameContainer(new Game());	
		container.setMinimumLogicUpdateInterval(TIME_DELTA);
		container.setMaximumLogicUpdateInterval(TIME_DELTA);
		//container.setSmoothDeltas(true);
		container.setClearEachFrame(false);
		container.setDisplayMode(800, 600, false);
		container.setVSync(true);
		container.setShowFPS(false);
		container.setMinimumLogicUpdateInterval(10);		
		container.start();		
	}

}
