package eol.entities;

import eol.components.StatsComponent;
import eol.components.CombatComponent;
import eol.components.HealthComponent;
import eol.utils.Vector2;

public class Player extends Character {
    private final HealthComponent health;
    private int speed;

    public Player(Vector2 position, Vector2 offset, int width, int height, StatsComponent stats) {
        super(position, offset, width, height, stats);
        this.health = new HealthComponent(100, this);
        this.speed = 5;
    }

    protected CombatComponent createCombatComponent() {
        return new CombatComponent(this, 1, 1.0f);
    }
    
}
