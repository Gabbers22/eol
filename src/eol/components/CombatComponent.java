package eol.components;

import java.awt.Rectangle;

import eol.utils.Vector2;
import eol.weapons.Weapon;
import eol.entities.Character;
import eol.entities.DefenseAlly;
import eol.entities.Enemy;
import eol.entities.GameEntity;
import eol.entities.Player;
import eol.entities.Projectile;
import eol.entities.MeleeEnemy;
import eol.entities.OffenseAlly;
import eol.entities.RangedEnemy;
import eol.entities.SupportAlly;
import eol.engine.InputHandler;
import eol.audio.AudioManager;
import eol.engine.EntityManager;

public class CombatComponent {
    private Weapon currentWeapon;
    private Character owner;
    private int baseDamage;
    private float baseCooldown;
    private float cooldown;
    private float projectileSpeed;
    private Rectangle hitbox;
    private String playerType;
    private boolean justAttacked = false;


    public CombatComponent(Character owner, int baseDamage, float baseCooldown) {
        this.owner = owner;
        this.baseDamage = baseDamage;
        this.baseCooldown = baseCooldown;
        cooldown = calculateCooldown();
        projectileSpeed = 300.0f;
    }

    public CombatComponent(Character owner, int baseDamage, float baseCooldown, String playerType, Weapon currentWeapon) {
        this.owner = owner;
        this.baseDamage = baseDamage;
        this.baseCooldown = baseCooldown;
        cooldown = calculateCooldown();
        projectileSpeed = 300.0f;
        this.playerType = playerType;
        this.currentWeapon = currentWeapon;
    }

    public int calculateDamage() {
        int strength = owner.getStatsComponent().getStrength();
        return baseDamage + (2 * strength);
    }

    public float calculateCooldown() {
        int dexterity = owner.getStatsComponent().getDexterity();
        return baseCooldown - (0.05f * dexterity);
    }

    public float getCooldown() {
        return cooldown;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public boolean getJustAttacked() {
        return justAttacked;
    }

    public void setJustAttacked(boolean b) {
        justAttacked = b;
    }

    public float getProjectileSpeed() {
        return projectileSpeed;
    }

    public void setWeapon(Weapon weapon) {
        currentWeapon = weapon;
    }

    public void update(float deltaTime, InputHandler inputHandler, EntityManager entityManager) {
        cooldown = Math.max(0, cooldown - deltaTime);

        if (owner instanceof Player) {
            currentWeapon.fire(this, inputHandler, entityManager, deltaTime);
            currentWeapon.update(this, entityManager, deltaTime);
        } else if (owner instanceof MeleeEnemy) {
            meleeEnemyAttack(entityManager);
        } else if (owner instanceof RangedEnemy) {
            rangedEnemyAttack(entityManager);
        } else if (owner instanceof SupportAlly) {
            supportAllyAttack(entityManager);
        } else if (owner instanceof DefenseAlly) {
            defenseAllyAttack(entityManager);
        } else if (owner instanceof OffenseAlly) {
            offenseAllyAttack(entityManager);
        } else {
            return;
        }
    }

    public void meleeEnemyAttack(EntityManager entityManager) {
        if (cooldown > 0) return;
        Player p = entityManager.getPlayer();
        if (owner.getBounds().intersects(p.getBounds())) {
            p.getHealthComponent().takeDamage(calculateDamage());
            cooldown = calculateCooldown();
        }
    }

    public void rangedEnemyAttack(EntityManager entityManager) {
        if (cooldown > 0 || owner.getMovementComponent().getVelocity().getX() > 0) return;
        Player p = entityManager.getPlayer();
        Vector2 dir = owner.getPosition().subtract(p.getPosition()).normalize();
        Projectile proj = new Projectile(new Vector2(owner.getPosition().getX() + dir.getX() * 10, owner.getPosition().getY()), new Vector2(-5, -5), 10, 10, dir.multiply(projectileSpeed), calculateDamage(), owner, entityManager);
        entityManager.addEntity(proj);
        cooldown = calculateCooldown();
    }

    public void supportAllyAttack(EntityManager entityManager) {
        if (cooldown > 0) return;
        Player p = entityManager.getPlayer();

        createMeleeHitbox();

        if (hitbox.intersects(p.getBounds())) {
            p.getHealthComponent().heal(calculateDamage());
        }

        for (Enemy e : entityManager.getEnemies()) {
            if (hitbox.intersects(e.getBounds())) {
                e.getHealthComponent().takeDamage(calculateDamage());
            }
        }
        cooldown = calculateCooldown();
    }

    public void defenseAllyAttack(EntityManager entityManager) {
        if (cooldown > 0) return;

        createMeleeHitbox();
        for (Enemy e : entityManager.getEnemies()) {
            if (hitbox.intersects(e.getBounds())) {
                e.getHealthComponent().takeDamage(calculateDamage());
                Vector2 knockbackDir = owner.getMovementComponent().getLastDirection();
                e.getMovementComponent().push(knockbackDir.multiply(350.0f), 0.25f);
            }
        }
        cooldown = calculateCooldown();
    }

    public void offenseAllyAttack(EntityManager entityManager) {
        if (cooldown > 0) return;

        createMeleeHitbox();
        for (Enemy e : entityManager.getEnemies()) {
            if (hitbox.intersects(e.getBounds())) {
                e.getHealthComponent().takeDamage(calculateDamage());
            }
        }
        cooldown = calculateCooldown();
    }

    public void createMeleeHitbox() {
        Vector2 dir = owner.getMovementComponent().getLastDirection();
        float px = owner.getPosition().getX();
        float py = owner.getPosition().getY();
        int w = 64, h = 64;
        int halfW = 32 / 2;
        int x = (int) (px + (dir.getX() < 0 ? -halfW - w : halfW));
        int y = (int) (py + 64 / 2 - 64);

        hitbox = new Rectangle(x, y, w, h);
    }

    public Rectangle getHitbox() { return hitbox; }
    public Character getOwner() { return owner; }

    /* 
    public void initPhaseTimes(float frameDuration) {
        startupTime = 1 * frameDuration;
        activeTime = 3 * frameDuration;
        recoveryTime = 1 * frameDuration;
    }
    */

}
