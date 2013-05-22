/**
 * This file was written by Miguel Gonzalez and is part of the
 * game "LittleWars". For more information mailto info@my-reality.de
 * or visit the game page: http://dev.my-reality.de/littlewars
 * 
 * Provides a resource manager that's compatible with:
 * 
 * - ConfigSound
 * - ConfigMusic
 * - Image
 * - ResourceAnimationData
 * - String
 * - Font
 * - Color
 * 
 * @version 	0.0.1
 * @author 		Miguel Gonzalez		
 */

package de.myreality.galacticum.prototype;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
public class ResourceManager {

	// Single instance of the manager
	private static ResourceManager _instance = new ResourceManager();
 
	// Map containing all sounds that are defined in XML
	private Map<String, Sound> soundMap;
	
	// Map containing all musics that are defined in XML
	private Map<String, Music> musicMap;
	
	// Map containing all images that are defined in XML
	private Map<String, Image> imageMap;	
	
	// Map containing all strings that are defined in XML
	private Map<String, String> textMap;
	
	// Map containing all fonts that are defined in XML
	private Map<String, Font> fontMap;
	
	// Map containing all colors that are defined in XML
	private Map<String, Color> colorMap;
	
	// Map containing all emitters that are defined in XML
	private Map<String, ConfigurableEmitter> emitterMap;
	
	// Map containing all animations
	private Map<String, Animation[]> animationMap;
	
	// List of all animation images
	private List<String> animationSource;

	
	/**
	 * Constructor of ResourceManager
	 */
	private ResourceManager(){
		// Create and initialize the maps
		soundMap 	    = new HashMap<String,Sound>();
		musicMap 	 	= new HashMap<String, Music>();
		imageMap 	 	= new HashMap<String, Image>();
		textMap 	 	= new HashMap<String, String>();
		fontMap 		= new HashMap<String, Font>();
		colorMap 	 	= new HashMap<String, Color>();
		emitterMap   	= new HashMap<String, ConfigurableEmitter>();
		animationMap    = new HashMap<String, Animation[]>();
		animationSource = new ArrayList<String>();
		loadDefaultResources();
	}
 
	
	/**
	 * @return Single instance of the resource manager
	 */
	public final static ResourceManager getInstance(){
		return _instance;
	}
	
	
	
	public void clear() {
		soundMap.clear();
		musicMap.clear();
		imageMap.clear();
		textMap.clear();
		fontMap.clear();
		emitterMap.clear();
		animationMap.clear();
		animationSource.clear();
	}
 
	
	/**
	 * Loads all resources, defined in a given XML file
	 * 
	 * @param path path to the XML file
	 * @throws SlickException
	 * @throws IOException
	 */
	public void loadResources(String path) throws SlickException, IOException {
		InputStream input;
		input = new FileInputStream(path);
		loadResources(input, false);
		
		input.close();
	}
	
	
	/**
	 * Loads all resources, defined in a given XML file
	 * 
	 * @param path path to the XML file
	 * @throws SlickException
	 * @throws IOException
	 */
	public void loadResources(String path, boolean deref) throws SlickException, IOException {
		InputStream input;
		input = new FileInputStream(path);
		loadResources(input, deref);
		
		input.close();
	}

	
	
	/**
	 * Loads all resources, defined in a given XML file
	 * 
	 * @param path path to the XML file
	 * @param deferred enable deferred loading
	 * @throws SlickException
	 */
	public void loadResources(InputStream is, boolean deferred) throws SlickException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new SlickException("Could not load resources", e);
		}
		Document doc = null;
        try {
			doc = docBuilder.parse (is);
		} catch (SAXException e) {
			throw new SlickException("Could not load resources", e);
		} catch (IOException e) {
			throw new SlickException("Could not load resources", e);
		}
 
		// normalize text representation
        doc.getDocumentElement ().normalize ();
 
        NodeList listResources = doc.getElementsByTagName("resource");
 
        int totalResources = listResources.getLength();
 
        if(deferred){        	
        	LoadingList.setDeferredLoading(true);
        }
        
        for(int resourceIdx = 0; resourceIdx < totalResources; resourceIdx++){
 
        	Node resourceNode = listResources.item(resourceIdx);
 
        	if(resourceNode.getNodeType() == Node.ELEMENT_NODE){
        		Element resourceElement = (Element)resourceNode;
 
        		String type = resourceElement.getAttribute("type");
        		if(type.equals("image")){
        			addElementAsImage(resourceElement);
        		}else if(type.equals("music")){
        			addElementAsMusic(resourceElement);
        		}if(type.equals("sound")){
        			addElementAsSound(resourceElement);
        		}else if(type.equals("text")){
        			addElementAsText(resourceElement);
        		}else if(type.equals("font")){
        			addElementAsFont(resourceElement);
        		} else if(type.equals("color")) {
        			addElementAsColor(resourceElement);
        		} else if(type.equals("emitter")) {
        			//addElementAsEmitter(resourceElement);
        		}        		
        	}
        }
 
	}
	
	
	
	public void loadDefaultResources() {
		try {
			loadResources("prototype-res/colors.xml"); // Colors
			loadResources("prototype-res/meta.xml");   // Meta
			loadResources("prototype-res/fonts.xml");   // Fonts
			//loadResources("res/particles.xml");   // Particles
			loadResources("prototype-res/images.xml"); // Images
			loadResources("prototype-res/music.xml");  // Music
			loadResources("prototype-res/sounds.xml");  // Sounds
		} catch (SlickException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}



	
	/**
	 * Add a text to the map
	 * 
	 * @throws SlickException
	 */
	private void addElementAsText(Element resourceElement) throws SlickException{
		loadText(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}
	
	
	
	/**
	 * Add a color to the map
	 * 
	 * @throws SlickException
	 */
	private void addElementAsColor(Element resourceElement) throws SlickException{
		loadColor(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}
	
	
	
	/**
	 * Add a sound to the map
	 * 
	 * @throws SlickException
	 */
	private void addElementAsSound(Element resourceElement) throws SlickException {
		loadSound(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}
	
	
	
	/**
	 * Add a music element to the map
	 * 
	 * @throws SlickException
	 */
	private void addElementAsMusic(Element resourceElement) throws SlickException {
		loadMusic(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}
	
	
	
	
	/**
	 * Add a font to the map
	 * 
	 * @throws SlickException
	 */
	private void addElementAsFont(Element resourceElement) throws SlickException {
		loadFont(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}
	
	
	/**
	 * Add a image to the map
	 * 
	 * @throws SlickException
	 */
	private final void addElementAsImage(Element resourceElement) throws SlickException {
		loadImage(resourceElement.getAttribute("id"), resourceElement.getTextContent());
		
		if (resourceElement.getAttribute("behavior").equals("frame")) {
			animationSource.add(resourceElement.getAttribute("id"));
		}
	}
	
	
	/**
	 * Loads a color to the map
	 * 
	 * @param id string id in the resource XML
	 * @param value string value
	 * @return Color that has been loaded
	 * @throws SlickException
	 */
	public Color loadColor(String id, String value) throws SlickException {
		if(value == null) {
			throw new SlickException("Color resource [" + id + "] has invalid value");
		}

		Color tmpColor = new Color(
	            Integer.valueOf( value.substring( 1, 3 ), 16 ),
	            Integer.valueOf( value.substring( 3, 5 ), 16 ),
	            Integer.valueOf( value.substring( 5, 7 ), 16 ));
		colorMap.put(id, tmpColor);
		
		return tmpColor;
	}
 
	
	
	/**
	 * Loads a text to the map
	 * 
	 * @param id string id in the resource XML
	 * @param value string value
	 * @return String that has been loaded
	 * @throws SlickException
	 */
	public String loadText(String id, String value) throws SlickException{
		if(value == null)
			throw new SlickException("Text resource [" + id + "] has invalid value");
 
		textMap.put(id, value);
 
		return value;
	}

	
	/**
	 * Loads a sound to the map
	 * 
	 * @param id string id in the resource XML
	 * @param path sound path
	 * @return Sound that has been loaded
	 * @throws SlickException
	 */
	public Sound loadSound(String id, String path) throws SlickException{
		if(path == null || path.length() == 0)
			throw new SlickException("Sound resource [" + id + "] has invalid path");
 
		Sound sound = null;
 
		try {
			sound = new Sound(path);
		} catch (SlickException e) {
			throw new SlickException("Could not load sound", e);
		}
 
		this.soundMap.put(id, sound);
 
		return sound;
	}
	
	
	
	/**
	 * Loads music to the map
	 * 
	 * @param id string id in the resource XML
	 * @param path music path
	 * @return Music that has been loaded
	 * @throws SlickException
	 */
	public Music loadMusic(String id, String path) throws SlickException{
		if(path == null || path.length() == 0)
			throw new SlickException("Music resource [" + id + "] has invalid path");
 
		Music music = null;
 
		try {
			music = new Music(path);
		} catch (SlickException e) {
			throw new SlickException("Could not load sound", e);
		}
		this.musicMap.put(id, music);
 
		return music;
	}
	
	
 
	
	/**
	 * Loads a font to the map
	 * 
	 * @param id string id in the resource XML
	 * @param path font path
	 * @return Font that has been loaded
	 * @throws SlickException
	 */
	public Font loadFont(String id, String path) throws SlickException {
		if(path == null || path.length() == 0)
			throw new SlickException("Font resource [" + id + "] has invalid path");
 
		Font font = null;
 
		try {
			// Image-Pfad erstellen
			String imagePath = path.substring( 0, path.length() - 3);
			StringBuilder builder = new StringBuilder();
			builder.append(imagePath);
			builder.append("png");
			font = new AngelCodeFont(path, builder.toString());
		} catch (SlickException e) {
			throw new SlickException("Could not load sound", e);
		}

		this.fontMap.put(id, font);
 
		return font;
	} 
	 
 
	
 
	
	/**
	 * Loads an image to the map
	 * 
	 * @param id string id in the resource XML
	 * @param path image path
	 * @return Image that has been loaded
	 * @throws SlickException
	 */
	public Image loadImage(String id, String path) throws SlickException{
		Image image = null;
		
		if (this.imageMap.get(id) == null) {
			if(path == null || path.length() == 0)
				throw new SlickException("Image resource [" + id + "] has invalid path");
	 
			
			try{
				image = new Image(path);				
			} catch (SlickException e) {
				throw new SlickException("Could not load image", e);
			}
			this.imageMap.put(id, image);
		} else return this.imageMap.get(id);
 
		return image;
	}
	
	
	/**
	 * Returns the image with the resource ID
	 * 
	 * @param ID resource ID
	 * @return resource image
	 */ 
	public final Image getImage(String ID){
		return imageMap.get(ID);
	}
	


	
	/**
	 * Returns a copy of the animation with the resource ID
	 * 
	 * @param ID resource ID
	 * @return resource animation copy
	 */ 
	public final Animation getNewAnimation(String ID, int direction) {			
		return animationMap.get(ID)[direction].copy();
	}
	

	
	/**
	 * Returns the sound with the resource ID
	 * 
	 * @param ID resource ID
	 * @return resource sound
	 */
	public final Sound getSound(String ID){
		return soundMap.get(ID);
	}
	
	
	
	/**
	 * Returns the music with the resource ID
	 * 
	 * @param ID resource ID
	 * @return resource music
	 */
	public final Music getMusic(String ID){
		return musicMap.get(ID);
	}
	

	/**
	 * Returns the string with the resource ID
	 * 
	 * @param ID resource ID
	 * @return resource string
	 */
	public String getText(String ID) {
		return textMap.get(ID);
	}
	
	
	
	/**
	 * Returns the color with the resource ID
	 * 
	 * @param ID resource ID
	 * @return resource color
	 */
	public Color getColor(String ID) {
		return colorMap.get(ID);
	}
	
	

	
	
	/**
	 * Returns the font with the resource ID
	 * 
	 * @param ID resource ID
	 * @return resource font
	 */
	public Font getFont(String ID) {
		return fontMap.get(ID);
	}
	
	
	// Resource releases
	public boolean releaseImage(String ID) {
		Image image = imageMap.get(ID);
		if (image != null) {
			try {
				image.destroy();
			} catch (SlickException e) {
				e.printStackTrace();
			}
			imageMap.remove(ID);
			return true;
		}
		
		return false;
	}
	
	public boolean releaseMusic(String ID) {
		if (musicMap.get(ID) != null) {
			musicMap.remove(ID);
			return true;
		}
		
		return false;
	}
	
	public boolean releaseSound(String ID) {
		if (soundMap.get(ID) != null) {
			soundMap.remove(ID);
			return true;
		}
		
		return false;
	}
}
