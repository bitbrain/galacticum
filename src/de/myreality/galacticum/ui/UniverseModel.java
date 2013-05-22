package de.myreality.galacticum.ui;

import de.myreality.galacticum.util.WorldXMLData;
import de.myreality.galacticum.util.XMLData;

/**
 * Universe model for a list box
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * 
 */
public class UniverseModel {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private XMLData data;

	// ===========================================================
	// Constructors
	// ===========================================================

	public UniverseModel(XMLData data) {
		this.data = data;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public String getUniverseName() {
		return data.getAttribute(WorldXMLData.NAME).value;
	}

	public String getUniverseID() {
		return data.getAttribute(WorldXMLData.ID).value;
	}

	public String getUniverseSeed() {
		return data.getAttribute(WorldXMLData.SEED).value;
	}

	public String getUniversePath() {
		return data.getAttribute(WorldXMLData.PATH).value;
	}

	public String getUniverseDate() {
		return data.getAttribute(WorldXMLData.CREATED).value;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public String toString() {
		return getUniverseName() + " (Seed: " + getUniverseSeed() + ")";
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
