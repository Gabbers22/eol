package eol.logic;

import java.util.LinkedList;
import java.util.Queue;

import eol.components.StatsComponent;
import eol.engine.EntityManager;
import eol.entities.Enemy;
import eol.entities.MeleeEnemy;
import eol.entities.RangedEnemy;
import eol.utils.Vector2;

public class EntitySpawner {
    private EntityManager entityManager;
    private Queue<Enemy> spawnQueue = new LinkedList<>();
    private Vector2 enemySpawn = new Vector2(820, 468);
    private float spawnTimer = 0f;
    private float spawnInterval = 1.5f;

    public EntitySpawner(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void spawnBasicEnemy() {
        MeleeEnemy basicEnemy = new MeleeEnemy(enemySpawn, new Vector2(-16, -32), 32, 64, entityManager, new StatsComponent(1, 1, 1, 1));
        entityManager.addEntity(basicEnemy);
    }

    public void spawnRangedEnemy() {
        RangedEnemy rangedEnemy = new RangedEnemy(enemySpawn, new Vector2(-16, -32), 32, 64, entityManager, new StatsComponent(1, 3, 1, 1));
        entityManager.addEntity(rangedEnemy);
    }

    public void spawnNext() {
        spawnBasicEnemy();
        spawnRangedEnemy();
    }

    public void update(float deltaTime) {
        //if (spawnQueue.isEmpty()) return;
        spawnTimer -= deltaTime;

        if (spawnTimer <= 0) {
            //Enemy e = spawnQueue.poll();
            spawnTimer = spawnInterval;
            spawnRangedEnemy();
        }

    }
    
}
