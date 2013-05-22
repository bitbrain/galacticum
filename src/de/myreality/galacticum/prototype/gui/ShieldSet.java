package de.myreality.galacticum.prototype.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.myreality.galacticum.prototype.GUIObject;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.Shield;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.ships.Spaceship;

public class ShieldSet extends GUIObject {

	private Spaceship target;
	
	private int border;
	
	private Shield activeShield;
	
	public ShieldSet(int x, int y, Spaceship target, WorldState game) {
		super(x, y, 0, 0, game);
		this.target = target;
		this.border = 2;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		Font font = ResourceManager.getInstance().getFont("FONT_SMALL");
		int startX = (int) getX();
		activeShield = target.getActiveShield();
		for (Shield shield : target.getAllShields()) {
			if (shield.equals(activeShield)) {
				g.setColor(Color.cyan);
				g.setLineWidth(3);
				g.drawRect(startX, getY() - border - 40, 20, 20);
			} 
			g.setColor(shield.getMaterial().getColor());
			g.fillRect(startX, getY() - border - 40, 20, 20);
			Image shaderImage = ResourceManager.getInstance().getImage("IMG_GLASS");
			shaderImage.draw(startX, getY() - border - 40, 20, 20);
			font.drawString(startX + 3, getY() - border - 37, String.valueOf(shield.getRank()));
			startX += 20 + border;
		}
		
		// Draw the active shield
		if (activeShield != null) {
			activeShield.drawGUI((int)getX(), (int)getY() - 20, 280, 15, g);
		}
	}
}
