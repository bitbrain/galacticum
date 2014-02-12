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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;

import de.myreality.galacticum.biomes.BiomeModule;
import de.myreality.galacticum.context.Context;
import de.myreality.galacticum.core.GameLight;
import de.myreality.galacticum.core.WorldListener;
import de.myreality.galacticum.entities.Entity;
import de.myreality.galacticum.modules.ActiveModule;
import de.myreality.galacticum.modules.Module;
import de.myreality.galacticum.modules.ModuleException;
import de.myreality.galacticum.physics.Box2DPhysicsModule;
import de.myreality.galacticum.player.Player;
import de.myreality.galacticum.player.PlayerModule;
import de.myreality.galacticum.util.ColorProvider;
import de.myreality.galacticum.util.GameColor;
import de.myreality.galacticum.util.Updateable;

/**
 * Subsystem to handle lighting
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
@ActiveModule(dependsOn = { BiomeModule.class, Box2DPhysicsModule.class, Player.class } )
public class LightingModule implements Module, WorldListener, Updateable {
	
	private RayHandler handler;
	
	private Context context;
	
	Light playerLight;
	
	private Map<GameLight, Light> lightMap;
	
	private Set<GameLight> requests;
	
	private ColorProvider ambientColorProvider;
	
	public LightingModule() {
		this.lightMap = new HashMap<GameLight, Light>();
		this.requests = new HashSet<GameLight>();
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
	public void load(Context context) throws ModuleException {
		
		BiomeModule biomeModule = context.getModule(BiomeModule.class);
		final Box2DPhysicsModule physics = context.getModule(Box2DPhysicsModule.class);
		final PlayerModule playerModule = context.getModule(PlayerModule.class);
		this.context = context;		
		this.ambientColorProvider = biomeModule.getAmbientColorProvider();
		System.out.println(ambientColorProvider.getColor());
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				
				handler = new RayHandler(physics.getWorld());
				RayHandler.useDiffuseLight(true);
				handler.setAmbientLight(0.2f, 0.2f, 0.3f, 0.8f);
				PointLight light = new PointLight(handler, 1000);
				light.setDistance(170);
				light.setColor(0.6f, 0.6f, 0.6f, 0.5f);
				Entity ship = playerModule.getPlayer().getCurrentShip();
				Body body = physics.getBody(ship);
				light.attachToBody(body, ship.getWidth() / 2f, ship.getHeight() / 2f);
			}

		});
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#update(float)
	 */
	@Override
	public void update(float delta) {
		
		if (!requests.isEmpty()) {
			for (GameLight request : requests) {
				addInternal(request);
			}
			requests.clear();
		}
		
		GameColor ambient = ambientColorProvider.getColor();
		handler.setAmbientLight(ambient.r, ambient.g, ambient.b, ambient.a);

		GameCamera cam  = context.getCamera();
		handler.setCombinedMatrix(cam.getCombinedMatrix(), cam.getX() + cam.getWidth() / 2f, cam.getY() + cam.getHeight() / 2f, cam.getWidth(),cam.getHeight());
		handler.updateAndRender();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.subsystem.Subsystem#shutdown()
	 */
	@Override
	public void dispose() {
		handler.dispose();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.WorldListener#onAddEntity(de.myreality.galacticum.core.entities.Entity)
	 */
	@Override
	public void onAddEntity(Entity entity) { }

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.WorldListener#onRemoveEntity(de.myreality.galacticum.core.entities.Entity)
	 */
	@Override
	public void onRemoveEntity(Entity entity) { }

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.WorldListener#onAddLight(de.myreality.galacticum.core.GameLight)
	 */
	@Override
	public void onAddLight(GameLight light) {
		
		if (handler != null) {
			addInternal(light);
		} else {
			if (!requests.contains(light)) {
				requests.add(light);
			}
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.WorldListener#onRemoveLight(de.myreality.galacticum.core.GameLight)
	 */
	@Override
	public void onRemoveLight(GameLight gameLight) {		
		
		Light light = lightMap.remove(gameLight);
		
		if (light != null) {
			requests.remove(gameLight);
			light.remove();
		}
	}
	
	private void addInternal(GameLight gameLight) {
		
		Light light = null;
		
		switch (gameLight.getType()) {
		case CONE:
			break;
		case DIRECTIONAL:
			break;
		case POINT:
			light = new PointLight(handler, gameLight.getNumberOfRays());
			break;
		default:
			break;
		
		}
		
		if (light != null) {
			light.setPosition(gameLight.getX(), gameLight.getY());
			light.setDistance(gameLight.getRadius());
			GameColor clr = gameLight.getColor();
			light.setColor(clr.r, clr.g, clr.b, clr.a);
			lightMap.put(gameLight, light);
		}				
	}
	
}