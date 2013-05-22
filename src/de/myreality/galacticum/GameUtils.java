package de.myreality.galacticum;

import java.io.File;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.imageout.ImageOut;

/**
 * Provides basic game functionalities
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class GameUtils {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static Image screenBuffer;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * Takes a screenshot, fileExt can be .png, .gif, .tga, .jpg or .bmp
	 */
	public static void takeScreenshot(GameContainer container) {
		
		final String EXT = ".png";

		try {
			File FileSSDir = new File("screenshots");
			if (!FileSSDir.exists()) {
				FileSSDir.mkdirs();
			}

			int number = 0;
			String screenShotFileName = "screenshots/" + "screenshot_" + number
					+ EXT;
			File screenShot = new File(screenShotFileName);

			while (screenShot.exists()) {
				number++;
				screenShotFileName = "screenshots/" + "screenshot_" + number
						+ EXT;
				screenShot = new File(screenShotFileName);
			}

			screenBuffer = new Image(container.getWidth(),
					container.getHeight());

			Graphics g = container.getGraphics();
			g.copyArea(screenBuffer, 0, 0);
			ImageOut.write(screenBuffer, screenShotFileName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// ===========================================================
	// Inner classes
	// ===========================================================
}
