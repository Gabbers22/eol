package eol.engine;

import eol.entities.GameEntity;

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

    public void updateAll(float deltaTime) {
        for (GameEntity e : getEntities()) {
            e.update(deltaTime);
        }
    }
}
