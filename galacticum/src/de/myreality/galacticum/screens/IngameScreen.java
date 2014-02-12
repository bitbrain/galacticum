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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.context.Context;
import de.myreality.galacticum.controls.IngameControls;
import de.myreality.galacticum.graphics.shader.CRTShader;
import de.myreality.galacticum.graphics.shader.ShadeArea;
import de.myreality.galacticum.graphics.shader.ShaderManager;
import de.myreality.galacticum.graphics.shader.SimpleShaderManager;
import de.myreality.galacticum.ui.DebugStage;
import de.myreality.galacticum.ui.Debugable;
import de.myreality.galacticum.util.Updateable;

/**
 * 
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class IngameScreen implements Screen, Debugable {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private GalacticumGame game;

	private Context context;

	private Stage stage;

	private DebugStage debugStage;
	
	private ShaderManager shaderManager;
	
	private OrthographicCamera fakeCamera;
	
	private ShadeArea gameContent = new ShadeArea() {

		@Override
		public void draw(Batch batch, float delta) {			
			if (context instanceof Updateable) {
				((Updateable)context).update(delta);
			}
			//batch.setProjectionMatrix(fakeCamera.combined);
		}
		
	};
	
	private ShadeArea stageArea = new ShadeArea() {

		@Override
		public void draw(Batch batch, float delta) {
			stage.draw();
			debugStage.draw();
			batch.setProjectionMatrix(fakeCamera.combined);
		}
		
	};

	// ===========================================================
	// Constructors
	// ===========================================================

	public IngameScreen(GalacticumGame game, Context context) {
		this.game = game;
		this.context = context;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public GalacticumGame getGame() {
		return game;
	}

	public Context getContext() {
		return context;
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

		SpriteBatch batch = context.getSpriteBatch();

		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		// Update tween manager
		context.getTweenManager().update(delta);
		
		// Update UI
		stage.act(delta);
		
		// Update debug stage
		debugStage.act();
		
		shaderManager.updateAndRender(batch, delta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		if (stage == null) {
			stage = new IngameControls(width, height, false, this);
			debugStage = new DebugStage(width, height, false, context);
			Gdx.input.setInputProcessor(stage);
			

		} else {
			stage.setViewport(width, height, false);
			debugStage.setViewport(width, height, false);
			shaderManager.resize(width, height);
		}
		

		fakeCamera.setToOrtho(true, width, height);
		shaderManager.resize(width, height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		fakeCamera = new OrthographicCamera();
		shaderManager = new SimpleShaderManager(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		CRTShader crtShader = new CRTShader();
		
		crtShader.setIntensity(1.0f);
		crtShader.setLineSpeed(0.3f);
		crtShader.setNoiseFactor(0.15f);
		crtShader.setFrequency(120.0f);
		
		// Add horizontal and vertical blur
		//BlurShader horizontalBlur = new BlurShader(true,.4f);
		//BlurShader verticalBlur = new BlurShader(false, 4f);
		
		shaderManager.add(gameContent);
		shaderManager.add(stageArea);
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
		shaderManager.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.ui.Debugable#setDebugEnabled(boolean)
	 */
	@Override
	public void setDebugEnabled(boolean debug) {
		debugStage.setDebugEnabled(debug);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.ui.Debugable#isDebugEnabled()
	 */
	@Override
	public boolean isDebugEnabled() {
		return debugStage.isDebugEnabled();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void leave() {
		context.dispose();
		game.setScreen(new MainMenuScreen(game));
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
