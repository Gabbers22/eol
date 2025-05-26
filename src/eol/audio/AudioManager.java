package eol.audio;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import javax.sound.sampled.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.HashMap;
import java.util.List;

public class AudioManager {
    private static AudioManager instance;

    private HashMap<String, Clip> music = new HashMap<>();
    private HashMap<String, List<Clip>> sfx = new HashMap<>();
    private Clip musicPlaying;

    // Private constructor to block other classes from creating a new audiomanager
    private AudioManager() {
    }

    /*
     * Allows for global access. e.x:
     * AudioManager.getInstance().playMusic("menu")
     */
    public static synchronized AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    // loads music, give filename and set hashmap id
    public void loadMusic(String id, String file) {
        Clip clip = createClip("/assets/music/" + file);
        if (clip != null) {
            music.put(id, clip);
        }
    }

    // Plays music given the id
    public void playMusic(String id) {
        Clip clip = music.get(id);
        if (clip == null)
            return;
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        musicPlaying = clip;
    }

    // stops currently playing music
    public void stopMusic() {
        if (musicPlaying != null) {
            musicPlaying.stop();
        }
    }

    // creates a clip given the file path
    public Clip createClip(String path) {
        try {
            URL url = getClass().getResource(path);
            if (url == null) {
                System.err.println("Audio resource not found: " + path);
                return null;
            }
            try (AudioInputStream ais = AudioSystem.getAudioInputStream(url)) {
                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                return clip;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void loadAll() {
        loadMusic("menu", "menu.wav");

        // load more as needed
    }

}