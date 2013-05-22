package de.myreality.galacticum.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import de.myreality.galacticum.core.chunks.Chunk;
import de.myreality.galacticum.core.chunks.ChunkManager;
import de.myreality.galacticum.core.chunks.ChunkSystem;
import de.myreality.galacticum.core.chunks.ConcurrentChunkManager;
import de.myreality.galacticum.core.lighting.Light;
import de.myreality.galacticum.core.lighting.LightingSystem;
import de.myreality.galacticum.core.physics.PhysicSystem;
import de.myreality.galacticum.core.rendering.RenderSystem;
import de.myreality.galacticum.exceptions.EntryNotFoundException;
import de.myreality.galacticum.util.AbstractHashProvider;
import de.myreality.galacticum.util.Background;
import de.myreality.galacticum.util.Debugger;
import de.myreality.galacticum.util.Seed;

/**
 * Basic universe implementation. Is not saved as file. Only the Chunk system is
 * needed to load all necessary content
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class BasicUniverse extends AbstractHashProvider implements Universe {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final String PLAYER_FILE_NAME = "player.dat";

	// ===========================================================
	// Fields
	// ===========================================================

	// Universe camera
	private Camera camera;

	// Chunk system where the content comes from
	private ChunkSystem chunkSystem;

	// Render system that does the rendering of the world
	private RenderSystem renderSystem;

	// Physic system that makes physics on each loaded entity
	private PhysicSystem physicSystem;

	// Lighting system that provides object lighting
	private LightingSystem lightingSystem;

	// List of all world objects
	private List<Entity> entities;

	// List of all lights
	private List<Light> lights;

	// Seed to generate the content
	private Seed seed;

	// Name of the universe
	private String name;

	// ID of the universe
	private String id;

	// Path of the universe
	private String path;

	private Background background;

	private ChunkManager chunkManager;

	private SampleEntity playerEntity;

	private Light playerLight;

	// ===========================================================
	// Constructors
	// ===========================================================

	public BasicUniverse(String id, String name, String seed, String path) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.seed = new Seed(seed);
		this.entities = new CopyOnWriteArrayList<Entity>();
		this.lights = new CopyOnWriteArrayList<Light>();
		playerEntity = loadPlayer();
		playerLight = new Light(0f, 0f, 800f);
		playerLight.setColor(new Color(0.2f, 0.2f, 0.4f));
		addEntity(playerEntity);
		addLight(playerLight);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public Camera getCamera() {
		return camera;
	}

	@Override
	public void setBackground(Background background) {
		this.background = background;
	}

	@Override
	public List<Entity> getEntities() {
		return entities;
	}

	@Override
	public void addEntity(Entity entity) {
		entities.add(entity);
		if (physicSystem != null) {
			physicSystem.addBody(entity);
		}
	}

	@Override
	public void setPhysicSystem(PhysicSystem physicSystem) {
		this.physicSystem = physicSystem;
		
		if (physicSystem != null) {
			for (Entity entity : entities) {
				
				try {
					physicSystem.addBody(entity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public PhysicSystem getPhysicSystem() {
		return physicSystem;
	}

	@Override
	public void setChunkSystem(ChunkSystem chunkSystem) {
		this.chunkSystem = chunkSystem;
	}

	@Override
	public ChunkSystem getChunkSystem() {
		return chunkSystem;
	}

	@Override
	public void setLightingSystem(LightingSystem lightingSystem) {
		this.lightingSystem = lightingSystem;

		if (getRenderSystem() != null) {
			renderSystem.setLightingSystem(lightingSystem);
		}
	}

	@Override
	public LightingSystem getLightingSystem() {
		return lightingSystem;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public Seed getSeed() {
		return seed;
	}

	@Override
	public void setRenderSystem(RenderSystem renderSystem) {
		this.renderSystem = renderSystem;

		if (getLightingSystem() != null) {
			renderSystem.setLightingSystem(lightingSystem);
		}

	}

	@Override
	public RenderSystem getRenderSystem() {
		return renderSystem;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public void addEntities(Collection<? extends Entity> entities) {
		this.entities.addAll(entities);
		if (renderSystem != null) {
			renderSystem.removeEntities(entities);
		}
		if (physicSystem != null) {
			for (Entity entity : entities) {
				physicSystem.addBody(entity);
			}
		}
	}

	@Override
	public void removeEntity(Entity entity) {
		this.entities.remove(entity);
		if (renderSystem != null) {
			renderSystem.removeEntity(entity);
		}
		if (physicSystem != null) {
				physicSystem.removeBody(entity);
		}
	}

	@Override
	public void removeEntities(Collection<? extends Entity> entities) {
		this.entities.removeAll(entities);
		if (physicSystem != null) {
			for (Entity entity : entities) {
				physicSystem.removeBody(entity);
			}
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		if (camera != null) {
			camera.bind(g);
		}

		if (background != null) {
			background.render(gc, sbg, g);
		}

		if (renderSystem != null) {
			renderSystem.render(gc, sbg, g);
		}

		if (camera != null) {
			camera.unbind(g);
		}

		if (Debugger.getInstance().isEnabled()) {
			g.setColor(Color.white);
			g.drawString("Objects: " + renderSystem.getRenderObjects().size()
					+ "/" + getEntities().size(), 10, 30);
			g.drawString("Lights: " + lightingSystem.getActiveLights().size()
					+ "/" + lights.size(), 10, 50);

			try {
				Chunk active = chunkSystem.getActiveChunk();
				g.setColor(Color.green);
				g.drawString(
						"Chunk [" + active.getIndexX() + "|"
								+ active.getIndexY() + "]", 10, 90);
			} catch (EntryNotFoundException e) {
				g.drawString("No active chunk", 10, 70);
			}

			g.setColor(Color.green);
			g.drawString("Seed: " + seed, 10, gc.getHeight() - 30);
			g.setColor(Color.white);
			g.drawString("Universe: " + this.getName() + " [" + getID() + "]",
					10, gc.getHeight() - 50);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		if (physicSystem != null) {
			physicSystem.update(gc, sbg, delta);
		}
		
		if (background != null) {
			background.update(gc, sbg, delta);
		}

		if (camera != null && playerEntity != null) {
			Input input = gc.getInput();

			float velocity = 0.20f * delta;
			float scaleFactor = 0.001f * delta;
			if (input.isKeyDown(Input.KEY_Q)) {
				camera.zoomIn(scaleFactor);
			}

			if (input.isKeyDown(Input.KEY_E)) {
				camera.zoomOut(scaleFactor);
			}

			if (input.isKeyDown(Input.KEY_W)) {
				playerEntity.setPosition(playerEntity.getLeft(),
						playerEntity.getTop() - velocity);
			} else if (input.isKeyDown(Input.KEY_S)) {
				playerEntity.setPosition(playerEntity.getLeft(),
						playerEntity.getTop() + velocity);
			}

			if (input.isKeyDown(Input.KEY_A)) {
				playerEntity.setPosition(playerEntity.getLeft() - velocity,
						playerEntity.getTop());
			} else if (input.isKeyDown(Input.KEY_D)) {
				playerEntity.setPosition(playerEntity.getLeft() + velocity,
						playerEntity.getTop());
			}

			float playerCenterX = playerEntity.getLeft()
					+ playerEntity.getWidth() / 2f;
			float playerCenterY = playerEntity.getTop()
					+ playerEntity.getHeight() / 2f;

			playerLight.setPosition(playerCenterX, playerCenterY);

			if (renderSystem != null) {
				for (Entity entity : entities) {
					renderSystem.update(entity, delta);
				}
			}
			camera.update(gc, sbg, delta);
		}
	}

	@Override
	public void start(GameContainer gc) {
 
		if (chunkManager != null) {
			chunkManager.shutdown();
		} else {
			chunkManager = new ConcurrentChunkManager(getChunkSystem());
		}		

		chunkManager.start();
	}

	@Override
	public void shutdown() {

		if (playerEntity != null) {
			lights.remove(playerLight);
			entities.remove(playerEntity);
			savePlayer();
			playerEntity = null;
			playerLight = null;
		}

		chunkManager.shutdown();

		entities.clear();
		lights.clear();

		if (lightingSystem != null) {
			lightingSystem.shutdown();
		}

		if (renderSystem != null) {
			renderSystem.shutdown();
		}

		if (physicSystem != null) {
			physicSystem.shutdown();
		}

		entities = null;
		lights = null;
		chunkManager = null;
		chunkSystem = null;
		renderSystem = null;
		physicSystem = null;
		background = null;
		camera = null;
		
		Log.info("Universe shutdown has been executed.");
	}

	private void savePlayer() {
		File file = new File(path + PLAYER_FILE_NAME);
		File parent = file.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}

		try {
			FileOutputStream fileStream = new FileOutputStream(file);
			ObjectOutputStream outputStream = new ObjectOutputStream(fileStream);
			outputStream.writeObject(playerEntity);
			outputStream.close();
			fileStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private SampleEntity loadPlayer() {
		File playerFile = new File(path + PLAYER_FILE_NAME);
		SampleEntity entity = null;

		if (playerFile.exists()) {
			try {
				InputStream fileStream = new FileInputStream(playerFile);
				ObjectInputStream objectStream = new ObjectInputStream(
						fileStream);
				entity = (SampleEntity) objectStream.readObject();
				objectStream.close();
				fileStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			entity = new SampleEntity(0, 0);
			entity.setMode(EntityMode.PLAYER);
		}
		return entity;
	}

	@Override
	public void setCamera(Camera camera) {
		this.camera = camera;		
		camera.alignToBoundable(playerEntity);
		camera.setFocusBoundable(playerEntity);
	}

	@Override
	public void addLights(Collection<? extends Light> lights) {
		this.lights.addAll(lights);
	}

	@Override
	public void addLight(Light light) {
		lights.add(light);
	}

	@Override
	public void removeLight(Light light) {
		lights.remove(light);
	}

	@Override
	public void removeLights(Collection<? extends Light> lights) {
		this.lights.removeAll(lights);
	}

	@Override
	public List<Light> getLights() {
		return lights;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.core.Universe#getBackground()
	 */
	@Override
	public Background getBackground() {
		return background;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.AbstractHashProvider#calculateHash ()
	 */
	@Override
	protected long calculateHash() {
		return seed.hashCode();
	}
}
