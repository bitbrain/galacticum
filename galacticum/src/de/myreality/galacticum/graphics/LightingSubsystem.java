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

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.math.Vector2;

import de.myreality.galacticum.core.context.Context;
import de.myreality.galacticum.core.entities.Entity;
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
	
	Light light;
	
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

		//handler.setShadows(false);
		handler.setAmbientLight(0.4f, 0.1f, 0.6f, 0.2f);
		PointLight light = new PointLight(handler, 420);
		light.setDistance(900);
		light.setColor(0.4f, 0.2f, 0.7f, 0.4f);
		light.setPosition(new Vector2());
		light = new PointLight(handler, 420);
		light.setPosition(200, -300);
		light.setDistance(900);
		light.setColor(0.6f, 0.2f, 0.2f, 0.4f);
		
		light = new PointLight(handler, 420);
		light.setPosition(400, 100);
		light.setDistance(1300);
		light.setColor(0.2f, 0.2f, 0.7f, 0.6f);

		this.light = light;
		
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#update(float)
	 */
	@Override
	public void update(float delta) {
		
		GameCamera cam  = context.getCamera();
		Entity e = context.getPlayer().getCurrentShip();
		light.setPosition(e.getX(), e.getY());
		handler.setCombinedMatrix(cam.getCombinedMatrix());
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

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.WorldListener#onAddEntity(de.myreality.galacticum.core.entities.Entity)
	 */
	@Override
	public void onAddEntity(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.WorldListener#onRemoveEntity(de.myreality.galacticum.core.entities.Entity)
	 */
	@Override
	public void onRemoveEntity(Entity entity) {
		// TODO Auto-generated method stub
		
	}
	
}
