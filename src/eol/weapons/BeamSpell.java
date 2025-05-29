package eol.weapons;

import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.HashSet;
import java.util.Set;

import eol.audio.AudioManager;
import eol.components.CombatComponent;
import eol.engine.EntityManager;
import eol.engine.InputHandler;
import eol.entities.Enemy;
import eol.utils.Vector2;

public class BeamSpell implements Weapon {
    private Line2D beam;
    private float duration = 0.5f;
    private float range = 300.0f;
    private float remaining = 0.0f;
    private Set<Enemy> enemiesHit = new HashSet<>();

    @Override
    public void fire(CombatComponent combatComponent, InputHandler inputHandler, EntityManager entityManager, float deltaTime) {
        if (inputHandler.isKeyPressed(KeyEvent.VK_X) && combatComponent.getCooldown() <= 0 && remaining <= 0) {
            remaining = duration;
            enemiesHit.clear();
            combatComponent.setCooldown(combatComponent.calculateCooldown());
        }
    }

    @Override
    public void update(CombatComponent combatComponent, EntityManager entityManager, float deltaTime) {
        if (remaining <= 0) {
            beam = null;
            return;
        }

        remaining -= deltaTime;

        Vector2 from = combatComponent.getOwner().getPosition();
        Vector2 dir = combatComponent.getOwner().getMovementComponent().getLastDirection();
        Vector2 to = from.add(dir.multiply(range));

        beam = new Line2D.Float(from.getX(), from.getY(), to.getX(), to.getY());

        for (Enemy e : entityManager.getEnemies()) {
            if (!enemiesHit.contains(e) && beam.intersects(e.getBounds())) {
                e.getHealthComponent().takeDamage(combatComponent.calculateDamage());
                enemiesHit.add(e);
                AudioManager.getInstance().playSound("hit");
            }
        }
    }

    public Line2D getBeam() {
        return beam;
    }
}
