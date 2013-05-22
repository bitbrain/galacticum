package de.myreality.galacticum.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * State of the camera distance
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public enum CameraDistance implements Serializable {

	SHORT {
		@Override
		public String toString() {
			return "Short";
		}
	},
	NORMAL {
		@Override
		public String toString() {
			return "Normal";
		}
	},
	FAR {
		@Override
		public String toString() {
			return "Far";
		}
	};

	public static List<CameraDistance> getAll() {
		List<CameraDistance> distances = new ArrayList<CameraDistance>();
		for (CameraDistance distance : values()) {
			distances.add(distance);
		}
		return distances;
	}

}
