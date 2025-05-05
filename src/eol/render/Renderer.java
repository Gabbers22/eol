package eol.render;

import eol.engine.EntityManager;
import eol.entities.GameEntity;
import eol.entities.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Renderer {
    private EntityManager entityManager;
    private SpriteManager spriteManager;

    public Renderer(EntityManager entityManager, SpriteManager spriteManager) {
        this.entityManager = entityManager;
        this.spriteManager = spriteManager;
    }

    public void renderAll(Graphics2D g, boolean debugMode) {
        // Loop over every entity and call renderEntity

        if (debugMode) {
            drawDebugInfo(g);
        }
    }

    public void renderEntity(Graphics2D g, GameEntity e) {
        
    }

    public void drawDebugInfo(Graphics2D g) {
        // System.out.println("drawing debug");
        g.setColor(new Color(0, 0, 255, 128));
        for (GameEntity e : entityManager.getEntities()) {
            Rectangle r = e.getBounds();
            // if (r == null) continue;
            g.fill(r);
        }
    }
    
}
