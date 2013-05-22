package de.myreality.galacticum.prototype.gui;

import org.newdawn.slick.GameContainer;

import de.myreality.galacticum.prototype.BackgroundBattle;
import de.myreality.galacticum.prototype.GameObject;
import de.myreality.galacticum.prototype.MenuState;
import de.myreality.galacticum.prototype.ResourceManager;
import de.myreality.galacticum.prototype.WorldState;

public class FlashText extends GameText {

	public FlashText(GameObject target, String text, WorldState game) {
		super((int)target.getX(), (int) target.getY(), text, ResourceManager.getInstance().getFont("FONT_MEDIUM"), game);
		setX(target.getCenterX() - getWidth() / 2);
		setY(target.getCenterY() - getHeight() / 2);
		if (game instanceof MenuState || game instanceof BackgroundBattle) {
			removeQuery();
		}
	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		setOpacity(getOpacity() - 0.001f * delta);
		if (getOpacity() < 0.3f) {
			removeQuery();
		}
	}
	
	
	
	
	
	
	

}
