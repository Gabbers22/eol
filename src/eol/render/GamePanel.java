package eol.render;

import eol.ui.ItemPanel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final int panelWidth = 800;
    private static final int panelHeight = 600;
    private Renderer renderer;
    private ItemPanel itemPanel;
    private boolean debugMode;

    public GamePanel(Renderer renderer, ItemPanel itemPanel) {
        debugMode = false;
        this.renderer = renderer;
        this.itemPanel = itemPanel;
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setFocusable(true);
        requestFocusInWindow();
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        renderer.renderAll(g2d, debugMode);
        itemPanel.render(g2d);
    }

    public static int getPanelWidth() {
        return panelWidth;
    }

    public static int getPanelHeight() {
        return panelHeight;
    }

}
