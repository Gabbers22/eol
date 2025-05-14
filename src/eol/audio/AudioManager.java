package eol.audio;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import javax.sound.sampled.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.HashMap;




public class AudioManager  {
	File menuSound;
	AudioInputStream menuFX1;

	
	HashMap<String, AudioInputStream> songs; 
	// define each song id

		public void AudioId() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
            URL url = getClass().getResource("/assets/sounds/menuSound.wav");
			menuFX1 = AudioSystem.getAudioInputStream(url);
		}
		
		public void openAudio () throws LineUnavailableException, IOException {
			Clip clip = AudioSystem.getClip(); // only required when opening clip
			clip.open(menuFX1);
			clip.start();
			System.out.println("hi");
		}
	
		}

		
		







		
		
		
		

