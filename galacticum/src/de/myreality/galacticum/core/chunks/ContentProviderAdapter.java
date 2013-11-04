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

import de.myreality.chunx.ContentProvider;
import de.myreality.galacticum.core.World;

/**
 * Adapter to convert {@see GameContainer} to content providers
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class ContentProviderAdapter implements ContentProvider {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private World target;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ContentProviderAdapter(World target) {
		this.target = target;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	/* (non-Javadoc)
	 * @see de.myreality.chunx.ContentProvider#add(java.lang.Object)
	 */
	@Override
	public void add(Object object) {
		target.add(object);		
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.ContentProvider#getContent()
	 */
	@Override
	public Collection<Object> getContent() {
		return target.getEntities();
	}

	/* (non-Javadoc)
	 * @see de.myreality.chunx.ContentProvider#remove(java.lang.Object)
	 */
	@Override
	public void remove(Object object) {
		target.remove(object);		
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
