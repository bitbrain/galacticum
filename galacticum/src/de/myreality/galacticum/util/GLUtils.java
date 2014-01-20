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
package de.myreality.galacticum.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

/**
 * Provides general OpenGL functionality
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public final class GLUtils {
	
	/** The normal drawing mode */
	public static int MODE_NORMAL = 1;

	/** Draw to the alpha map */
	public static int MODE_ALPHA_MAP = 2;

	/** Draw using the alpha blending */
	public static int MODE_ALPHA_BLEND = 3;

	/** Draw multiplying the source and destination colours */
	public static int MODE_COLOR_MULTIPLY = 4;
	
	/** Draw adding the existing colour to the new colour */
	public static int MODE_ADD = 5;
	
	/** Draw blending the new image into the old one by a factor of it's colour */
	public static int MODE_SCREEN = 6;
	
	public static int MODE_ADD_ALPHA = 7;
	
	public static void setMode(int mode) {
		
		if (mode == MODE_NORMAL) {
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glColorMask(true, true, true, true);
			Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		}
		if (mode == MODE_ALPHA_MAP) {
			Gdx.gl.glDisable(GL10.GL_BLEND);
			Gdx.gl.glColorMask(false, false, false, true);
		}
		if (mode == MODE_ALPHA_BLEND) {
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glColorMask(true, true, true, false);
			Gdx.gl.glBlendFunc(GL10.GL_DST_ALPHA, GL10.GL_ONE_MINUS_DST_ALPHA);
		}
		if (mode == MODE_COLOR_MULTIPLY) {
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glColorMask(true, true, true, true);
			Gdx.gl.glBlendFunc(GL10.GL_ONE_MINUS_SRC_COLOR, GL10.GL_SRC_COLOR);
		}
		if (mode == MODE_ADD) {
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glColorMask(true, true, true, true);
			Gdx.gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE);
		}
		if (mode == MODE_ADD_ALPHA) {
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glColorMask(true, true, true, true);
			Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
		}
		if (mode == MODE_SCREEN) {
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glColorMask(true, true, true, true);
			Gdx.gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_COLOR);
		}
	}
}
