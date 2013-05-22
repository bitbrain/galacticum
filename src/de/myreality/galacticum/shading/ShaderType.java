package de.myreality.galacticum.shading;

/**
 * Shader type of a specific shader
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public enum ShaderType {

	// ===========================================================
	// Types
	// ===========================================================

	RADIAL_BLUR("radialblur") {

		@Override
		protected Shader loadShader() {
			return new RadialBlurShader(getVertexPath(), getFragmentPath());
		}

	},
	
	BLUR("blur") {

		@Override
		protected Shader loadShader() {
			return new BlurShader(getVertexPath(), getFragmentPath());
		}
	},
	
	BLOOM("bloom") {

		@Override
		protected Shader loadShader() {
			return new BloomShader(getVertexPath(), getFragmentPath());
		}
		
		
	},
	
	CRT("crt") {

		@Override
		protected Shader loadShader() {
			return new CRTShader(getVertexPath(), getFragmentPath());
		}
		
		
	},
	
	STAR("star") {

		@Override
		protected Shader loadShader() {
			return new StarShader(getVertexPath(), getFragmentPath());
		}
		
	};

	// ===========================================================
	// Constants
	// ===========================================================

	public static final String SHADER_PATH = "res/shaders/";
	public static final String FRAGMENT_EXTENSION = "frag";
	public static final String VERTEX_EXTENSION = "vert";

	// ===========================================================
	// Fields
	// ===========================================================

	private String namespace;

	private Shader shader;

	// ===========================================================
	// Constructors
	// ===========================================================

	ShaderType(String namespace) {
		this.namespace = namespace;
		this.shader = loadShader();
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	public String getFragmentPath() {
		return SHADER_PATH + namespace + "." + FRAGMENT_EXTENSION;
	}

	public String getVertexPath() {
		return SHADER_PATH + namespace + "." + VERTEX_EXTENSION;
	}

	public Shader getShader() {
		return shader;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected abstract Shader loadShader();
}
