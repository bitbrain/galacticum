package de.myreality.galacticum.prototype.items;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.myreality.galacticum.prototype.GameObject;
import de.myreality.galacticum.prototype.MovableSpriteObject;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.ships.AlliedShip;
import de.myreality.galacticum.prototype.ships.Spaceship;


public class Item extends MovableSpriteObject {	
	
	private int rank;
	
	public static final int ICON_SIZE = 30;
	private boolean used;
	
	public Item(int x, int y, WorldState game) {
		super(x, y, ICON_SIZE, ICON_SIZE, game);
		used = false;
		setSpeed((float) (0.1f + Math.random() * 0.03f));	
		rank = 1;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public void upgrade() {
		rank++;
	}
	
	public int getRank() {
		return rank;
	}
	
	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		move(DOWN, delta);
		if (getY() > gc.getHeight()) {
			removeQuery();
		}
		
		if (!isUsed()) {
			setRotation(getRotation() + 0.1f * delta);
		}
	}




	public boolean isUsed() {
		return used;
	}
	
	public void setItemUse(boolean use) {
		used = use;	
		
		if (used) {
			removeQuery();
		}
	}
	
	public void setIcon(Image icon) {
		setSprite(icon);		
	}

	public void alignToShip(Spaceship owner) {
		if (!used) {
			setItemUse(true);
			//ResourceManager.getInstance().getSound("SOUND_PLOP").playAt(1.0f, 1.0f, getX(), getY(), 0.0f);
		}
	}
	
	
	
	@Override
	public void draw(Graphics g) {
		if (!isUsed()) {
			super.draw(g);
		}
	}

	public Image getIcon() {
		return sprite;
	}
	
	public int getIconWidth() {
		if (sprite != null) {
			return sprite.getWidth();
		} else {
			return 0;
		}
	}
	
	public int getIconHeight() {
		if (sprite != null) {
			return sprite.getWidth();
		} else {
			return 0;
		}
	}

	@Override
	public void onCollidateWith(GameObject other) {
		super.onCollidateWith(other);
		if (other instanceof AlliedShip) {
			AlliedShip ship = (AlliedShip)other;
			alignToShip(ship);
			removeQuery();
		}
	}
	
	
}
