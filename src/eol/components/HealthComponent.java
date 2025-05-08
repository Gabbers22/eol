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
    }

    public void takeDamage() {

    }

    public void heal() {

    }
    
}
