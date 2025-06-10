package eol.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import eol.engine.InputHandler;
import eol.entities.Player;
import eol.weapons.Weapon;

public class WeaponPanel {
    private boolean visible;
    private Weapon offeredWeapon;
    private Player player;
    private Font titleFont;
    private Font descFont;
    private int choiceIndex;
    private float timer;

    private static final String[] options = { "Leave", "Take" };

    public WeaponPanel(Player player) {
        this.player = player;
        this.visible = false;
        this.choiceIndex = 1;
        this.timer = 0f;
        this.titleFont = new Font("Martian Mono", Font.BOLD, 36);
        this.descFont = new Font("Martian Mono", Font.PLAIN, 24);
    }

    public void showWeapon(Weapon weapon) {
        this.offeredWeapon = weapon;
        this.visible = true;
        this.choiceIndex = 1;
        this.timer = 0f;
    }

    public boolean isVisible() {
        return visible;
    }

    public void update(InputHandler input, float deltaTime) {
        if (!visible) return;
        timer += deltaTime;

        if (input.isKeyPressed(KeyEvent.VK_LEFT) || input.isKeyPressed(KeyEvent.VK_A)) {
            choiceIndex = 0;
        } else if (input.isKeyPressed(KeyEvent.VK_RIGHT) || input.isKeyPressed(KeyEvent.VK_D)) {
            choiceIndex = 1;
        }

        if (input.isKeyPressed(KeyEvent.VK_X) && timer > 0.5f) {
            if (choiceIndex == 1) {
                player.setWeapon(offeredWeapon);
            }
            visible = false;
        }
        input.clearKeysPressed();
    }

    public void render(Graphics2D g) {
        if (!visible) return;

        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, 800, 600);

        // Draw a centered box
        int boxW = 600, boxH = 300;
        int boxX = (800 - boxW) / 2;
        int boxY = (600 - boxH) / 2;
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(boxX, boxY, boxW, boxH, 20, 20);
        g.setColor(Color.WHITE);
        g.drawRoundRect(boxX, boxY, boxW, boxH, 20, 20);

        String title = "NEW WEAPON UNLOCKED";
        drawCenteredString(g, title, 400, boxY + 50, titleFont);

        String name = offeredWeapon.getName();
        drawCenteredString(g, name, 400, boxY + 100, titleFont);

        String desc = offeredWeapon.getDescription();
        g.setFont(descFont);
        g.setColor(Color.WHITE);
        drawCenteredString(g, desc, 400, boxY + 150, descFont);
        drawCenteredString(g, "PRESS X TO CONFIRM", 400, 575, titleFont);

        // Draw options
        int optY = boxY + boxH - 60;
        for (int i = 0; i < options.length; i++) {
            String label = options[i];
            int optX = 400 + (i == 0 ? -100 : +100);

            if (i == choiceIndex) {
                g.setColor(Color.CYAN);
            } else {
                g.setColor(Color.LIGHT_GRAY);
            }
            drawCenteredString(g, label, optX, optY, titleFont);
        }
    }

    private static void drawCenteredString(Graphics2D g, String text, int centerX, int centerY, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = centerX - metrics.stringWidth(text) / 2;
        int y = centerY - (metrics.getHeight() - metrics.getAscent());
        g.setFont(font);
        g.drawString(text, x, y);
    }
}
