package eol.entities;

import eol.utils.Vector2;

import java.awt.image.BufferedImage;

import eol.components.AnimationComponent;
import eol.engine.EntityManager;
import eol.render.Animator;
import eol.render.SpriteManager;

public class Projectile extends GameEntity {
    private AnimationComponent anims;
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
        anims = new AnimationComponent();

        BufferedImage[] frames = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            frames[i] = SpriteManager.getInstance().getSprite("projectile_" + i);
        }

        anims.addAnimation("shoot", new Animator(frames, 0.1f));
    }

    public boolean isAlive() {
        return alive;
    }

    public AnimationComponent getAnimationComponent() {
        return anims;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

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
                    System.out.println("intersected enemy");
                    e.getHealthComponent().takeDamage(damage);
                    alive = false;
                    return;
                }
            }
        }

        if (owner instanceof SupportAlly && getBounds().intersects(player.getBounds())) {
            player.getHealthComponent().heal(damage);
            alive = false;
            return;
        }

        //movement
        Vector2 displacement = velocity.multiply(deltaTime);
        setPosition(position.add(displacement));

        anims.update(deltaTime);
    }

}
