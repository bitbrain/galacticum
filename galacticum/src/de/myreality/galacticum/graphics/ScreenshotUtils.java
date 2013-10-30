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
package de.myreality.galacticum.graphics;

import java.io.File;
import java.nio.ByteBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.PixmapIO;

import de.myreality.galacticum.Resources;

/**
 * Provides utility methods for screenshots
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public final class ScreenshotUtils {
	
	public static void screenshot() {
		FileHandle handle = createHandle();
		saveScreenshot(handle);
	}
	
	private static void saveScreenshot(FileHandle file) {
		Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), true);
		PixmapIO.writePNG(file, pixmap);
	}

	private static Pixmap getScreenshot(int x, int y, int w, int h, boolean flipY) {
		Gdx.gl.glPixelStorei(GL10.GL_PACK_ALIGNMENT, 1);

		final Pixmap pixmap = new Pixmap(w, h, Format.RGBA8888);
		ByteBuffer pixels = pixmap.getPixels();
		Gdx.gl.glReadPixels(x, y, w, h, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE,
				pixels);

		final int numBytes = w * h * 4;
		byte[] lines = new byte[numBytes];
		if (flipY) {
			final int numBytesPerLine = w * 4;
			for (int i = 0; i < h; i++) {
				pixels.position((h - i - 1) * numBytesPerLine);
				pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
			}
			pixels.clear();
			pixels.put(lines);
		} else {
			pixels.clear();
			pixels.get(lines);
		}

		return pixmap;
	}
	
	private static FileHandle createHandle() {
		
		 final String EXT = ".png";
		 final String FOLDER = "screenshots";
		 File screenShot = null;
		 
         try {
                 File FileSSDir = Gdx.files.external(Resources.ROOT_PATH + FOLDER + "/").file();
                 if (!FileSSDir.exists()) {
                         FileSSDir.mkdirs();
                 }

                 int number = 0;
                 String screenShotFileName = Resources.ROOT_PATH + FOLDER + "/" + "screenshot_" + number
                                 + EXT;
                 screenShot = Gdx.files.external(screenShotFileName).file();

                 while (screenShot.exists()) {
                         number++;
                         screenShotFileName = Resources.ROOT_PATH + FOLDER + "/" + "screenshot_" + number
                                         + EXT;
                         screenShot = Gdx.files.external(screenShotFileName).file();
                 }

                 screenShot.createNewFile();
         } catch (Exception e) {
                 e.printStackTrace();
         }
         
         return Gdx.files.external(screenShot.getPath());
	}
}
