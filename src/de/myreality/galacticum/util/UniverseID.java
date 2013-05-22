package de.myreality.galacticum.util;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.myreality.galacticum.core.UniverseManager;

/**
 * Universe ID that controls itself on wrong chars
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class UniverseID implements Serializable {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final String DEFAULT_ID = "universe";

	private static final long serialVersionUID = 1L;

	// ===========================================================
	// Fields
	// ===========================================================

	private String id;

	// ===========================================================
	// Constructors
	// ===========================================================

	public UniverseID(String name) {
		this(name, true);
	}

	public UniverseID(String name, boolean unique) {

		id = validateID(name);

		if (id.isEmpty()) {
			id = DEFAULT_ID;
		}

		// Check if id already exists
		if (unique) {
			while (UniverseManager.getInstance().doesUniverseExist(id)) {
				id = getIncreasedString(id);
			}
		}
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	private String getIncreasedString(String idString) {
		final Pattern lastIntPattern = Pattern.compile("[^0-9]+([0-9]+)$");
		Matcher matcher = lastIntPattern.matcher(idString);
		if (matcher.find()) {
			String someNumberStr = matcher.group(1);
			int extension = Integer.parseInt(someNumberStr);
			// Remove the old number
			idString = idString.replaceAll("\\d*$", "");

			// Append the new increased number
			idString += String.valueOf(extension + 1);
		} else {
			// Append the first number
			idString += "_1";
		}

		return idString;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public String toString() {
		return id;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private String validateID(String id) {
		return id.replaceAll("[^\\dA-Za-z ]", "").replaceAll("\\s+", "");
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
