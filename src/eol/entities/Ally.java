package eol.entities;

import eol.utils.Vector2;

public class Ally extends Character {
    private int speed = 0;

    public Ally(Vector2 position, Vector2 offset, int width, int height) {
        super(position, offset, width, height);
    }

    public int getSpeed() {
        return speed;
    }
    
}