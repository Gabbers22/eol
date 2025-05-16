package eol.entities;

import eol.components.StatsComponent;
import eol.components.CombatComponent;
import eol.components.HealthComponent;
import eol.utils.Vector2;

public class Player extends Character {
    private final HealthComponent health;
    private String playerType;

    public Player(Vector2 position, Vector2 offset, int width, int height, StatsComponent stats, String playerType) {
        super(position, offset, width, height, stats);
        this.health = new HealthComponent(100, this);
        this.playerType = playerType;
        this.combat = new CombatComponent(this, 5, 0.1f, playerType);
    }
}
