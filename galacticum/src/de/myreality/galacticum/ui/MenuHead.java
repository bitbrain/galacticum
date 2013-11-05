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
package de.myreality.galacticum.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/**
 * Displays a head on top of the screen
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class MenuHead extends Group {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Background background;
	
	private Label label;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	
	public MenuHead(String text, LabelStyle style) {
		
		
		label = new Label(text, style);
		addActor(label);
		label.setX(Gdx.graphics.getWidth() / 2f - label.getWidth() / 2f);
		label.setY(Gdx.graphics.getHeight() - label.getHeight());
		
		background = new Background();
		background.setWidth(Gdx.graphics.getWidth());
		background.setHeight(label.getHeight());
		background.setY(Gdx.graphics.getHeight() - background.getHeight());
		addActorBefore(label, background);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
		
	@Override
	public void act(float delta) {
		super.act(delta);
		label.setX(Gdx.graphics.getWidth() / 2f - label.getWidth() / 2f);
		label.setY(Gdx.graphics.getHeight() - label.getHeight());
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	class Background extends Actor {

		private ShapeRenderer renderer = new ShapeRenderer();

		@Override
		public void draw(SpriteBatch batch, float parentAlpha) {
			super.draw(batch, parentAlpha);
			
			batch.end();
			    
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			
			renderer.begin(ShapeType.Filled);
			renderer.setColor(0f, 0f, 0f, 0.25f);
			renderer.rect(getX(), getY(), getWidth(), getHeight());
			renderer.end();
			      
			Gdx.gl.glDisable(GL10.GL_BLEND);
			      
			batch.begin();
		}
	}
}
