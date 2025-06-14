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
    private List<String> oneTimeItems = List.of("4");
    private float legendaryFactor = 0.0f; // value in [0, 1], e.g. 0.10 for +10%

    private List<String> COMMON;
    private List<String> RARE;
    private List<String> EPIC;
    private List<String> LEGENDARY;
    private List<String> MYTHIC;

    public LootManager() {
        COMMON = new ArrayList<>(List.of("1", "5", "7", "10"));
        RARE = new ArrayList<>(List.of("2", "8"));
        EPIC = new ArrayList<>(List.of("3", "9"));
        LEGENDARY = new ArrayList<>(List.of("11", "4"));
        MYTHIC = new ArrayList<>(List.of("6"));
    }

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
     * common (67.5%)
     * rare (25%)
     * epic (5%)
     * legendary (2%)
     * mythic (0.5%)
     */
    public void rollRarity() {
        //Base probabilities (total = 1000)
        int commonWeight = 675;
        int rareWeight = 250;
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

    public void updatePool(Item selectedItem) {
        String id = selectedItem.getId();
        if (oneTimeItems.contains(id)) {
            COMMON.remove(id);
            RARE.remove(id);
            EPIC.remove(id);
            LEGENDARY.remove(id);
            MYTHIC.remove(id);
        }
    }
    
}
