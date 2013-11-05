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

import java.util.Collection;
import java.util.Iterator;

import de.myreality.chunx.Chunk;
import de.myreality.chunx.ChunkListener;
import de.myreality.chunx.ChunkTarget;

/**
 * Adapts chunks to content areas
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class ContentAreaAdapter implements ContentArea, Chunk {
	
	private static final long serialVersionUID = 1L;
	
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

	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Indexable#getIndexX()
	 */
	@Override
	public int getIndexX() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Indexable#getIndexY()
	 */
	@Override
	public int getIndexY() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<ChunkTarget> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Observable#addListener(java.lang.Object)
	 */
	@Override
	public void addListener(ChunkListener arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Observable#getListeners()
	 */
	@Override
	public Collection<ChunkListener> getListeners() {
		return chunk.getListeners();
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Observable#hasListener(java.lang.Object)
	 */
	@Override
	public boolean hasListener(ChunkListener arg0) {
		return chunk.hasListener(arg0);
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Observable#removeListener(java.lang.Object)
	 */
	@Override
	public void removeListener(ChunkListener arg0) {
		chunk.removeListener(arg0);
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Boundable#contains(float, float)
	 */
	@Override
	public boolean contains(float arg0, float arg1) {
		return chunk.contains(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Boundable#getBottom()
	 */
	@Override
	public float getBottom() {
		return chunk.getBottom();
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Boundable#getLeft()
	 */
	@Override
	public float getLeft() {
		return chunk.getLeft();
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Boundable#getRight()
	 */
	@Override
	public float getRight() {
		return chunk.getRight();
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.util.Boundable#getTop()
	 */
	@Override
	public float getTop() {
		return chunk.getTop();
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.Chunk#add(de.myreality.chunx.ChunkTarget)
	 */
	@Override
	public void add(ChunkTarget arg0) {
		chunk.add(arg0);
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.Chunk#clear()
	 */
	@Override
	public void clear() {
		chunk.clear();
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.Chunk#contains(de.myreality.chunx.ChunkTarget)
	 */
	@Override
	public boolean contains(ChunkTarget arg0) {
		return chunk.contains(arg0);
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.Chunk#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return chunk.isEmpty();
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.Chunk#retrieve()
	 */
	@Override
	public ChunkTarget retrieve() {
		return chunk.retrieve();
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.Chunk#size()
	 */
	@Override
	public int size() {
		return chunk.size();
	}

	

}
