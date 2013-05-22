package de.myreality.galacticum.util;

import java.util.HashMap;

/**
 * Default XML data
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class XMLData {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private String tagName;

	private String content;

	private String lineResult;

	private HashMap<String, Attribute> attributes;

	// ===========================================================
	// Constructors
	// ===========================================================

	public XMLData(String tagName) {
		attributes = new HashMap<String, Attribute>();
		this.tagName = tagName;
		this.lineResult = "";
		this.content = "";
		computeLine();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public XMLData setContent(String content) {
		this.content = content;
		computeLine();
		return this;
	}

	public Attribute getAttribute(String key) {
		return attributes.get(key);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public String toString() {
		return lineResult;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void computeLine() {
		// Beginning
		lineResult = "<" + tagName;

		// Attributes
		for (Attribute attribute : attributes.values()) {
			lineResult += " " + attribute;
		}

		// Check if content is given, otherwise
		// close tag completely
		if (content.isEmpty()) {
			lineResult += "/>";
		} else {
			lineResult += ">" + content + "</" + tagName + ">";
		}
	}

	public static String filter(String string) {
		return string.replace(">", "").replace('"', '_').replace("<", "")
				.replace("/", "");
	}

	public XMLData addAttribute(String key, String value) {
		attributes.put(key, new Attribute(key, value));
		computeLine();
		return this;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public class Attribute {

		public String key;

		public String value;

		public Attribute(String key, String value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return key + '=' + '"' + value + '"';
		}
	}
}
