package de.myreality.galacticum.prototype;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.imageout.ImageOut;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.prototype.items.Item;

public abstract class WorldState extends BasicGameState {
	
	private int id;
	private Image target;
	protected GameContainer gc;
	protected Image background;
	protected CopyOnWriteArrayList<GameObject> renderObjects;
	private CopyOnWriteArrayList<GameObject> guiObjects;
	private boolean allowGUI;
	Animations animations;
	private GameTracker gameTracker;
	//protected Music music;
	
	// System for the ships power
	ParticleSystem powerSystem;
	
	public WorldState(int id) {
		this.id = id;
		allowGUI = true;
		gameTracker = new GameTracker();
		renderObjects = new CopyOnWriteArrayList<GameObject>();
		guiObjects = new CopyOnWriteArrayList<GameObject>();
		animations = new Animations(this);
		try {
			powerSystem = ParticleIO.loadConfiguredSystem("prototype-res/particles/default.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getWorldWidth() {
		return gc.getWidth();
	}
	
	public int getWorldHeight() {
		return gc.getHeight();
	}
	
	public boolean isGUIAllowed() {
		return allowGUI;
	}
	
	public void setAllowGUI(boolean value) {
		allowGUI = value;
	}
	
	public Animations getAnimations() {
		return animations;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		gc = arg0;				
	}
	
	
	
	
	
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);
		gameTracker = new GameTracker();
	}

	public GameContainer getContainer() {
		return gc;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setAntiAlias(true);

		for (GameObject object : renderObjects) {
			object.draw(g);
		}
		
		animations.render(g);
		
		if (isGUIAllowed()) {
			for (GameObject object : guiObjects) {
				object.draw(g);
			}
		}
	}
	
	
	public void addRenderObject(GameObject object) {
		if (object instanceof Item && this instanceof BackgroundBattle) {
			return;
		}
		
		renderObjects.add(object);
	}
	
	public void removeRenderObject(GameObject object) {
		renderObjects.remove(object);
	}
	
	public void addGUIObject(GameObject object) {
		guiObjects.add(object);
	}
	
	public void removeGUIObject(GameObject object) {
		guiObjects.remove(object);
	}


	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		animations.update(gc, delta);

		if (gc.getInput().isKeyPressed(Input.KEY_F12)) {
			takeScreenShot(gc, ".jpg");
		}
		
		
		for (GameObject renderObject : renderObjects) {
			onObjectUpdate(renderObject, gc, delta);
			renderObject.update(gc, delta);
			
			if (renderObject.isRemoveQuery()) {
				if (!renderObject.isOnRemoved()) {
					renderObject.onRemove(gc);
				}
				   
				renderObjects.remove(renderObject);	
				continue;
			}
			   
			for (GameObject other : renderObjects) {
				onCollisionCheck(renderObject, other);
				if (renderObject.collidateWith(other)) {
					renderObject.onCollidateWith(other);
				}
			}
		}
		
		if (isGUIAllowed()) {
			for (GameObject guiObject : guiObjects) {
				guiObject.update(gc, delta);
				   
			    if (guiObject.isRemoveQuery()) {
				   if (!guiObject.isOnRemoved() && !(this instanceof BackgroundBattle)) {
					   guiObject.onRemove(gc);
				   }
				   guiObject.detach();
				   guiObject.removeAllChildren();
				   guiObjects.remove(guiObject);
				   continue;
			   }
			}		
		}	
	}

	@Override
	public int getID() {
		return id;
	}
	
	
	
	public void setBackground(Image image) {
		this.background = image;
	}
	

	/** Takes a screenshot, fileExt can be .png, .gif, .tga, .jpg or .bmp */
	   public void takeScreenShot(GameContainer container, String fileExt) {

	      try {
	         File FileSSDir = new File("screenshots");
	         if (!FileSSDir.exists()) {
	            FileSSDir.mkdirs();
	         }

	         int number = 0;
	         String screenShotFileName = "screenshots/" + "screenshot_" + number + fileExt;
	         File screenShot = new File(screenShotFileName);

	         while (screenShot.exists()) {
	            number++;
	            screenShotFileName = "screenshots/" + "screenshot_" + number + fileExt;
	            screenShot = new File(screenShotFileName);
	         }

	         target = new Image(container.getWidth(), container.getHeight());

	         Graphics g = container.getGraphics();
	         g.copyArea(target, 0, 0);
	         ImageOut.write(target, screenShotFileName);

	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	
	   
	   public void reset(GameContainer gc) {
		   gameTracker = new GameTracker();
		   renderObjects.clear();
		   guiObjects.clear();
		   animations.clear();
	   }
	   
	   public GameTracker getGameTracker() {
			return gameTracker;
	   }
	   
	   protected Image getGraphicsAsImage(GameContainer gc) {
			try {
				Image image = new Image(gc.getWidth(), gc.getHeight());
				Graphics graphics = gc.getGraphics();
				graphics.copyArea(image, 0, 0);
				return image;
			} catch (SlickException e) {
				e.printStackTrace();
			}
			return null;
		}
	   
	   public abstract void onCollisionCheck(GameObject object1, GameObject object2);
	   
	   
	   public abstract void onObjectUpdate(GameObject target, GameContainer gc, int delta);

}
