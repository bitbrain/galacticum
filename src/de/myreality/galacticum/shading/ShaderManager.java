package de.myreality.galacticum.shading;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;


/**
 * Manager that manages different shaders
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class ShaderManager {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static ShaderManager instance;

	private boolean supported;
	
	private List<ShaderType> types;

	// ===========================================================
	// Constructors
	// ===========================================================

	static {
		instance = new ShaderManager();
	}

	private ShaderManager() {
		supported = ShaderProgram.isSupported();
		types = new ArrayList<ShaderType>();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public static ShaderManager getInstance() {
		return instance;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void addShader(ShaderType type) {
		types.add(type);
	}
	
	public void clear() {
		types.clear();
	}
	
	public void apply(Image buffer) {
		bind();
		buffer.draw();
		unbind();
	}

	public boolean isSupported() {
		return supported;
	}

	private void bind() {
		for (ShaderType type : types) {
			type.getShader().bind();
		}
	}

	private void unbind() {
		for (int i = types.size() - 1; i >= 0; i--) {
			types.get(i).getShader().unbind();
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
