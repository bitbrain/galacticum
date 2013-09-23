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

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

import de.myreality.galacticum.Resources;

/**
 * Form which provides information for a game context
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class CreationForm extends Table {
	
	// ===========================================================
	// Constants
	// ===========================================================
	
	public static final String DEFAULT_TEXT_NAME = "Name";
	
	public static final String DEFAULT_TEXT_SEED = "Seed";

	// ===========================================================
	// Fields
	// ===========================================================
	
	private TextField tfName, tfSeed;

	private TextButton btnSubmit;
	
	private Label lblError;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public CreationForm(TextFieldStyle style) {

		
		lblError = new Label(" ", Resources.STYLE_LABEL_ERROR);
		
		tfName = new TextField("", style);
		tfSeed = new TextField("", style);
		
		tfName.setMessageText(DEFAULT_TEXT_NAME);
		tfSeed.setMessageText(DEFAULT_TEXT_SEED);		
		
		add(lblError).padBottom(20).padTop(30f);
		row();
		
		add(tfName).width(400f).padBottom(20f).height(110f);
		row();
		add(tfSeed).width(400f).height(110f);
		
		btnSubmit = new TextButton("Create", Resources.STYLE_BUTTON_DEFAULT);
		row();
		add(btnSubmit).width(400f).padTop(20f).height(50f);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public String getName() {
		return tfName.getText();
	}
	
	public String getSeed() {
		return tfName.getText();
	}
	
	public void setError(String error) {
		lblError.setText(error);
	}
	
	public boolean addListener(EventListener listener) {
		return btnSubmit.addListener(listener);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
