package de.myreality.galacticum.ui;

import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.SizeValue;

/**
 * Resizes a label correctly after changing the text
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * 
 */
public class LabelResizer {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// Target label
	private Label label;

	// ===========================================================
	// Constructors
	// ===========================================================

	public LabelResizer(Label label) {
		this.label = label;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	/**
	 * Set the text of a label correctly
	 */
	public void setText(String text) {
		label.setText(text);
		TextRenderer renderer = label.getElement().getRenderer(
				TextRenderer.class);
		label.setWidth(new SizeValue(renderer.getTextWidth() + "px"));
		label.getElement().getParent().getParent().layoutElements();
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
