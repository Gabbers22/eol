package eol.components;

public class StatsComponent {
    private int health;
    private int speed;
    private int strength;
    private int dexterity;

    public StatsComponent(int health, int speed, int strength, int dexterity) {
        this.health = health;
        this.speed = speed;
        this.strength = strength;
        this.dexterity = dexterity;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

}
