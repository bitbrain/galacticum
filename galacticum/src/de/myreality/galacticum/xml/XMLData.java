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
package de.myreality.galacticum.xml;

import java.util.HashMap;

/**
 * 
 * 
 * @author miguel
 * @since
 * @version
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
