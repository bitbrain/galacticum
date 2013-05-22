package de.myreality.galacticum.prototype;

public class Vector {

	public float x;
	public float y;
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	// TODO: Create angle to vector calculation
	public Vector(float angle) {
		
		x = (float) (Math.cos(Math.toRadians(angle)));
		y = (float) (Math.sin(Math.toRadians(angle)));

		// y-Achse invertieren
		y = -y;
		
		normalize();
		//System.out.println("Angle: " + angle + " | " + x + " | " + y);
	}
	
	public Vector(Point point1, Point point2) {
		this.x = point2.x - point1.x;
		this.y = point2.y - point1.y;
	}
	
	public double length() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public float getAngle() {
		return (float)Math.toDegrees(Math.acos(x / length()));
	}
	
	public void normalize() {
		double length = length();
		x /= length;
		y /= length;
	}

	@Override
	public String toString() {
		return x + " | " + y;
	}
	
	
	
}
