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
package de.myreality.galacticum.physics;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import de.myreality.galacticum.core.context.Context;
import de.myreality.galacticum.core.entities.Entity;
import de.myreality.galacticum.core.subsystem.ProgressListener;
import de.myreality.galacticum.core.subsystem.Subsystem;
import de.myreality.galacticum.core.subsystem.SubsystemException;

/**
 * Subsystem for handling physics
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class PhysicSubsystem implements Subsystem {

	private World world;

	public static int POSITION_ITERATIONS = 5;

	public static int VELOCITY_ITERATIONS = 5;
	
	private Map<Entity, Body> bodyMap;

	public PhysicSubsystem() {
		world = new World(new Vector2(), false);
		bodyMap = new HashMap<Entity, Body>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Nameable#getName()
	 */
	@Override
	public String getName() {
		return "physics";
	}

	public World getWorld() {
		return world;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#start()
	 */
	@Override
	public void start() throws SubsystemException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.subsystem.Subsystem#onEnter(de.myreality
	 * .galacticum.core.context.Context)
	 */
	@Override
	public void onEnter(Context context) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#update(float)
	 */
	@Override
	public void update(float delta) {
		synchronized (world) {
			world.step(delta, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#shutdown()
	 */
	@Override
	public void shutdown() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.subsystem.Subsystem#addProgressListener(
	 * de.myreality.galacticum.core.subsystem.ProgressListener)
	 */
	@Override
	public void addProgressListener(ProgressListener listener) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.subsystem.Subsystem#removeProgressListener
	 * (de.myreality.galacticum.core.subsystem.ProgressListener)
	 */
	@Override
	public void removeProgressListener(ProgressListener listener) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.WorldListener#onAddEntity(de.myreality.
	 * galacticum.core.entities.Entity)
	 */
	@Override
	public void onAddEntity(Entity entity) {
		
		synchronized (world) {	
			// First we create a body definition
			BodyDef bodyDef = new BodyDef();
			// We set our body to dynamic, for something like ground which doesn't
			// move we would set it to StaticBody
			bodyDef.type = BodyType.DynamicBody;
			// Set our body's starting position in the world
			bodyDef.position.set(entity.getX(), entity.getY());
	
			// Create our body in the world using our body definition
			Body body = world.createBody(bodyDef);
			
			synchronized (body) {
				body.setUserData(entity);
		
				// Create a circle shape and set its radius to 6
				PolygonShape shape = new PolygonShape();
		
				float[] vertices = new float[] { 0, 0, entity.getWidth(), 0,
						entity.getWidth(), entity.getHeight(), 0, entity.getHeight() };
		
				shape.set(vertices);
		
				// Create a fixture definition to apply our shape to
				FixtureDef fixtureDef = new FixtureDef();
				fixtureDef.shape = shape;
				fixtureDef.density = 0.5f;
				fixtureDef.friction = 0.4f;
				fixtureDef.restitution = 0.6f; // Make it bounce a little bit
		
				// Create our fixture and attach it to the body
				body.createFixture(fixtureDef);
		
				// Remember to dispose of any shapes after you're done with them!
				// BodyDef and FixtureDef don't need disposing, but shapes do.
				shape.dispose();
			
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.core.WorldListener#onRemoveEntity(de.myreality
	 * .galacticum.core.entities.Entity)
	 */
	@Override
	public void onRemoveEntity(Entity entity) {
		
		Body body = bodyMap.get(entity);

		if (body != null) {
			synchronized (body) {			
				world.destroyBody(body);			
			}
		}
	}

}
