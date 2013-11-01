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

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * @author miguel
 * @since
 * @version
 */
public class JsonArray extends Json {
	 
    private List< Object > keys = new ArrayList < Object > ();
 
    void populate() {
 
    }
 
    void addBuffer(String buffer) {
        if (buffer == null || buffer.trim().length() == 0) {
            return;
        }
        String valueCls = cleanUp(buffer);
 
        keys.add(valueCls);
 
 
    }
 
    String pop() {
        return null;
    }
 
    public int size() {
        return keys.size();
    }
 
    @Override
    public Object getProperty(String key) {
        return null;
    }
 
    public void addNode(Object value) {
        addNode(null, value);
    }
 
    public void addNode(String key, Object value) {
        if (value instanceof String) {
            String valueCls = cleanUp(value.toString());
            keys.add(valueCls);
        } else {
            keys.add(value);
 
        }
    }
 
    public List < Object > getKeys() {
        return keys;
    }
 
    @Override
    public String toString() {
        return "{" + keys.toString() + '}';
    }
 
    boolean isJsonArray() {
        return true;
    }
 
    public Object get(int i) {
        return keys.get(i);
    }
 
}
