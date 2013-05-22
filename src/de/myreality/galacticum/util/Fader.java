package de.myreality.galacticum.util;

/**
 * Fader that fades between two types and render the result as output.
 * <p>
 * You can set the time and the percentage of the fading process.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface Fader<Type> extends Renderable, Updatable {

	// ===========================================================
	// Constants
	// ===========================================================

	/**
	 * Default interval
	 */
	public static final int DEFAULT_INTERVAL = 1000;

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * Sets a new source
	 * 
	 * @param source
	 *            the new source
	 */
	void setSource(Type source);

	/**
	 * Sets a new target
	 * 
	 * @param target
	 *            the new target
	 */
	void setTarget(Type target);

	/**
	 * @return Gets the current source
	 */
	Type getSource();

	/**
	 * @return Gets the current target
	 */
	Type getTarget();

	/**
	 * Sets a new fading progress
	 * <p>
	 * The progress has to be between 0.0f and 1.0f
	 * 
	 * @param progress
	 *            new progress
	 */
	void setProgress(float progress);

	/**
	 * Sets a new fading progress
	 * <p>
	 * The progress has to be between 0 and 100
	 * 
	 * @param progress
	 *            new progress
	 */
	void setProgress(int progress);

	/**
	 * @return The current progress
	 */
	float getProgress();

	/**
	 * Sets a new interval
	 * 
	 * @param interval
	 *            the new interval in miliseconds
	 */
	void setInterval(int interval);

	/**
	 * @return The current interval
	 */
	int getInterval();

	/**
	 * @return True, when the current fading is done
	 */
	boolean isDone();

}
