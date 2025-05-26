package eol.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import eol.components.StatsComponent;
import eol.entities.Player;

public class GameState {
    private String path;
    private WaveManager waveManager;
    private Player player;
    private int wave;
    private StatsComponent playerStats;

    public GameState(WaveManager waveManager, Player player) {
        path = "save.txt";
        this.waveManager = waveManager;
        this.player = player;
    }

    public void saveState() {
        wave = waveManager.getWave();
        playerStats = player.getStatsComponent();
        int health = playerStats.getHealth();
        int speed = playerStats.getSpeed();
        int strength = playerStats.getStrength();
        int dexterity = playerStats.getDexterity();

        try (FileWriter fw = new FileWriter(path, true);
             PrintWriter out = new PrintWriter(fw)) {

            String line = wave + ", " + health + ", " + speed + ", " + strength + ", " + dexterity;

            out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadState() {
        File file = new File(path);

        try (Scanner scanner = new Scanner(file)) {
            String line = scanner.nextLine();
            String[] parts = line.split(", ");

            wave = Integer.parseInt(parts[0]);
            int health = Integer.parseInt(parts[1]);
            int speed = Integer.parseInt(parts[2]);
            int strength = Integer.parseInt(parts[3]);
            int dexterity = Integer.parseInt(parts[4]);

            playerStats = new StatsComponent(health, speed, strength, dexterity);
            waveManager.setWave(wave);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StatsComponent getPlayerStats() {
        return playerStats;
    }

}
