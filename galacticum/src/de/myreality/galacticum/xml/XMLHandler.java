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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * 
 * @author miguel
 * @since
 * @version
 */
public class XMLHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String CHARSET = "UTF-8";

	// ===========================================================
	// Fields
	// ===========================================================

	private ArrayList<XMLData> lines;

	private String base;

	private String fileResult;

	// ===========================================================
	// Constructors
	// ===========================================================

	public XMLHandler(String base) {
		lines = new ArrayList<XMLData>();
		fileResult = "";
		this.base = base;
		computeFile();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public XMLHandler setBase(String base) {
		this.base = base;
		computeFile();
		return this;
	}

	public ArrayList<XMLData> getLines() {
		return lines;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public String toString() {
		return fileResult;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public XMLHandler addLine(XMLData line) {
		lines.add(line);
		computeFile();
		return this;
	}

	public XMLHandler addLines(Collection<XMLData> lines) {
		for (XMLData data : lines) {
			addLine(data);
		}
		return this;
	}

	public void writeToStream(OutputStream stream) {
		PrintWriter writer = new PrintWriter(stream);
		writer.write(toString());
		writer.close();

	}

	public void readFromStream(InputStream stream) {

		clear();
		Document doc = null;

		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = null;
			docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(stream);

			// normalize text representation
			doc.getDocumentElement().normalize();
			NodeList listResources = doc.getElementsByTagName(base);
			int totalResources = listResources.getLength();

			// Go through data
			for (int resourceIdx = 0; resourceIdx < totalResources; resourceIdx++) {
				Node resourceNode = listResources.item(resourceIdx);
				// Data found, save it to this class
				if (resourceNode.getNodeType() == Node.ELEMENT_NODE) {

					NodeList children = resourceNode.getChildNodes();

					for (int id = 0; id < children.getLength(); id++) {
						Node node = children.item(id);
						// Data found, save it to this class
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element resourceElement = (Element) node;
							XMLData xmlData = new XMLData(
									resourceElement.getTagName());

							// Fetch the content
							xmlData.setContent(resourceElement.getTextContent());

							// Fetch all attributes
							NamedNodeMap attributes = resourceElement
									.getAttributes();

							for (int index = 0; index < attributes.getLength(); ++index) {
								Node attributeNode = attributes.item(index);
								if (attributeNode.getNodeType() == Node.ATTRIBUTE_NODE) {
									Attr attr = (Attr) attributeNode;
									xmlData.addAttribute(attr.getNodeName(),
											attr.getValue());
								}
							}

							// Add the data
							addLine(xmlData);
						}
					}
				}
			}
		} catch (IOException e) {
			//
		} catch (ParserConfigurationException e) {
			//
		} catch (SAXException e) {
			//
		} catch (NullPointerException e) {
			//
		}

	}

	public void clear() {
		lines.clear();
		fileResult = "";
	}

	private void computeFile() {
		fileResult = "<?xml version=" + '"' + "1.0" + '"' + " encoding=" + '"'
				+ CHARSET + '"' + "?>\n";
		fileResult += "<" + base + ">\n";

		for (XMLData line : lines) {
			fileResult += line + "\n";
		}

		fileResult += "</" + base + ">";
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
