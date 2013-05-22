package de.myreality.galacticum.util;

import java.util.ArrayList;
import java.util.List;

import de.myreality.galacticum.core.BasicBoundable;
import de.myreality.galacticum.core.Boundable;

/**
 * This file is part of Chronos (Myreality Game Development Toolkit). Chronos is
 * licenced under GNU LESSER GENERAL PUBLIC LICENSE (Version 3) <br />
 * <br />
 * For more information visit http://dev.my-reality.de/chronos <br />
 * <br />
 * Quadtree implementation for Chronos entities
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.7
 */
public class Quadtree {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private int MAX_OBJECTS = 10;
	private int MAX_LEVELS = 30;

	private int level;
	private List<Boundable> objects;
	private Boundable bounds;
	private Quadtree[] nodes;

	// ===========================================================
	// Constructors
	// ===========================================================

	/*
	 * Constructor
	 */
	public Quadtree(int pLevel, Boundable pBounds) {
		level = pLevel;
		objects = new ArrayList<Boundable>();
		bounds = pBounds;
		nodes = new Quadtree[4];
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/*
	 * Clears the quadtree
	 */
	public void clear() {
		objects.clear();

		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] != null) {
				nodes[i].clear();
				nodes[i] = null;
			}
		}
	}

	/*
	 * Splits the node into 4 subnodes
	 */
	private void split() {
		int subWidth = (int) ((bounds.getRight() - bounds.getLeft()) / 2);
		int subHeight = (int) ((bounds.getBottom() - bounds.getTop()) / 2);
		float x = bounds.getLeft();
		float y = bounds.getTop();

		nodes[0] = new Quadtree(level + 1, new BasicBoundable(x + subWidth, y,
				subWidth, subHeight));
		nodes[1] = new Quadtree(level + 1, new BasicBoundable(x, y, subWidth,
				subHeight));
		nodes[2] = new Quadtree(level + 1, new BasicBoundable(x, y + subHeight,
				subWidth, subHeight));
		nodes[3] = new Quadtree(level + 1, new BasicBoundable(x + subWidth, y
				+ subHeight, subWidth, subHeight));
	}

	/*
	 * Determine which node the object belongs to. -1 means object cannot
	 * completely fit within a child node and is part of the parent node
	 */
	private int getIndex(Boundable pRect) {
		int index = -1;
		double verticalMidpoint = bounds.getLeft() + (bounds.getWidth() / 2);
		double horizontalMidpoint = bounds.getTop() + (bounds.getHeight() / 2);

		// Object can completely fit within the top quadrants
		boolean topQuadrant = (pRect.getTop() < horizontalMidpoint && pRect
				.getBottom() < horizontalMidpoint);
		// Object can completely fit within the bottom quadrants
		boolean bottomQuadrant = (pRect.getTop() > horizontalMidpoint);

		// Object can completely fit within the left quadrants
		if (pRect.getLeft() < verticalMidpoint
				&& pRect.getRight() < verticalMidpoint) {
			if (topQuadrant) {
				index = 1;
			} else if (bottomQuadrant) {
				index = 2;
			}
		}
		// Object can completely fit within the right quadrants
		else if (pRect.getLeft() > verticalMidpoint) {
			if (topQuadrant) {
				index = 0;
			} else if (bottomQuadrant) {
				index = 3;
			}
		}

		return index;
	}

	/*
	 * Insert the object into the quadtree. If the node exceeds the capacity, it
	 * will split and add all objects to their corresponding nodes.
	 */
	public void insert(Boundable pRect) {
		if (nodes[0] != null) {
			int index = getIndex(pRect);

			if (index != -1) {
				nodes[index].insert(pRect);

				return;
			}
		}

		objects.add(pRect);

		if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
			if (nodes[0] == null) {
				split();
			}

			int i = 0;
			while (i < objects.size()) {
				int index = getIndex(objects.get(i));
				if (index != -1) {
					nodes[index].insert(objects.remove(i));
				} else {
					i++;
				}
			}
		}
	}

	/*
	 * Return all objects that could collide with the given object
	 */
	public List<Boundable> retrieve(List<Boundable> returnObjects,
			Boundable pRect) {
		int index = getIndex(pRect);
		if (index != -1 && nodes[0] != null) {
			nodes[index].retrieve(returnObjects, pRect);
		}

		returnObjects.addAll(objects);

		return returnObjects;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
