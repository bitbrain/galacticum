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
package de.myreality.galacticum.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import de.myreality.galacticum.MetaData;
import de.myreality.galacticum.Resources;
import de.myreality.galacticum.core.chunks.ChunkSubsystem;
import de.myreality.galacticum.core.context.Context;
import de.myreality.galacticum.graphics.GameCamera;

/**
 * This stage shows up important debug information. Debugging can be enabled or
 * disabled.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class DebugStage extends Stage implements Debugable {

	// ===========================================================
	// Constants
	// ===========================================================
	
	private final int PADDING = 10;

	// ===========================================================
	// Fields
	// ===========================================================

	private boolean debug;
	
	private Label lblCaption, lblFps, lblJVM, lblObjects, lblCameraPos, lblViewport,
	              lblSeed, lblWorld, lblChunk;
	
	private Context context;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * @param width
	 * @param height
	 * @param keepAspectRatio
	 */
	public DebugStage(float width, float height, boolean debug, Context context) {
		super(width, height, false);
		this.debug = debug;
		this.context = context;
		MetaData meta = Resources.META_DATA;
		
		// TOP
		
		lblCaption = new Label(meta.getName() + " " + meta.getVersion() + meta.getPhase(), Resources.STYLE_LABEL_DEBUG);
		lblCaption.setPosition(PADDING, Gdx.graphics.getHeight() - PADDING - lblCaption.getHeight());
		lblCaption.setColor(Resources.COLOR_DEBUG_INFO);
		addActor(lblCaption);
		
		lblFps = new Label("FPS: 0", Resources.STYLE_LABEL_DEBUG);
		addActor(lblFps);
		
		lblJVM = new Label("JVM: 0", Resources.STYLE_LABEL_DEBUG);
		addActor(lblJVM);
		
		lblCameraPos = new Label("Camera:", Resources.STYLE_LABEL_DEBUG);
		addActor(lblCameraPos);
		
		lblViewport = new Label("Viewport:", Resources.STYLE_LABEL_DEBUG);
		addActor(lblViewport);
		
		// BOTTOM
		
		lblObjects = new Label("Objects: 0", Resources.STYLE_LABEL_DEBUG);
		addActor(lblObjects);
		
		lblSeed = new Label("Seed: ", Resources.STYLE_LABEL_DEBUG);	
		addActor(lblSeed);
		
		lblWorld = new Label("World: ", Resources.STYLE_LABEL_DEBUG);	
		addActor(lblWorld);
		
		lblChunk = new Label("Chunk: ", Resources.STYLE_LABEL_DEBUG);	
		addActor(lblChunk);
		
		updateLabels();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.ui.Debugable#setDebugEnabled(boolean)
	 */
	@Override
	public void setDebugEnabled(boolean debug) {
		this.debug = debug;
	}

	@Override
	public void draw() {
		if (isDebugEnabled()) {
			
			updateLabels();
			super.draw();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.ui.Debugable#isDebugEnabled()
	 */
	@Override
	public boolean isDebugEnabled() {
		return debug;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private void updateLabels() {
		lblCaption.setPosition(PADDING, Gdx.graphics.getHeight() - PADDING - lblCaption.getHeight());
		lblFps.setPosition(PADDING, lblCaption.getY() - PADDING - lblFps.getHeight());
		lblJVM.setPosition(PADDING, lblFps.getY() - PADDING - lblFps.getHeight());	
		lblCameraPos.setPosition(PADDING, lblJVM.getY() - PADDING - lblCameraPos.getHeight());	
		lblViewport.setPosition(PADDING, lblCameraPos.getY() - PADDING - lblViewport.getHeight());	
		
		lblObjects.setPosition(PADDING, PADDING);	
		lblSeed.setPosition(PADDING, lblObjects.getY() + lblObjects.getHeight() + PADDING);	
		lblWorld.setPosition(PADDING, lblSeed.getY() + lblSeed.getHeight() + PADDING);	
		lblChunk.setPosition(PADDING, lblWorld.getY() + lblWorld.getHeight() + PADDING);	
		
		lblJVM.setText(getJVMText());
		lblFps.setText(getFPSText());
		lblCameraPos.setText(getCameraText());
		lblViewport.setText(getViewportText());
		lblObjects.setText(getObjectsText());
		lblSeed.setText(getSeedText());
		lblWorld.setText(getWorldText());
		lblChunk.setText(getChunkText());
	}
	
	private String getJVMText() {
		
		String text = "JVM: ";
		
		Runtime r = Runtime.getRuntime();
		
		int totalMb = Math.round(r.totalMemory() / (1024 * 1024));
		int usedMb = Math.round((r.totalMemory() - r.freeMemory()) / (1024 * 1024));
		
		int percentage = Math.round(100 * (r.totalMemory() - r.freeMemory()) / (float)r.totalMemory());
		
		if (percentage < 85) {
			lblJVM.setColor(Resources.COLOR_DEBUG_GOOD);
		} else {
			lblJVM.setColor(Resources.COLOR_DEBUG_FAIL);
		}
		
		return text + usedMb + "MB/" + totalMb + "MB (" + percentage + "%)";
	}
	
	private String getFPSText() {
		
		int fps = Gdx.graphics.getFramesPerSecond();
		
		if (fps > 40) {
			lblFps.setColor(Resources.COLOR_DEBUG_GOOD);
		} else {
			lblFps.setColor(Resources.COLOR_DEBUG_FAIL);
		}
		
		return "FPS: " + fps;
	}
	
	private String getObjectsText() {
		return "Objects: " + context.getWorld().getEntities().size();
	}
	
	private String getCameraText() {
		GameCamera c = context.getCamera();
		return "Camera: " + c.getX() + "  " + c.getY();
	}
	
	private String getViewportText() {
		GameCamera c = context.getCamera();
		return "Viewport: " + Math.round(c.getWidth()) + "x" + Math.round(c.getHeight());
	}
	
	private String getWorldText() {
		return "World: " + context.getConfiguration().getName();
	}
	
	private String getSeedText() {
		return "Seed: " + context.getConfiguration().getSeed();
	}
	
	private String getChunkText() {
		ChunkSubsystem c = context.getSubsystem(ChunkSubsystem.class);
		
		if (c != null && c.getActiveChunk() != null) {
			return "Chunk: " + c.getActiveChunk().getIndexX() + " , " + c.getActiveChunk().getIndexY();
		} else {
			return "No active chunk";
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
