package de.myreality.galacticum.prototype;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.prototype.gui.GameText;
import de.myreality.galacticum.prototype.ships.AlliedShip;
import de.myreality.galacticum.prototype.ships.AnnihilatorShip;
import de.myreality.galacticum.prototype.ships.BigDestroyerShip;
import de.myreality.galacticum.prototype.ships.CPUAlliedShip;
import de.myreality.galacticum.prototype.ships.DestroyerShip;
import de.myreality.galacticum.prototype.ships.Spaceship;

public class MenuState extends WorldState {
	
	private Timer timer, fontTimer;
	
	private Image header;
	
	private Image battleGround;
	
	private final int INTERVAL = 400;

	public MenuState(int id) {
		super(id);
		timer = new Timer();
		timer.start();
		fontTimer = new Timer();
		fontTimer.start();
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		super.init(arg0, arg1);
		setBackground(ResourceManager.getInstance().getImage("BG_MAIN"));
		header = ResourceManager.getInstance().getImage("IMG_HEADER");
		battleGround = new Image(arg0.getWidth(), arg0.getHeight());
		Font mediumFont = ResourceManager.getInstance().getFont("FONT_MEDIUM");
		Font smallFont = ResourceManager.getInstance().getFont("FONT_SMALL");
		
		EffectFunction blinkFunction = new EffectFunction() {
			
			@Override
			public void doEffect(GameObject obj) {
				if (fontTimer.getMiliseconds() > 500) {
					
					float opacity= (1000 - fontTimer.getMiliseconds()) / 500.0f;
					obj.setOpacity(opacity);
				} else {
					obj.setOpacity(1.0f);
				}
				
				if (fontTimer.getMiliseconds() > 1000) {
					fontTimer.reset();
				}
			}
			
		};	
		
		
		GameText enterText = new GameText(0, 0, "Click LEFT to start", mediumFont, this);
		enterText.setFontColor( new Color(200, 200, 0));
		enterText.alignToCenterX();
		enterText.alignToCenterY();
		enterText.setEffectFunction(blinkFunction);
		
		GameText infoText = new GameText(0, 0, "Prototype - Programmed by Miguel Gonzalez", smallFont, this);
		infoText.setFontColor( new Color(80, 80, 80));
		infoText.alignToCenterX();
		infoText.setY(getWorldHeight() - infoText.getHeight() - 20);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		if (background != null) {
			battleGround.getGraphics().drawImage(background, 0, 0);			
		}
		super.render(gc, sbg, battleGround.getGraphics());		
		g.drawImage(battleGround, 0, 0, new Color(120, 120, 120, 100));
		g.setColor(Color.white);
		header.draw(gc.getWidth() / 2 - header.getWidth() / 2, 50);
	}
	
	
	
	

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);
		//music = ResourceManager.getInstance().getMusic("MUSIC_MAIN");
		//music.loop(0.5f, 1.0f);
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.leave(container, game);
		//reset(container);
		//music.stop();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		super.update(gc, sbg, delta);
		timer.update(delta);
		fontTimer.update(delta);
		Input input = gc.getInput();

		generateUnits(gc);
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			gc.exit();
		}
		
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			sbg.enterState(Game.INGAME_STATE);
		}

	}
	
	public void generateUnits(GameContainer gc) {
		if (timer.getMiliseconds() > INTERVAL) {
			timer.reset();
			try {
				new DestroyerShip((int)(Math.random() * gc.getWidth()), -30, this);
				
				if (Math.random() * 100 < 12) {
					new CPUAlliedShip((int)(Math.random() * gc.getWidth()), gc.getHeight(), this);
				}
				
				if (Math.random() * 100 < 10) {
					new BigDestroyerShip((int)(Math.random() * gc.getWidth()), -60, this);
				}
				
				if (Math.random() * 100 < 1) {
					new AnnihilatorShip((int)(Math.random() * gc.getWidth()), -60, this);
				}
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
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
		// TODO Auto-generated method stub
		
	}

}
