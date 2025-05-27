package eol.render;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class SpriteManager {
    private static String path = "/assets/sprites/";
    private static Map<String, BufferedImage> sprites = new HashMap<>();

    private static SpriteManager instance;

    private SpriteManager() {
    }

    public static synchronized SpriteManager getInstance() {
        if (instance == null) {
            instance = new SpriteManager();
        }
        return instance;
    }

    public void loadSprite(String id, String fileName) {
        try (InputStream in = getClass().getResourceAsStream(path + fileName)) {
            if (in == null) {
                System.out.println("Sprite not found: " + fileName);
                return;
            }
            BufferedImage img = ImageIO.read(in);
            sprites.put(id, img);
            System.out.println("Loaded sprite: " + id);
        } catch (Exception e) {
            System.out.println("Failed to load sprite: " + fileName);
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite(String id) {
        return sprites.get(id);
    }

    public void loadAllSprites() {

        for (int i = 0; i < 8; i++) {
            loadSprite("boss_idle_" + i, "boss_idle_" + i + ".png");
        }
        for (int i = 0; i < 3; i++) {
            loadSprite("player_idle_" + i, "player_idle_" + i + ".png");
        }
        for (int i = 0; i < 3; i++) {
            loadSprite("player_walk_" + i, "player_walk_" + i + ".png");
        }
        for (int i = 0; i < 3; i++) {
            loadSprite("player_jump_" + i, "player_jump_" + i + ".png");
        }
        for (int i = 0; i < 3; i++) {
            loadSprite("player_attack_" + i, "player_attack_" + i + ".png");
        }
        for (int i = 0; i < 6; i++) {
            loadSprite("projectile_" + i, "projectile_" + i + ".png");
        }

        /* Loading items:
         * Name the file item_id.png
        for (int i = 0; i < # of items; i++) {
            loadSprite("item_" + i, "item_" + i + ".png");
        }
        */

        // Add more as needed
    }

}