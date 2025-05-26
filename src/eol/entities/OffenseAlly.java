package eol.entities;

import eol.components.CombatComponent;
import eol.components.StatsComponent;
import eol.engine.EntityManager;
import eol.utils.Vector2;

public class OffenseAlly extends Ally {
    private float abilityCooldown;

    public OffenseAlly(Vector2 position, Vector2 offset, int width, int height, EntityManager entityManager, StatsComponent stats) {
        super(position, offset, width, height, entityManager, stats);
        this.combat = new CombatComponent(this, 15, 0.5f);
        abilityCooldown = 60.0f;
    }

    @Override
    public void update(float deltaTime) {
        movement.update(deltaTime);
        abilityCooldown = Math.max(0, abilityCooldown - deltaTime);
        if (abilityCooldown > 0) return;
        ability();
        abilityCooldown = 60.0f;
    }

    public void ability() {
        // apply random item
    }

}