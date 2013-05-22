package de.myreality.galacticum.prototype;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.prototype.ships.AlliedShip;
import de.myreality.galacticum.prototype.ships.AnnihilatorShip;
import de.myreality.galacticum.prototype.ships.BigDestroyerShip;
import de.myreality.galacticum.prototype.ships.CPUAlliedShip;
import de.myreality.galacticum.prototype.ships.DestroyerShip;
import de.myreality.galacticum.prototype.ships.Spaceship;

public class BackgroundBattle extends WorldState {
	
	private int strength;
	private Timer timer;
	private Image renderImage;
	private float scale;
	
	public BackgroundBattle(GameContainer gc, int strength, float scale) {
		super(-1);
		this.scale = scale;
		this.strength = strength;
		timer = new Timer();
		timer.start();
		try {
			renderImage = new Image((int)(gc.getWidth() * (1.0f / scale)), (int)(gc.getHeight() * (1.0f / scale)));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	
	

	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		super.init(arg0, arg1);
		this.gc = arg0;
	}





	public void generateUnits(GameContainer gc) {		
		if (timer.getMiliseconds() > (2000 / strength) && gc != null) {
			timer.reset();
			this.gc = gc;
			try {				
				new DestroyerShip((int)(Math.random() * renderImage.getWidth()), -30, this);
				
				if (Spaceship.isProbably(40)) {
					new CPUAlliedShip((int)(Math.random() * renderImage.getWidth()), renderImage.getHeight(), this);
				}
				
				if (Spaceship.isProbably(2)) {
					new BigDestroyerShip((int)(Math.random() * renderImage.getWidth()), -60, this);
				}
				
				if (Spaceship.isProbably(5)) {
					new AnnihilatorShip((int)(Math.random() * renderImage.getWidth()), -60, this);
				}
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
	

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		this.background = ResourceManager.getInstance().getImage("BG_MOTHERSHIP");
		if (this.background != null) {
			renderImage.getGraphics().clear();
			g.drawImage(this.background, 0, 0);	
		} 				
		super.render(gc, sbg, renderImage.getGraphics());
		Image scaled = renderImage.getScaledCopy(scale);
		g.drawImage(scaled, 0, 0, new Color(50, 50, 50, 200));
	}
	
	
	
	


	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {		
		super.update(gc, sbg, delta);
		timer.update(delta);
		generateUnits(gc);
	}


	@Override
	public void onCollisionCheck(GameObject object1, GameObject object2) {
		if (object1 instanceof Spaceship && !(object1 instanceof AlliedShip) && object2 instanceof AlliedShip) {
			Spaceship attacker = (Spaceship)object1;
			if (!attacker.hasTarget()) {
				attacker.setTarget((Spaceship)object2);
			} else {
				if (attacker.getTarget().isRemoveQuery() || attacker.getTarget().isHalfInvisible()) {
					attacker.setTarget(null);
				}
			}
		} else if (object1 instanceof AlliedShip && !(object2 instanceof AlliedShip) && object2 instanceof Spaceship) {
			Spaceship attacker = (Spaceship)object1;
			if (!attacker.hasTarget()) {
				attacker.setTarget((Spaceship)object2);
			} else {
				if (attacker.getTarget().isRemoveQuery() || attacker.getTarget().isHalfInvisible()) {
					attacker.setTarget(null);
				}
			}
		}
		
		
	}


	@Override
	public void onObjectUpdate(GameObject target, GameContainer gc, int delta) {
		 if (target instanceof MovableSpriteObject) {
				MovableSpriteObject movable = (MovableSpriteObject)target;
				movable.setSpeed(0.006f * delta);
			}
	}





	@Override
	public int getWorldWidth() {
		return renderImage.getWidth();
	}





	@Override
	public int getWorldHeight() {
		return renderImage.getHeight();
	}
	
	
	
	

}
