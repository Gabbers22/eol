package eol.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
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

    public ItemPanel(Player player) {
        this.visible = false;
        this.items = new ArrayList<>();
        this.selectedIndex = 0;
        this.player = player;
        this.font = new Font("Arial", Font.PLAIN, 16);
    }

    public void showItems(Set<Item> itemSet) {
        items.clear();
        items.addAll(itemSet);
        selectedIndex = 0;
        visible = true;
    }

    public void update(InputHandler inputHandler) {
        if (!visible) return;

        if (inputHandler.isKeyPressed(KeyEvent.VK_LEFT)) {
            System.out.println("selected left item");
            selectedIndex = (selectedIndex + items.size() - 1) % items.size();
        } else if (inputHandler.isKeyPressed(KeyEvent.VK_RIGHT)) {
            System.out.println("selected right item");
            selectedIndex = (selectedIndex + 1) % items.size();
        } else if (inputHandler.isKeyPressed(KeyEvent.VK_X)) {
            Item selectedItem = items.get(selectedIndex);
            selectedItem.applyStats(player.getStatsComponent());
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
            int x = 100 + i * 150;
            int y = 420;

            if (i == selectedIndex) {
                g.setColor(Color.YELLOW);
                g.drawRect(x - 5, y - 5, 74, 74);
            }

            //g.drawImage(item.getSprite(), x, y, 64, 64, null);
            g.setColor(Color.RED);
            g.fillRect(x, y, 64, 64);
        }

        Item selectedItem = items.get(selectedIndex);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(selectedItem.getName(), 100, 520);
        g.drawString(selectedItem.getRarity(), 100, 540);
        HashSet<String> statLabels = selectedItem.getStatLabels();
        Iterator<String> it = statLabels.iterator();
        int y = 550;
        while (it.hasNext()) {
            g.drawString(it.next(), 100, y);
            y += 10;
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void hide() {
        visible = false;
    }



}