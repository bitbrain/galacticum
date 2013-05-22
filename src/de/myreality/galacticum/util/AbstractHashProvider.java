package de.myreality.galacticum.util;

/**
 * Abstract implementation of an hash provider
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since
 * @version
 */
public abstract class AbstractHashProvider extends AbstractNode<HashProvider>
										   implements HashProvider {


	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.HashProvider#getProvidedHash()
	 */
	@Override
	public final long getHash() {
		
		long childrenHashSum = 0;
		
		for (HashProvider children : getChildren()) {
			childrenHashSum += children.getHash();			
		}
		
		return calculateHash() + childrenHashSum;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected abstract long calculateHash();
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
