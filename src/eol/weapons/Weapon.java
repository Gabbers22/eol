package eol.weapons;

import eol.components.CombatComponent;
import eol.engine.EntityManager;
import eol.engine.InputHandler;

public abstract class Weapon {
    protected int[] weaponStats;

    public abstract void fire(CombatComponent combatComponent, InputHandler inputHandler, EntityManager entityManager, float deltaTime);

    public void update(CombatComponent combatComponent, EntityManager entityManager, float deltaTime) { }

    public int[] getStats() {
        return weaponStats;
    }
}
