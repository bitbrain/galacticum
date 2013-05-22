package de.myreality.galacticum.prototype.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import de.myreality.galacticum.prototype.GUIObject;
import de.myreality.galacticum.prototype.WorldState;

public class GameText extends GUIObject {
	
	private String text;
	private Font font;
	private Color fontColor;

	public GameText(int x, int y, String text, Font font, WorldState game) {
		super(x, y, 0, 0, game);
		this.text = text;
		this.font = font;
		fontColor = new Color(255, 255, 255);
		calculateSize();
	}
	
	
	
	@Override
	public void setOpacity(float opacity) {
		this.opacity = opacity;
		fontColor.a = opacity;
	}



	public void alignToCenterX() {
		setX(game.getWorldWidth() / 2 - getWidth() / 2);
	}
	
	public void alignToCenterY() {
		setY(game.getWorldHeight() / 2 - getHeight() / 2);
	}
	
	public void setFontColor(Color color) {
		fontColor = new Color(color.r, color.g, color.b);
	}
	
	public void setText(String text) {
		this.text = text;
		calculateSize();
	}
	
	public void setFont(Font font) {
		this.font = font;
		calculateSize();
	}
	
	private void calculateSize() {
		if (font != null) {
			setWidth(font.getWidth(text));
			setHeight(font.getLineHeight());
		}
	}
	
	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		font.drawString(getX(), getY(), text, fontColor);
	}
	
	
}
