package eol.weapons;

import java.awt.event.KeyEvent;
import eol.components.CombatComponent;
import eol.engine.InputHandler;
import eol.engine.EntityManager;
import eol.entities.Character;
import eol.entities.Projectile;
import eol.utils.Vector2;

public class LightCannon implements Weapon {
    private int count = 3;

    public void fire(CombatComponent combatComponent, InputHandler inputHandler, EntityManager entityManager, float deltaTime) {
        if (!inputHandler.isKeyDown(KeyEvent.VK_X) || combatComponent.getCooldown() > 0) return;
        combatComponent.setJustAttacked(true);
        //AudioManager.getInstance().playSfx("shoot");
        Character owner = combatComponent.getOwner();

        float baseAngle = 0.0f;
        float spreadStep = (float) (Math.PI / 12);
        float startAngle = baseAngle - spreadStep * (count - 1) / 2f;
        float spawnDistance = -15f;

        for (int i = 0; i < count; i++) {
            float angle = startAngle + spreadStep * i;
            Vector2 dir = new Vector2((float) Math.cos(angle), (float) Math.sin(angle)).multiply(owner.getMovementComponent().getLastDirection().getX() * -1f);
            Vector2 spawnPos = owner.getPosition().add(dir.multiply(spawnDistance));

            Vector2 vel = dir.multiply(combatComponent.getProjectileSpeed());
            entityManager.addEntity(new Projectile(spawnPos, new Vector2(-5, -5), 10, 10, vel.multiply(-1), 10, combatComponent.getOwner(), entityManager));
        }

        combatComponent.setCooldown(combatComponent.calculateCooldown());
    }

}
