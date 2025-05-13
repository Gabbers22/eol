package eol.engine;

import eol.entities.*;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<GameEntity> entities;
    private List<Enemy> enemies;

    public EntityManager() {
        entities = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    public void addEntity(GameEntity entity) {
        entities.add(entity);
        if (entity instanceof Enemy) {
            enemies.add((Enemy)entity);
        }
    }

    public void removeEntity(GameEntity entity) {
        entities.remove(entity);
        if (entity instanceof Enemy) {
            enemies.remove((Enemy)entity);
        }
    }

    public List<GameEntity> getEntities() {
        return entities;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Ground getGround() {
        for (GameEntity e : entities) {
            if (e instanceof Ground) return (Ground)e;
        }
        return null;
    }

    public Player getPlayer() {
        for (GameEntity e : entities) {
            if (e instanceof Player) return (Player)e;
        }
        return null;
    }

    public void updateAll(float deltaTime) {
        for (GameEntity e : getEntities()) {
            e.update(deltaTime);
        }
    }
}
