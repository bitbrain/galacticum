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

import com.badlogic.gdx.math.Vector2;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import de.myreality.galacticum.core.context.Context;
import de.myreality.galacticum.core.subsystem.ProgressListener;
import de.myreality.galacticum.core.subsystem.Subsystem;
import de.myreality.galacticum.core.subsystem.SubsystemException;
import de.myreality.galacticum.physics.PhysicSubsystem;

/**
 * Subsystem to handle lighting
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class LightingSubsystem implements Subsystem {
	
	private RayHandler handler;
	
	private Context context;
	
	public LightingSubsystem() {
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Nameable#getName()
	 */
	@Override
	public String getName() {
		return "lighting";
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#start()
	 */
	@Override
	public void start() throws SubsystemException {
		
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#onEnter(de.myreality.galacticum.core.context.Context)
	 */
	@Override
	public void onEnter(Context context) {
		this.context = context;
		
		PhysicSubsystem physics = context.getSubsystem(PhysicSubsystem.class);
		handler = new RayHandler(physics.getWorld());
		handler.setAmbientLight(0.2f, 0.05f, 0.2f, 0.5f);
		PointLight light = new PointLight(handler, 20);
		light.setDistance(400);
		light.setColor(0.4f, 0.2f, 0.7f, 0.5f);
		light.setPosition(new Vector2());
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#update(float)
	 */
	@Override
	public void update(float delta) {
		
		GameCamera cam  = context.getCamera();
		
		handler.setCombinedMatrix(cam.getCombinedMatrix(), 0, 0, cam.getWidth(), cam.getY());
		handler.update();
		handler.render();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#shutdown()
	 */
	@Override
	public void shutdown() {
		handler.dispose();
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
	
}
