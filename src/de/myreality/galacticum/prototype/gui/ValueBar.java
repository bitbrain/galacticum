package de.myreality.galacticum.prototype.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.myreality.galacticum.prototype.GUIObject;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.WorldState;

public class ValueBar extends GUIObject {
	
	private int border;
	
	private Color borderColor;
	
	private Image glassImage;
	
	// Value
	private int value, maxValue;
	
	private GameText description;

	public ValueBar(int x, int y, int width, int height, WorldState game) {
		super(x, y, width, height, game);
		value = 0; 
		maxValue = 0;
		borderColor = new Color(0, 0, 0, 1f);
		glassImage = ResourceManager.getInstance().getImage("IMG_GLASS");
		description = new GameText((int)getX(), (int)getY(), "0/0", ResourceManager.getInstance().getFont("FONT_SMALL"), game);
		description.setX(getX() + getWidth() / 2 - description.getWidth() / 2);
		description.setY(getY() + getHeight() / 2 - description.getHeight() / 2);
	}
	
	public void setBorder(int border) {
		this.border = border;
	}
	
	public int getBorder() {
		return border;
	}
	
	
	
	@Override
	public void removeQuery() {
		super.removeQuery();
		description.removeQuery();
	}

	@Override
	public void setOpacity(float opacity) {
		super.setOpacity(opacity);
		borderColor.a = opacity;
	}

	public void setValue(int value) {
		this.value = value;
		if (this.value > maxValue) {
			this.value = maxValue;
		}
	}
	
	public void setMaxValue(int value) {
		this.maxValue = value;
		if (this.value > maxValue) {
			this.value = maxValue;
		}
	}
	
	public float getPercent() {
		return (float)value / (float)maxValue;
	}
	
	
	

	@Override
	public void draw(Graphics g) {
		g.setColor(borderColor);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(color);
		g.fillRect(getX() + border, getY() + border, (getWidth() - border * 2) * getPercent(), getHeight() - border * 2);
		glassImage.draw(getX() + border, getY() + border, (getWidth() - border * 2) * getPercent(), getHeight() - border * 2);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		description.setText(value + "/" + maxValue);
		description.setX(getX() + getWidth() / 2 - description.getWidth() / 2);
		description.setY(getY() + getHeight() / 2 - description.getHeight() / 2);
		description.setOpacity(getOpacity());
	}
	
	public void alignToCenterX() {
		setX(game.getWorldWidth() / 2 - getWidth() / 2);
	}
	
	public void alignToCenterY() {
		setY(game.getWorldHeight() / 2 - getHeight() / 2);
	}

}
