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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;

import de.myreality.galacticum.Resources;

/**
 * Displays a progress bar
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class ProgressBar extends TextButton {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private float progress;

	// ===========================================================
	// Constructors
	// ===========================================================

	public ProgressBar(float progress) {
		super("0%", Resources.STYLE_BUTTON_DEFAULT);
		setProgress(progress);
		this.pad(20f);
		
		TextButtonStyle style = getStyle();
		Background background = new Background();		
		style.over = background;
		style.up = background;
		style.fontColor = Resources.COLOR_VIOLET_LIGHT;
		style.overFontColor = Resources.COLOR_VIOLET_LIGHT;
		
		
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	/**
	 * 
	 * 
	 * @param progress
	 */
	public final void setProgress(float progress) {
		
		if (progress > 1.0f) {
			progress = 1.0f;
		}
		
		if (progress < 0f) {
			progress = 0f;
		}		

		this.progress = progress;		
		this.setText(((int)(progress*100)) + "%");
	}
	
	public float getProgress() {
		return progress;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================	
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public static class ProgressBarStyle {

		public Texture background = Resources.TEXTURE_BRIGHT_TRANSPARENT;

		public Texture foreground = Resources.TEXTURE_DARK_TRANSPARENT;

		public BitmapFont font = Resources.FONT_REGULAR;

		public boolean labeled = true;
	}

	class Background extends BaseDrawable {
		
		@Override
		public void draw(SpriteBatch batch, float x, float y, float width,
				float height) {
			super.draw(batch, x, y, width, height);
			batch.draw(Resources.TEXTURE_BRIGHT_TRANSPARENT, x, y, width, height);
			batch.draw(Resources.TEXTURE_BLUE, x, y, width * getProgress(), height);
		}
	}

}
