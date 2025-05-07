package eol.engine;

import eol.entities.*;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<GameEntity> entities;

    public EntityManager() {
        entities = new ArrayList<>();
    }

    public void addEntity(GameEntity entity) {
        entities.add(entity);
    }

    public void removeEntity(GameEntity entity) {
        entities.remove(entity);
    }

    public List<GameEntity> getEntities() {
        return entities;
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
