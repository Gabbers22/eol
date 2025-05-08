package eol.entities;

import eol.utils.Vector2;
import eol.components.HealthComponent;
import eol.engine.EntityManager;

public class RangedEnemy extends Enemy {
    private final HealthComponent health;

    public RangedEnemy(Vector2 position, Vector2 offset, int width, int height, EntityManager entityManager) {
        super(position, offset, width, height, entityManager);
        health = new HealthComponent(25, this);
    }

    public void update(float deltaTime) {
        // walk to 600 and start shooting
        if (position.getX() > 600) {
            movement.move(Vector2.left);
        } else {
            //attack();
        }
        movement.update(deltaTime);
    }
    
}
