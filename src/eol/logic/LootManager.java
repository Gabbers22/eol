package eol.logic;

import java.util.HashSet;
import java.util.Random;

import eol.items.Item;
import eol.items.ItemRegistry;

public class LootManager {
  
  int randomNumber = new Random().nextInt(101);
  int randomItem;
  Random random = new Random();
  HashSet<Item> currentItems = new HashSet<>();
  ItemRegistry itemRegistry = new ItemRegistry();
  
  public void chooseItem() {

    for(int counter = 0; counter < 4; counter++){

      if(randomNumber >= 1 && randomNumber <= 50){
        randomItem = random.nextInt(4) + 1;
        currentItems.add(itemRegistry.getItemById(String.valueOf(randomItem)));
      }
      else if(randomNumber >= 51 && randomNumber <= 75){
        randomItem = random.nextInt(3) + 5;
        currentItems.add(itemRegistry.getItemById(String.valueOf(randomItem)));
      }
      else if(randomNumber >= 76 && randomNumber <= 90){
        currentItems.add(itemRegistry.getItemById(String.valueOf(8)));
      }
      else if(randomNumber >= 91 && randomNumber <= 97){
        randomItem = random.nextInt(2) + 9;
        currentItems.add(itemRegistry.getItemById(String.valueOf(randomItem)));
      } 
      else{
        currentItems.add(itemRegistry.getItemById(String.valueOf(11)));
      }
    } 
  }
}

