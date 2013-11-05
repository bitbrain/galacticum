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
package de.myreality.galacticum.core.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Simple implementation of {@see Entity}
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SimpleEntity implements Entity {

	
	// ===========================================================
	// Constants
	// ===========================================================
	
	private static final long serialVersionUID = 1L;
	
	private static int ids = 0;

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Shape shape;
	
	private EntityType type;
	
	private int id;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleEntity(EntityType type, float width, float height) {
		shape = new SimpleShape(width, height);
		this.id = ids++;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.IDProvider#getID()
	 */
	@Override
	public String getID() {
		return getClass().getSimpleName() + id;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Entity#getX()
	 */
	@Override
	public float getX() {
		return shape.getX();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Entity#getY()
	 */
	@Override
	public float getY() {
		return shape.getY();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Entity#getWidth()
	 */
	@Override
	public float getWidth() {
		return shape.getWidth();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Entity#getHeight()
	 */
	@Override
	public float getHeight() {
		return shape.getHeight();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Entity#getType()
	 */
	@Override
	public EntityType getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Entity#draw(float, com.badlogic.gdx.graphics.g2d.SpriteBatch)
	 */
	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Entity#update(float)
	 */
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Shape#setX(float)
	 */
	@Override
	public void setX(float x) {
		shape.setX(x);
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Shape#setY(float)
	 */
	@Override
	public void setY(float y) {
		shape.setY(y);
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Shape#collidesWith(de.myreality.galacticum.core.entities.Shape)
	 */
	@Override
	public boolean collidesWith(Shape other) {
		return shape.collidesWith(other);
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Entity#setRotation(float)
	 */
	@Override
	public void setRotation(float rotation) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Entity#getRotation()
	 */
	@Override
	public float getRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
