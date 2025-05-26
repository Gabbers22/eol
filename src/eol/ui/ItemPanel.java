package eol.ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import eol.engine.InputHandler;
import eol.entities.Player;
import eol.items.Item;

public class ItemPanel {
    private boolean visible;
    private List<Item> items;
    private int selectedIndex;
    private Player player;
    private Font font;
    private Font labelFont;
    private float timer = 0f;

    public ItemPanel(Player player) {
        this.visible = false;
        this.items = new ArrayList<>();
        this.selectedIndex = 0;
        this.player = player;
        this.font = new Font("Martian Mono", Font.BOLD, 32);
        this.labelFont = new Font("Martian Mono", Font.PLAIN, 24);
    }

    public void showItems(List<Item> itemSet) {
        items.clear();
        items.addAll(itemSet);
        selectedIndex = 0;
        visible = true;
        timer = 0f;
    }

    public void update(InputHandler inputHandler, float deltaTime) {
        if (!visible) return;
        timer += deltaTime;

        if (inputHandler.isKeyPressed(KeyEvent.VK_LEFT)) {
            System.out.println("selected left item");
            selectedIndex = (selectedIndex + items.size() - 1) % items.size();
        } else if (inputHandler.isKeyPressed(KeyEvent.VK_RIGHT)) {
            System.out.println("selected right item");
            selectedIndex = (selectedIndex + 1) % items.size();
        } else if (inputHandler.isKeyPressed(KeyEvent.VK_X) && timer > 1.5f) {
            Item selectedItem = items.get(selectedIndex);
            selectedItem.applyStats(player);
            visible = false;
        }
        inputHandler.clearKeysPressed();
    }

    public void render(Graphics2D g) {
        if (!visible) return;

        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, 800, 600);

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            int x = 150 + i * 150;
            int y = 100;

            if (i == selectedIndex) {
                g.setColor(Color.YELLOW);
                g.drawRect(x - 5, y - 5, 74, 74);
            }

            //g.drawImage(item.getSprite(), x, y, 64, 64, null);
            g.setColor(Color.RED);
            g.fillRect(x, y, 64, 64);
        }
        
        Item selectedItem = items.get(selectedIndex);
        String rarity = selectedItem.getRarity();
        switch (rarity) {
            case "Common":
                g.setColor(Color.WHITE);
                break;
            case "Rare":
                g.setColor(Color.BLUE);
                break;
            case "Epic":
                g.setColor(Color.MAGENTA);
                break;
            case "Legendary":
                g.setColor(Color.ORANGE);
                break;
            case "Mythic":
                g.setColor(Color.CYAN);
                break;
        }
        drawCenteredString(g, selectedItem.getName(), 400, 240, font);
        drawCenteredString(g, selectedItem.getRarity(), 400, 280, font);
        List<String> statLabels = selectedItem.getStatLabels();
        Iterator<String> it = statLabels.iterator();
        int y = 320;
        g.setColor(Color.WHITE);
        while (it.hasNext()) {
            drawCenteredString(g, it.next(), 400, y, labelFont);
            y += 40;
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void hide() {
        visible = false;
    }

    public static void drawCenteredString(Graphics2D g, String text, int centerX, int centerY, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = centerX - metrics.stringWidth(text) / 2;
        int y = centerY - ((metrics.getHeight() - metrics.getAscent()));
        g.setFont(font);
        g.drawString(text, x, y);
    }


}