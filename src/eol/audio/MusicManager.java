
package eol.audio;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.HashMap;


public class MusicManager {


    HashMap<String, AudioInputStream> songs;
    // define each song id

    public void AudioId() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        songs = new HashMap<>();

        File testSong = new File("/Users/coolstein/eclipse-workspace/EoL/eol-main/assets/music/04. A Line In The Sand.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(testSong);
        Clip clip = AudioSystem.getClip();
        songs.put("songOne", audioStream);
        clip.open(audioStream);


        File testSongThree = new File("/Users/coolstein/eclipse-workspace/EoL/eol-main/assets/music/Kirby's Dream Land OST - 06 - Boss Theme.wav");
        AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(testSongThree);
        Clip clipThree = AudioSystem.getClip();


        Scanner kb = new Scanner(System.in);
        String response = ("");
        while (!response.equalsIgnoreCase("quit")) {


            System.out.println("USE WORDS: PLAY, STOP, RESET, QUIT");
            System.out.println("Well? Choose.");

            response = kb.next();

            switch (response) {
                case ("play"):
                    clip.start();

                    break;
                default:
                    System.out.println("hey! no! try again!");

                case ("stop"):
                    clip.stop();
                    clip.close();
                    break;

                case ("reset"):
                    clip.setMicrosecondPosition(0); //set to 0 because is the beginning
                    break;

                case ("quit"):
                    clip.close();

            }
        }
        System.out.println("Deuces...");


    }

}

		
		







		
		
		
		


