package eol.effects;

import eol.audio.AudioManager;
import eol.entities.Character;

public class BurnEffect extends Effect {
    private float burnSpeed;
    private float timer;
    private Character attacker;

    public BurnEffect(Character character, Character attacker, float duration) {
        super(character, duration);
        this.attacker = attacker;
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
            character.getHealthComponent().takeDamage((int) (attacker.getCombatComponent().calculateDamage() * 0.25));
            AudioManager.getInstance().playSound("burnTick");
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
