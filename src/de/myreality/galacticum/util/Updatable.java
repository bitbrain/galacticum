package de.myreality.galacticum.util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Updatable interface for slick operations
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface Updatable {

    void update(GameContainer gc, StateBasedGame sbg, int delta);
}
