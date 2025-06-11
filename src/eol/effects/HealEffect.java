package eol.effects;

import eol.entities.Character;

public class HealEffect extends Effect {
    private float healPerSecond;
    private float accumulatedHealAmount;

    public HealEffect(Character character, float duration) {
        super(character, duration);
        float totalHeal = character.getHealthComponent().getMaxHealth() * 0.5f;
        healPerSecond = totalHeal / duration;
        accumulatedHealAmount = 0f;
        label = "Healing";
    }

    @Override
    public boolean update(float deltaTime) {
        duration -= deltaTime;
        float healAmountThisFrame = healPerSecond * deltaTime;
        if (accumulatedHealAmount < 1) {
            accumulatedHealAmount += healAmountThisFrame;
        } else {
            character.getHealthComponent().heal(Math.round(accumulatedHealAmount));
            accumulatedHealAmount = 0f;
        }
        return duration <= 0f;
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
