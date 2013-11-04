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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.MetaData;
import de.myreality.galacticum.Resources;
import de.myreality.galacticum.controls.GeneralStage;
import de.myreality.galacticum.ui.MenuHead;

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

	// ===========================================================
	// Fields
	// ===========================================================
	
	private GalacticumGame game;
	
	private Stage stage;
	
	private SpriteBatch batch;
	
	private Texture background;
	
	private Button buttonLeft, buttonRight;
	
	private OrthographicCamera camera;
	
	private String caption;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public MenuScreen(String caption, GalacticumGame game) {
		this.game = game;
		this.caption = caption;
		background = Resources.TEXTURE_MENU_BACKGROUND;
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public GalacticumGame getGame() {
		return game;
	}
	
	public void setBackground(Texture texture) {
		this.background = texture;
	}
	
	public Button getButtonLeft() {
		return buttonLeft;
	}
	
	public Button getButtonRight() {
		return buttonRight;
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
		
		stage.act(delta);
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		
		batch.begin();
		batch.draw(background, -width / 2f, -height / 2f, width, height);
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
		
		if (stage == null) {
			stage = new GeneralStage(width, height, false);
			Gdx.input.setInputProcessor(stage);
			LabelStyle labelStyle = new LabelStyle();
			labelStyle.font = Resources.FONT_REGULAR;
			labelStyle.fontColor = Resources.COLOR_GREEN;
			MenuHead head = new MenuHead(caption, labelStyle);
			stage.addActor(head);
			onCreateUI(stage);
			onResizeUI(width, height);
			
			Label label = new Label("", Resources.STYLE_LABEL_DEBUG);
			MetaData meta = Resources.META_DATA;
			label.setText(meta.getName() + " " + meta.getVersion() + meta.getPhase());
			label.setY(20f);
			label.setX(10f);
			stage.addActor(label);
			
		} else {
			stage.setViewport(width, height, false);
			onResizeUI(width, height);
			camera.viewportWidth = width;
			camera.viewportHeight = height;
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

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
