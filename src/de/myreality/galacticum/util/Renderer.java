package de.myreality.galacticum.util;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.myreality.galacticum.core.rendering.RenderTarget;

/**
 * Renderer implementation to render single targets on runtime. This class is a
 * singleton to avoid multiple instances.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class Renderer implements Renderable {

	// ===========================================================
	// Constants
	// ===========================================================

	public static final int DEFAULT_JOB_COUNT = 5;

	private static Renderer instance;

	// ===========================================================
	// Fields
	// ===========================================================

	private List<RenderTarget> targets;

	private int jobCount;

	// ===========================================================
	// Constructors
	// ===========================================================

	static {
		instance = new Renderer();
	}

	private Renderer() {
		targets = new CopyOnWriteArrayList<RenderTarget>();
		setJobCount(DEFAULT_JOB_COUNT);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void setJobCount(int jobs) {
		this.jobCount = jobs;
	}

	public int getJobCount() {
		return jobCount;
	}

	public static Renderer getInstance() {
		return instance;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.util.Renderable#render(org.newdawn.slick
	 * .GameContainer, org.newdawn.slick.state.StateBasedGame,
	 * org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		int jobIndex = 0;
		for (RenderTarget target : targets) {
			target.beforeRender();
			target.computeRendering(gc, g);
			target.afterRender();
			targets.remove(target);
			if (++jobIndex >= getJobCount()) {
				break;
			}
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void addTarget(RenderTarget target) {
		targets.add(target);
	}

	public void clear() {
		targets.clear();
	}

	public boolean isDone() {
		return targets.isEmpty();
	}

	public void removeTarget(RenderTarget target) {
		targets.remove(target);
	}

	public boolean contains(RenderTarget target) {
		return targets.contains(target);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}