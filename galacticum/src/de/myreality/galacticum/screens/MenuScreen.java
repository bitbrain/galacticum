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
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.MetaData;
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
	
	private Sprite background, earth, earthOverlay;
	
	private Label lblVersion, lblCopyright;
	
	private int width, height;
	
	private ShaderProgram blurShader, crtShader;
	
	private float time;
	
    private FrameBuffer targetA;
    
    private FrameBuffer targetB;
    
    private FrameBuffer targetC;

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
	
	public Sprite getBackground() {
		return background;
	}
	
	public void fadeBackground(float from, float to) {

		background.setColor(1f, 1f, 1f, from);
		
		Tween.to(background, SpriteTween.ALPHA, FADE_IN_TIME)
			 .target(to)
			 .ease(TweenEquations.easeInOutCubic)
			 .start(tweenManager);
		
		earth.setColor(1f, 1f, 1f, from);
		earthOverlay.setColor(1f, 1f, 1f, from);
		Tween.to(earth, SpriteTween.ALPHA, FADE_IN_TIME)
			 .target(to)
			 .ease(TweenEquations.easeInOutCubic)
			 .start(tweenManager);
		
		Tween.to(earthOverlay, SpriteTween.ALPHA, FADE_IN_TIME)
		 .target(to)
		 .ease(TweenEquations.easeInOutCubic)
		 .start(tweenManager);
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

		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		// Rotate the earth
		earth.setOrigin(earth.getWidth() / 2, earth.getHeight() / 2);
		earth.rotate(delta * 3.0f);
		tweenManager.update(delta);
		stage.act(delta);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		
		time += delta * Math.random();
		
		drawTargetA(batch, delta);
		drawTargetB(batch, delta);
		drawTargetC(batch, delta);

		TextureRegion bg = new TextureRegion(targetC.getColorBufferTexture());
		
		batch.setShader(crtShader);
		batch.begin();
			crtShader.setUniformf("u_time", time);
			batch.draw(bg, 0, 0);
		batch.end();
		batch.flush();
	}
	
	private void drawTargetA(Batch batch, float delta) {
		
		targetA.begin();
		
		batch.begin();
			background.draw(batch);
			earth.draw(batch);
			earthOverlay.draw(batch);
		batch.end();
		targetA.end();		
		batch.flush();
	}
	
	private void drawTargetB(Batch batch, float delta) {

		batch.setShader(blurShader);
		TextureRegion targetABuffer = new TextureRegion(targetA.getColorBufferTexture());

		targetB.begin();
		
		batch.begin();
			blurShader.setUniformi("horizontal", 1);
			blurShader.setUniformf("u_time", time);
			batch.draw(targetABuffer, 0f, 0f);
		batch.end();
		targetB.end();
		batch.flush();
	}
	
	private void drawTargetC(Batch batch, float delta) {
		
		targetC.begin();

		TextureRegion bg = new TextureRegion(targetB.getColorBufferTexture());
		
		batch.setShader(blurShader);
		batch.begin();
			blurShader.setUniformi("horizontal", 0);
			blurShader.setUniformf("u_time", time);
			batch.draw(bg, 0, 0);
		batch.end();
		batch.flush();

		stage.draw();
		
		targetC.end();
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
			
			lblVersion = new Label("version 0.0", footerStyle);	
			stage.addActor(lblVersion);
			lblCopyright = new Label("© 2014, all rights reserved.", footerStyle);			
			stage.addActor(lblCopyright);
			
			MetaData meta = Resources.META_DATA;
			lblVersion.setText("version " + meta.getVersion()
                            + meta.getPhase());
			
			onResize();
			onCreateUI(stage);
			onResizeUI(width, height);
			fadeIn();
		} else {
			stage.setViewport(width, height, false);
			onResize();
			onResizeUI(width, height);
		}
		

		
		targetA = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
	    targetB = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
	    targetC = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
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
		earth = new Sprite(Resources.TEXTURE_EARTH);
		earthOverlay = new Sprite(Resources.TEXTURE_EARTH_OVERLAY);
		background.flip(false, true);
		earth.flip(false, true);
		earthOverlay.flip(false, true);
		camera = new OrthographicCamera();
		
		FileHandle blurVertex = Gdx.files.internal("shaders/blur.vert");
		FileHandle blurFragment = Gdx.files.internal("shaders/blur.frag");
		
		blurShader = new ShaderProgram(blurVertex, blurFragment);
		
		FileHandle crtVertec = Gdx.files.internal("shaders/crt.vert");
		FileHandle crtFragment = Gdx.files.internal("shaders/crt.frag");
		
		crtShader = new ShaderProgram(crtVertec, crtFragment);
		
		System.out.println(blurShader.getLog());
		System.out.println(crtShader.getLog());
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
		stage.getRoot().getColor().a = 0;
		Tween.to(stage.getRoot(), ActorTween.ALPHA, FADE_IN_TIME)
		 .target(1f)
		 .ease(TweenEquations.easeInOutCubic)
		 .start(tweenManager);
	}
	
	private void onResize() {

		background.setBounds(0, 0, width, height);
		
		float scale = width * 0.0012f;
		
		float paddingX = width * PADDING / 2;
		float paddingY = height * PADDING / 2;
		
		lblVersion.setFontScale(scale);
		lblCopyright.setFontScale(scale);
		
		lblVersion.setX(paddingX);
		lblVersion.setY(paddingY);
		
		lblCopyright.setX(Gdx.graphics.getWidth() - paddingX - lblCopyright.getPrefWidth());
		lblCopyright.setY(paddingY);
		
		float earthWidth = width - width * (PADDING * 0.6f);
		float earthHeight = earth.getHeight() * (earthWidth / earth.getWidth());
		float earthX = width / 2 - earthWidth / 2;
		float earthY = height + -earthHeight / 1.5f;		
		
		earth.setBounds(earthX, earthY, earthWidth, earthHeight);
		earthOverlay.setBounds(earthX, earthY, earthWidth, earthHeight);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
