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
package de.myreality.galacticum.graphics.rendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

import de.myreality.galacticum.Resources;

/**
 * Generates spaceship textures depending on the hash
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public abstract class AbstractTextureLayer implements TextureLayer {

	private boolean shadingEnabled;

	private int textureX, textureY, textureWidth, textureHeight;

	public AbstractTextureLayer(boolean shadingEnabled) {
		this.shadingEnabled = shadingEnabled;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.graphics.rendering.TextureLayer#build(int,
	 * int, int, java.lang.Iterable, com.badlogic.gdx.graphics.Color)
	 */
	@Override
	public Pixmap build(long hash, int width, int height,
			Iterable<TextureLayer> others, Color color) {

		Pixmap map = new Pixmap(width, height, Format.RGBA8888);
		map.setColor(color);

		draw(map, width, height, others, hash);
		
		if (shadingEnabled) {
			
//			Pixmap transMap = new Pixmap(width, height, Format.RGBA8888);
//			transMap.setColor(color);
//			GLUtils.setMode(GLUtils.MODE_ADD_ALPHA);
//			
//			draw(transMap, width, height, others, hash);
//			
//			GLUtils.setMode(GLUtils.MODE_ALPHA_BLEND);
//			
//			createGradient(transMap, color);
//			
//			GLUtils.setMode(GLUtils.MODE_NORMAL);
//			
//			map.drawPixmap(transMap, 0, 0);
//			transMap.dispose();
		}

		return map;
	}

	protected abstract void draw(Pixmap map, int width, int height,
			Iterable<TextureLayer> others, long hash);

	@SuppressWarnings("unused")
	private void createGradient(Pixmap original, Color color) {

		Texture gradient = Resources.TEXTURE_GRADIENT;
		
		TextureData data = gradient.getTextureData();
		data.prepare();
		Pixmap gradientMap = data.consumePixmap();
		original.setColor(color);
		float padding = 100;

		int xPos = getTextureX();
		int yPos = getTextureY();
		int width = getTextureWidth();
		int height = getTextureHeight();

		xPos -= padding;
		yPos -= padding;
		width += padding * 2;
		height += padding * 2;

		if (xPos + width < yPos + height) {
			original.drawPixmap(gradientMap, 0, 0, original.getWidth(),
					original.getHeight(), xPos, yPos - (width - height) / 2,
					width, width);
		} else {
			original.drawPixmap(gradientMap, 0, 0, original.getWidth(),
					original.getHeight(), xPos - (height - width) / 2, yPos,
					height, height);
		}

		gradientMap.dispose();
	}

	protected int getTextureX() {
		return textureX;
	}

	protected int getTextureY() {
		return textureY;
	}

	protected int getTextureWidth() {
		return textureWidth;
	}

	protected int getTextureHeight() {
		return textureHeight;
	}

	protected void setTextureWidth(int width) {
		this.textureWidth = width;
	}

	protected void setTextureHeight(int height) {
		this.textureHeight = height;
	}

	protected void setTextureX(int x) {
		this.textureX = x;
	}

	protected void setTextureY(int y) {
		this.textureY = y;
	}

	protected void alignTextureSize(int newWidth, int newHeight) {
		if (newWidth > getTextureWidth()) {
			setTextureWidth(newWidth);
		}

		if (newHeight > getTextureHeight()) {
			setTextureHeight(newHeight);
		}
	}

}
