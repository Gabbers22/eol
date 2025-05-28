package eol.effects;

import eol.entities.Character;

public abstract class Effect {
    protected Character character;
    protected float duration;

    public Effect(Character character, float duration) {
        this.character = character;
        this.duration = duration;
    }

    public abstract void update(float deltaTime);
    
}
