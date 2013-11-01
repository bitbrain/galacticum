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
import de.myreality.galacticum.core.context.Context;

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
	
	private Label lblCaption, lblFps, lblJVM;
	
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
		
		lblCaption = new Label(meta.getName() + " " + meta.getVersion() + meta.getPhase(), Resources.STYLE_LABEL_DEBUG);
		lblCaption.setPosition(PADDING, Gdx.graphics.getHeight() - PADDING - lblCaption.getHeight());
		addActor(lblCaption);
		
		lblFps = new Label("FPS: 0", Resources.STYLE_LABEL_DEBUG);
		lblFps.setPosition(PADDING, lblCaption.getY() - PADDING - lblFps.getHeight());	
		addActor(lblFps);
		lblJVM = new Label("JVM: 0", Resources.STYLE_LABEL_DEBUG);
		lblJVM.setPosition(PADDING, lblFps.getY() - PADDING - lblFps.getHeight());	
		addActor(lblJVM);
		
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
			lblCaption.setPosition(PADDING, Gdx.graphics.getHeight() - PADDING - lblCaption.getHeight());
			lblFps.setPosition(PADDING, lblCaption.getY() - PADDING - lblFps.getHeight());
			lblJVM.setPosition(PADDING, lblFps.getY() - PADDING - lblFps.getHeight());	
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
		lblJVM.setText(getJVMText());
		lblFps.setText(getFPSText());
	}
	
	private String getJVMText() {
		
		String text = "JVM: ";
		
		Runtime r = Runtime.getRuntime();
		
		int totalMb = Math.round(r.totalMemory() / (1024 * 1024));
		int usedMb = Math.round((r.totalMemory() - r.freeMemory()) / (1024 * 1024));
		
		int percentage = Math.round(100 * (r.totalMemory() - r.freeMemory()) / (float)r.totalMemory());
		
		return text + usedMb + "/" + totalMb + " (" + percentage + "%)";
	}
	
	private String getFPSText() {
		return "FPS: " + Gdx.graphics.getFramesPerSecond();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
