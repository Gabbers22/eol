package eol.components;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import eol.utils.Vector2;
import eol.entities.Character;
import eol.entities.Enemy;
import eol.entities.GameEntity;
import eol.entities.Player;
import eol.entities.Projectile;
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
    private String playerType;

    public CombatComponent(Character owner, int baseDamage, float baseCooldown) {
        this.owner = owner;
        this.baseDamage = baseDamage;
        damage = calculateDamage();
        this.baseCooldown = baseCooldown;
        cooldown = calculateCooldown();
        projectileSpeed = 250.0f;
        hitbox = new Rectangle(
            (int)(owner.getPosition().getX() - 32/2 + owner.getMovementComponent().getLastDirection().getX()*32),
            (int)(owner.getPosition().getY() + 64/2 - 64),
            32, 64
        );
    }

    public CombatComponent(Character owner, int baseDamage, float baseCooldown, String playerType) {
        this.owner = owner;
        this.baseDamage = baseDamage;
        damage = calculateDamage();
        this.baseCooldown = baseCooldown;
        cooldown = calculateCooldown();
        projectileSpeed = 250.0f;
        this.playerType = playerType;
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
            if (playerType.equals("melee")) {
                meleePlayerAttack(inputHandler, entityManager);;
            } else if (playerType.equals("ranged")) {
                rangedPlayerAttack(inputHandler, entityManager);
            }
        } else if (owner instanceof MeleeEnemy) {
            meleeEnemyAttack(entityManager);
        } else if (owner instanceof RangedEnemy) {
            rangedEnemyAttack(entityManager);
        } else {
            return;
        }
        
    }

    public void meleePlayerAttack(InputHandler inputHandler, EntityManager entityManager) {
        if (!inputHandler.isKeyPressed(KeyEvent.VK_X) || cooldown > 0) return;
       
        hitbox = new Rectangle(
            (int)(owner.getPosition().getX() - 32/2 + owner.getMovementComponent().getLastDirection().getX()*32),
            (int)(owner.getPosition().getY() + 64/2 - 64),
            32, 64
        );
        for (Enemy e : entityManager.getEnemies()) {
            if (hitbox.intersects(e.getBounds())) {
                e.getHealthComponent().takeDamage(damage);
            }
        }
        cooldown = calculateCooldown();
    }

    public void rangedPlayerAttack(InputHandler inputHandler, EntityManager entityManager) {
        if (!inputHandler.isKeyPressed(KeyEvent.VK_X) || cooldown > 0) return;
        
        Vector2 dir = owner.getMovementComponent().getLastDirection();
        dir.print();
        Projectile proj = new Projectile(new Vector2(owner.getPosition().getX() + dir.getX()*10, owner.getPosition().getY()), new Vector2(-5, -5), 10, 10, dir.multiply(projectileSpeed), damage, owner, entityManager);
        entityManager.addEntity(proj);
        cooldown = calculateCooldown();
    }

    public void meleeEnemyAttack(EntityManager entityManager) {
        if (cooldown > 0) return;
        Player p = entityManager.getPlayer();
        if (owner.getBounds().intersects(p.getBounds())) {
            p.getHealthComponent().takeDamage(damage);
            System.out.println("hit player");
            cooldown = calculateCooldown();
        }
    }

    public void rangedEnemyAttack(EntityManager entityManager) {
        if (cooldown > 0 || owner.getMovementComponent().getVelocity().getX() > 0) return;
        Player p = entityManager.getPlayer();
        Vector2 dir = owner.getPosition().subtract(p.getPosition()).normalize();
        Projectile proj = new Projectile(new Vector2(owner.getPosition().getX() + dir.getX()*10, owner.getPosition().getY()), new Vector2(-5, -5), 10, 10, dir.multiply(projectileSpeed), damage, owner, entityManager);
        entityManager.addEntity(proj);
        cooldown = calculateCooldown();
    }
    
}
