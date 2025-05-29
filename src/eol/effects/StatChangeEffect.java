package eol.effects;

import eol.components.StatsComponent;
import eol.entities.Character;

public class StatChangeEffect extends Effect {
    private int healthChange;
    private int speedChange;
    private int strengthChange;
    private int dexterityChange;
    private StatsComponent stats;

    public StatChangeEffect(Character character, float duration, int health, int speed, int strength, int dexterity) {
        super(character, duration);
        this.healthChange = health;
        this.speedChange = speed;
        this.strengthChange = strength;
        this.dexterityChange = dexterity;
        this.stats = character.getStatsComponent();
        label = "Stat change";
    }

    @Override
    public boolean update(float deltaTime) {
        duration -= deltaTime;
        if (duration <= 0f) {
            stats.setHealth(Math.max(1, stats.getHealth() - healthChange));
            stats.setSpeed(Math.max(1, stats.getSpeed() - speedChange));
            stats.setStrength(Math.max(1, stats.getStrength() - strengthChange));
            stats.setDexterity(Math.max(1, stats.getDexterity() - dexterityChange));
            character.getHealthComponent().calculateMaxHealth();
            return true;
        }
        return false;
    }

    @Override
    public void onStart() {
        stats.setHealth(stats.getHealth() + healthChange);
        stats.setSpeed(stats.getSpeed() + speedChange);
        stats.setStrength(stats.getStrength() + strengthChange);
        stats.setDexterity(stats.getDexterity() + dexterityChange);
    }



}
