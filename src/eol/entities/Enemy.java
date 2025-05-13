package eol.entities;

import eol.utils.Vector2;
import eol.components.StatsComponent;
import eol.engine.EntityManager;;

public abstract class Enemy extends Character {
    private Player player;
    private EntityManager entityManager;
    private int speed;

    public Enemy(Vector2 position, Vector2 offset, int width, int height, EntityManager entityManager, StatsComponent stats) {
        super(position, offset, width, height, stats);
        this.entityManager = entityManager;
        player = entityManager.getPlayer();
        this.speed = 1;
    }

    public abstract void update(float deltaTime);

    public void moveToPlayer() {
        Vector2 direction = position.subtract(player.getPosition()).normalize();
        movement.move(direction);
    }
    
}