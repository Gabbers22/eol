package eol.logic;

import java.util.HashSet;
import java.util.Set;

import eol.audio.AudioManager;
import eol.components.StatsComponent;
import eol.effects.StatChangeEffect;
import eol.engine.EntityManager;
import eol.entities.*;
import eol.utils.Vector2;
import eol.weapons.*;

public class WaveManager {
    private EntitySpawner spawner;
    private EntityManager entityManager;
    private Player player;
    private int currentWave;
    private float countdown;
    private boolean waveEnded;
    private Set<Integer> triggeredWaves;
    private boolean bossSpawned;

    public WaveManager(EntitySpawner spawner, EntityManager entityManager) {
        this.spawner = spawner;
        this.entityManager = entityManager;
        this.player = entityManager.getPlayer();
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
            switch (currentWave) {
                case 2:
                    //entityManager.getPlayer().setWeapon(new FireSpell());
                    break;
                case 4:
                    if (player.getType().equals("melee")) {
                        player.setWeapon(new Greatsword());
                    } else {
                        player.setWeapon(new StormSpell());
                    }
                    break;
                case 5:
                    SupportAlly supportAlly = new SupportAlly(new Vector2(100, 500), new Vector2(-16, -32), 32, 64, entityManager, new StatsComponent(1, 1, 1, 1));
                    entityManager.addEntity(supportAlly);
                    AudioManager.getInstance().stopMusic();
                    AudioManager.getInstance().playMusic("songTwo");
                    break;
                case 8:
                    if (player.getType().equals("melee")) {
                        player.setWeapon(new StarterSword());
                    } else {
                        player.setWeapon(new FireSpell());
                    }
                    break;
                case 10:
                    OffenseAlly offenseAlly = new OffenseAlly(new Vector2(200, 500), new Vector2(-16, -32), 32, 64, entityManager, new StatsComponent(1, 1, 1, 1));
                    entityManager.addEntity(offenseAlly);
                    AudioManager.getInstance().stopMusic();
                    AudioManager.getInstance().playMusic("songThree");
                    break;
                case 12:
                    if (player.getType().equals("melee")) {
                        player.setWeapon(new StarterSword());
                    } else {
                        player.setWeapon(new BeamSpell());
                    }
                    break;
                case 15:
                    DefenseAlly defenseAlly = new DefenseAlly(new Vector2(300, 500), new Vector2(-16, -32), 32, 64, entityManager, new StatsComponent(1, 1, 1, 1));
                    entityManager.addEntity(defenseAlly);
                    break;
                case 20:
                    Boss boss = new Boss(new Vector2(400, -100), new Vector2(-42.5f, -47), 85, 94, entityManager, new StatsComponent(25, 1, 1, 1));
                    entityManager.addEntity(boss);
                    bossSpawned = true;
                    AudioManager.getInstance().stopMusic();
                    AudioManager.getInstance().playMusic("boss");
                    break;
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
