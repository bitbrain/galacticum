package de.myreality.galacticum;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Galacticum";
		cfg.useGL20 = true;
		cfg.width = 1920;
		cfg.height = 1080;
		cfg.fullscreen =  false;
		cfg.vSyncEnabled = true;
		cfg.backgroundFPS = 10;
	
		new LwjglApplication(new GalacticumGame(), cfg);
	}
}
