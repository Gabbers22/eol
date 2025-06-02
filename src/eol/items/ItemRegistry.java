package eol.items;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import eol.components.StatsComponent;
import eol.entities.Player;

import java.util.ArrayList;

public class ItemRegistry {
    HashMap<String, Item> items = new HashMap<>();

    // define all the items
    Item obj1 = new Item("1", "Adrenaline Injector", "Common");
    Item obj2 = new Item("2", "Irradiated Blood Pack", "Rare");
    Item obj3 = new Item("3", "Scrap-Forged Plating ", "Epic", new HashMap<String, Integer>(Map.of("health", 5)));
    Item obj4 = new Item("4", "Optic Enhancer Implant", "Legendary");
    Item obj5 = new Item("5", "Mutagenic Serum", "Common", new HashMap<String, Integer>(Map.of("health", -4, "strength", 5)));
    Item obj6 = new Item("6", "Wanderer's Lucky Charm", "Mythic");
    Item obj7 = new Item("7", "Power Core Fragment", "Common", new HashMap<String, Integer>(Map.of("strength", 3)));
    Item obj8 = new Item("8", "Caffeine-Stim Rig ", "Rare", new HashMap<String, Integer>(Map.of("dexterity", 2)));
    Item obj9 = new Item("9", "Neural Sync Collar", "Epic", new HashMap<String, Integer>(Map.of("health", 4, "speed", 4, "strength", 4, "dexterity", 4)));
    Item obj10 = new Item("10", "Cryo-Vest Lining", "Common", new HashMap<String, Integer>(Map.of("speed", 2)));
    Item obj11 = new Item("11", "Black Market Cyber Chip", "Legendary");

    public ItemRegistry() {
        // map each item to an ID
        items.put(obj1.getId(), obj1);
        items.put(obj2.getId(), obj2);
        items.put(obj3.getId(), obj3);
        items.put(obj4.getId(), obj4);
        items.put(obj5.getId(), obj5);
        items.put(obj6.getId(), obj6);
        items.put(obj7.getId(), obj7);
        items.put(obj8.getId(), obj8);
        items.put(obj9.getId(), obj9);
        items.put(obj10.getId(), obj10);
        items.put(obj11.getId(), obj11);
    }

    // getter method to retrieve item by id
    public Item getItemById(String Id) {
        return items.get(Id);
    }
}


