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
    private static final List<String> RARE = List.of("2", "8");
    private static final List<String> EPIC = List.of("3", "9");
    private static final List<String> LEGENDARY = List.of("11", "4");
    private static final List<String> MYTHIC = List.of("6");

    private float legendaryFactor = 0.0f; // value in [0, 1], e.g. 0.10 for +10%

    public void increaseLegendaryFactor(float increase) {
        legendaryFactor = Math.min(legendaryFactor + increase, 1.0f); // clamp to 1
    }

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
    public void rollRarity() {
        //Base probabilities (total = 1000)
        int commonWeight = 775;
        int rareWeight = 150;
        int epicWeight = 50;
        int legendaryWeight = 20;

        // Adjust weights
        int addedLegendary = (int)(legendaryFactor * 1000);
        legendaryWeight += addedLegendary;
        commonWeight -= addedLegendary;

        int roll = random.nextInt(1000) + 1; // 1–1000

        if (roll <= commonWeight) {
            rarity = COMMON;
        } else if (roll <= commonWeight + rareWeight) {
            rarity = RARE;
        } else if (roll <= commonWeight + rareWeight + epicWeight) {
            rarity = EPIC;
        } else if (roll <= commonWeight + rareWeight + epicWeight + legendaryWeight) {
            rarity = LEGENDARY;
        } else {
            rarity = MYTHIC;
        }
    }
    
}
