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
public class JsonReader {
 
    public JsonObject parse(String jsonContent) {
        jsonContent = jsonContent.replaceAll("\n", "");
 
        return (JsonObject) readAhead(jsonContent);
    }
 
    private Json readAhead(String txt) {
 
 
        int index = 0;
        Json currentNode = null;
        StringBuilder buffer = new StringBuilder();
 
        while (index != txt.length()) {
 
 
            char c = txt.charAt(index);
 
            if (c == JsonSettings.JSON_OBJECT_LEFT_BRACKET) {
 
                JsonObject newNode = new JsonObject();
                newNode.addBuffer(buffer.toString());
                currentNode = checkAndAssignNewNode(currentNode, newNode);
 
            } else if (c == JsonSettings.JSON_OBJECT_RIGHT_BRACKET) {
                currentNode.addBuffer(buffer.toString());
 
 
                currentNode = checkAndCloseCurrentNode(currentNode);
                buffer = new StringBuilder();
 
 
            } else if (c == JsonSettings.JSON_ARRAY_LEFT_BRACKET) {
 
                JsonArray newNode = new JsonArray();
                newNode.addBuffer(buffer.toString());
                currentNode = checkAndAssignNewNode(currentNode, newNode);
 
            } else if (c == JsonSettings.JSON_ARRAY_RIGHT_BRACKET) {
                currentNode.addBuffer(buffer.toString());
 
                currentNode = checkAndCloseCurrentNode(currentNode);
                buffer = new StringBuilder();
 
            } else if (c == JsonSettings.JSON_COMMA) {
                currentNode.addBuffer(buffer.toString());
 
                buffer = new StringBuilder();
 
 
            } else if (c == JsonSettings.JSON_TOKEN_SPLIT) {
 
                currentNode.addBuffer(buffer.toString());
                buffer = new StringBuilder();
 
            } else {
                buffer.append(c);
            }
            index++;
 
        }
 
 
        return currentNode;
    }
 
    private Json checkAndCloseCurrentNode(Json currentNode) {
        Json prevNode = currentNode.parent;
        if (prevNode == null) {
            //no parent current node is the parent node
            prevNode = currentNode;
        } else {
            prevNode.addNode(prevNode.pop(), currentNode);
        }
 
        currentNode.populate();
 
        currentNode = prevNode;
 
        return currentNode;
    }
 
    private Json checkAndAssignNewNode(Json currentNode, Json newNode) {
        if (currentNode == null) {
            currentNode = newNode;
        } else {
 
            newNode.parent = currentNode;
            currentNode = newNode;
        }
        return currentNode;
    }
 
}