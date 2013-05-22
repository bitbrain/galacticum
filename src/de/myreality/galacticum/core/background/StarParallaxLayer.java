package de.myreality.galacticum.core.background;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import de.myreality.galacticum.core.Boundable;
import de.myreality.galacticum.shading.ShaderProgram;
import de.myreality.galacticum.shading.ShaderType;
import de.myreality.galacticum.shading.StarShader;
import de.myreality.galacticum.util.HashProvider;
import de.myreality.galacticum.util.ImagePack;

/**
 * Layer that contains random generated stars. The amount and position of the
 * stars depend on the distance, the current chunk index and the game seed.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class StarParallaxLayer extends AbstractParallaxLayer {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public StarParallaxLayer(Boundable boundable, HashProvider hashProvider, int tileWidth,
			int tileHeight, float distance) {
		super(boundable, hashProvider, tileWidth, tileHeight, distance);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void computeSprite(Graphics g) {
		StarShader shader = (StarShader) ShaderType.STAR.getShader();
		shader.setResolution(getTileWidth(), getTileHeight());
		// Change to the current hash provider hash
		shader.setSeedHash((long) (Math.random() * 10));
		if (ShaderProgram.isSupported()) {
			shader.bind();
			g.fillRect(0, 0, getTileWidth(), getTileHeight());
			shader.unbind();
		}
		int starAmount = (int) (3 * getDistance());
	
		for (int i = 0; i < starAmount; ++i) {
			drawStar((float) (getTileWidth() * Math.random()),
					(float) (getTileHeight() * Math.random()), g);
		}
		
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void drawStar(float x, float y, Graphics g) {
		Color color = new Color(255, 255, 255, 255);
		float size = 1f;
		if (Math.random() < 0.03f) {
			size += 3f;
		} else if (Math.random() < 0.05f) {
			size += 2f;
		} else if (Math.random() < 0.08f) {
			size += 1f;
		}
		color.r = (float) (Math.random() * 0.4f + 0.6f);
		color.g = (float) (Math.random() * 0.4f + 0.6f);
		color.b = (float) (Math.random() * 0.4f + 0.6f);
		g.setDrawMode(Graphics.MODE_ALPHA_MAP);
		Image blendImage = ImagePack.GRADIENT;
		blendImage.draw(x - size / 2f, y - size / 2f, size * 2f, size * 2f);
		g.setDrawMode(Graphics.MODE_ALPHA_BLEND);
		g.setColor(color);
		g.fillRect(x - size / 2f, y - size / 2f, size * 2f, size * 2f);
		
		color.a = 1.0f;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
