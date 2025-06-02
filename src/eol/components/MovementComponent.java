package eol.components;

import eol.audio.AudioManager;
import eol.entities.Boss;
import eol.entities.Character;
import eol.utils.Vector2;

public class MovementComponent {
    public static final float baseAcceleration = 80.0f;
    public static final float baseMaxSpeed = 200.0f;
    public static final float jumpForce = 600.0f;
    public static final float gravity = 2000.0f;
    private final Character owner;
    private Vector2 velocity = Vector2.zero;
    private boolean grounded = false;
    private boolean pushed = false;
    private float pushTimer = 0f;
    private Vector2 lastDirection = Vector2.right;
    float time = 0;


    public MovementComponent(Character owner) {
        this.owner = owner;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 v) {
        velocity = v;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public void setGrounded(boolean g) {
        grounded = g;
    }

    public boolean isPushed() {
        return pushed;
    }

    public Vector2 getLastDirection() {
        return lastDirection;
    }

    public void setLastDirection(Vector2 d) {
        lastDirection = d;
    }

    public void move(Vector2 direction) {
        if (pushed) return;
        if (direction.getX() != 0.0f) setLastDirection(direction);
        float speedStat = owner.getStatsComponent().getSpeed();
        float speedFactor = (float)(Math.sqrt(speedStat) * 0.25);

        Vector2 acceleration = direction.multiply(baseAcceleration * speedFactor);

        velocity = new Vector2(velocity.getX() + acceleration.getX(), velocity.getY()); // apply acceleration

        float max = baseMaxSpeed * speedFactor;
        float vx = velocity.getX();
        if (Math.abs(vx) > max) {
            vx = Math.signum(vx) * max;
        }

        velocity = new Vector2(vx, velocity.getY());
    }

    public void jump() {
        if (grounded) {
            AudioManager.getInstance().playSound("jump");
            velocity = new Vector2(velocity.getX(), -jumpForce);
            grounded = false;
        }
    }

    public void applyFriction() {
        float vx = velocity.getX();
        if (vx != 0) {
            float nf = -Math.signum(vx) * 60.0f;
            float nv = vx + nf;
            if (Math.signum(nv) != Math.signum(vx)) nv = 0;
            velocity = new Vector2(nv, velocity.getY());
        }
    }

    public void update(float deltaTime) {
        if (pushTimer > 0f) {
            pushTimer -= deltaTime;
            if (pushTimer <= 0f) {
                pushed = false;
            }
        }

        if (!grounded) {
            if (owner instanceof Boss) {
                velocity = new Vector2(velocity.getX(), velocity.getY() + 200.0f * deltaTime);
            } else {
                velocity = new Vector2(velocity.getX(), velocity.getY() + gravity * deltaTime);
            }
        }

        Vector2 displacement = velocity.multiply(deltaTime);
        Vector2 position = owner.getPosition();
        owner.setPosition(position.add(displacement));

        if (grounded && !pushed) {
            applyFriction();
            //owner.setPosition(new Vector2(position.getX(), (float)Math.floor(position.getY()) + 0.1f));
        }
    }

    public void bossFloat(float deltaTime) {
        time += deltaTime;
        float x = 400 + (float) (Math.sin(time * 0.5f * Math.PI) * 40f);
        float y = 125 + (float) (Math.sin(time * 1.0f * Math.PI + (Math.PI / 2)) * 30f);
        owner.setPosition(new Vector2(x, y));
        return;
    }

    public void push(Vector2 force, float duration) {
        if (owner instanceof Boss) return;
        velocity = force;
        pushTimer = duration;
        pushed = true;
    }
}
