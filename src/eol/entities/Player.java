package eol.entities;

import eol.components.HealthComponent;
import eol.utils.Vector2;

public class Player extends Character {
    private final HealthComponent health;
    private int speed;

    public Player(Vector2 position, Vector2 offset, int width, int height) {
        super(position, offset, width, height);
        this.health = new HealthComponent(100, this);
        this.speed = 5;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getSpeed() {
        return speed;
    }
    
}
