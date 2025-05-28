package eol.entities;

import eol.components.CombatComponent;
import eol.utils.Vector2;
import eol.engine.EntityManager;
import eol.components.StatsComponent;

public class DefenseAlly extends Ally {
    private float abilityCooldown;
    private boolean trapDown;

    public DefenseAlly(Vector2 position, Vector2 offset, int width, int height, EntityManager entityManager, StatsComponent stats) {
        super(position, offset, width, height, entityManager, stats);
        this.combat = new CombatComponent(this, 5, 2.0f);
        abilityCooldown = 5.0f;
        trapDown = false;
    }

    @Override
    public void update(float deltaTime) {
        movement.update(deltaTime);
        if (!trapDown) abilityCooldown -= deltaTime;
        if (abilityCooldown <= 0 && !trapDown) {
            ability();
        }
    }

    public void ability() {
        Vector2 target = new Vector2(600, 496);
        Trap trap = new Trap(position, new Vector2(-24, -4), 12, 8, entityManager, target, this);
        entityManager.addEntity(trap);
        trapDown = true;
        abilityCooldown = 5.0f;
    }

    public void setTrapDown (boolean b) {
        trapDown = b;
    }

}