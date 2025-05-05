package eol.components;

import eol.entities.Character;
import eol.utils.Vector2;

public class MovementComponent {
    public static final float baseAcceleration = 20.0f;
    public static final float baseMaxSpeed = 80.0f;
    public static final float jumpForce = 800.0f;
    public static final float gravity = 2000.0f;
    private final Character owner;
    private Vector2 velocity = Vector2.zero;
    private boolean grounded = false;


    public MovementComponent(Character owner) {
        this.owner = owner;
    }

    public void move(Vector2 direction) {
        Vector2 acceleration = direction.multiply(baseAcceleration);

        velocity = new Vector2(velocity.getX() + acceleration.getX(), velocity.getY()); // apply acceleration

        float vx = velocity.getX();
        if (Math.abs(vx) > baseMaxSpeed) {
            vx = Math.signum(vx) * baseMaxSpeed;
        }

        velocity = new Vector2(vx, velocity.getY());
    }

    public void jump() {
        if (grounded) {
            velocity = new Vector2(velocity.getX(), -jumpForce);
            grounded = false;
        }
    }

    public void applyFriction() {
        float vx = velocity.getX();
        if (vx != 0) {
            float nf = -Math.signum(vx) * 35.0f;
            float nv = vx + nf;
            if (Math.signum(nv) != Math.signum(vx)) nv = 0;
            velocity = new Vector2(nv, velocity.getY());
        }
    }
    
    public void update(float deltaTime) {
        if (!grounded) {
            // velocity = new Vector2(velocity.getX(), velocity.getY() + gravity * deltaTime);
        }

        Vector2 displacement = velocity.multiply(deltaTime);
        Vector2 position = owner.getPosition();
        if (displacement.getX() != 0)  {
            owner.setPosition(position.add(displacement));
        }

        if (grounded) {
            applyFriction();
            owner.setPosition(new Vector2(position.getX(), (float)Math.floor(position.getY()) + 0.1f));
        }

    }

}
