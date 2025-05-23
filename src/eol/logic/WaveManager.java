package eol.logic;

import java.util.HashSet;
import java.util.Set;

import eol.components.StatsComponent;
import eol.engine.EntityManager;
import eol.entities.Boss;
import eol.entities.DefenseAlly;
import eol.entities.OffenseAlly;
import eol.entities.SupportAlly;
import eol.utils.Vector2;

public class WaveManager {
    private EntitySpawner spawner;
    private EntityManager entityManager;
    private int currentWave;
    private float countdown;
    private boolean waveEnded;
    private Set<Integer> triggeredWaves;
    private boolean bossSpawned;

    public WaveManager(EntitySpawner spawner, EntityManager entityManager) {
        this.spawner = spawner;
        this.entityManager = entityManager;
        currentWave = 1;
        countdown = 3.0f;
        waveEnded = false;
        bossSpawned = false;
        triggeredWaves = new HashSet<>();
        spawner.prepareWave(currentWave);
    }

    public void update(float deltaTime) {
        if (bossSpawned) return;
        spawner.update(deltaTime);
        if (!spawner.waveOver()) return;

        if (!waveEnded) waveEnded = true;
        countdown -= deltaTime;
        if (countdown <= 0) {
            currentWave++;
            spawner.prepareWave(currentWave);
            waveEnded = false;
            countdown = 3.0f;
        }

        if (!triggeredWaves.contains(currentWave)) {
            switch(currentWave) {
                case 2:
                    Boss boss = new Boss(new Vector2(400, -100), new Vector2(-42.5f, -47), 85, 94, entityManager, new StatsComponent(25, 1, 1, 1));
                    entityManager.addEntity(boss);  
                    bossSpawned = true;

                    SupportAlly supportAlly = new SupportAlly(new Vector2(100, 500), new Vector2(-16, -32), 32, 64, entityManager, new StatsComponent(1, 1, 1, 1));
                    entityManager.addEntity(supportAlly);

                    OffenseAlly offenseAlly = new OffenseAlly(new Vector2(200, 500), new Vector2(-16, -32), 32, 64, entityManager, new StatsComponent(1, 1, 1, 1));
                    entityManager.addEntity(offenseAlly);

                    DefenseAlly defenseAlly = new DefenseAlly(new Vector2(300, 500), new Vector2(-16, -32), 32, 64, entityManager, new StatsComponent(1, 1, 1, 1));
                    entityManager.addEntity(defenseAlly);
            }
            triggeredWaves.add(currentWave);
        }
    }

    public int getWave() {
        return currentWave;
    }

    public void setWave(int wave) {
        currentWave = wave;
    }

    public boolean hasWaveEnded() {
        return waveEnded;
    }

    public void clearWaveEnded() {
        waveEnded = false;
    }
    
}
