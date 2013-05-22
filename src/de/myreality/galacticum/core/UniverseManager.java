package de.myreality.galacticum.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.newdawn.slick.util.Log;

import de.myreality.dev.chronos.util.FileHelper;
import de.myreality.galacticum.exceptions.UniverseNotFoundException;
import de.myreality.galacticum.util.WorldXMLData;
import de.myreality.galacticum.util.XMLData;
import de.myreality.galacticum.util.XMLLoader;

/**
 * Singleton manager that manages the different universes. Additionally the
 * universe manager will handle the world folder structure
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * 
 */
public class UniverseManager {

	// ===========================================================
	// Constants
	// ===========================================================

	// Singleton instance
	private static UniverseManager instance;

	// World data folder
	private static final String FOLDER = "data/";

	// XML file name
	private static final String XML_FILE = "worlds.xml";

	private static final String NAME_BASE = "universes";

	// ===========================================================
	// Fields
	// ===========================================================

	private File xmlFile;

	// ===========================================================
	// Constructors
	// ===========================================================

	static {
		instance = new UniverseManager();
	}

	private UniverseManager() {
		xmlFile = new File(FOLDER + XML_FILE);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	/**
	 * @return Current singleton instance
	 */
	public static UniverseManager getInstance() {
		return instance;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * Save the data of the created universe in XML
	 * 
	 * @param universe
	 *            Target Universe
	 */
	public void saveUniverseData(Universe un) {

		// Create folder structure if not already exists
		File directories = new File(FOLDER);
		if (!directories.exists()) {
			directories.mkdirs();
		}

		try {

			if (!xmlFile.exists()) {
				xmlFile.createNewFile();
			}

			// Fetch the current data and look if the universe
			// is already there. If so, overwrite the data.
			// Otherwise create new data.
			List<XMLData> currentData = getUniverseData();
			XMLData data = null;

			try {
				data = findUniverseDataById(un.getID());
				// Universe exists, change data
				data.getAttribute(WorldXMLData.NAME).value = un.getName();
				data.getAttribute(WorldXMLData.SEED).value = un.getSeed()
						.toString();
			} catch (UniverseNotFoundException e) {
				// Ok, universe is not registered yet. Register a new one

				Date date = new Date();
				String dateString = DateFormat.getInstance().format(date);
				data = new WorldXMLData(un.getName(), un.getID(), un.getSeed()
						.toString(), getUniversePath() + un.getID() + "/",
						dateString);
				currentData.add(data);
			}

			// Save the entire data
			XMLLoader loader = new XMLLoader(NAME_BASE);
			loader.addLines(currentData);
			OutputStream stream = new FileOutputStream(xmlFile);
			loader.writeToStream(stream);
			// After all, close the stream
			stream.close();
		} catch (UniverseNotFoundException e) {
			Log.error(e);
		} catch (IOException e) {
			Log.error(e);
		}
	}

	/**
	 * Removes an universe with the given ID
	 * 
	 * @param id
	 *            ID of the universe
	 */
	public void removeUniverseData(String id) throws UniverseNotFoundException {
		List<XMLData> data = getUniverseData();
		XMLData universe = findUniverseDataById(id, data);
		data.remove(universe);

		// Save the entire data
		XMLLoader loader = new XMLLoader(NAME_BASE);
		loader.addLines(data);
		OutputStream stream;
		try {
			stream = new FileOutputStream(xmlFile);
			loader.writeToStream(stream);
			stream.close();

			// Remove the folder
			if (universe != null) {
				File file = new File(
						universe.getAttribute(WorldXMLData.PATH).value);
				FileHelper.removeFilesRecursively(file);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return The path where the universes should be stored in
	 */
	public String getUniversePath() {
		return FOLDER;
	}

	public XMLData findUniverseDataById(String id, List<XMLData> universeData)
			throws UniverseNotFoundException {

		for (XMLData data : universeData) {
			// Universe already exists, update data
			if (data.getAttribute(WorldXMLData.ID).value.equals(id)) {
				return data;
			}
		}

		throw new UniverseNotFoundException("Universe with id=" + id
				+ " does not exist");
	}

	public XMLData findUniverseDataById(String id)
			throws UniverseNotFoundException {
		return findUniverseDataById(id, getUniverseData());
	}

	public boolean doesUniverseExist(String id, List<XMLData> universeData) {

		try {
			findUniverseDataById(id, universeData);
		} catch (UniverseNotFoundException e) {
			return false;
		}

		return true;
	}

	/**
	 * Returns true, when the target universe with the ID already exists
	 */
	public boolean doesUniverseExist(String id) {
		try {
			return doesUniverseExist(id, getUniverseData());
		} catch (UniverseNotFoundException e) {
			return false;
		}
	}

	/**
	 * Returns all current registered universes
	 * 
	 * @return A list of all registered universes
	 * @throws UniverseNotFoundException
	 *             Is thrown when no universe has been registered yet
	 * @throws IOException
	 */
	public List<XMLData> getUniverseData() throws UniverseNotFoundException {

		synchronized (xmlFile) {
			if (xmlFile.exists()) {
				try {
					XMLLoader loader = new XMLLoader(NAME_BASE);
					InputStream xmlStream = new FileInputStream(xmlFile);
					loader.readFromStream(xmlStream);
					xmlStream.close();
					return loader.getLines();
				} catch (IOException e) {
					throw new UniverseNotFoundException(
							"Can not fetch any universe data. Universe XML is empty");

				}
			} else {
				throw new UniverseNotFoundException(
						"Can not fetch any universe data. Universe XML does not exist.");
			}
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
