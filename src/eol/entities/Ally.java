package eol.entities;

import eol.components.CombatComponent;
import eol.components.StatsComponent;
import eol.engine.EntityManager;
import eol.utils.Vector2;

public abstract class Ally extends Character {
    protected EntityManager entityManager;

    public Ally(Vector2 position, Vector2 offset, int width, int height, EntityManager entityManager, StatsComponent stats) {
        super(position, offset, width, height, stats);
        this.entityManager = entityManager;
    }

    public abstract void ability();

    public abstract void update(float deltaTime);

}