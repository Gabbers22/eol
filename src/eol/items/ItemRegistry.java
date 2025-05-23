package eol.items;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import eol.components.StatsComponent;
import eol.entities.Player;

import java.util.ArrayList;

public class ItemRegistry {
	HashMap<String,Item> items = new HashMap<>();
	HashMap<String, Integer> stats = new HashMap<>(); //temp
	
	// define all the items
	Item obj1 = new Item("1","Adrenaline Injector","Common", stats);
	Item obj2 = new Item("2","Irradiated Blood Pack","Rare", stats);
	Item obj3 = new Item("3","Scrap-Forged Plating ","Epic", stats);
	Item obj4 = new Item("4","Optic Enhancer Implant","Rare", stats);
	Item obj5 = new Item("5","Mutagenic Serum","Common", stats);
	Item obj6 = new Item("6","Wanderer's Lucky Charm","Mythic", stats);
	Item obj7 = new Item("7","Power Core Fragment","Common", stats);
	Item obj8 = new Item("8","Caffeine-Stim Rig ","Rare", stats);
	Item obj9 = new Item("9","Neural Sync Collar","Legendary", stats);
	Item obj10 = new Item("10","Cryo-Vest Lining","Common", stats);
	Item obj11 = new Item("11","Black Market Cyber Chip","Legendary", stats);
	
	public ItemRegistry() {
   		// map each item to a ID	  
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


