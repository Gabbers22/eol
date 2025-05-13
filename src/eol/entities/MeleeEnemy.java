package eol.entities;

import eol.components.StatsComponent;
import eol.utils.Vector2;
import eol.components.HealthComponent;
import eol.components.CombatComponent;
import eol.engine.EntityManager;

public class MeleeEnemy extends Enemy {
    private final HealthComponent health;

    public MeleeEnemy(Vector2 position, Vector2 offset, int width, int height, EntityManager entityManager, StatsComponent stats) {
        super(position, offset, width, height, entityManager, stats);
        this.health = new HealthComponent(50, this);
    }

    protected CombatComponent createCombatComponent() {
        return new CombatComponent(this, 1, 1.0f);
    }

    @Override
    public void update(float deltaTime) {
        moveToPlayer();
        movement.update(deltaTime);
    }
    
}
