package de.myreality.galacticum.prototype.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.myreality.galacticum.prototype.GUIObject;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.ships.Spaceship;
import de.myreality.galacticum.prototype.weapons.Weapon;

public class WeaponSet extends GUIObject {
	
	private Spaceship playerShip;
	
	private int slots;
	
	private int border;
	
	private Font rankFont;
	
	private Color backgroundColor, enableColor, disableColor;
	
	private Image glassImage;
	
	public WeaponSet(int x, int y, int height, int slots, Spaceship ship, WorldState game) {
		super(x, y, slots * height, height, game);
		playerShip = ship;
		this.slots = slots;
		border = 8;
		rankFont = ResourceManager.getInstance().getFont("FONT_SMALL");
		backgroundColor = new Color(0, 0, 0, 200);
		enableColor = new Color(120, 255, 0, 60);
		disableColor = new Color(255, 30, 0, 60);
		glassImage = ResourceManager.getInstance().getImage("IMG_GLASS2");
		
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < slots; ++i) {
			g.setColor(new Color(0, 0, 0, 80));
			g.fillRect(getX() + getHeight() * i, getY(), getHeight(), getHeight());
			if (playerShip.getWeapon(i) != null) {
				Weapon weapon =  playerShip.getWeapon(i);
				Image weaponIcon = weapon.getIcon().copy();
				weaponIcon.setRotation(0.0f);
		
				// LEFT
				float left = getX() + getHeight() * i + border;
				float top = getY() + border;
				float width = getHeight() - border * 2;
				float height = getHeight() - border * 2;
				
				g.setColor(backgroundColor);
				g.fillRect(left - 2, top - 2, width + 4, height + 4);
				
				if (weapon.couldShoot()) {
					glassImage.draw(left, top, width, height, enableColor);
					weaponIcon.draw(left, top, width, height);						
				} else {
					weaponIcon.draw(left, top, width, height, disableColor);
					g.setColor(disableColor);
					glassImage.draw(left, top + height * (1.0f - weapon.getLoadPercent()), width, height * weapon.getLoadPercent(), disableColor);
					
				}				
				rankFont.drawString(left + 3, top + 3, String.valueOf(weapon.getRank()));
			}
		}
	}
	
}
