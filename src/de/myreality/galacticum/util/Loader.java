package de.myreality.galacticum.util;

import de.myreality.galacticum.exceptions.LoadingException;

/**
 * Basic loader that loads something
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 * @param <T>
 *            Type to load
 */
public interface Loader<T> {

	/**
	 * @return The current loading progress
	 */
	float getLoadingProgress();

	/**
	 * @return Returns the current universe if it has been loaded completely
	 * @throws LoadingNotDoneException
	 */
	T fetch() throws LoadingException;

	/**
	 * @return True, if the loading process is done
	 */
	boolean isDone();

	/**
	 * @return The current message of the state
	 */
	String getStateMessage();

	void setStateMessage(String message);
}
