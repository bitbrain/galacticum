package de.myreality.galacticum.util;

import java.util.Collection;
import java.util.HashSet;

/**
 * 
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since
 * @version
 */
public abstract class AbstractNode<Type extends Node<Type> > implements Node<Type> {

	// ===========================================================
	// Constants
	// ===========================================================
	
	// ===========================================================
	// Fields
	// ===========================================================

	private Collection<Type> parents;
	
	private Collection<Type> children;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public AbstractNode() {
		children = new HashSet<Type>();
		parents = new HashSet<Type>();
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Node#getParent()
	 */
	@Override
	public Collection<Type> getParents() {
		return parents;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Node#hasParent()
	 */
	@Override
	public boolean hasParent() {
		return !parents.isEmpty();
	}
	
	

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Node#hasChild(java.lang.Object)
	 */
	@Override
	public boolean hasChild(Type child) {
		return children.contains(child);
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Node#hasParent(java.lang.Object)
	 */
	@Override
	public boolean hasParent(Type node) {
		return hasParent() && parents.contains(node);
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Node#addChild(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addChild(Type child) {
		if (child != this) {
			children.add(child);
			if (!child.hasParent((Type) this)) {
				child.addParent((Type) this);
			}
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Node#removeChild(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void removeChild(Type child) {
		if (children.contains(child)) {
			children.remove(child);
			if (child.hasParent((Type) this)) {
				child.removeParent((Type) this);
			}
		}
	}
	
	

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Node#removeParent(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void removeParent(Type parent) {
		if (parent != this) {
			parents.remove(parent);
			if (parent.hasChild((Type) this)) {
				parent.removeChild((Type) this);
			}
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Node#setParent(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addParent(Type parent) {
		if (parent != this) {
			parents.add(parent);
			if (!parent.hasChild((Type) this)) {
				parent.addChild((Type) this);
			}
		}
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Node#hasChildren()
	 */
	@Override
	public boolean hasChildren() {
		return !children.isEmpty();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Node#getNumberOfChildren()
	 */
	@Override
	public int getNumberOfChildren() {
		return children.size();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Node#getNumberOfParents()
	 */
	@Override
	public int getNumberOfParents() {
		return parents.size();
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Node#getChildren()
	 */
	@Override
	public Collection<Type> getChildren() {
		return children;
	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.Node#clearChildren()
	 */
	@Override
	public void clearChildren() {
		for (Type child : children) {
			child.addParent(null);
		}
		children.clear();
	}

	
	
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
