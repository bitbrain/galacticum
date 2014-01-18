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
package de.myreality.galacticum.zones;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.myreality.galacticum.context.Context;
import de.myreality.galacticum.entities.SpaceShip;
import de.myreality.galacticum.modules.ModulePrototype;
import de.myreality.galacticum.player.Player;
import de.myreality.galacticum.player.PlayerListenerPrototype;
import de.myreality.galacticum.player.PlayerModule;
import de.myreality.galacticum.util.ColorProvider;
import de.myreality.galacticum.util.GameColor;
import de.myreality.galacticum.util.HashGenerator;
import de.myreality.galacticum.util.Seed;
import de.myreality.galacticum.util.SimpleHashGenerator;

/**
 * Module which handles zones in space
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public class SpaceZoneModule extends ModulePrototype implements ColorProvider,
		ZoneHandler, HashGenerator {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private GameColor color;

	private TargetHandler target;

	private long oldHash;

	private HashGenerator hashGenerator;

	private Set<ZoneListener> listeners;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SpaceZoneModule(Seed seed) {
		color = new GameColor(0.1f, 0.0f, 0.1f, 0.7f);
		listeners = new HashSet<ZoneHandler.ZoneListener>();
		hashGenerator = new SimpleHashGenerator(seed);
		oldHash = seed.get();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public String getName() {
		return "zones";
	}

	@Override
	public void onEnter(Context context) {
		super.onEnter(context);

		PlayerModule playerModule = context.getModule(PlayerModule.class);
		Player player = playerModule.getPlayer();
		SpaceShip currentPlayerShip = player.getCurrentShip();
		this.target = new TargetHandler(currentPlayerShip);
		player.addListener(target);
		this.oldHash = generateHash();
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		if (target != null) {

			long currentHash = generateHash();

			if (oldHash != currentHash) {

				for (ZoneListener l : getListeners()) {
					l.onLeaveZone(oldHash, target);
					l.onEnterZone(currentHash, target);
				}
			}

			this.oldHash = currentHash;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.ColorProvider#getColor()
	 */
	@Override
	public GameColor getColor() {
		return color;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private long generateHash() {
		return hashGenerator.generate(target.getX(), target.getY());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Observer#addListener(java.lang.Object)
	 */
	@Override
	public void addListener(ZoneListener listener) {
		listeners.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.myreality.galacticum.util.Observer#removeListener(java.lang.Object)
	 */
	@Override
	public void removeListener(ZoneListener listener) {
		listeners.remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Observer#hasListener(java.lang.Object)
	 */
	@Override
	public boolean hasListener(ZoneListener listener) {
		return listeners.contains(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.util.Observer#getListeners()
	 */
	@Override
	public Collection<ZoneListener> getListeners() {
		return listeners;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.zones.Zone#getAmbientColor()
	 */
	@Override
	public GameColor getAmbientColor() {
		return getColor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.myreality.galacticum.zones.ZoneHandler#getHashGenerator()
	 */
	@Override
	public HashGenerator getHashGenerator() {
		return hashGenerator;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	private class TargetHandler extends PlayerListenerPrototype implements
			ZoneTarget {

		private SpaceShip ship;

		public TargetHandler(SpaceShip initialShip) {
			this.ship = initialShip;
		}

		@Override
		public void onSetCurrentShip(SpaceShip oldShip, SpaceShip newShip,
				Player player) {
			super.onSetCurrentShip(oldShip, newShip, player);
			this.ship = newShip;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see de.myreality.galacticum.zones.ZoneTarget#getX()
		 */
		@Override
		public float getX() {
			return ship.getX();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see de.myreality.galacticum.zones.ZoneTarget#getY()
		 */
		@Override
		public float getY() {
			return ship.getY();
		}

	}

	/* (non-Javadoc)
	 * @see de.myreality.galacticum.util.HashGenerator#generate(float, float)
	 */
	@Override
	public long generate(float x, float y) {
		return hashGenerator.generate(x, y);
	}

}
