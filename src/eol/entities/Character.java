package eol.entities;

import eol.utils.Vector2;
import eol.components.HealthComponent;
import eol.components.MovementComponent;

public abstract class Character extends GameEntity {
    protected final MovementComponent movement;

    public Character(Vector2 position, Vector2 offset, int width, int height) {
        super(position, offset, width, height);
        this.movement = new MovementComponent(this);
    }

    public MovementComponent getMovementComponent() {
        return movement;
    }

    public void update(float deltaTime) {
        movement.update(deltaTime);
    }    

    public abstract int getSpeed();
}
