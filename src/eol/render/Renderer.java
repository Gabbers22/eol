package eol.render;

import eol.engine.EntityManager;
import eol.entities.GameEntity;

import java.awt.Graphics2D;

public class Renderer {
    private EntityManager entityManager;
    private SpriteManager spriteManager;

    public Renderer(EntityManager entityManager, SpriteManager spriteManager) {
        this.entityManager = entityManager;
        this.spriteManager = spriteManager;
    }

    public void renderAll(Graphics2D g) {
        // Loop over every entity and call renderEntity
    }

    public void renderEntity(Graphics2D g, GameEntity e) {
        
    }
    
}
