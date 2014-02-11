/* Galacticum space game for Android, PC and browser.
 * Copyright (C) 2013  Miguel Gonzalez
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.myreality.galacticum.context;

import java.util.Collection;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.myreality.galacticum.core.World;
import de.myreality.galacticum.entities.Entity;
import de.myreality.galacticum.graphics.GameCamera;
import de.myreality.galacticum.io.ContextConfiguration;
import de.myreality.galacticum.modules.Module;
import de.myreality.galacticum.modules.ModuleException;
import de.myreality.galacticum.player.Player;


/**
 * Game context which represents one single game
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public interface Context {
	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Throws an exception if it can't fint the module
	 * 
	 * @param subsystemClass
	 * @return
	 */
	<Type extends Module> Type getModule(Class<Type> subsystemClass) throws ModuleException;
	
	/**
	 * 
	 * @return
	 */
	World getWorld();
	
	/**
	 * 
	 * @return
	 */
	ContextConfiguration getConfiguration();
	
	/**
	 * 
	 * @return
	 */
	Collection<Entity> getVisibleEntities();

	/**
	 * 
	 * 
	 * @return
	 */
	Player getPlayer();
	
	/**
	 * 
	 * 
	 * @return
	 */
	GameCamera getCamera();
	
	/**
	 * 
	 * @return
	 */
	SpriteBatch getSpriteBatch();
	
	/**
	 * 
	 * 
	 * @return
	 */
	TweenManager getTweenManager();
	
	/**
	 * 
	 */
	void dispose();
}
