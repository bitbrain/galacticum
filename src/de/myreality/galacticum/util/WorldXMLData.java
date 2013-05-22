package de.myreality.galacticum.util;

/**
 * Contains all data for a XML universe entry
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 * 
 */
public class WorldXMLData extends XMLData {

	// ===========================================================
	// Constants
	// ===========================================================

	public static final String TAG = "world";

	public static final String ID = "id";

	public static final String NAME = "name";

	public static final String SEED = "seed";

	public static final String PATH = "path";

	public static final String CREATED = "created";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public WorldXMLData(String name, String id, String seed, String path,
			String created) {
		super(TAG);
		addAttribute(NAME, name);
		addAttribute(ID, id);
		addAttribute(SEED, seed);
		addAttribute(PATH, path);
		addAttribute(CREATED, created);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

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
