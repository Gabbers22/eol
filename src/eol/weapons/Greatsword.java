package eol.weapons;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import eol.audio.AudioManager;
import eol.components.CombatComponent;
import eol.engine.EntityManager;
import eol.engine.InputHandler;
import eol.entities.Enemy;
import eol.entities.Character;
import eol.entities.Player;
import eol.utils.Vector2;

public class Greatsword implements Weapon {
    private enum AttackPhase {NONE, STARTUP, ACTIVE, RECOVERY}

    private AttackPhase attackPhase = AttackPhase.NONE;
    private float attackTimer, startupTime = 0.05f, activeTime = 0.15f, recoveryTime = 0.05f;
    private final Set<Enemy> enemiesHit = new HashSet<>();
    private Rectangle hitbox;

    @Override
    public void fire(CombatComponent ctx, InputHandler input, EntityManager em, float dt) {
        // start-up
        if (attackPhase == AttackPhase.NONE && input.isKeyPressed(KeyEvent.VK_X) && ctx.getCooldown() <= 0) {
            attackPhase = AttackPhase.STARTUP;
            attackTimer = startupTime;
            ctx.setCooldown(ctx.calculateCooldown());
            ctx.setJustAttacked(true);
            AudioManager.getInstance().playSound("sword");
        }
    }

    @Override
    public void update(CombatComponent ctx, EntityManager em, float dt) {
        if (attackPhase == AttackPhase.NONE) return;

        attackTimer -= dt;
        switch (attackPhase) {
            case STARTUP:
                if (attackTimer <= 0) {
                    attackPhase = AttackPhase.ACTIVE;
                    attackTimer = activeTime;
                    enemiesHit.clear();
                }
                break;

            case ACTIVE:
                if (attackTimer <= 0) {
                    attackPhase = AttackPhase.RECOVERY;
                    attackTimer = recoveryTime;
                    hitbox = null;
                } else {
                    hitbox = createHitbox(ctx.getOwner());
                    for (Enemy e : em.getEnemies()) {
                        if (!enemiesHit.contains(e) && hitbox.intersects(e.getBounds())) {
                            e.getHealthComponent().takeDamage(ctx.calculateDamage());
                            Vector2 knockbackDir = ctx.getOwner().getMovementComponent().getLastDirection();
                            e.getMovementComponent().push(knockbackDir.multiply(350.0f), 0.25f);
                            AudioManager.getInstance().playSound("hit");
                            enemiesHit.add(e);
                        }
                    }
                }
                break;

            case RECOVERY:
                if (attackTimer <= 0) {
                    attackPhase = AttackPhase.NONE;
                    enemiesHit.clear();
                    ctx.setJustAttacked(false);
                }
                break;
        }
    }

    private Rectangle createHitbox(Character p) {
        Vector2 dir = p.getMovementComponent().getLastDirection();
        float px = p.getPosition().getX(), py = p.getPosition().getY();
        int w = 64, h = 64, half = 32 / 2;
        int x = (int) (px + (dir.getX() < 0 ? -half - w : half));
        int y = (int) (py + 64 / 2 - 64);
        return new Rectangle(x, y, w, h);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}