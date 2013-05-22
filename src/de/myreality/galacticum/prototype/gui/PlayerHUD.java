package de.myreality.galacticum.prototype.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import de.myreality.galacticum.prototype.GUIObject;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.ships.AlliedShip;

public class PlayerHUD extends GUIObject {
	
	private AlliedShip playerShip;
	
	private ValueBar lifeBar, expBar;
	
	private WeaponSet weaponSet;
	
	public static final int HEIGHT = 50;
	
	//private Font mediumFont;

	public PlayerHUD(int x, int y, AlliedShip playerShip, WorldState game) {
		super(x, y, 0, 0, game);
		this.playerShip = playerShip;
		this.game = game;
		
		lifeBar = new ValueBar(x + 50, y, 230, 30, game);
		lifeBar.setOpacity(0.65f);
		lifeBar.setColor(new Color(140, 255, 0));
		lifeBar.setMaxValue(100);
		lifeBar.setValue(50);
		lifeBar.setBorder(2);
		expBar = new ValueBar(x + 50, y + 30 - lifeBar.getBorder(), 230, (int) (HEIGHT - lifeBar.getHeight()), game);
		expBar.setColor(new Color(90, 0, 255));
		expBar.setMaxValue(100);
		expBar.setValue(50);
		expBar.setBorder(2);
		expBar.setOpacity(0.65f);
		
		weaponSet = new WeaponSet((int)(getX() + 230), (int)getY(), HEIGHT, 5, playerShip, game);
		weaponSet.setX(game.getWorldWidth() - weaponSet.getWidth() - 10);
		new ShieldSet((int)getX(), (int)getY(), playerShip, game);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		lifeBar.setMaxValue(playerShip.getMaxLife());
		lifeBar.setValue(playerShip.getCurrentLife());
		expBar.setValue(playerShip.getExperience());
		expBar.setMaxValue(playerShip.getNextExperience());		
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.black);
		g.fillRect(getX(), getY(), 50, 50);
		Font font = ResourceManager.getInstance().getFont("FONT_MEDIUM");
		String rank = String.valueOf(playerShip.getRank());
		font.drawString(getX() + (50 / 2 - font.getWidth(rank) / 2), getY() + (50 / 2 - font.getLineHeight() / 2), rank);
	}
	
	
	

}
