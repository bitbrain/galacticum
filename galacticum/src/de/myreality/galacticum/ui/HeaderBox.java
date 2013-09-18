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
 * Provides a header box which can be displayed on the screen.
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class HeaderBox extends Group {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Label label;
	
	private Box box;
	
	private ShapeRenderer renderer;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public HeaderBox(String name, LabelStyle labelStyle) {
		label = new Label(name, labelStyle);
		renderer = new ShapeRenderer();
		box = new Box();
		this.addActor(box);
		this.addActor(label);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void setX(float x) {
		super.setX(x);
		box.setX(getX());
		label.setX(getX() + getWidth() / 2f - label.getWidth() / 2f);
	}

	@Override
	public void setY(float y) {
		super.setY(y);
		box.setY(getY() - getHeight());
		label.setY(getY());
	}
	
	@Override
	public void setWidth(float width) {
		super.setWidth(width);
		box.setWidth(getWidth());
	}

	@Override
	public void setHeight(float height) {
		super.setHeight(height);
		box.setHeight(getHeight());
		
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	class Box extends Actor {

		@Override
		public void draw(SpriteBatch batch, float parentAlpha) {
			super.draw(batch, parentAlpha);
			
			batch.end();
			
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			
			renderer.begin(ShapeType.FilledRectangle);
			renderer.setColor(0f, 0f, 0f, 0.25f);
			renderer.filledRect(getX(), getY(), getWidth(), getHeight());
			renderer.end();
			
			Gdx.gl.glDisable(GL10.GL_BLEND);
			
			batch.begin();
		}
		
		
	}

}
