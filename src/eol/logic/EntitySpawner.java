package eol.logic;

import eol.components.StatsComponent;
import eol.engine.EntityManager;
import eol.entities.MeleeEnemy;
import eol.utils.Vector2;

public class EntitySpawner {
    private EntityManager entityManager;
    private Vector2 enemySpawn = new Vector2(820, 468);

    public EntitySpawner(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void spawnBasicEnemy() {
        MeleeEnemy basicEnemy = new MeleeEnemy(enemySpawn, new Vector2(-16, -32), 32, 64, entityManager, new StatsComponent(1, 1, 1, 1));
        entityManager.addEntity(basicEnemy);
    }

    public void spawnNext() {
        spawnBasicEnemy();
    }
    
}
