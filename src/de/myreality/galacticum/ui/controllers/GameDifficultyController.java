package de.myreality.galacticum.ui.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import de.myreality.galacticum.util.GameDifficulty;

/**
 * Controller that loads the game difficulty into a list view
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * 
 */
public class GameDifficultyController implements Controller {

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
		@SuppressWarnings("unchecked")
		DropDown<GameDifficulty> resolutionDropDown = screen
				.findNiftyControl("drpDifficulty", DropDown.class);
		resolutionDropDown.addAllItems(getDifficulties());
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

	private List<GameDifficulty> getDifficulties() {
		List<GameDifficulty> difficulties = new ArrayList<GameDifficulty>();
		for (GameDifficulty diff : GameDifficulty.values()) {
			difficulties.add(diff);
		}
		return difficulties;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
