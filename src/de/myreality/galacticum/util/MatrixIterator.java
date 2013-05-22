package de.myreality.galacticum.util;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class MatrixIterator<Type extends Indexable> implements Iterator<Type> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// X Index
	private Iterator<ConcurrentHashMap<Integer, Type> > iteratorX;

	// Y Index
	private Iterator<Type> iteratorY;

	// ===========================================================
	// Constructors
	// ===========================================================

	public MatrixIterator(ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Type> > chunks,
			MatrixList<Type> list) {
		iteratorX = chunks.values().iterator();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public boolean hasNext() {
		if (iteratorY != null) {
			return iteratorY.hasNext() || iteratorX.hasNext();
		} else {
			return iteratorX.hasNext();
		}
	}

	@Override
	public Type next() {

		if (iteratorY == null || !iteratorY.hasNext()) {
			ConcurrentHashMap<Integer, Type> data = iteratorX.next();
			iteratorY = data.values().iterator();
		}
		if (iteratorY.hasNext()) {
			return iteratorY.next();
		} else {
			return null;
		}
	}

	@Override
	public void remove() {

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
