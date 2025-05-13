package eol.entities;

import eol.utils.Vector2;
import eol.engine.EntityManager;

public class Projectile extends GameEntity {
    private Vector2 velocity;
    private int damage;
    private Character owner;
    private EntityManager entityManager;
    private Player player;
    private float lifeSpan;
    private boolean alive;

    public Projectile(Vector2 position, Vector2 offset, int width, int height, Vector2 velocity, int damage, Character owner, EntityManager entityManager) {
        super(position, offset, width, height);
        this.velocity = velocity;
        this.damage = damage;
        this.owner = owner;
        this.entityManager = entityManager;
        player = entityManager.getPlayer();
        lifeSpan = 10;
        alive = true;
    }

    public boolean isAlive() { return alive; }

    public void update(float deltaTime) {
        lifeSpan -= deltaTime;
        if (lifeSpan <= 0) {
            alive = false;
            return;
        }
        
        if (owner instanceof Enemy && getBounds().intersects(player.getBounds())) {
            player.getHealthComponent().takeDamage(damage);
            alive = false;
            return;
        }


        if (owner instanceof Player) {
            for (Enemy e : entityManager.getEnemies()) {
                if (getBounds().intersects(e.getBounds())) {
                    e.getHealthComponent().takeDamage(damage);
                    alive = false;
                    return;
                }
            }
        }

        //movement
        Vector2 displacement = velocity.multiply(deltaTime);
        setPosition(position.add(displacement));
    }

}
