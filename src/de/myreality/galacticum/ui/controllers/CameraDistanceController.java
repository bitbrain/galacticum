package de.myreality.galacticum.ui.controllers;

import java.util.Properties;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import de.myreality.galacticum.util.CameraDistance;

/**
 * Controls the dropdown element for camera distance selection
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class CameraDistanceController implements Controller {

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
		// Load all data into the element
		@SuppressWarnings("unchecked")
		DropDown<CameraDistance> resolutionDropDown = screen
				.findNiftyControl("drpCameraDistance", DropDown.class);
		resolutionDropDown.addAllItems(CameraDistance.getAll());
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
