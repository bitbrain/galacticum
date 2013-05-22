package de.myreality.galacticum.core;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import de.myreality.galacticum.core.background.HashSpaceZone;
import de.myreality.galacticum.core.background.SpaceZone;
import de.myreality.galacticum.exceptions.LoadingException;
import de.myreality.galacticum.exceptions.UniverseNotFoundException;
import de.myreality.galacticum.util.Loader;
import de.myreality.galacticum.util.UniverseID;
import de.myreality.galacticum.util.WorldXMLData;
import de.myreality.galacticum.util.XMLData;

/**
 * Implementation for an universe loader, that loades new and existing universes
 * into the system
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class UniverseLoader implements Loader<Universe>, Runnable {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// Temporary one, we'll load it seperately
	protected Universe temporaryUniverse;

	// Finished one, is null, when not loaded
	protected Universe loadedUniverse;

	// Current state message
	private String stateMessage;

	// Error exception
	private LoadingException errorException;

	// System loaders
	private ArrayList<SubsystemLoader<?>> loaders;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * Constructor to create a fresh and new universe
	 * 
	 * @param name
	 *            Name of the universe
	 * @seed seed Seed of the user
	 */
	public UniverseLoader(String name, StateBasedGame game, String seed) {
		init();
		UniverseID universeID = new UniverseID(name);
		File file = computeUniverseFolder(universeID.toString());
		temporaryUniverse = new BasicUniverse(universeID.toString(), name,
				seed, file.getPath() + "/");
		UniverseManager.getInstance().saveUniverseData(temporaryUniverse);
		temporaryUniverse.setCamera(new TrackingCamera(game.getContainer()));
	}

	/**
	 * Constructor to load an existing universe
	 */
	public UniverseLoader(String universeID, StateBasedGame game)
			throws UniverseNotFoundException {
		init();
		XMLData data = UniverseManager.getInstance().findUniverseDataById(
				universeID);
		// Fetching the data
		String id = data.getAttribute(WorldXMLData.ID).value;
		String name = data.getAttribute(WorldXMLData.NAME).value;
		String seed = data.getAttribute(WorldXMLData.SEED).value;
		String path = data.getAttribute(WorldXMLData.PATH).value;

		computeUniverseFolder(id);

		temporaryUniverse = new BasicUniverse(id, name, seed, path);
		temporaryUniverse.setCamera(new TrackingCamera(game.getContainer()));
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public float getLoadingProgress() {
		float totalProgress = 0;

		for (SubsystemLoader<?> loader : loaders) {
			totalProgress += loader.getLoadingProgress();
		}

		return totalProgress / loaders.size();
	}

	public boolean errorOccured() {
		return errorException != null;
	}

	@Override
	public Universe fetch() throws LoadingException {

		if (errorOccured()) {
			throw errorException;
		}

		if (isDone()) {
			return loadedUniverse;
		} else {
			String msg = "The universe '" + temporaryUniverse.getName()
					+ "' with seed " + temporaryUniverse.getSeed()
					+ " has not loaded to the end";
			throw new LoadingException(msg);
		}
	}

	@Override
	public void run() {

		checkSubSytems();

		if (temporaryUniverse != null && !errorOccured()) {

			for (SubsystemLoader<?> loader : loaders) {
				setStateMessage(loader.getStateMessage());
				loader.load(temporaryUniverse);
			}
			
			// All systems are loaded
			if (temporaryUniverse.getChunkSystem() != null) {
				SpaceZone area = new HashSpaceZone(temporaryUniverse);
				temporaryUniverse.setBackground(area);
			}
			loadedUniverse = temporaryUniverse;
			temporaryUniverse = null;
		} else {
			Log.error(errorException);
		}
	}

	private void checkSubSytems() {
		if (loaders.isEmpty()) {
			setErrorOccured("No loader has been set");
		}
	}

	@Override
	public void setStateMessage(String message) {
		this.stateMessage = message;
	}

	@Override
	public boolean isDone() {
		return loadedUniverse != null;
	}

	@Override
	public String getStateMessage() {
		return stateMessage;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private File computeUniverseFolder(String id) {
		UniverseManager universeManager = UniverseManager.getInstance();
		// Create a new folder with the given ID
		String basePath = universeManager.getUniversePath();
		File file = new File(basePath + id);

		if (!file.exists()) {
			file.mkdirs();
		}

		return file;
	}

	private void init() {
		loaders = new ArrayList<SubsystemLoader<?>>();
	}

	public Universe getTemporary() {
		return temporaryUniverse;
	}

	private void setErrorOccured(String message) {
		this.errorException = new LoadingException(message);
	}

	/**
	 * @param chunkSystemLoader
	 *            the chunkSystemLoader to set
	 */
	public void addSubsystemLoader(SubsystemLoader<?> subsystemLoader) {
		loaders.add(subsystemLoader);
	}
}
