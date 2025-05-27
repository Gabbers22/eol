package eol.engine;

import eol.components.HealthComponent;
import eol.entities.*;
import eol.entities.Character;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityManager {
    private List<GameEntity> entities;
    private List<Enemy> enemies;
    private List<GameEntity> pendingAdd;
    private List<GameEntity> pendingRemove;

    public EntityManager() {
        entities = new ArrayList<>();
        enemies = new ArrayList<>();
        pendingAdd = new ArrayList<>();
        pendingRemove = new ArrayList<>();
    }

    public void forceAddEntity(GameEntity entity) {
        entities.add(entity);
        if (entity instanceof Enemy) {
            enemies.add((Enemy) entity);
        }
    }

    public void addEntity(GameEntity entity) {
        pendingAdd.add(entity);
    }

    public void removeEntity(GameEntity entity) {
        pendingRemove.remove(entity);
    }

    public List<GameEntity> getEntities() {
        return entities;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Ground getGround() {
        for (GameEntity e : entities) {
            if (e instanceof Ground) return (Ground) e;
        }
        return null;
    }

    public Player getPlayer() {
        for (GameEntity e : entities) {
            if (e instanceof Player) return (Player) e;
        }
        return null;
    }

    public boolean enemyCheck() {
        if (!enemies.isEmpty()) {
            return false;
        }
        
        for (GameEntity e : pendingAdd) {
            if (e instanceof Enemy) {
                return false;
            }
        }
        return true;
    }

    public void updateAll(float deltaTime) {
        if (!pendingAdd.isEmpty()) {
            for (GameEntity e : pendingAdd) {
                entities.add(e);
                if (e instanceof Enemy) {
                    enemies.add((Enemy) e);
                }
            }
            pendingAdd.clear();
        }

        for (GameEntity e : getEntities()) {
            e.update(deltaTime);
        }

        // removes dead entities
        Iterator<GameEntity> it = entities.iterator();
        while (it.hasNext()) {
            GameEntity e = it.next();

            boolean remove = false;
            if (e instanceof Character && !(e instanceof Player)) {
                HealthComponent hc = ((Character) e).getHealthComponent();
                if (!hc.isAlive()) remove = true;
            } else if (e instanceof Projectile) {
                if (!((Projectile) e).isAlive()) remove = true;
            } else if (e instanceof Trap) {
                if (!((Trap) e).isAlive()) remove = true;
            }

            if (remove) {
                it.remove();
                if (e instanceof Enemy) {
                    enemies.remove(e);
                }
            }
        }

        if (!pendingRemove.isEmpty()) {
            for (GameEntity e : pendingRemove) {
                entities.remove(e);
                if (e instanceof Enemy) {
                    enemies.remove(e);
                }
            }
            pendingRemove.clear();
        }
    }
}
