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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * 
 * @author miguel
 * @since
 * @version
 */
public class JsonObject extends Json {

	private Deque<String> keys = new ArrayDeque<String>();

	private Map<String, Object> properties = new LinkedHashMap<String, Object>();

	@Override
	public int size() {
		return properties.size();
	}

	public Object getProperty(String key) {
		return properties.get(key);
	}

	public void addNode(String key, Object value) {
		key = cleanUp(key);
		properties.put(key, value);
	}

	void addProperty(String key, String value) {
		key = cleanUp(key);
		value = cleanUp(value);

		properties.put(key, value);
	}

	void populate() {
		String[] keysArr = new String[keys.size()];
		keys.toArray(keysArr);
		for (int i = 0; i < keysArr.length - 1; i = i + 2) {
			String key = keysArr[i];
			String value = keysArr[i + 1];
			addProperty(key, value);
		}
		keys.clear();
	}

	String pop() {
		return keys.pop();
	}

	void addBuffer(String buffer) {
		if (buffer == null || buffer.trim().length() == 0) {
			return;
		}
		keys.add(buffer);
		if (keys.size() > 2) {
			addProperty(keys.pop(), keys.pop());
		}

	}

	Map<String, Object> getProperties() {
		return properties;
	}

	@Override
	public String toString() {
		return "{" + properties + '}';
	}
}