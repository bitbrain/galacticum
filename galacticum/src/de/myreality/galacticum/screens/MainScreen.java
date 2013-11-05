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
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.Resources;

/**
 * Main screen of the game
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class MainScreen extends MenuScreen {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private GalacticumGame game;
	
	private Texture logo;
	
	private TextButton btnNewGame, btnLoadGame;
	
	private boolean canLeave = false;

	// ===========================================================
	// Constructors
	// ===========================================================

	public MainScreen(GalacticumGame game) {
		super("", game);
		this.game = game;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.screens.MenuScreen#onCreateUI(com.badlogic.gdx
	 * .scenes.scene2d.Stage)
	 */
	@Override
	protected void onCreateUI(Stage stage) {
		logo = Resources.TEXTURE_LOGO;
		stage.clear();
		
		TextButtonStyle style = new TextButtonStyle(Resources.STYLE_BUTTON_DEFAULT);

		style.font = Resources.FONT_REGULAR;
		btnNewGame = new TextButton("New game", style);
		btnLoadGame = new TextButton("Load game", style);
		
		stage.addActor(btnNewGame);
		stage.addActor(btnLoadGame);
		
		btnNewGame.setWidth(getWidth());
		btnNewGame.setHeight(100f);
		
		btnLoadGame.setWidth(getWidth());
		btnLoadGame.setHeight(100f);
		
		// add listeners
		btnNewGame.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				game.setScreen(new CreationScreen(game));
			}
			
		});
		
		btnLoadGame.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
			}
			
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.screens.MenuScreen#onResizeUI(int, int)
	 */
	@Override
	protected void onResizeUI(int width, int height) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.screens.MenuScreen#onDraw(com.badlogic.gdx.graphics
	 * .g2d.SpriteBatch, float)
	 */
	@Override
	protected void onDraw(SpriteBatch batch, float delta) {
		
		final int width = getWidth();
		final int height = (int) (logo.getHeight() * getScale());
		
		float offsetY = Gdx.graphics.getHeight() / 7f;
		
		batch.draw(logo, -width / 2f, Gdx.graphics.getHeight() / 2f - height - offsetY, width, height);		
		
		final int PADDING = (int) (Gdx.graphics.getHeight() / 6f) + 40;
		offsetY += height + PADDING;
		
		btnNewGame.setX(Gdx.graphics.getWidth() / 2f - btnNewGame.getWidth() / 2f);
		btnNewGame.setY(Gdx.graphics.getHeight() - offsetY);	
		
		btnLoadGame.setX(Gdx.graphics.getWidth() / 2f - btnLoadGame.getWidth() / 2f);
		btnLoadGame.setY(btnNewGame.getY() - PADDING);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		boolean pressed = (Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ESCAPE));
		
		if (canLeave && pressed) {
			Gdx.app.exit();
		}
		
		if (!pressed) {
			canLeave = true;
		}
	}
	
	
	private float getScale() {
		return Gdx.graphics.getWidth() / logo.getWidth() - 0.3f;
	}
	
	private int getWidth() {
		return (int) (logo.getWidth() * getScale());
	}
	

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
