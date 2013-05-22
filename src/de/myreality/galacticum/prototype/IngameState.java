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
import de.myreality.galacticum.prototype.gui.PlayerHUD;
import de.myreality.galacticum.prototype.obstacles.BigMeteor;
import de.myreality.galacticum.prototype.obstacles.SmallMeteor;
import de.myreality.galacticum.prototype.ships.AlliedShip;
import de.myreality.galacticum.prototype.ships.AnnihilatorShip;
import de.myreality.galacticum.prototype.ships.BigDestroyerShip;
import de.myreality.galacticum.prototype.ships.DestroyerShip;
import de.myreality.galacticum.prototype.ships.EnemyShip;
import de.myreality.galacticum.prototype.ships.PlayerShip;
import de.myreality.galacticum.prototype.ships.SeekerShip;
import de.myreality.galacticum.prototype.ships.Spaceship;
import de.myreality.galacticum.prototype.ships.boss.Boss1;
import de.myreality.galacticum.prototype.ships.boss.Boss2;

public class IngameState extends WorldState {
	
	// Gameplay timers
	private Timer timer, phaseTimer, deathTimer;
	private int interval;
	private int denumerator;
	private int phase;
	private BackgroundBattle backgroundBattle;
	private EnemyShip bossUnit;
	private GameText points;
	
	// Pause mode
	private boolean paused;
	private Image pauseImage;
	private Color pauseColor;

	
	// Time for each phase
	public static final int PHASE_TIME = 40000;
	
	private Spaceship ship;
	public IngameState(int id) {
		super(id);
		interval = 2300;
		denumerator = 2;
		timer = new Timer();
		phaseTimer = new Timer();
		deathTimer = new Timer();
		timer.start();
		phaseTimer.start();
		phase = 1;
		bossUnit = null;	
		paused = false;
		pauseColor = new Color(0, 0, 0, 150);
		pauseImage = null;
	}
	
	public Spaceship getPlayerShip() {
		return ship;
	}


	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		super.init(arg0, arg1);		
		reset(arg0);
		backgroundBattle = new BackgroundBattle(arg0, 1, 0.5f);
		backgroundBattle.setBackground(ResourceManager.getInstance().getImage("BG_MOTHERSHIP"));
		backgroundBattle.init(arg0, arg1);		
	}
	
	
	
	
	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);
		backgroundBattle.enter(container, game);
		reset(container);
		//music = ResourceManager.getInstance().getMusic("MUSIC_MAIN");
		//music.loop(1.1f, 0.4f);		
	}

	
	
	
	
	@Override
	public void reset(GameContainer gc) {
		super.reset(gc);
		deathTimer.reset();
		deathTimer.stop();
		timer.reset();
		backgroundBattle = new BackgroundBattle(gc, 1, 0.5f);
		phaseTimer.start();
		phaseTimer.reset();
		AlliedShip playerShip = null;		
		paused = false;
		try {
			playerShip = new PlayerShip(350, 430, this);
			ship = playerShip;
			new PlayerHUD(10, gc.getHeight() - 60, playerShip, this);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		setAllowGUI(true);
		points = new GameText(10, 10, "0", ResourceManager.getInstance().getFont("FONT_MEDIUM"), this);
		points.setX(getWorldWidth()- points.getWidth() - 10);
		points.setFontColor(Color.white);
	}
	
	public void generateBossUnit(GameContainer gc) {
		if (Spaceship.isProbably(100 - phase)) {
			bossUnit = new Boss1((int)(Math.random() * gc.getWidth()), -140, this);
			bossUnit.setRank(phase);
		} else {
			bossUnit = new Boss2((int)(Math.random() * gc.getWidth()), -200, this);
			bossUnit.setRank(phase);
		}
	}
	
	public void generateObstacles(GameContainer gc) {
		if (Spaceship.isProbably(0.3f)) {
			new SmallMeteor((int)(Math.random() * gc.getWidth()), -30, this);
		}
		if (Spaceship.isProbably(0.1f)) {
			new BigMeteor((int)(Math.random() * gc.getWidth()), -60, this);
		}
	}
	
	public void generateEnemyUnit(GameContainer gc) {
		if (timer.getMiliseconds() > interval) {
			timer.reset();
			interval -= (interval / denumerator);
			denumerator += 5;
			try {
				if (Spaceship.isProbably(50 - phase * 2)) {
					Spaceship enemy = new DestroyerShip((int)(Math.random() * gc.getWidth()), -30, this);
					enemy.setRank(phase);
				}
			} catch (SlickException e) {
				e.printStackTrace();
			}
			
			if (Spaceship.isProbably(phase)) {
				try {
					Spaceship enemy = new BigDestroyerShip((int)(Math.random() * gc.getWidth()), -60, this);
					enemy.setRank(phase);
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			} 

			if (phase > 1) {
				if (Spaceship.isProbably(1)) {
					Spaceship enemy = new AnnihilatorShip((int)(Math.random() * gc.getWidth()), -60, this);
					enemy.setRank(phase - 3);		
				}
			}
			
			if (Spaceship.isProbably(1)) {
				new BigMeteor((int)(Math.random() * gc.getWidth()), -60, this);
			}
			
			if (Spaceship.isProbably(30)) {
				new SeekerShip((int)(Math.random() * gc.getWidth()), -25, this);
			}
			
			if (Spaceship.isProbably(2)) {
				new SmallMeteor((int)(Math.random() * gc.getWidth()), -30, this);
			}
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {		
		if (!paused) {
			backgroundBattle.render(gc, sbg, g);
			super.render(gc, sbg, g);
		} else if (pauseImage != null) {
			pauseImage.draw();
			g.setColor(pauseColor);
			g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
			
			// Information text
			Font caption = ResourceManager.getInstance().getFont("FONT_LARGE");
			Font information = ResourceManager.getInstance().getFont("FONT_MEDIUM");
			Color captionColor = new Color(200, 200, 0);
			String captionText = "Pause";
			String informationText = "Press ESC to continue or ENTER to leave";
			caption.drawString(gc.getWidth() / 2 - caption.getWidth(captionText) / 2, 60, captionText, captionColor);
			information.drawString(gc.getWidth() / 2 - information.getWidth(informationText) / 2, gc.getHeight() / 2 - information.getLineHeight() / 2, informationText);
		}
	}
	
	public void leaveIngame(GameContainer gc, StateBasedGame sbg) {
		
		try {
			setAllowGUI(false);
			paused = false;
			render(gc, sbg, gc.getGraphics());		
			sbg.addState(new StatisticGameState(Game.STATISTIC_STATE, getGameTracker(), gc));		
			sbg.getState(Game.STATISTIC_STATE).init(gc, sbg);
			sbg.enterState(Game.STATISTIC_STATE);
		} catch (SlickException e) {
			e.printStackTrace();
			sbg.enterState(Game.MENU_STATE);
		}				
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		Input input = gc.getInput();
		if (!paused) {
			super.update(gc, sbg, delta);
			backgroundBattle.update(gc, sbg, delta);
			timer.update(delta);
			phaseTimer.update(delta);
			deathTimer.update(delta);		
			if (phaseTimer.getMiliseconds() > PHASE_TIME) {
				phaseTimer.reset();
				phaseTimer.stop();
				generateBossUnit(gc);
			}
			
			if (points != null) {
				points.setText(getGameTracker().getPointsAsString());
				points.setX(getWorldWidth()- points.getWidth() - 10);
			}
			
			if (phaseTimer.isRunning()) {
				generateEnemyUnit(gc);
			} else {
				if (bossUnit != null && bossUnit.isRemoveQuery()) {
					bossUnit = null;
					phaseTimer.start();
					phase++;
				} else if (bossUnit != null) {
					generateObstacles(gc);
				}
			}
			
			if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
				if (ship.getBounds().getY() > 0) { 
					ship.move(GameObject.UP, delta);
				} else {
					ship.getBounds().setY(0);
				}
			} else if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
				if (ship.getBounds().getY() + ship.getBounds().getHeight() < gc.getHeight()) { 
					ship.move(GameObject.DOWN, delta);
				} else {
					ship.getBounds().setY(gc.getHeight() - ship.getBounds().getHeight());
				}
			} 
			if(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
				if (ship.getBounds().getX() > 0) { 
					ship.move(GameObject.LEFT, delta);
				} else {
					ship.getBounds().setX(0);
				}
			} else if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
				if (ship.getBounds().getX() + ship.getBounds().getWidth() < gc.getWidth()) { 
					ship.move(GameObject.RIGHT, delta);
				} else {				
					ship.getBounds().setX(gc.getWidth() - ship.getBounds().getWidth());				
				}
			} else if (input.isKeyPressed(Input.KEY_1)) {
				ship.setActiveShield(0);				
			} else if (input.isKeyPressed(Input.KEY_2)) {
				ship.setActiveShield(1);				
			} else if (input.isKeyPressed(Input.KEY_3)) {
				ship.setActiveShield(2);				
			} else if (input.isKeyPressed(Input.KEY_4)) {
				ship.setActiveShield(3);				
			} else if (input.isKeyPressed(Input.KEY_5)) {
				ship.setActiveShield(4);				
			}
			
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				ship.shoot();
			}
			
			if (input.isKeyPressed(Input.KEY_1)) {
				ship.upgrade();
			}
			
			if ((ship.isDead() && !deathTimer.isRunning() && ship.isOnRemoved())) {
				deathTimer.start();		
			}
		} 
		
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			paused = !paused;
			if (paused) {
				pauseImage = getGraphicsAsImage(gc);
			} else {
				pauseImage = null;
			}
		}
		
		if (deathTimer.getMiliseconds() > 1000 || (paused && input.isKeyPressed(Input.KEY_ENTER))) {	
			deathTimer.reset();
			deathTimer.stop();
			leaveIngame(gc, sbg);
		}		
		
	}

	@Override
	public void onCollisionCheck(GameObject object1, GameObject object2) {
		
	}

	@Override
	public void onObjectUpdate(GameObject target, GameContainer gc, int delta) {
		if (deathTimer.isRunning() && target instanceof MovableSpriteObject) {
			MovableSpriteObject movable = (MovableSpriteObject)target;
			movable.setSpeed(movable.getSpeed() - (movable.getSpeed() * 0.003f * delta));
		}
	}
}
