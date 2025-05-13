package eol.components;

import eol.entities.Character;

public class HealthComponent {
    private Character owner;
    private int baseHealth;
    private int healthStat;
    private int maxHealth;
    private int currentHealth;
    

    public HealthComponent(int baseHealth, Character owner) {
        this.baseHealth = baseHealth;
        this.owner = owner;
        healthStat = owner.getStatsComponent().getHealth();
        calculateMaxHealth();
        currentHealth = maxHealth;
    }

    public void takeDamage(int amount) {
        currentHealth = Math.max(0, currentHealth - amount);
    }

    public void heal(int amount) {
        currentHealth = Math.min(maxHealth, currentHealth + amount);
    }

    public void calculateMaxHealth() {
        healthStat = owner.getStatsComponent().getHealth();
        maxHealth = baseHealth * (1 + healthStat / 10);
    }

    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }
    
}
