package de.myreality.galacticum;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import de.myreality.dev.chronos.util.FileHelper;

/**
 * This file is part of Chronos (Myreality Game Development Toolkit). Chronos is
 * licenced under GNU LESSER GENERAL PUBLIC LICENSE (Version 3)
 * 
 * For more information visit http://dev.my-reality.de/chronos
 * 
 * Example for scanning the current game setup. This is necessary for updating
 * the game correctly.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 */
public class FileScanner {

	public static void main(String[] args) throws FileNotFoundException {

		ArrayList<String> directories = new ArrayList<String>();
		directories.add("res/");
		FileHelper.getInstance().scanDirectories(directories);
	}

}
