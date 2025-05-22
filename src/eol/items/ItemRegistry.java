package eol.items;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import eol.components.StatsComponent;
import eol.entities.Player;

import java.util.ArrayList;

public class ItemRegistry {
	HashMap<String,Item> items = new HashMap<>();
	
	// define all the items
	Item obj1 = new Item("001","Adrenaline Injector","Common");
	Item obj2 = new Item("002","Irradiated Blood Pack","Rare");
	Item obj3 = new Item("003","Scrap-Forged Plating ","Epic");
	Item obj4 = new Item("004","Optic Enhancer Implant","Rare");
	Item obj5 = new Item("005","Mutagenic Serum","Common");
	Item obj6 = new Item("006","Wanderer's Lucky Charm","Mythic");
	Item obj7 = new Item("007","Power Core Fragment","Common");
	Item obj8 = new Item("008","Caffeine-Stim Rig ","Rare");
	Item obj9 = new Item("009","Neural Sync Collar","Legendary");
	Item obj10 = new Item("010","Cryo-Vest Lining","Common");
	Item obj11 = new Item("011","Black Market Cyber Chip","Legendary");
	
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
		return  items.get(Id);
    }
}


