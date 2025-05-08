package eol.render;

import eol.engine.EntityManager;
import eol.entities.GameEntity;
import eol.utils.Vector2;
import eol.entities.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Renderer {
    private EntityManager entityManager;
    private SpriteManager spriteManager;

    // Bounding box colors
    private static final Color playerDebugColor = new Color(0, 0, 255, 128);
    private static final Color groundDebugolor = new Color(0, 0, 0, 128);
    private static final Color defaultDebugColor = new Color(255, 0, 00, 128);

    public Renderer(EntityManager entityManager, SpriteManager spriteManager) {
        this.entityManager = entityManager;
        this.spriteManager = spriteManager;

    }

    public void renderAll(Graphics2D g, boolean debugMode) {
        // Loop over every entity and call renderEntity


        // Draw debug info
        if (debugMode) {
            drawDebugInfo(g);
        }
    }

    public void renderEntity(Graphics2D g, GameEntity e) {
        
    }

    public void drawDebugInfo(Graphics2D g) {
        for (GameEntity e : entityManager.getEntities()) {
            Vector2 entityPosition = e.getPosition();
            if (e instanceof Player) {
                g.setColor(playerDebugColor);
            } else if (e instanceof Ground) {
                g.setColor(groundDebugolor);
            } else {
                g.setColor(defaultDebugColor);
            }

            Rectangle r = e.getBounds();
            if (r == null) continue;

            g.fill(r);

            if (e instanceof Ground) continue;
            String name = e.getClass().getSimpleName();
            g.setColor(Color.BLACK);
            g.setFont(new Font("Monospaced", Font.PLAIN, 12));
            g.drawString(name, entityPosition.getX() - 20, entityPosition.getY() - 40);
        }
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospaced", Font.PLAIN, 16));

        int y = 15;
        g.drawString("x: " + Math.floor(entityManager.getPlayer().getPosition().getX()) + " y: " + Math.floor(entityManager.getPlayer().getPosition().getY()), 10, y); y += 15;
        g.drawString("x-velocity: " + Math.abs(entityManager.getPlayer().getMovementComponent().getVelocity().getX()), 10, y); y += 15;
        g.drawString("y-velocity: " + Math.abs(entityManager.getPlayer().getMovementComponent().getVelocity().getY()), 10, y); y += 15;
        g.drawString("grounded: " + entityManager.getPlayer().getMovementComponent().isGrounded(), 10, y); y +=15;

        


    }
    
}
