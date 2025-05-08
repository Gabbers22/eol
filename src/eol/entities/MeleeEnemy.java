package eol.entities;

import eol.utils.Vector2;
import eol.components.HealthComponent;
import eol.engine.EntityManager;

public class MeleeEnemy extends Enemy {
    private final HealthComponent health;

    public MeleeEnemy(Vector2 position, Vector2 offset, int width, int height, EntityManager entityManager) {
        super(position, offset, width, height, entityManager);
        this.health = new HealthComponent(50, this);
    }

    public void update(float deltaTime) {
        moveToPlayer();
        movement.update(deltaTime);
    }
    
}
