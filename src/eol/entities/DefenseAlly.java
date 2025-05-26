package eol.entities;

import eol.components.CombatComponent;
import eol.utils.Vector2;
import eol.engine.EntityManager;
import eol.components.StatsComponent;

public class DefenseAlly extends Ally {
    private float abilityCooldown;

    public DefenseAlly(Vector2 position, Vector2 offset, int width, int height, EntityManager entityManager, StatsComponent stats) {
        super(position, offset, width, height, entityManager, stats);
        this.combat = new CombatComponent(this, 5, 2.0f);
        abilityCooldown = 5.0f;
    }

    @Override
    public void update(float deltaTime) {
        movement.update(deltaTime);
        abilityCooldown = Math.max(0, abilityCooldown - deltaTime);
        if (abilityCooldown > 0) return;
        ability();
        abilityCooldown = 20.0f;
    }

    public void ability() {
        Vector2 target = new Vector2(600, 496);
        Trap trap = new Trap(position, new Vector2(-24, -4), 12, 8, entityManager, target);
        entityManager.addEntity(trap);
    }

}