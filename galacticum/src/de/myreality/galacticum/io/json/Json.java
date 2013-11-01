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
package de.myreality.galacticum.io.json;

/**
 * 
 * 
 * @author miguel
 * @since
 * @version
 */
public abstract class Json {

	Json parent;
	Json child;

	public abstract int size();

	public abstract Object getProperty(String key);

	public void addNode(String key, Integer value) {
		addNode(key, String.valueOf(value));
	}

	public void addNode(String key, Double value) {
		addNode(key, String.valueOf(value));
	}

	public void addNode(String key, Boolean value) {
		addNode(key, String.valueOf(value));
	}

	public void addNode(String key, Float value) {
		addNode(key, String.valueOf(value));
	}

	public void addNode(String key, int value) {
		addNode(key, String.valueOf(value));
	}

	public void addNode(String key, long value) {
		addNode(key, String.valueOf(value));
	}

	public void addNode(String key, double value) {
		addNode(key, String.valueOf(value));
	}

	public void addNode(String key, float value) {
		addNode(key, String.valueOf(value));
	}

	public abstract void addNode(String key, Object value);

	String cleanUp(String token) {
		token = token.trim();
		token = token.replaceAll("'", "");
		return token;
	}

	abstract String pop();

	abstract void populate();

	abstract void addBuffer(String token);

	boolean isJsonArray() {
		return false;
	}
}