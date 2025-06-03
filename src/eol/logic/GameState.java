package eol.logic;

import eol.components.StatsComponent;
import eol.weapons.Weapon;

public class GameState {
    public int wave;
    public String playerType;
    public StatsComponent stats;
    public int currentHealth;
    public Weapon weapon;

    public GameState(int wave, String playerType, StatsComponent stats, int currentHealth, Weapon weapon) {
        this.wave = wave;
        this.playerType = playerType;
        this.stats = stats;
        this.currentHealth = currentHealth;
        this.weapon = weapon;
    }
}
