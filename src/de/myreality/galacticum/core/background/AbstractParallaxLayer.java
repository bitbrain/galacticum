package de.myreality.galacticum.core.background;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import de.myreality.dev.chronos.util.Vector2f;
import de.myreality.galacticum.core.Boundable;
import de.myreality.galacticum.util.HashProvider;
import de.myreality.galacticum.util.Renderer;

/**
 * Abstract implementation of a parallax layer
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public abstract class AbstractParallaxLayer implements ParallaxLayer {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// The target tile
	private Image image;

	// The current distance
	private float distance;

	// The current velocity
	private Vector2f velocity;

	// The target mapper
	private Boundable boundable;

	// The target hash provider which provides an specific hash
	private HashProvider hashProvider;
	
	// Determines if the layer is preprocessed
	// by a renderer
	private boolean rendered;

	// Sprite width
	private int tileWidth;

	// Sprite height
	private int tileHeight;

	// Local X
	private int localX;

	// Local Y
	private int localY;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * Constructor to create a new layer
	 */
	public AbstractParallaxLayer(Boundable boundable, HashProvider hashProvider, int tileWidth,
			int tileHeight, float distance) {
		Renderer.getInstance().addTarget(this);
		this.hashProvider = hashProvider;
		this.boundable = boundable;
		this.velocity = new Vector2f(0, 0);
		this.distance = distance;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	
	
	
	@Override
	public Boundable getBoundable() {
		return boundable;
	}
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.background.ParallaxLayer#getHashProvider()
	 */
	@Override
	public HashProvider getHashProvider() {
		return hashProvider;
	}

	@Override
	public void beforeRender() {

	}

	/**
	 * @return the spriteWidth
	 */
	@Override
	public int getTileWidth() {
		return tileWidth;
	}
	
	@Override
	public Color getFilter() {
		return Color.white;
	}

	@Override
	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
		Renderer renderer = Renderer.getInstance();
		if (renderer.contains(this)) {
			renderer.removeTarget(this);
			renderer.addTarget(this);
		}

	}

	@Override
	public int getTileHeight() {
		return tileHeight;
	}

	@Override
	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
		Renderer renderer = Renderer.getInstance();
		if (renderer.contains(this)) {
			renderer.removeTarget(this);
			renderer.addTarget(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.rendering.RenderTarget#computeRendering
	 * (org.newdawn.slick.GameContainer, org.newdawn.slick.Graphics)
	 */
	@Override
	public void computeRendering(GameContainer gc, Graphics g) {
		try {
			this.rendered = false;
			image = new Image(tileWidth, tileHeight);
			Graphics imageGraphics = image.getGraphics();
			imageGraphics.clear();
			computeSprite(imageGraphics);
			imageGraphics.flush();
		} catch (SlickException e) {
			Log.error(e);
		}
	}

	@Override
	public void afterRender() {
		rendered = true;
	}

	@Override
	public boolean hasRendered() {
		return rendered;
	}

	@Override
	public float getDistance() {
		return distance;
	}

	@Override
	public void setDistance(float distance) {
		this.distance = distance;
	}

	@Override
	public Vector2f getVelocity() {
		return velocity;
	}

	@Override
	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}

	@Override
	public void setVelocity(float velocityX, float velocityY) {
		velocity.x = velocityX;
		velocity.y = velocityY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ParallaxLayer other) {
		if (distance > other.getDistance()) {
			return -1;
		} else if (distance < other.getDistance()) {
			return 1;
		} else {
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.util.Updatable#update(org.newdawn.slick
	 * .GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		localX -= getVelocity().x * delta;
		localY -= getVelocity().y * delta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.util.Renderable#render(org.newdawn.slick
	 * .GameContainer, org.newdawn.slick.state.StateBasedGame,
	 * org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		if (image != null) {
			int boundableX = 0;
			int boundableY = 0;
			
			if (getBoundable() != null) {
				boundableX = (int) Math.ceil(getBoundable().getLeft());
				boundableY = (int) Math.ceil(getBoundable().getTop());
			}

			for (int x = getStartX() + boundableX; x < gc.getWidth()
					+ getTileWidth() + boundableX; x += getTileWidth()) {
				for (int y = getStartY() + boundableY; y < gc.getHeight()
						+ getTileHeight() + boundableY; y += getTileHeight()) {
					image.draw(x + getXClip(), y + getYClip(), getTileWidth(),
							getTileHeight(), getFilter());
				}
			}

			g.clearClip();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * Is called by the child class
	 */
	protected abstract void computeSprite(Graphics g);

	private float getTargetX() {
		if (getBoundable() != null) {
			return (float) (Math.floor(-getBoundable().getLeft()
					+ localX) / getDistance());
		} else {
			return localX;
		}
	}

	private float getTargetY() {
		if (getBoundable() != null) {
			return (float) (Math
					.floor(-getBoundable().getTop() + localY) / getDistance());
		} else {
			return localY;
		}
	}

	private int getStartX() {
		int startX = 0;
		if (getTargetX() > 0) {
			startX = -getTileWidth();
		}
		return startX;
	}

	private int getStartY() {
		int startY = 0;
		if (getTargetY() > 0) {
			startY = -getTileHeight();
		}
		return startY;
	}

	private int getXClip() {
		return (int) (getTargetX() % getTileWidth());
	}

	private int getYClip() {
		return (int) (getTargetY() % getTileHeight());
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
