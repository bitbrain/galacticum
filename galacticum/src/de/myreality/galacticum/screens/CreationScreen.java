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
package de.myreality.galacticum.screens;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.myreality.galacticum.GalacticumGame;
import de.myreality.galacticum.Resources;
import de.myreality.galacticum.io.ConfigurationBuilder;
import de.myreality.galacticum.io.ConfigurationManager;
import de.myreality.galacticum.io.ConfigurationReader;
import de.myreality.galacticum.io.ConfigurationRemover;
import de.myreality.galacticum.io.ConfigurationWriter;
import de.myreality.galacticum.io.ContextConfiguration;
import de.myreality.galacticum.io.ContextException;
import de.myreality.galacticum.io.SimpleConfigurationBuilder;
import de.myreality.galacticum.io.SimpleConfigurationManager;
import de.myreality.galacticum.ui.CreationForm;
import de.myreality.galacticum.util.ContextIDConverter;
import de.myreality.galacticum.xml.XMLConfigurationReader;
import de.myreality.galacticum.xml.XMLConfigurationWriter;

/**
 * Screen which displays configuration to create a new universe. Additionally
 * you can select between multiple existing universes.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class CreationScreen extends MenuScreen {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private CreationForm form;
	
	private TweenManager tweenManager;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public CreationScreen(String caption, GalacticumGame game) {
		super(caption, game);
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
	 * @see de.myreality.galacticum.screens.MenuScreen#onCreateUI()
	 */
	@Override
	protected void onCreateUI(Stage stage) {
		tweenManager = new TweenManager();
		form = new CreationForm(Resources.STYLE_TEXTFIELD_DEFAULT, tweenManager);
		form.addListener(new CreationValidator());
		stage.addActor(form);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.screens.MenuScreen#onResizeUI(int, int)
	 */
	@Override
	protected void onResizeUI(int width, int height) {
		form.setWidth(width);
		form.setHeight(height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.screens.MenuScreen#onDraw(com.badlogic.gdx.graphics
	 * .g2d.SpriteBatch, float)
	 */
	@Override
	protected void onDraw(SpriteBatch batch, float delta) {
		tweenManager.update(delta);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private void createGame() throws ContextException {
		
		ContextIDConverter converter = new ContextIDConverter(form.getNameLabel());

		ConfigurationReader reader = new XMLConfigurationReader(Resources.CONTEXT_PATH);
		ConfigurationWriter writer = new XMLConfigurationWriter(Resources.CONTEXT_PATH, reader);
		ConfigurationRemover remover = null; // TODO
		ConfigurationManager manager = new SimpleConfigurationManager(writer, reader, remover);
		
		ConfigurationBuilder builder = new SimpleConfigurationBuilder();
		
		builder.setID(converter.toString())
			   .setName(form.getNameLabel())
			   .setSeed(form.getSeedLabel());
		
		ContextConfiguration configuration = builder.build();
		
		if (!manager.hasContext(configuration.getID())) {
			
			// Create a new configuration in the config file
			manager.save(configuration);
			
			getGame().setScreen(new LoadingScreen(
					"Loading game", 
					getGame(), 
					configuration, 
					manager, 
					null));
		} else {
			throw new ContextException("Context '" + configuration.getName() + "' already exists");
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	class CreationValidator extends ClickListener {

		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			super.touchDown(event, x, y, pointer, button);
			
			if (form.getNameLabel().isEmpty()) {
				form.setErrorMessage("Specify a name for your universe");				
				return false;
			} else {			
				try {
					createGame();
				} catch (ContextException e) {
					form.setErrorMessage(e.getMessage());	
					return false;
				}
			}
			
			return true;
		}
		
		
	}

}
