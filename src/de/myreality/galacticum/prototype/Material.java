package de.myreality.galacticum.prototype;

import org.newdawn.slick.Color;

/**
 * Material class for weapons and shields
 * 
 * @author Miguel Gonzalez
 */
public enum Material {
	
	NONE(new Color(255, 255, 255), "unknown"),
	ION(new Color(0, 255, 0), "Ion"), 
	PLASMA(new Color(0, 50, 255), "Plasma"), 
	LASER(new Color(255, 0, 0), "Laser"), 
	RHADEON(new Color(255, 50, 0), "Rhadeon");
	
	private Color color;
	private String name;
	
	Material(Color color, String name) {
		this.color = color;
		this.name = name;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean equals(Material material) {
		return color.equals(material.getColor()) && name.equals(material.getName());
	}
}
