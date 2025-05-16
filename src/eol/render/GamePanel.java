package eol.render;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private static final int panelWidth = 800;
    private static final int panelHeight = 600; 
    private Renderer renderer;
    private JPanel itemPanel;
    private boolean debugMode;
    private boolean showingItems;

    public GamePanel(Renderer renderer) {
        debugMode = false;
        showingItems = false;
        this.renderer = renderer;
        setupItemPanel();
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setFocusable(true);
        requestFocusInWindow();
    }
    
    private void setupItemPanel() {
        itemPanel = new JPanel();
        itemPanel.setBackground(new Color(0, 0, 0, 128));
        itemPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        JButton test = new JButton("test");
        
        test.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showingItems = false;
                toggleItemPanel(false);
            }
        });
    
        itemPanel.add(test);
        add(itemPanel);
        itemPanel.setVisible(false);
    }

    public boolean showingItems() {
        return showingItems;
    }

    public void toggleItemPanel(boolean b) {
        itemPanel.setVisible(b);
        showingItems = b;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        renderer.renderAll(g2d, debugMode);
    }

    public static int getPanelWidth() {
        return panelWidth;
    }

    public static int getPanelHeight() {
        return panelHeight;
    }

}
