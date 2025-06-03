package eol.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import eol.components.StatsComponent;
import eol.entities.Player;
import eol.weapons.WeaponRegistry;

public class SaveManager {
    private static final String saveFile = "save.txt";
    private static final String settingsFile = "settings.txt";

    public static void saveGameState(int wave, Player player) {
        String playerType = player.getType();
        StatsComponent stats = player.getStatsComponent();
        int health = stats.getHealth();
        int speed = stats.getSpeed();
        int strength = stats.getStrength();
        int dexterity = stats.getDexterity();
        int currentHealth = player.getHealthComponent().getCurrentHealth();
        String weaponId = player.getWeapon().getId();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile))) {
            bw.write(Integer.toString(wave));
            bw.newLine();
            bw.write(playerType);
            bw.newLine();
            bw.write(health + " " + speed + " " + strength + " " + dexterity);
            bw.newLine();
            bw.write(Integer.toString(currentHealth));
            bw.newLine();
            bw.write(weaponId);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static GameState loadGameState() {
        File f = new File(saveFile);
        if (!f.exists()) return null;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line1 = br.readLine();
            String line2 = br.readLine();
            String line3 = br.readLine();
            String line4 = br.readLine();
            String line5 = br.readLine();

            int wave = Integer.parseInt(line1);
            String playerType = line2;
            String[] parts = line3.split(" ");
            int health = Integer.parseInt(parts[0]);
            int speed = Integer.parseInt(parts[1]);
            int strength = Integer.parseInt(parts[2]);
            int dexterity = Integer.parseInt(parts[3]);
            int currentHealth = Integer.parseInt(line4);
            String weaponId = line5;

            return new GameState(wave, playerType, new StatsComponent(health, speed, strength, dexterity), currentHealth, WeaponRegistry.getInstance().getWeaponById(weaponId));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void clearGameState() {
        try {
            Files.deleteIfExists(Paths.get(saveFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean gameStateExists() {
        return Files.exists(Paths.get(saveFile));
    }

    public static boolean loadBeatenBefore() {
        File f = new File(settingsFile);
        if (!f.exists()) return false;

        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line = reader.readLine();
            return Boolean.parseBoolean(line.trim());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void saveBeatenBefore(boolean beatenBefore) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(settingsFile))) {
            writer.write(Boolean.toString(beatenBefore));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}