package eol.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import eol.components.StatsComponent;
import eol.entities.Player;

public class Item {
    HashMap<String, Integer> statModifiers = new HashMap<>();
    private String Id;
    private String Name;
    private String Rarity;

    public Item(String Id, String Name, String Rarity, HashMap<String, Integer> statModifiers) {
        this.Id = Id;
        this.Name = Name;
        this.Rarity = Rarity;
        this.statModifiers = statModifiers;
    }

    public Item(String Id, String Name, String Rarity) {
        this.Id = Id;
        this.Name = Name;
        this.Rarity = Rarity;
    }

    public void applyStats(Player player) {
        StatsComponent stats = player.getStatsComponent();
        stats.setHealth(Math.max(1, stats.getHealth() + statModifiers.getOrDefault("health", 0)));
        stats.setSpeed(Math.max(1, stats.getSpeed() + statModifiers.getOrDefault("speed", 0)));
        stats.setStrength(Math.max(1, stats.getStrength() + statModifiers.getOrDefault("strength", 0)));
        stats.setDexterity(Math.max(1, stats.getDexterity() + statModifiers.getOrDefault("dexterity", 0)));
        player.getHealthComponent().calculateMaxHealth();

        //trigger effect based on item
        if (Id.equals("1")) {
            player.getHealthComponent().heal((int)(player.getHealthComponent().getMaxHealth() * 0.25));
        }
    }

    public List<String> getStatLabels() {
        List<String> statLabels = new ArrayList<>();
        String text;
        int stat;

        if (statModifiers.containsKey("health")) {
            stat = statModifiers.get("health");
            text = (stat < 0) ? ("Health: " + stat) : ("Health: " + "+" + stat);
            statLabels.add(text);
        }
        if (statModifiers.containsKey("speed")) {
            stat = statModifiers.get("speed");
            text = (stat < 0) ? ("Speed: " + stat) : ("Speed: " + "+" + stat);
            statLabels.add(text);
        }
        if (statModifiers.containsKey("strength")) {
            stat = statModifiers.get("strength");
            text = (stat < 0) ? ("Strength: " + stat) : ("Strength: " + "+" + stat);
            statLabels.add(text);
        }
        if (statModifiers.containsKey("dexterity")) {
            stat = statModifiers.get("dexterity");
            text = (stat < 0) ? ("Dexterity: " + stat) : ("Dexterity: " + "+" + stat);
            statLabels.add(text);
        }
        if (Id.equals("1")) {
            text = "Heals 25% of max HP";
            statLabels.add(text);
        }
        if (Id.equals("2")) {
            text = "Heals 50% of max HP over time";
            statLabels.add(text);
        }
        if (Id.equals("6")) {
            text = "Chance for legendary items to";
            statLabels.add(text);
            text = "appear increases by 15%";
            statLabels.add(text);
        }
        if (Id.equals("11")) {
            text = "Increases every stat by 10 for 10 seconds";
            statLabels.add(text);
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
        return Rarity;
    }

}