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
package de.myreality.galacticum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Contains basic resources of the game
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public final class Resources {
	
	// ===========================================================
	// Textures
	// ===========================================================
	
	public static Texture BACKGROUND_MAIN;
	
	public static Texture BACKGROUND_TRANSPARENT;
	
	public static Texture BACKGROUND_TRANSPARENT_DARK;
	
	public static void loadTextures() {		
		unloadTextures();		
		BACKGROUND_MAIN = new Texture(Gdx.files.internal("images/backgrounds/main.png"));
		
		Pixmap map = new Pixmap(10, 10, Pixmap.Format.RGBA4444);
		Gdx.gl.glEnable(GL10.GL_BLEND);
		Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		map.setColor(0, 0, 0, 0.3f);
		map.fill();
		BACKGROUND_TRANSPARENT = new Texture(map);
		map.setColor(0, 0, 0, 0.5f);
		map.fill();
		BACKGROUND_TRANSPARENT_DARK = new Texture(map);
		map.dispose();
		Gdx.gl.glDisable(GL10.GL_BLEND);
	}
	
	public static void unloadTextures() {
		if (BACKGROUND_MAIN != null) {
			BACKGROUND_MAIN.dispose();
		}
		
		if (BACKGROUND_TRANSPARENT != null) {
			BACKGROUND_TRANSPARENT.dispose();
		}
	}

	// ===========================================================
	// Fonts
	// ===========================================================
	
	public static BitmapFont FONT_LARGE;
	public static BitmapFont FONT_REGULAR;
	public static BitmapFont FONT_SMALL;
	
	public static void loadFonts() {		
		unloadFonts();		
		FONT_LARGE = new BitmapFont(Gdx.files.internal("fonts/font-large.fnt"), false);
		FONT_REGULAR = new BitmapFont(Gdx.files.internal("fonts/font-regular.fnt"), false);
		FONT_SMALL = new BitmapFont(Gdx.files.internal("fonts/font-small.fnt"), false);
	}
	
	public static void unloadFonts() {
		if (FONT_LARGE != null) {
			FONT_LARGE.dispose();
		}
		
		if (FONT_REGULAR != null) {
			FONT_REGULAR.dispose();
		}
		
		if (FONT_SMALL != null) {
			FONT_SMALL.dispose();
		}
	}
	
	// ===========================================================
	// Sounds
	// ===========================================================
	
	// ===========================================================
	// Music
	// ===========================================================
	
	// ===========================================================
	// Colors
	// ===========================================================
	
	public static Color COLOR_MAIN_GREEN = Color.valueOf("b8c61a");
	public static Color COLOR_MAIN_GREEN_LIGHT = Color.valueOf("dded26");
	public static Color COLOR_MAIN_BLUE = Color.valueOf("5d37bc");
	
	// ===========================================================
	// Particles
	// ===========================================================
	
	// ===========================================================
	// Shader
	// ===========================================================
	
	// ===========================================================
	// Styles
	// ===========================================================
	
	public static TextButtonStyle STYLE_BUTTON_DEFAULT = new TextButtonStyle();
	
	public static TextFieldStyle STYLE_TEXTFIELD_DEFAULT = new TextFieldStyle();
	
	public static LabelStyle STYLE_LABEL_ERROR = new LabelStyle();
	
	public static void loadStyles() {		
		STYLE_BUTTON_DEFAULT.up = new SpriteDrawable(new Sprite(BACKGROUND_TRANSPARENT));
		STYLE_BUTTON_DEFAULT.over = new SpriteDrawable(new Sprite(BACKGROUND_TRANSPARENT_DARK));
		STYLE_BUTTON_DEFAULT.fontColor = Resources.COLOR_MAIN_GREEN;
		STYLE_BUTTON_DEFAULT.overFontColor = COLOR_MAIN_GREEN_LIGHT;
		STYLE_BUTTON_DEFAULT.font = Resources.FONT_SMALL;
		
		STYLE_TEXTFIELD_DEFAULT.background = new SpriteDrawable(new Sprite(BACKGROUND_TRANSPARENT));
		STYLE_TEXTFIELD_DEFAULT.font = Resources.FONT_REGULAR;
		STYLE_TEXTFIELD_DEFAULT.messageFont = Resources.FONT_REGULAR;
		STYLE_TEXTFIELD_DEFAULT.messageFontColor = Color.GRAY;
		STYLE_TEXTFIELD_DEFAULT.fontColor = COLOR_MAIN_BLUE;
		STYLE_TEXTFIELD_DEFAULT.cursor = new SpriteDrawable(new Sprite(BACKGROUND_TRANSPARENT));
		
		STYLE_LABEL_ERROR.font = FONT_SMALL;
		STYLE_LABEL_ERROR.fontColor = Color.RED;
	}

}
