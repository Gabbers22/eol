package eol.components;

import eol.entities.Boss;
import eol.entities.Character;

public class HealthComponent {
    private Character owner;
    private int healthStat;
    private int maxHealth;
    private int currentHealth;
    private boolean alive;

    public HealthComponent(Character owner) {
        this.owner = owner;
        healthStat = owner.getStatsComponent().getHealth();
        calculateMaxHealth();
        currentHealth = maxHealth;
        alive = true;
    }

    public void takeDamage(int amount) {
        if (owner instanceof Boss && currentHealth > 0) {
            currentHealth = Math.max(1, currentHealth - amount);
            return;
        }

        currentHealth = Math.max(0, currentHealth - amount);
        if (currentHealth == 0) alive = false;
    }

    public void heal(int amount) {
        currentHealth = Math.min(maxHealth, currentHealth + amount);
    }

    public void calculateMaxHealth() {
        healthStat = owner.getStatsComponent().getHealth();
        maxHealth = (healthStat * 20) * (1 + healthStat / 10);
        currentHealth = Math.min(currentHealth, maxHealth);
    }

    public boolean isAlive() {
        return alive;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        if (this.currentHealth == 0) {
            alive = false;
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }

}
