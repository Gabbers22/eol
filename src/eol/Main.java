package eol;

import eol.audio.AudioManager;
import eol.engine.Game;
import eol.logic.LootManager;
import eol.ui.MainMenu;

public class Main {

    public static void main (String[] args) {
        AudioManager audioManager = AudioManager.getInstance();
        audioManager.loadAll();
        audioManager.playMusic("menu");
        MainMenu mainmenu = new MainMenu();
        mainmenu.show();
    }
}
