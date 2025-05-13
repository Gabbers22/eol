package eol.entities;

import eol.components.CombatComponent;
import eol.components.StatsComponent;
import eol.utils.Vector2;

public class Ally extends Character {

    public Ally(Vector2 position, Vector2 offset, int width, int height, StatsComponent stats) {
        super(position, offset, width, height, stats);
    }

    protected CombatComponent createCombatComponent() {
        return new CombatComponent(this, 1, 1.0f);
    }
    
}