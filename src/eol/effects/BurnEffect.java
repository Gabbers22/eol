package eol.effects;

import eol.entities.Character;

public class BurnEffect extends Effect {
    private float burnSpeed;
    private float timer;

    public BurnEffect(Character character, float duration) {
        super(character, duration);
        burnSpeed = 0.5f;
        timer = burnSpeed;
    }

    public void update(float deltaTime) {
        duration -= deltaTime;
        if (duration <= 0) {
            character.removeEffect(this);
            return;
        }
        timer -= deltaTime;
        if (timer <= 0) {
            character.getHealthComponent().takeDamage(5);
            timer = burnSpeed;
        }
    }
    
}
