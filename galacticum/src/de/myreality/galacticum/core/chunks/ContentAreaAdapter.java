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
package de.myreality.galacticum.core.chunks;

import de.myreality.chunx.Chunk;

/**
 * Adapts chunks to content areas
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class ContentAreaAdapter implements ContentArea {
	
	private Chunk chunk;
	
	public ContentAreaAdapter(Chunk chunk) {
		this.chunk = chunk;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.ContentArea#add(de.myreality.galacticum.core.entities.ContentTarget)
	 */
	@Override
	public void add(ContentTarget target) {
		chunk.add(new ContentTargetAdapter(target));
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.ContentArea#getX()
	 */
	@Override
	public float getX() {
		return chunk.getX();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.ContentArea#getY()
	 */
	@Override
	public float getY() {
		return chunk.getY();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.ContentArea#getWidth()
	 */
	@Override
	public float getWidth() {
		return chunk.getWidth();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.ContentArea#getHeight()
	 */
	@Override
	public float getHeight() {
		return chunk.getHeight();
	}

	

}
