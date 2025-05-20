package eol.logic;

import eol.components.StatsComponent;
import eol.engine.EntityManager;
import eol.entities.SupportAlly;
import eol.utils.Vector2;

public class WaveManager {
    private EntitySpawner spawner;
    private EntityManager entityManager;
    private int currentWave;
    private float countdown;
    private boolean waveEnded;
    private boolean supportSpawned; 

    public WaveManager(EntitySpawner spawner, EntityManager entityManager) {
        this.spawner = spawner;
        this.entityManager = entityManager;
        currentWave = 1;
        countdown = 0.1f;
        waveEnded = false;
        supportSpawned = false;
        spawner.prepareWave(currentWave);
    }

    public void update(float deltaTime) {
        spawner.update(deltaTime);
        if (!spawner.waveOver()) return;

        if (!waveEnded) waveEnded = true;
        countdown -= deltaTime;
        if (countdown <= 0) {
            currentWave++;
            spawner.prepareWave(currentWave);
            waveEnded = false;
            countdown = 0.1f;
        }

        if (currentWave == 5 && !supportSpawned) {
            supportSpawned = true;
            SupportAlly supportAlly = new SupportAlly(new Vector2(200, 500), new Vector2(-16, -32), 32, 64, entityManager, new StatsComponent(1, 1, 1, 1));
            entityManager.addEntity(supportAlly);
            System.out.println("support ally spawned");
        }

        
    }

    public int getWave() {
        return currentWave;
    }

    public boolean hasWaveEnded() {
        return waveEnded;
    }

    public void clearWaveEnded() {
        waveEnded = false;
    }
    
}
