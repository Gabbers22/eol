package eol.components;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import eol.utils.Vector2;
import eol.entities.Character;
import eol.entities.Enemy;
import eol.entities.GameEntity;
import eol.entities.Player;
import eol.entities.MeleeEnemy;
import eol.entities.RangedEnemy;
import eol.engine.InputHandler;
import eol.engine.EntityManager;

public class CombatComponent {
    private Character owner;
    private int baseDamage;
    private int damage;
    private float baseCooldown;
    private float cooldown;
    private float projectileSpeed;
    private Rectangle hitbox;

    public CombatComponent(Character owner, int baseDamage, float baseCooldown) {
        this.owner = owner;
        this.baseDamage = baseDamage;
        damage = calculateDamage();
        this.baseCooldown = baseCooldown;
        cooldown = calculateCooldown();
        projectileSpeed = 10.0f;
        hitbox = new Rectangle(
            (int)(owner.getPosition().getX() - 32/2 + owner.getMovementComponent().getLastDirection().getX()*32),
            (int)(owner.getPosition().getY() + 64/2 - 64),
            32, 64
        );
    }

    public int calculateDamage() {
        int strength = owner.getStatsComponent().getStrength();
        return baseDamage * strength;
    }

    public float calculateCooldown() {
        int dexterity = owner.getStatsComponent().getDexterity();
        return baseCooldown/dexterity;
    }

    public void update(float deltaTime, InputHandler inputHandler, EntityManager entityManager) {
        cooldown = Math.max(0, cooldown - deltaTime);

        if (owner instanceof Player) {
            playerAttack(inputHandler, entityManager);
        } else if (owner instanceof MeleeEnemy) {
            meleeEnemyAttack(entityManager);
        } else if (owner instanceof RangedEnemy) {
            rangedEnemyAttack(entityManager);
        } else {
            return;
        }
    }

    public void playerAttack(InputHandler inputHandler, EntityManager entityManager) {
        if (!inputHandler.isKeyDown(KeyEvent.VK_X) || cooldown > 0) return;
        System.out.println("player attacking");
       
        hitbox = new Rectangle(
            (int)(owner.getPosition().getX() - 32/2 + owner.getMovementComponent().getLastDirection().getX()*32),
            (int)(owner.getPosition().getY() + 64/2 - 64),
            32, 64
        );
        for (Enemy e : entityManager.getEnemies()) {
            if (hitbox.intersects(e.getBounds())) {
                System.out.println("hit");
                e.getHealthComponent().takeDamage(damage);
            }
        }
        cooldown = calculateCooldown();
    }

    public void meleeEnemyAttack(EntityManager entityManager) {

    }

    public void rangedEnemyAttack(EntityManager entityManager) {

    }
    
}
