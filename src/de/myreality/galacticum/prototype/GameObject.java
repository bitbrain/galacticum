package de.myreality.galacticum.prototype;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public abstract class GameObject extends FamilyObject<GameObject> {
	
	// Bounds of the object
	protected Shape bounds;
	
	// Image (sprite) of the object to display on the screen
	protected Image sprite;
	
	// Opacity of the game object
	protected float opacity;
	
	private EffectFunction function;
	
	// Specific color of the object (default: white)
	protected Color color, fadeColor;
	
	// Target game to display to
	protected WorldState game;
	private boolean remove;
	
	// Rotation of the object
	private float rotation;
	
	private boolean onRemoved;
	
	private Point rotationPoint;
	
	// Direction constants
	public final static int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	
	public GameObject(int x, int y, int width, int height, WorldState game) {
		bounds = new Rectangle(x, y, width, height);
		color = new Color(255, 255, 255, 0);		
		this.game = game;
		remove = false;
		rotation = 0.0f;
		rotationPoint = null;
		opacity = 1.0f;
		fadeColor = new Color(255, 255, 255);
		onRemoved = false;
	}
	
	public void setOpacity(float opacity) {
		this.opacity = opacity;
		color.a = opacity;
		fadeColor.a = opacity;
	}
	
	public float getOpacity() {
		return opacity;
	}
	
	public void setRotationPoint(Point p) {
		rotationPoint = p;
	}
	
	public void unsetRotationPoint() {
		rotationPoint = null;
	}
	
	public boolean hasCustomRotationPoint() {
		return rotationPoint != null;
	}
	
	public void setRotation(float angle) {
		rotation = angle;
	}
	
	public void removeQuery() {
		remove = true;
	}
	
	public boolean isRemoveQuery() {
		return remove;
	}
	
	public void setSprite(Image image) {
		this.sprite = image;
		//bounds = new Rectangle(getX(), getY(), image.getWidth(), image.getHeight());
	}
	
	public Shape getBounds() {
		return bounds;
	}
	
	public float getX() {
		return bounds.getX();
	}
	
	public float getY() {
		return bounds.getY();
	}
	
	public void setY(float y) {
		bounds.setY(y);
	}
	
	public void setX(float x) {
		bounds.setX(x);
	}
	
	public float getWidth() {
		return bounds.getWidth();
	}
	
	public float getHeight() {
		return bounds.getHeight();
	}
	
	public void setColor(Color color) {
		this.color = new Color(color.r, color.g, color.b, opacity);
		
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean collidateWith(GameObject obj) {
		return bounds.intersects(obj.getBounds()) && !equals(obj);
	}
	
	public void update(GameContainer gc, int delta) {
		if (function != null) {
			function.doEffect(this);
		}
	}
	
	protected void rotate(Graphics g) {
		if (hasParent()) {
			if (getParent() instanceof GameObject) {
				GameObject tempParent = (GameObject)getParent();
				if (hasCustomRotationPoint()) {
					g.rotate(rotationPoint.x, rotationPoint.y, rotation);
				} else {
					g.rotate(tempParent.getCenterX(), tempParent.getCenterY(), rotation);
				}
			} else {
				if (hasCustomRotationPoint()) {
					g.rotate(rotationPoint.x, rotationPoint.y, rotation);
				} else {
					g.rotate(getCenterX(), getCenterY(), rotation);
				}
			}
		} else {
			if (hasCustomRotationPoint()) {
				g.rotate(rotationPoint.x, rotationPoint.y, rotation);
			} else {
				g.rotate(getCenterX(), getCenterY(), rotation);
			}
		}
	}
	
	public float getCenterX() {
		return bounds.getCenterX();
	}
	
	public float getCenterY() {
		return bounds.getCenterY();
	}
	
	protected void rotateBack(Graphics g) {
		if (hasParent()) {
			if (getParent() instanceof GameObject) {
				GameObject tempParent = (GameObject)getParent();
				g.rotate(tempParent.getX(), tempParent.getY(), -rotation);
			} else {
				g.rotate(getCenterX(), getCenterY(), -rotation);
			}
		} else {
			g.rotate(getCenterX(), getCenterY(), -rotation);
		}
	}
	
	
	public void addRotation(float angle) {
		setRotation(getRotation() + angle);
	}
	
	public void draw(Graphics g) {
		
		if (sprite != null) {
			sprite.setRotation(rotation);
			sprite.draw(getX(), getY(), getWidth(), getHeight(), fadeColor);
		} else {
			g.setColor(color);
			rotate(g);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			rotateBack(g);
		}
	}
	
	
	public boolean isOnRemoved() {
		return onRemoved;
	}
	
	
	public void onRemove(GameContainer gc) {
		onRemoved = true;
	}
	
	public void setFadeColor(Color color) {
		fadeColor = new Color(color.r, color.g, color.b);
	}
	
	
	
	public boolean isHalfInvisible() {
		return getOpacity() < 0.6f;
	}
	
	public boolean isInvisible() {
		return getOpacity() == 0.0f;
	}
	
	
	
	public void setWidth(int width) {
		bounds = new Rectangle(getX(), getY(), width, getHeight());
	}
	
	public void setHeight(int height) {
		bounds = new Rectangle(getX(), getY(), getWidth(), height);
	}
	
	
	public void setEffectFunction(EffectFunction func) {
		function = func;
	}
	
	
	public boolean hasEffectFunction() {
		return function != null;
	}
	
	public EffectFunction getEffectFuntion() {
		return function;
	}
	
	public void onCollidateWith(GameObject other) {
		
	}
}
