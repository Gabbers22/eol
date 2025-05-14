package eol.logic;

import eol.engine.EntityManager;

public class WaveManager {
    private EntitySpawner spawner;
    private EntityManager entityManager;
    private int currentWave;
    private float countdown;
    

    public WaveManager(EntitySpawner spawner, EntityManager entityManager) {
        this.spawner = spawner;
        this.entityManager = entityManager;
        spawner.spawnNext(); //testing
    }

    public void update(float deltaTime) {
        spawner.update(deltaTime);
    }
    
}
