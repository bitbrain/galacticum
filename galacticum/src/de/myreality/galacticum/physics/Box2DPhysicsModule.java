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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import de.myreality.galacticum.context.Context;
import de.myreality.galacticum.core.SimpleWorldListener;
import de.myreality.galacticum.core.WorldModule;
import de.myreality.galacticum.core.WorldSystemListener;
import de.myreality.galacticum.entities.Entity;
import de.myreality.galacticum.entities.Shape;
import de.myreality.galacticum.entities.Shape.ShapeListener;
import de.myreality.galacticum.modules.ProgressListener;
import de.myreality.galacticum.modules.Module;
import de.myreality.galacticum.modules.ModuleException;
import de.myreality.galacticum.util.VerticesProvider;

/**
 * Subsystem for handling physics
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class Box2DPhysicsModule extends SimpleWorldListener implements Module, WorldSystemListener, ShapeListener {

	private World world;

	public static int POSITION_ITERATIONS = 30;

	public static int VELOCITY_ITERATIONS = 30;

	private Map<Entity, Body> bodyMap;

	private List<Entity> removalList;

	private List<Entity> addList;
	
	private VerticesProvider verticesProvider;

	public Box2DPhysicsModule(VerticesProvider provider) {
		world = new World(new Vector2(0f, 0f), false);
		bodyMap = new HashMap<Entity, Body>();
		removalList = new ArrayList<Entity>();
		addList = new ArrayList<Entity>();
		this.verticesProvider = provider;
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
	public void start() throws ModuleException {

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
		WorldModule system = context.getModule(WorldModule.class);
		if (system != null) {
			system.addListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#update(float)
	 */
	@Override
	public void update(float delta) {

		synchronized (world) {

			world.step(1 / 60f, VELOCITY_ITERATIONS, POSITION_ITERATIONS);

			if (!world.isLocked()) {

				for (Entity e : removalList) {
					onRemoveEntity(e);
				}

				removalList.clear();

				for (Entity e : addList) {
					onAddEntity(e);
				}

				addList.clear();
			}

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
		
		entity.addListener(this);

		if (!world.isLocked()) {
			synchronized (world) {
				// First we create a body definition
				BodyDef bodyDef = new BodyDef();
				
				// We set our body to dynamic, for something like ground which
				// doesn't
				// move we would set it to StaticBody
				bodyDef.type = BodyType.DynamicBody;
				// Set our body's starting position in the world
				bodyDef.position.set(entity.getX(), entity.getY());
				bodyDef.angle = MathUtils.degreesToRadians * entity.getRotation();
				
				// Create our body in the world using our body definition
				Body body = world.createBody(bodyDef);

				bodyMap.put(entity, body);
				body.setUserData(entity);
				
				// Create a fixture definition to apply our shape to
				FixtureDef fixtureDef = new FixtureDef();
				com.badlogic.gdx.physics.box2d.Shape shape = getShape(entity);
				
				fixtureDef.shape = shape;
				fixtureDef.density = 0.5f;
				fixtureDef.friction = 0.4f;
				fixtureDef.restitution = 0.6f; // Make it bounce a little bit

				// Create our fixture and attach it to the body
				body.createFixture(fixtureDef);

				// Remember to dispose of any shapes after you're done with
				// them!
				// BodyDef and FixtureDef don't need disposing, but shapes do.
				shape.dispose();
			}
		} else {
			addList.add(entity);
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
		
		entity.removeListener(this);

		synchronized (world) {

			if (!world.isLocked()) {
				Body body = bodyMap.get(entity);

				synchronized (body) {
					world.destroyBody(body);
				}
			} else {
				removalList.add(entity);
			}
		}
	}

	private com.badlogic.gdx.physics.box2d.Shape getShape(Entity entity) {
		switch (entity.getType()) {
			
			case PLANET:
				CircleShape circle = new CircleShape();
				circle.setRadius(entity.getWidth() / 2f);
				circle.setPosition(new Vector2(entity.getWidth() / 2f, entity.getHeight() / 2f));
				return circle;			
			default:
				PolygonShape poly = new PolygonShape();	
				poly.set(verticesProvider.getVerticesFor(entity));
				return poly;		
		}
		
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.WorldSystemListener#onUpdateEntity(de.myreality.galacticum.core.entities.Entity, float)
	 */
	@Override
	public void onUpdateEntity(Entity entity, float delta) {
		
		Body body = bodyMap.get(entity);
		
		if (body != null && (entity.getX() != body.getPosition().x || entity.getY() != body.getPosition().y)) {
			entity.setX(body.getPosition().x);
			entity.setY(body.getPosition().y);
			entity.setRotation(MathUtils.radiansToDegrees * body.getAngle());
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Shape.ShapeListener#onSetX(float)
	 */
	@Override
	public void onSetX(Shape shape) {
		
		Body body = bodyMap.get(shape);
		
		if (body != null && shape.getX() != body.getPosition().x) {
			//body.setTransform(shape.getX(), shape.getY(), MathUtils.degreesToRadians * shape.getRotation());
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Shape.ShapeListener#onSetY(float)
	 */
	@Override
	public void onSetY(Shape shape) {
		Body body = bodyMap.get(shape);
		
		if (body != null && shape.getY() != body.getPosition().y) {
			//body.setTransform(shape.getX(), shape.getY(), MathUtils.degreesToRadians * shape.getRotation());
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.entities.Shape.ShapeListener#onSetRotation(float)
	 */
	@Override
	public void onSetRotation(Shape shape) {
		Body body = bodyMap.get(shape);
		
		if (body != null) {
			//body.setTransform(shape.getX(), shape.getY(), MathUtils.degreesToRadians * shape.getRotation());
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.entities.Shape.ShapeListener#onMove(float, float)
	 */
	@Override
	public void onMove(float x, float y, Shape shape) {
		
		Body body = bodyMap.get(shape);
		
		if (body != null) {
			body.applyLinearImpulse(x * 100, y * 100, shape.getWidth() / 2, 0, true);
			
		}
	}
}
