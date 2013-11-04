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
package de.myreality.galacticum.core;

import de.myreality.galacticum.core.chunks.ContentArea;
import de.myreality.galacticum.core.chunks.ContentListener;
import de.myreality.galacticum.core.entities.Entity;
import de.myreality.galacticum.core.entities.SharedSpaceShipFactory;
import de.myreality.galacticum.core.entities.SpaceShipFactory;
import de.myreality.galacticum.core.entities.SpaceShipType;
import de.myreality.galacticum.util.Seed;

/**
 * Handles the content of the world
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class ContentHandler implements ContentListener {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Seed seed;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ContentHandler(Seed seed) {
		this.seed = seed;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	


	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.ContentListener#onCreate(de.myreality.galacticum.core.entities.ContentArea)
	 */
	@Override
	public void onCreate(ContentArea area) {
		
		SpaceShipFactory f = SharedSpaceShipFactory.getInstance();
		
		final int AMOUNT = 500;
		
		for (int i = 0; i < AMOUNT; ++i) {
			
			float x = (float) (area.getX() + Math.random() * area.getWidth());
			float y = (float) (area.getY() + Math.random() * area.getHeight());
			
			Entity entity = f.create(x, y, SpaceShipType.FIGHTER, seed);
			area.add(entity);			
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
