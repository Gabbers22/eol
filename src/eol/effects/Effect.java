package eol.effects;

import eol.entities.Character;

public abstract class Effect {
    protected Character character;
    protected float duration;
    protected String label;

    public Effect(Character character, float duration) {
        this.character = character;
        this.duration = duration;
    }

    public String getLabel() { return label; }

    public abstract boolean update(float deltaTime);
    
}
