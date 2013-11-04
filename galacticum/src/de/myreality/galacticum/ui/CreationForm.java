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

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import de.myreality.galacticum.Resources;

/**
 * Form which provides information for a game context
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
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
	
	public CreationForm(TextFieldStyle style, TweenManager tweenManager) {
		
		lblError = new ErrorLabel(tweenManager);
		
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
		
		align(Align.center);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	public String getNameLabel() {
		return tfName.getText();
	}
	
	public String getSeedLabel() {
		return tfSeed.getText();
	}
	
	public void setErrorMessage(String error) {
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
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		final int PADDING = 20;
		
		tfName.setX(Gdx.graphics.getWidth() / 2f - tfName.getWidth() / 2f);
		tfSeed.setX(Gdx.graphics.getWidth() / 2f - tfSeed.getWidth() / 2f);
		btnSubmit.setX(Gdx.graphics.getWidth() / 2f - btnSubmit.getWidth() / 2f);
		lblError.setX(Gdx.graphics.getWidth() / 2f - lblError.getWidth() / 2f);
		
		lblError.setY(Gdx.graphics.getHeight()  - Gdx.graphics.getHeight() / 5 - 50);
		tfName.setY(lblError.getY() - PADDING - tfName.getHeight());
		tfSeed.setY(tfName.getY() - PADDING - tfSeed.getHeight());
		btnSubmit.setY(tfSeed.getY() - PADDING - btnSubmit.getHeight());
		
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
