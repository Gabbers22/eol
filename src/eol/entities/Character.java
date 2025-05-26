package eol.entities;

import eol.utils.Vector2;
import eol.components.CombatComponent;
import eol.components.HealthComponent;
import eol.components.MovementComponent;
import eol.components.StatsComponent;

public abstract class Character extends GameEntity {
    protected final MovementComponent movement;
    protected final StatsComponent stats;
    protected final HealthComponent health;
    protected CombatComponent combat;

    public Character(Vector2 position, Vector2 offset, int width, int height, StatsComponent stats) {
        super(position, offset, width, height);
        this.movement = new MovementComponent(this);
        this.stats = stats;
        this.health = new HealthComponent(this);
    }

    public MovementComponent getMovementComponent() {
        return movement;
    }

    public StatsComponent getStatsComponent() {
        return stats;
    }

    public HealthComponent getHealthComponent() {
        return health;
    }

    public CombatComponent getCombatComponent() {
        return combat;
    }

    public abstract void update(float deltaTime);
}
