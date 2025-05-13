package eol.logic;

public class WaveManager {
    private EntitySpawner spawner;
    private int currentWave;
    private float countdown;

    public WaveManager(EntitySpawner spawner) {
        this.spawner = spawner;
        spawner.spawnNext(); //testing
    }
    
}
