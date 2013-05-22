package de.myreality.galacticum.prototype;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.prototype.gui.GameText;


public class StatisticGameState extends WorldState {
	
	private Timer fadeTimer, countTimer;
	private Color fadeColor;
	private GameText pointsText;
	private GameTracker tracker;
	private int score;

	public StatisticGameState(int id, GameTracker tracker, GameContainer gc) {
		super(id);
		setBackground(getGraphicsAsImage(gc));
		fadeTimer = new Timer();
		countTimer = new Timer();
		fadeColor = new Color(0, 0, 0, 0);
		fadeTimer.start();
		countTimer.start();
		this.tracker = tracker;
		score = 0;
	}
	

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		super.init(arg0, arg1);
		GameText header = new GameText(0, 0, "Game Over", ResourceManager.getInstance().getFont("FONT_LARGE"), this);
		header.alignToCenterX();
		header.setY(60);
		header.setFontColor(new Color(200, 200, 0));
		GameText text = new GameText(0, 0, "Press ENTER to continue", ResourceManager.getInstance().getFont("FONT_MEDIUM"), this);
		text.alignToCenterX();
		text.alignToCenterY();
		text.setY(text.getY() + 100);
		text.setFontColor(new Color(200, 200, 0));
		pointsText = new GameText(0, 0, "0", ResourceManager.getInstance().getFont("FONT_LARGE"), this);
		pointsText.alignToCenterX();
		pointsText.alignToCenterY();
		pointsText.setFontColor(Color.white);
		GameText pointsTextDescription = new GameText(0, 0, "Your score:", ResourceManager.getInstance().getFont("FONT_MEDIUM"), this);
		pointsTextDescription.alignToCenterX();
		pointsTextDescription.alignToCenterY();
		pointsTextDescription.setY(pointsTextDescription.getY() - 75);
		pointsTextDescription.setFontColor(new Color(200, 200, 0));
		
		Sound defeatSound = ResourceManager.getInstance().getSound("SOUND_DEFEATED");
		defeatSound.play(1.0f, 0.2f);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		super.update(gc, sbg, delta);
		Input input = gc.getInput();
		fadeTimer.update(delta);
		countTimer.update(delta);
		if (fadeTimer.getMiliseconds() > 10) {
			fadeTimer.reset();
			if (fadeColor.a < 0.7f) {
				fadeColor.a += 0.01f;				
			} else {
				fadeColor.a = 0.7f;
			}
		}
		
		if (countTimer.getMiliseconds() > 10 && tracker.getTotalPoints() > 0) {
			score += Math.ceil(tracker.getTotalPoints() / 50.0f);
			Sound scoreSound = ResourceManager.getInstance().getSound("SOUND_COUNT");
			scoreSound.play(2.5f, 0.4f);
			if (score >= tracker.getTotalPoints()) {
				score = tracker.getTotalPoints();
				countTimer.reset();
				countTimer.stop();
			}
		}
		
		if (pointsText != null) {
			pointsText.setText(tracker.getPointsAsString(score));
			pointsText.alignToCenterX();
		}
		
		
		if (input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(Game.MENU_STATE);
		}
	}
	
		

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		if (background != null) {
			background.draw(0, 0, gc.getWidth(), gc.getHeight(),0, 0, gc.getWidth(), gc.getHeight());
		}
		g.setColor(fadeColor);
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		g.setColor(Color.white);
		super.render(gc, sbg, g);		
	}

	@Override
	public void onCollisionCheck(GameObject object1, GameObject object2) {
		
	}

	@Override
	public void onObjectUpdate(GameObject target, GameContainer gc, int delta) {
		// TODO Auto-generated method stub
		
	}


}
