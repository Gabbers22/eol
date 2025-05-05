package eol.items;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;

public class ItemRegistry {
    private Map<Item, Integer> items;

    public ItemRegistry() {
        HashMap<Item,Integer> items = new HashMap<>();
        int  Nextid=1;

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
        

        items.put(obj2,Nextid++);
        items.put(obj3,Nextid++);
        items.put(obj4,Nextid++);
        items.put(obj5,Nextid++);
        items.put(obj6,Nextid++);
        items.put(obj7,Nextid++);
        items.put(obj8,Nextid++);
        items.put(obj9,Nextid++);
        items.put(obj10,Nextid++);
        items.put(obj11,Nextid++);
        items.put(obj1,Nextid++); 
    }

    public Integer get(String Item) { 
        return items.get(Item);
    }

}