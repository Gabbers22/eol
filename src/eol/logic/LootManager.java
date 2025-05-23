package eol.logic;

import java.util.HashSet;
import java.util.Random;

import eol.items.Item;
import eol.items.ItemRegistry;

public class LootManager {
  
  int randomItem;
  Random random = new Random();
  HashSet<Item> currentItems = new HashSet<>();
  ItemRegistry itemRegistry = new ItemRegistry();
  
  public void chooseItem() {
    currentItems.clear();

    for (int counter = 0; counter < 4; counter++) {
      int randomNumber = random.nextInt(101);
      Item item = null;

      if (randomNumber <= 50) {
          item = itemRegistry.getItemById(String.valueOf(random.nextInt(4) + 1));
      } else if (randomNumber <= 75) {
          item = itemRegistry.getItemById(String.valueOf(random.nextInt(3) + 5));
      } else if (randomNumber <= 90) {
          item = itemRegistry.getItemById("8");
      } else if (randomNumber <= 97) {
          item = itemRegistry.getItemById(String.valueOf(random.nextInt(2) + 9));
      } else {
          item = itemRegistry.getItemById("11");
      }

      if (item != null) {
        currentItems.add(item);
        System.out.println("added item");
      } else {
        System.out.println("not added");
      }
    }
  }

  public HashSet<Item> getItems() {
    return currentItems;
  }

}

