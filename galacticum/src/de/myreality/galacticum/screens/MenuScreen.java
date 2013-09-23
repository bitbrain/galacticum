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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.Resources;
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
	
	private float width, height;
	
	private String caption;
	
	private float padding;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public MenuScreen(String caption, GalacticumGame game) {
		this.game = game;
		this.caption = caption;
		background = Resources.BACKGROUND_MAIN;
		padding = 30;
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
		
		batch.begin();
		batch.draw(background, 0, 0, width, height);
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
		
		if (stage == null) {
			stage = new Stage(width, height, false);
			Gdx.input.setInputProcessor(stage);
			LabelStyle labelStyle = new LabelStyle();
			labelStyle.font = Resources.FONT_REGULAR;
			labelStyle.fontColor = Resources.COLOR_MAIN_GREEN;
			MenuHead head = new MenuHead(caption, labelStyle);
			stage.addActor(head);
			onCreateUI(stage);
			onResizeUI(width, height);
			
			buttonLeft = new TextButton("Back", Resources.STYLE_BUTTON_DEFAULT);
			buttonLeft.setX(padding);
			buttonLeft.setY(padding);
			buttonLeft.setWidth(buttonLeft.getWidth() + 50f);
			buttonLeft.setHeight(buttonLeft.getHeight() + 25f);
			
			// TODO: Implement proper button design
			//stage.addActor(buttonLeft);
		} else {
			stage.setViewport(width, height, false);
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
