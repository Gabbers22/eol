package eol.weapons;

import java.awt.event.KeyEvent;
import eol.components.CombatComponent;
import eol.engine.InputHandler;
import eol.engine.EntityManager;
import eol.entities.Character;
import eol.entities.Player;
import eol.entities.Projectile;
import eol.utils.Vector2;

public class StarterSpell extends Weapon {

    public StarterSpell() {
        weaponStats = new int[] {0, 0, 0, 0};
    }

    public void fire(CombatComponent combatComponent, InputHandler inputHandler, EntityManager entityManager, float deltaTime) {
        if (!inputHandler.isKeyPressed(KeyEvent.VK_X) || combatComponent.getCooldown() > 0) return;
        combatComponent.setJustAttacked(true);
        //AudioManager.getInstance().playSfx("shoot");
        Character owner = combatComponent.getOwner();


        Vector2 origin = owner.getPosition().add(owner.getMovementComponent().getLastDirection().multiply(10f));
        Vector2 dir;
        // if auto‐aim is on, find the nearest enemy
        if (((Player)owner).isAutoAimEnabled()) {
            dir = entityManager.findNearestEnemyDir(origin);
            if (dir == null) {
                dir = owner.getMovementComponent().getLastDirection();
            }
        } else {
            dir = owner.getMovementComponent().getLastDirection();
        }


        //Projectile proj = new Projectile(new Vector2(owner.getPosition().getX() + dir.getX() * 10, owner.getPosition().getY()), new Vector2(-5, -5), 10, 10, dir.multiply(combatComponent.getProjectileSpeed()), combatComponent.calculateDamage(), owner, entityManager);
        Projectile proj = new Projectile(
                origin,
                new Vector2(-5, -5),
                10, 10,
                dir.normalize().multiply(combatComponent.getProjectileSpeed()),
                combatComponent.calculateDamage(),
                owner,
                entityManager
        );
        entityManager.addEntity(proj);
        combatComponent.setCooldown(combatComponent.calculateCooldown());
    }

}
