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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

import de.myreality.galacticum.core.subsystem.ProgressListener;
import de.myreality.galacticum.core.subsystem.Subsystem;
import de.myreality.galacticum.core.subsystem.SubsystemException;

/**
 * Is responsible for providing a camera
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class CameraSystem implements Subsystem {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private OrthographicCamera camera;
	
	private float viewportWidth;
	
	private float viewportHeight;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public CameraSystem(float viewportWidth, float viewportHeight) {
		this.viewportWidth = viewportWidth;
		this.viewportHeight = viewportHeight;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public Camera getCamera() {
		return camera;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Nameable#getName()
	 */
	@Override
	public String getName() {
		return "Camera system";
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#start()
	 */
	@Override
	public void start() throws SubsystemException {
		
		if (viewportWidth <= 0 || viewportHeight <= 0) {
			throw new SubsystemException("Viewport of " + viewportWidth + "x" + viewportHeight + " is not allowed.");
		}
		
		camera = new OrthographicCamera(viewportWidth, viewportHeight);
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#update(float)
	 */
	@Override
	public void update(float delta) {
		float width = Gdx.graphics.getWidth() * 2;
		float height = Gdx.graphics.getHeight();
		Gdx.gl.glViewport((int) (-width / 2), 0, (int) width, (int) height * 2);
		camera.update();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#shutdown()
	 */
	@Override
	public void shutdown() {
		camera = null;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#addProgressListener(de.myreality.galacticum.core.subsystem.ProgressListener)
	 */
	@Override
	public void addProgressListener(ProgressListener listener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#removeProgressListener(de.myreality.galacticum.core.subsystem.ProgressListener)
	 */
	@Override
	public void removeProgressListener(ProgressListener listener) {
		// TODO Auto-generated method stub

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
