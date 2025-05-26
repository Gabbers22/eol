package eol.entities;

import java.awt.*;

import eol.utils.Vector2;
import eol.render.SpriteManager;

public abstract class GameEntity {
    protected Vector2 position;
    protected SpriteManager spriteManager;
    protected String spriteKey;
    protected Vector2 offset;
    protected int width;
    protected int height;

    public GameEntity(Vector2 position, Vector2 offset, int width, int height) {
        this.position = position;
        this.offset = offset;
        this.width = width;
        this.height = height;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public String getSprite() {
        return spriteKey;
    }

    public Rectangle getBounds() {
        return new Rectangle(
                (int) (position.getX() + offset.getX()),
                (int) (position.getY() + offset.getY()),
                width,
                height
        );
    }

    public abstract void update(float deltaTime);

}
