package de.myreality.galacticum;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Galacticum";
		cfg.useGL20 = true;
		cfg.width = 1366;
		cfg.height = 768;
		cfg.fullscreen =  true;
		cfg.vSyncEnabled = true;
		cfg.useCPUSynch = true;
	
		new LwjglApplication(new GalacticumGame(), cfg);
	}
}
