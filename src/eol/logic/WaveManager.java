package eol.logic;

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
    private boolean supportSpawned; 
    private boolean offenseSpawned;
    private boolean defenseSpawned; 
    private boolean bossSpawned;

    public WaveManager(EntitySpawner spawner, EntityManager entityManager) {
        this.spawner = spawner;
        this.entityManager = entityManager;
        currentWave = 1;
        countdown = 3.0f;
        waveEnded = false;
        supportSpawned = false;
        offenseSpawned = false;
        defenseSpawned = false;
        bossSpawned = false;
        spawner.prepareWave(currentWave);
    }

    public void update(float deltaTime) {
        if (currentWave == 2) {
            if (!bossSpawned) {
                Boss boss = new Boss(new Vector2(400, -100), new Vector2(-42.5f, -47), 85, 94, entityManager, new StatsComponent(25, 1, 1, 1));
                entityManager.addEntity(boss);
                bossSpawned = true;
            }
            return;
        }

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

        if (currentWave == 2 && !supportSpawned) {
            supportSpawned = true;
            SupportAlly supportAlly = new SupportAlly(new Vector2(100, 500), new Vector2(-16, -32), 32, 64, entityManager, new StatsComponent(1, 1, 1, 1));
            entityManager.addEntity(supportAlly);
            System.out.println("support ally spawned");
        }

        if (currentWave == 2 && !offenseSpawned) {
            offenseSpawned = true;
            OffenseAlly offenseAlly = new OffenseAlly(new Vector2(200, 500), new Vector2(-16, -32), 32, 64, entityManager, new StatsComponent(1, 1, 1, 1));
            entityManager.addEntity(offenseAlly);
            System.out.println("offense ally spawned");
        }

        if (currentWave == 2 && !defenseSpawned) {
            defenseSpawned = true;
            DefenseAlly defenseAlly = new DefenseAlly(new Vector2(300, 500), new Vector2(-16, -32), 32, 64, entityManager, new StatsComponent(1, 1, 1, 1));
            entityManager.addEntity(defenseAlly);
            System.out.println("defense ally spawned");
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
