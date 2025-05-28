package eol.weapons;

import eol.components.CombatComponent;
import eol.engine.EntityManager;
import eol.engine.InputHandler;

public interface Weapon {
    void fire(CombatComponent combatComponent, InputHandler inputHandler, EntityManager entityManager, float deltaTime);

    default void update(CombatComponent combatComponent, EntityManager entityManager, float deltaTime) { }
}
