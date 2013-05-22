package de.myreality.galacticum.ui.controllers;

import java.util.Properties;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import de.myreality.dev.chronos.resource.ResourceManager;

/**
 * Controller to manage the version string.
 * <p>
 * The version is fetched from the resource manager
 * 
 * @author Miguel Gonzalez
 * @version 0.1dev
 * @since 0.1dev
 */
public class VersionTextController implements Controller {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void bind(Nifty nifty, Screen screen, Element element,
			Properties parameter, Attributes controlDefinitionAttributes) {
		// Load the version string
		String version = ResourceManager.getInstance()
				.getResource("APP_VERSION", String.class).get();
		String phase = ResourceManager.getInstance()
				.getResource("APP_PHASE", String.class).get();
		element.getRenderer(TextRenderer.class)
				.setText("v. " + version + phase);
	}

	@Override
	public void init(Properties parameter,
			Attributes controlDefinitionAttributes) {

	}

	@Override
	public boolean inputEvent(NiftyInputEvent inputEvent) {
		return false;
	}

	@Override
	public void onFocus(boolean getFocus) {

	}

	@Override
	public void onStartScreen() {

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
