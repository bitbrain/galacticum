package de.myreality.galacticum.prototype.ships;

import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

import de.myreality.galacticum.prototype.BackgroundBattle;
import de.myreality.galacticum.prototype.EffectFunction;
import de.myreality.galacticum.prototype.Timer;
import de.myreality.galacticum.prototype.WorldState;
import de.myreality.galacticum.prototype.gui.ValueBar;
import de.myreality.galacticum.prototype.items.HealPower;

public abstract class BossShip extends EnemyShip {
	
	private Map<Integer, EffectFunction> phases;
	
	private Timer timer;
	
	private ValueBar lifeBar;
	
	private int phaseInterval, phase;

	public BossShip(int x, int y, Image sprite, WorldState game) {
		super(x, y, sprite, game);
		lifeBar = new ValueBar(0, 10, 300, 30, game);
		lifeBar.setColor(new Color(140, 255, 0));
		lifeBar.alignToCenterX();
		phases = new TreeMap<Integer, EffectFunction>();
		timer = new Timer();
		timer.start();
		phaseInterval = 10000;
		phase = 0;
	}
	
	public void addPhase(EffectFunction function) {
		phases.put(phases.size(), function);
	}
		
	
	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		timer.update(delta);
		lifeBar.setValue(getCurrentLife());
		lifeBar.setMaxValue(getMaxLife());
		
		if (timer.getMiliseconds() > phaseInterval) {
			timer.reset();
			phase++;
			onNextPhase(phase);
			if (phase >= phases.size()) {
				phase = 0;
			}
		}
		
		if (!phases.isEmpty()) {
			phases.get(phase).doEffect(this);
		}		
	}
	
	
	public abstract void onNextPhase(int phase);


	@Override
	public void onRemove(GameContainer gc) {
		super.onRemove(gc);
		if (isProbably(50) && !(game instanceof BackgroundBattle)) {
			new HealPower((int)getCenterX(), (int)getCenterY(), 50, game);
		}	
		if (isProbably(25) && !(game instanceof BackgroundBattle)) {
			getRandomWeapon().getAsNewItem();
		}
		if (isProbably(25) && !(game instanceof BackgroundBattle)) {
			getRandomWeapon().getAsNewItem();
		}
		lifeBar.removeQuery();
	}
}
