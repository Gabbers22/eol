package eol.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import eol.items.Item;
import eol.items.ItemRegistry;

public class LootManager {

    private final Random random = new Random();
    private final ItemRegistry itemRegistry = new ItemRegistry();
    private List<String> rarity;

    private static final List<String> COMMON = List.of("1", "5", "7", "10");
    private static final List<String> RARE = List.of("2", "4", "8");
    private static final List<String> EPIC = List.of("3");
    private static final List<String> LEGENDARY = List.of("9", "11");
    private static final List<String> MYTHIC = List.of("6");

    public List<Item> chooseItems() {
        List<Item> picks = new ArrayList<>(4);

        while (picks.size() < 4) {
            rollRarity();

            // Try to find a non-duplicate in the selected rarity
            String id;
            Item item;
            int tries = 0;
            do {
                id = rarity.get(random.nextInt(rarity.size()));
                item = itemRegistry.getItemById(id);
                tries++;
            } while (item != null && picks.contains(item) && tries < rarity.size());

            // If non-duplicate found, add it
            if (item != null && !picks.contains(item)) {
                picks.add(item);
            }
        }
        return picks;
    }

    /**
     * 1–775: common (77.5%)
     * 776–925: rare (15%)
     * 926–975: epic (5%)
     * 976–995: legendary (2%)
     * 996–1000: mythic (0.5%)
     */

     /*
    public void rollRarity() {
        int roll = random.nextInt(1000) + 1; // 1–1000
        if (roll <= 775) {
            rarity = COMMON;
        } else if (roll <= 925) {
            rarity = RARE;
        } else if (roll <= 975) {
            rarity = EPIC;
        } else if (roll <= 995) {
            rarity = LEGENDARY;
        } else {
            rarity = MYTHIC;
        }
    }
    */

    
    public void rollRarity() {
        int roll = random.nextInt(1000) + 1; // 1–1000
        if (roll <= 250) {
            rarity = COMMON;
        } else if (roll <= 500) {
            rarity = RARE;
        } else if (roll <= 750) {
            rarity = EPIC;
        } else if (roll <= 950) {
            rarity = LEGENDARY;
        } else {
            rarity = MYTHIC;
        }
    }
    
}
