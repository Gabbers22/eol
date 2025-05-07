package eol.entities;

import eol.utils.Vector2;
import eol.engine.EntityManager;;

public class Enemy extends Character {
    private Player player;
    private EntityManager entityManager;
    private int speed;

    public Enemy(Vector2 position, Vector2 offset, int width, int height, EntityManager entityManager) {
        super(position, offset, width, height);
        this.entityManager = entityManager;
        player = entityManager.getPlayer();
        this.speed = 1;
    }

    @Override
    public void update(float deltaTime)  {
        Vector2 direction = position.subtract(player.getPosition());
        movement.move(direction);
        movement.update(deltaTime);
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getSpeed() {
        return speed;
    }
    
}