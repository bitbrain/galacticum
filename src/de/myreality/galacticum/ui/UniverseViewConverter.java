package de.myreality.galacticum.ui;

import de.lessvoid.nifty.controls.Label;
import de.lessvoid.nifty.controls.ListBox.ListBoxViewConverterSimple;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;

public class UniverseViewConverter extends
		ListBoxViewConverterSimple<UniverseModel> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static final String UNIVERSE_NAME = "#universe-name";
	private static final String UNIVERSE_DATE = "#universe-date";
	private static final String UNIVERSE_SEED = "#universe-seed";

	// ===========================================================
	// Constructors
	// ===========================================================

	public UniverseViewConverter() {

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
	 * @see
	 * de.lessvoid.nifty.controls.ListBox.ListBoxViewConverterSimple#display
	 * (de.lessvoid.nifty.elements.Element, java.lang.Object)
	 */
	@Override
	public void display(Element element, UniverseModel item) {
		super.display(element, item);

		final Label name = element.findNiftyControl(UNIVERSE_NAME, Label.class);
		final Element date = element.findElementByName(UNIVERSE_DATE);
		final Element seed = element.findElementByName(UNIVERSE_SEED);
		final TextRenderer dateRenderer = date.getRenderer(TextRenderer.class);
		final TextRenderer seedRenderer = seed.getRenderer(TextRenderer.class);

		LabelResizer nameResizer = new LabelResizer(name);

		if (item != null) {
			nameResizer.setText(item.getUniverseName());
			dateRenderer.setText(" (" + item.getUniverseDate() + ")");
			seedRenderer.setText("Seed: " + item.getUniverseSeed());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.lessvoid.nifty.controls.ListBox.ListBoxViewConverterSimple#getWidth
	 * (de.lessvoid.nifty.elements.Element, java.lang.Object)
	 */
	@Override
	public int getWidth(Element element, UniverseModel item) {
		final Element name = element.findElementByName(UNIVERSE_NAME);
		final Element date = element.findElementByName(UNIVERSE_DATE);
		final Element seed = element.findElementByName(UNIVERSE_SEED);
		final TextRenderer nameRenderer = name.getRenderer(TextRenderer.class);
		final TextRenderer dateRenderer = date.getRenderer(TextRenderer.class);
		final TextRenderer seedRenderer = seed.getRenderer(TextRenderer.class);
		int topWidth = nameRenderer.getTextWidth()
				+ dateRenderer.getTextWidth();

		return Math.max(topWidth, seedRenderer.getTextWidth());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
