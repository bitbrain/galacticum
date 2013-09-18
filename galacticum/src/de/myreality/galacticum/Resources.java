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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

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
	
	public static void loadTextures() {		
		unloadTextures();		
		BACKGROUND_MAIN = new Texture(Gdx.files.internal("images/backgrounds/main.png"));
	}
	
	public static void unloadTextures() {
		if (BACKGROUND_MAIN != null) {
			BACKGROUND_MAIN.dispose();
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
	public static Color COLOR_MAIN_BLUE = Color.valueOf("5d37bc");
	
	// ===========================================================
	// Particles
	// ===========================================================
	
	// ===========================================================
	// Shader
	// ===========================================================

}
