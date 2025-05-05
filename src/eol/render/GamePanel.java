package eol.render;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final int panelWidth = 1920;
    private static final int panelHeight = 1080; 
    private Renderer renderer;

    public GamePanel(Renderer renderer) {
        this.renderer = renderer;
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setFocusable(true);
        requestFocus();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        renderer.renderAll(g2d);
    }

    public static int getPanelWidth() {
        return panelWidth;
    }

    public static int getPanelHeight() {
        return panelHeight;
    }

}
