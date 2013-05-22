package de.myreality.galacticum.util;

import java.io.Serializable;

/**
 * Difficulty of the game
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public enum GameDifficulty implements Serializable {

	FRIENDLY {
		@Override
		public String toString() {
			return "Friendly";
		}
	},
	NORMAL {
		@Override
		public String toString() {
			return "Normal";
		}
	},
	HARD {
		@Override
		public String toString() {
			return "Hard";
		}
	};

}
