package eol;

import eol.audio.AudioManager;
import eol.logic.SaveManager;
import eol.render.SpriteManager;
import eol.ui.MainMenu;

public class Main {

    public static void main(String[] args) {
        AudioManager audioManager = AudioManager.getInstance();
        audioManager.loadAll(); // Comment this out to make the game load quicker
        SpriteManager.getInstance().loadAllSprites();
        boolean beatenBefore = SaveManager.loadBeatenBefore();
        boolean canLoad = SaveManager.gameStateExists();
        MainMenu mainmenu = new MainMenu(beatenBefore, canLoad);
        mainmenu.show();
    }
}
