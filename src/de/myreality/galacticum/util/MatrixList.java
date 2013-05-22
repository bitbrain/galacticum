package de.myreality.galacticum.util;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import de.myreality.galacticum.exceptions.EntryNotFoundException;

/**
 * Chunk ist that contains loaded chunks
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class MatrixList<Type extends Indexable> implements Iterable<Type> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private int elementSize = 0;

	private ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Type>> chunks = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Type>>();

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public Iterator<Type> iterator() {
		return new MatrixIterator<Type>(chunks, this);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void add(Type element) {

		if (chunks.containsKey(element.getIndexX())) {
			ConcurrentHashMap<Integer, Type> yMap = chunks.get(element
					.getIndexX());
			if (!yMap.containsKey(element.getIndexY())) {
				yMap.put(element.getIndexY(), element);
				elementSize++;
			}
		} else {
			ConcurrentHashMap<Integer, Type> yChunkMap = new ConcurrentHashMap<Integer, Type>();
			yChunkMap.put(element.getIndexY(), element);
			chunks.put(element.getIndexX(), yChunkMap);
			elementSize++;
		}

	}

	public void remove(int x, int y) {
		// Y axis
		ConcurrentHashMap<Integer, Type> yChunkMap = chunks.get(x);

		if (yChunkMap != null) {
			yChunkMap.remove(y);
			// X axis
			if (yChunkMap.isEmpty()) {
				chunks.remove(x);
			}
			elementSize--;
		}
	}

	public MatrixList<Type> copy() {
		MatrixList<Type> copyList = new MatrixList<Type>();
		for (Type element : this) {
			copyList.add(element);
		}
		return copyList;
	}

	public boolean contains(int x, int y) {
		try {
			get(x, y);
			return true;
		} catch (EntryNotFoundException e) {
			return false;
		}
	}

	public boolean contains(Type element) {
		return contains(element.getIndexX(), element.getIndexY());
	}

	public Type get(int x, int y) throws EntryNotFoundException {
		ConcurrentHashMap<Integer, Type> yChunkMap = chunks.get(x);

		if (yChunkMap != null) {
			Type element = yChunkMap.get(y);

			if (element != null) {
				return element;
			} else {
				throw new EntryNotFoundException(x, y);
			}
		} else {
			throw new EntryNotFoundException(x, y);
		}
	}
	
	public void clear() {
		chunks.clear();
	}

	public int size() {
		return elementSize;
	}

	public void set(MatrixList<Type> newContent) {
		this.elementSize = newContent.elementSize;
		this.chunks.putAll(newContent.chunks);
	}

}
