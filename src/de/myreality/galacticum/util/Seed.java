package de.myreality.galacticum.util;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Seed for the world generation
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class Seed implements Serializable {

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

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return base.hashCode();
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

	/**
	 * @return A new generated base string
	 * @param bits
	 *            Bits of the base
	 * @param range
	 *            range of the base
	 */
	public String generateBase(int bits, int range) {
		return new BigInteger(bits, random).toString(subRandom.nextInt(range));
	}

	public String generateBase() {
		return generateBase(subRandom.nextInt(BASE_SIZE),
				subRandom.nextInt(BASE_SIZE));
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
