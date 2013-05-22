package de.myreality.galacticum.prototype;

import java.util.ArrayList;


/**
 * Basic family class to create a proper family structure with
 * any object.
 * 
 * @author Miguel Gonzalez
 *
 * @param <T>
 */
public class FamilyObject<T> {

	
	// Child elements
	private ArrayList<FamilyObject<T> > children;
	
	// Parent element
	private FamilyObject<T> parent;
	
	public FamilyObject() {
		children = new ArrayList<FamilyObject<T> >();
		parent = null;
	}
	
	
	/**
	 * Align element to a parent one
	 */
	public void attachTo(FamilyObject<T> parent) {
		detach();
		this.parent = parent;
	}
	
	public void detach() {
		if (hasParent()) {
			parent.removeChild(this);
			this.parent = null;
		}
	}
	
	public void addChild(FamilyObject<T> child) {
		if (!children.contains(child)) {
			children.add(child);
			child.attachTo(this);
		}
	}
	
	public void removeChild(FamilyObject<T> child) {
		if (children.contains(child)) {
			children.remove(child);
		}
	}
	
	public FamilyObject<T> getParent() {
		return parent;
	}
	
	public boolean hasChild(FamilyObject<T> child) {
		return children.contains(child);
	}
	
	public FamilyObject<T> getChild(int index) {
		return children.get(index);
	}
	
	public int getNumberOfChildren() {
		return children.size();
	}
	
	
	public boolean isChildOf(FamilyObject<T> parent) {
		if (hasParent()) {
			return getParent().equals(parent);
		} else {
			return false;
		}
	}
	
	
	public boolean hasParent() {
		return getParent() != null;
	}
	
	
	public void removeAllChildren() {
		for (FamilyObject<T> child : children) {
			child.detach();
		}
	}
	
}
