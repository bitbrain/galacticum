package de.myreality.galacticum.core;

import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.exceptions.LoadingException;
import de.myreality.galacticum.util.Loader;

/**
 * Loader to load a specific sub system
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 */
public abstract class SubsystemLoader<T> implements Loader<T> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected String stateMessage;

	private float loadingProgress;

	protected T subSystem;

	private StateBasedGame game;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SubsystemLoader(StateBasedGame game) {
		this.stateMessage = "";
		loadingProgress = 0;
		this.game = game;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public float getLoadingProgress() {
		return loadingProgress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Loader#fetch()
	 */
	@Override
	public T fetch() throws LoadingException {
		return subSystem;
	}

	@Override
	public boolean isDone() {
		return getLoadingProgress() >= 100;
	}

	@Override
	public String getStateMessage() {
		return stateMessage;
	}

	@Override
	public void setStateMessage(String message) {
		this.stateMessage = message;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public abstract void load(Universe universe);

	public StateBasedGame getGame() {
		return game;
	}

	protected void setLoadingProgress(float progress) {
		this.loadingProgress = progress;
	}
}
