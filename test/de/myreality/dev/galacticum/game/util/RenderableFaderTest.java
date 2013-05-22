/*package de.myreality.galacticum.util;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.GameTest;
import de.myreality.galacticum.core.Camera;
import de.myreality.galacticum.core.TrackingCamera;
import de.myreality.galacticum.core.background.HashParallaxMapper;

*//**
 * 
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 *//*
public class RenderableFaderTest extends GameTest {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	BackgroundFader renderableFader;

	HashParallaxMapper mapperA, mapperB, mapperC;

	Camera camera;

	// ===========================================================
	// Constructors
	// ===========================================================

	*//**
	 * @param title
	 *//*
	public RenderableFaderTest() {
		super(BackgroundFader.class);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Game#render(org.newdawn.slick.GameContainer,
	 * org.newdawn.slick.Graphics)
	 
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		if (!Renderer.getInstance().isDone()) {
			Renderer.getInstance().render(gc, null, g);
		} else {
			
			renderableFader.render(gc, null, g);
		}
	}

	
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.BasicGame#init(org.newdawn.slick.GameContainer)
	 
	@Override
	public void init(GameContainer gc) throws SlickException {
		camera = new TrackingCamera(gc);
		mapperA = new HashParallaxMapper(camera);
		mapperB = new HashParallaxMapper(camera);
		mapperC = new HashParallaxMapper(camera);
		renderableFader = new BackgroundFader(mapperA, mapperB);
		renderableFader.setInterval(2000);
		
		
	}

	
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.BasicGame#update(org.newdawn.slick.GameContainer,
	 * int)
	 
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		renderableFader.update(gc, null, delta);
		if (gc.getInput().isKeyPressed(Input.KEY_S)) {
			// renderableFader.switchBack();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	Background background = new Background() {

		@Override
		public void update(GameContainer gc, StateBasedGame sbg, int delta) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
				throws SlickException {
			g.setColor(Color.green);
			g.fillRect(0, 0, gc.getWidth(), gc.getHeight());			
		}

		@Override
		public Color getFilter() {
			// TODO Auto-generated method stub
			return null;
		}
		
	};

	public static void main(String[] args) throws SlickException {
		AppGameContainer game = new AppGameContainer(new RenderableFaderTest());
		game.setDisplayMode(1280, 720, false);
		game.start();
	}

}
*/