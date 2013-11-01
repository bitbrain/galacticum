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

import java.io.IOException;

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

import de.myreality.galacticum.io.json.JsonMetaData;
import de.myreality.galacticum.ui.ProgressBar.ProgressBarStyle;

/**
 * Contains basic resources of the game
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public final class Resources {
	
	// ===========================================================
	// Colors
	// ===========================================================
		
	public static Color COLOR_GREEN = Color.valueOf("b8c61a");
	public static Color COLOR_GREEN_LIGHT = Color.valueOf("dded26");
	public static Color COLOR_BLUE = Color.valueOf("30226d");
	public static Color COLOR_CYAN = Color.valueOf("00baff");
	public static Color COLOR_VIOLET = Color.valueOf("6a37bf");
	public static Color COLOR_VIOLET_LIGHT = Color.valueOf("524ab1");
	public static Color COLOR_DEBUG = Color.valueOf("ffc515");
	
	// ===========================================================
	// Textures
	// ===========================================================
	
	public static Texture TEXTURE_MENU_BACKGROUND;
	
	public static Texture TEXTURE_BRIGHT_TRANSPARENT;
	
	public static Texture TEXTURE_DARK_TRANSPARENT;
	
	public static Texture TEXTURE_BLUE;
	
	public static Texture TEXTURE_SPACE_FAR;
	
	public static Texture TEXTURE_FOG_MEDIUM;
	
	public static void loadTextures() {		
		unloadTextures();		
		TEXTURE_MENU_BACKGROUND = new Texture(Gdx.files.internal("images/backgrounds/main.png"));
		TEXTURE_BRIGHT_TRANSPARENT = createColoredTexture(new Color(0f, 0f, 0f, 0.3f));
		TEXTURE_DARK_TRANSPARENT = createColoredTexture(new Color(0f, 0f, 0f, 0.5f));
		TEXTURE_BLUE = createColoredTexture(COLOR_BLUE);
		TEXTURE_SPACE_FAR = new Texture(Gdx.files.internal("images/space-far.png"));
		TEXTURE_FOG_MEDIUM = new Texture(Gdx.files.internal("images/space-middle.png"));
	}
	
	public static void unloadTextures() {
		if (TEXTURE_MENU_BACKGROUND != null) {
			TEXTURE_MENU_BACKGROUND.dispose();
		}
		
		if (TEXTURE_BRIGHT_TRANSPARENT != null) {
			TEXTURE_BRIGHT_TRANSPARENT.dispose();
		}
		
		if (TEXTURE_SPACE_FAR != null) {
			TEXTURE_SPACE_FAR.dispose();
		}
		
		if (TEXTURE_FOG_MEDIUM != null) {
			TEXTURE_FOG_MEDIUM.dispose();
		}
	}
	
	private static Texture createColoredTexture(Color color) {
		Pixmap map = new Pixmap(8, 8, Pixmap.Format.RGBA4444);
		Gdx.gl.glEnable(GL10.GL_BLEND);
		Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		map.setColor(color.r, color.g, color.b, color.a);
		map.fill();		
		Texture texture = new Texture(map);
		map.dispose();
		Gdx.gl.glDisable(GL10.GL_BLEND);
		
		return texture;
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
	
	public static ProgressBarStyle STYLE_PROGRESS_BAR = new ProgressBarStyle();
	
	public static LabelStyle STYLE_LABEL_ERROR = new LabelStyle();
	
	public static LabelStyle STYLE_LABEL_DEBUG = new LabelStyle();
	
	public static void loadStyles() {		
		STYLE_BUTTON_DEFAULT.up = new SpriteDrawable(new Sprite(TEXTURE_BRIGHT_TRANSPARENT));
		STYLE_BUTTON_DEFAULT.over = new SpriteDrawable(new Sprite(TEXTURE_DARK_TRANSPARENT));
		STYLE_BUTTON_DEFAULT.fontColor = Resources.COLOR_GREEN;
		STYLE_BUTTON_DEFAULT.overFontColor = COLOR_GREEN_LIGHT;
		STYLE_BUTTON_DEFAULT.font = Resources.FONT_SMALL;
		
		STYLE_TEXTFIELD_DEFAULT.background = new SpriteDrawable(new Sprite(TEXTURE_BRIGHT_TRANSPARENT));
		STYLE_TEXTFIELD_DEFAULT.font = Resources.FONT_REGULAR;
		STYLE_TEXTFIELD_DEFAULT.messageFont = Resources.FONT_REGULAR;
		STYLE_TEXTFIELD_DEFAULT.messageFontColor = COLOR_BLUE;
		STYLE_TEXTFIELD_DEFAULT.fontColor = COLOR_VIOLET_LIGHT;
		STYLE_TEXTFIELD_DEFAULT.cursor = new SpriteDrawable(new Sprite(TEXTURE_BRIGHT_TRANSPARENT));
		
		STYLE_LABEL_ERROR.font = FONT_SMALL;
		STYLE_LABEL_ERROR.fontColor = Color.RED;
		
		STYLE_LABEL_DEBUG.font = FONT_SMALL;
		STYLE_LABEL_DEBUG.fontColor = COLOR_DEBUG;
		
		STYLE_PROGRESS_BAR.background = TEXTURE_BRIGHT_TRANSPARENT;
		STYLE_PROGRESS_BAR.labeled = true;
		STYLE_PROGRESS_BAR.font = FONT_SMALL;
		STYLE_PROGRESS_BAR.foreground = TEXTURE_DARK_TRANSPARENT;
	}
	
	// ===========================================================
	// File paths
	// ===========================================================
	
	public static final String ROOT_PATH = ".galacticum/";
	
	public static final String CONTEXT_PATH = ROOT_PATH + "context.xml";
	
	// ===========================================================
	// Meta data
	// ===========================================================
	
	public static MetaData META_DATA;
	
	public static void loadMetaData() throws IOException {		
		META_DATA = new JsonMetaData(Gdx.files.internal("meta.json"));
	}
	

}
