/* Galacticum space game for Android, PC and browser.
 * Copyright (C) 2013  Miguel Gonzalez
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.myreality.galacticum.screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.Resources;
import de.myreality.galacticum.controls.GeneralStage;
import de.myreality.galacticum.tweens.ActorTween;
import de.myreality.galacticum.tweens.SpriteTween;

/**
 * Screen which displays a default template for underlying screens.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public abstract class MenuScreen implements Screen {

	// ===========================================================
	// Constants
	// ===========================================================

	public static final float PADDING = 0.1f; // PERCENTAGE
	
	public static final float FADE_IN_TIME = 1.5f;

	// ===========================================================
	// Fields
	// ===========================================================

	private GalacticumGame game;

	private Stage stage;

	private SpriteBatch batch;

	private OrthographicCamera camera;
	
	private TweenManager tweenManager;
	
	private Sprite background;
	
	private Label lblVersion, lblCopyright;
	
	private int width, height;

	// ===========================================================
	// Constructors
	// ===========================================================

	public MenuScreen(GalacticumGame game) {
		this.game = game;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public GalacticumGame getGame() {
		return game;
	}
	
	public TweenManager getTweenManager() {
		return tweenManager;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);

		tweenManager.update(delta);
		stage.act(delta);

		camera.update();

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
			background.setBounds(0, 0, width, height);
			background.draw(batch);
			onDraw(batch, delta);
		batch.end();

		
		stage.draw();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		
		this.width = width;
		this.height = height;
		camera.setToOrtho(true, width, height);
		if (stage == null) {
			stage = new GeneralStage(width, height, false);
			Gdx.input.setInputProcessor(stage);
			
			LabelStyle footerStyle = new LabelStyle();
			footerStyle.font = Resources.FONT_SMALL;
			footerStyle.fontColor = new Color(Resources.COLOR_GREEN);
			footerStyle.fontColor.a = 0.2f;
			
			lblVersion = new Label("version 0.1.30", footerStyle);	
			stage.addActor(lblVersion);
			lblCopyright = new Label("© 2014, all rights reserved.", footerStyle);			
			stage.addActor(lblCopyright);
			
			updateFooter();
			onCreateUI(stage);

			fadeIn();
		} else {
			stage.setViewport(width, height, false);
			updateFooter();
			onResizeUI(width, height);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		batch = new SpriteBatch();
		tweenManager = new TweenManager();
		background = new Sprite(Resources.TEXTURE_MENU_BACKGROUND);
		background.flip(false, true);
		camera = new OrthographicCamera();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected abstract void onCreateUI(Stage stage);

	protected abstract void onResizeUI(int width, int height);

	protected abstract void onDraw(SpriteBatch batch, float delta);
	
	private void fadeIn() {
		background.setColor(1f, 1f, 1f, 0f);
		
		Tween.to(background, SpriteTween.ALPHA, FADE_IN_TIME)
			 .target(1f)
			 .ease(TweenEquations.easeInOutCubic)
			 .start(tweenManager);
		
		stage.getRoot().getColor().a = 0;
		Tween.to(stage.getRoot(), ActorTween.ALPHA, FADE_IN_TIME)
		 .target(1f)
		 .ease(TweenEquations.easeInOutCubic)
		 .start(tweenManager);
	}
	
	private void updateFooter() {
		
		float scale = width * 0.0012f;
		
		float paddingX = width * PADDING / 2;
		float paddingY = height * PADDING / 2;
		
		lblVersion.setFontScale(scale);
		lblCopyright.setFontScale(scale);
		
		lblVersion.setX(paddingX);
		lblVersion.setY(paddingY);
		
		lblCopyright.setX(Gdx.graphics.getWidth() - paddingX - lblCopyright.getPrefWidth());
		lblCopyright.setY(paddingY);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
