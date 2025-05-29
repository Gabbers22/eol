package eol.effects;

import eol.entities.Character;

public class BurnEffect extends Effect {
    private float burnSpeed;
    private float timer;

    public BurnEffect(Character character, float duration) {
        super(character, duration);
        burnSpeed = 0.5f;
        timer = burnSpeed;
        label = "Burning";
    }

    @Override
    public boolean update(float deltaTime) {
        duration -= deltaTime;
        timer -= deltaTime;
        if (duration <= 0f) {
            return true;
        }
        if (timer <= 0f) {
            character.getHealthComponent().takeDamage(5);
            timer = burnSpeed;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o.getClass() == getClass();
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
