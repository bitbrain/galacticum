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
package de.myreality.galacticum.util;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Provides seed functionality for universes
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public final class Seed implements Serializable {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 1L;

	private static final int BASE_SIZE = 200;

	// ===========================================================
	// Fields
	// ===========================================================

	// Base of the seed
	String base;
	
	private long customHash;

	// Randomizer
	private SecureRandom random;

	// Sub randomizer
	private Random subRandom;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Seed(String base) {
		random = new SecureRandom();
		subRandom = new Random();

		// Generate a random base if the currents one is empty
		if (base == null || base.isEmpty()) {
			base = generateBase();
		}

		this.base = base;
	}
	
	public Seed() {
		this("");
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/**
	 * @param generate
	 */
	public Seed(long hash) {
		this.base = null;
		customHash = hash;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (base != null) {
			return base.hashCode();
		} else {
			return Math.round(customHash);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return base;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	public int get() {
		return hashCode();
	}

	/**
	 * @return A new generated base string
	 * @param bits Bits of the base
	 * @param range range of the base
	 */
	private String generateBase(int bits, int range) {
		return new BigInteger(bits, random).toString(subRandom.nextInt(Math.abs(range + 1)));
	}

	private String generateBase() {
		return generateBase(subRandom.nextInt(BASE_SIZE),
				subRandom.nextInt(BASE_SIZE));
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
