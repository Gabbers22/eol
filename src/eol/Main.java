package eol;

import eol.audio.AudioManager;
import eol.engine.Game;
import eol.ui.MainMenu;

public class Main {

    public static void main (String[] args) {
        //AudioManager audioManager = AudioManager.getInstance();
        //audioManager.loadALl();
        //audioManager.playMusic("menu", true);
        MainMenu mainmenu = new MainMenu();
        mainmenu.show();
    }
}
