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

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import de.myreality.galacticum.Resources;
import de.myreality.galacticum.core.context.ContextEvent;
import de.myreality.galacticum.core.context.ContextListener;
import de.myreality.galacticum.util.Nameable;

/**
 * Provides loading functionality in order to listen to a context
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class LoadingBox extends Table implements ContextListener {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private ProgressBar progressBar;
	
	private Label information;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public LoadingBox() {
		progressBar = new ProgressBar(0.0f);

		progressBar.setWidth(40f);
		progressBar.setHeight(40f);
		add(progressBar).width(500f);
		row();
		
		LabelStyle style = new LabelStyle();
		style.font = Resources.FONT_SMALL;
		style.fontColor = Resources.COLOR_GREEN;
		
		information = new Label("Preparing...", style);
		add(information).width(500f).center().padTop(20f);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	
	
	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.context.ContextListener#onStart(de.myreality.galacticum.core.context.ContextEvent)
	 */
	@Override
	public void onStart(ContextEvent event) {
		information.setText("Preprocessing...");
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.context.ContextListener#onSuccess(de.myreality.galacticum.core.context.ContextEvent)
	 */
	@Override
	public void onSuccess(ContextEvent event) {
		progressBar.setProgress(100f);
		information.setText("Done.");
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.context.ContextListener#onFail(de.myreality.galacticum.core.context.ContextEvent, java.lang.Throwable)
	 */
	@Override
	public void onFail(ContextEvent event, Throwable error) {
		information.setText("Fail.");
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.core.context.ContextListener#onLoad(de.myreality.galacticum.core.context.ContextEvent, de.myreality.galacticum.util.Nameable)
	 */
	@Override
	public void onLoad(ContextEvent event, Nameable target) {
		progressBar.setProgress(event.getProgress());
		information.setText("" + event.getCurrentCount());
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
