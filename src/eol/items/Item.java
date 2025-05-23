package eol.items;

import java.util.HashMap;
import java.util.HashSet;

import eol.components.StatsComponent;

public class Item {
    HashMap<String, Integer> statModifiers = new HashMap<>();
    private String Id;
	private String Name;
	private String Rairty;
 

	public Item(String Id, String Name, String Rairty, HashMap<String, Integer> statModifiers) {
        this.Id = Id;
        this.Name = Name;
        this.Rairty = Rairty;
		this.statModifiers = statModifiers;
	}

	public void applyStats(StatsComponent stats) {
		stats.setHealth(Math.max(1, stats.getHealth() + statModifiers.get("health")));
		stats.setSpeed(Math.max(1, stats.getSpeed() + statModifiers.get("speed")));
		stats.setStrength(Math.max(1, stats.getStrength() + statModifiers.get("strength")));
		stats.setDexterity(Math.max(1, stats.getDexterity() + statModifiers.get("dexterity")));
	}

	public HashSet<String> getStatLabels() {
		HashSet<String> statLabels = new HashSet<>();

		if (statModifiers.containsKey("health")) {
			statLabels.add("Health: " + statModifiers.get("health"));
		}
		if (statModifiers.containsKey("speed")) {
			statLabels.add("Speed: " + statModifiers.get("speed"));
		}
		if (statModifiers.containsKey("strength")) {
			statLabels.add("Strength: " + statModifiers.get("strength"));
		}
		if (statModifiers.containsKey("dexterity")) {
			statLabels.add("Dexterity: " + statModifiers.get("dexterity"));
		}
		return statLabels;
	}
	
	public String getId() {
		return Id;	
	}

	public String getName() {
		return Name;	
	}

	public String getRarity() {
		return Rairty;	
	}



}

