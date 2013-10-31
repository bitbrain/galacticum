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

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.controls.IngameControls;
import de.myreality.galacticum.core.context.Context;
import de.myreality.galacticum.core.subsystem.Subsystem;

/**
 * 
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class IngameScreen implements Screen {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private GalacticumGame game;
	
	private Context context;
	
	private Stage stage;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public IngameScreen(GalacticumGame game, Context context) {
		this.game = game;
		this.context = context;
		
		for (Subsystem system : context.getSubsystems()) {
			system.onEnter(context);
		}
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
		
		stage.act(delta);
		
		batch.begin();
		for (Subsystem system : context.getSubsystems()) {
			system.update(delta);
		}
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
		if (stage  == null) {
			stage = new IngameControls(width, height, false, this);
			Gdx.input.setInputProcessor(stage);
		} else {
			stage.setViewport(width, height, false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub

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
	
	public void leave() {
		
		Stack<Subsystem> systems = context.getSubsystems();
		
		// Free the last element first
		while (!systems.isEmpty()) {
			Subsystem system = systems.pop();
			system.shutdown();
		}

		game.setScreen(new CreationScreen(game));
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
