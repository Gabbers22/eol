package eol.weapons;

import eol.components.CombatComponent;
import eol.engine.EntityManager;
import eol.engine.InputHandler;
import eol.entities.Character;
import eol.entities.Projectile;
import eol.utils.Vector2;

import java.awt.event.KeyEvent;

public class FireSpell implements Weapon {

    public void fire(CombatComponent combatComponent, InputHandler inputHandler, EntityManager entityManager, float deltaTime) {
        if (!inputHandler.isKeyPressed(KeyEvent.VK_X) || combatComponent.getCooldown() > 0) return;
        combatComponent.setJustAttacked(true);
        //AudioManager.getInstance().playSfx("shoot");
        Character owner = combatComponent.getOwner();
        Vector2 dir = owner.getMovementComponent().getLastDirection();
        Projectile proj = new Projectile(new Vector2(owner.getPosition().getX() + dir.getX() * 10, owner.getPosition().getY()), new Vector2(-5, -5), 10, 10, dir.multiply(combatComponent.getProjectileSpeed()), combatComponent.calculateDamage(), owner, entityManager);
        entityManager.addEntity(proj);
        combatComponent.setCooldown(combatComponent.calculateCooldown());
    }

}
