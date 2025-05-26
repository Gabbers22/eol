package eol.entities;

import eol.components.CombatComponent;
import eol.utils.Vector2;
import eol.engine.EntityManager;
import eol.components.StatsComponent;

public class SupportAlly extends Ally {
    private float abilityCooldown;

    public SupportAlly(Vector2 position, Vector2 offset, int width, int height, EntityManager entityManager, StatsComponent stats) {
        super(position, offset, width, height, entityManager, stats);
        this.combat = new CombatComponent(this, 1, 1.0f);
        abilityCooldown = 20.0f;
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
        Vector2 dir = this.getPosition().subtract(entityManager.getPlayer().getPosition()).normalize();
        Character owner = this;
        Projectile heal = new Projectile(owner.getPosition(), new Vector2(-5, -5), 10, 10, dir.multiply(300.0f), 5, this, entityManager);
        entityManager.addEntity(heal);
    }

}
