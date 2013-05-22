package de.myreality.galacticum.prototype;

import org.newdawn.slick.GameContainer;

import de.myreality.galacticum.prototype.ships.AlliedShip;

public class MovableSpriteObject extends GameObject {
	
	private Vector velocity;
	private float speed;
	private float lastX, lastY, dX, dY;

	public MovableSpriteObject(int x, int y, int width, int height,
			WorldState game) {
		super(x, y, width, height, game);
		velocity = new Vector(0, 0);
		speed = 0;
		if (game != null) {
			game.addRenderObject(this);
		}
		lastX = getX();
		lastY = getY();
		dX = 0; dY = 0;
	}
	
	public void moveTo(Point point) {
		moveTo(point.x, point.y);
	}
	
	public void moveTo(int x, int y) {
		// Move to the given position with a direction vector
		Point point1 = new Point(getCenterX(), getCenterY());
		Point point2 = new Point(x, y);
		Vector direction = new Vector(point1, point2);
		move(direction);
		
	}
	
	
	public float getDeltaX() {
		return dX;
	}
	
	public float getDeltaY() {
		return dY;
	}

	@Override
	public void update(GameContainer gc, int delta) {
		dX = getX() - lastX;
		dY = getY() - lastY;
		lastX = getX();
		lastY = getY();		
		
		super.update(gc, delta);
		
		bounds.setX(bounds.getX() + velocity.x * speed * delta);
		bounds.setY(bounds.getY() + velocity.y * speed * delta);
		
		if (getY() > game.getWorldHeight()) {
			removeQuery();
		}
		
		if (this instanceof AlliedShip) {
			if (getY() + getHeight() < 0) {
				removeQuery();
			}
		}
	}
	
	public void setSpeed(float f) {
		this.speed = f;
		
		if (speed < 0) {
			speed = 0;
		}
	}
	
	public void stop() {
		this.speed = 0;
		
	}
	
	public Vector getVelocity() {
		if (velocity.length() == 0) {
			return new Vector(getDeltaX(), getDeltaY());
		}
		
		return velocity;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void move(Vector veloVector) {
		if (veloVector.length() == 0) {
			return;
		}
		velocity = veloVector;
		velocity.normalize();
	}
	
	public void move(int direction, int delta) {
		switch (direction) {
			case UP:
				bounds.setY(bounds.getY() - speed * delta);
				break;
			case DOWN:
				bounds.setY(bounds.getY() + speed * delta);
				break;
			case LEFT:
				bounds.setX(bounds.getX() - speed * delta);
				break;
			case RIGHT:
				bounds.setX(bounds.getX() + speed * delta);
				break;
		}
	}
	
	
	public float getLastX() {
		return lastX;
	}
	
	public float getLastY() {
		return lastY;
	}

}
