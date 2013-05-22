package de.myreality.galacticum.prototype;


/**
 * Tracks a game
 * 
 * @author Miguel Gonzalez
 *
 */
public class GameTracker {

	private int totalOwnDamage;
	
	private int totalOtherDamage;
	
	private int totalHeal;
	
	private int totalExperience;
	
	private int kills;
	
	public GameTracker() {
		
	}
	
	public void addKill() {
		++kills;
	}
	
	public void addHeal(int value) {
		totalHeal += value;
	}
	
	public void addExperience(int value) {
		totalExperience += value;
	}

	public void addOwnDamage(int value) {
		totalOwnDamage += value;
	}
	
	public void addOtherDamage(int value) {
		totalOtherDamage += value;
	}

	public int getTotalOwnDamage() {
		return totalOwnDamage;
	}

	public int getTotalOtherDamage() {
		return totalOtherDamage;
	}

	public int getTotalHeal() {
		return totalHeal;
	}

	public void setTotalHeal(int totalHeal) {
		this.totalHeal = totalHeal;
	}

	public int getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(int totalExperience) {
		this.totalExperience = totalExperience;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}
	
	public int getTotalPoints() {
		return kills + totalHeal + totalOwnDamage;
	}
	
	public String getPointsAsString(int totalPoints) {
		String points = String.valueOf(totalPoints);
		String result = "";
		for (int index = 0; index < points.length(); index++) {
			if ((points.length() - index) % 4 == 0) {
				result += points.charAt(index) + ".";
			} else {
				result += points.charAt(index);
			}
		}
		return result;
	}
	
	public String getPointsAsString() {
		return getPointsAsString(getTotalPoints());
	}
}
