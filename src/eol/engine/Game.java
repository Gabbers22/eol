package eol.engine;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import eol.ui.ItemPanel;
import eol.ui.MainMenu;
import eol.render.*;
import eol.audio.AudioManager;
import eol.components.StatsComponent;
import eol.entities.*;
import eol.items.ItemRegistry;
import eol.logic.EntitySpawner;
import eol.logic.GameState;
import eol.logic.LootManager;
import eol.logic.WaveManager;
import eol.utils.Vector2;
import eol.weapons.StarterSpell;
import eol.weapons.StarterSword;
import eol.weapons.Weapon;

public class Game {
    private JFrame frame;
    private EntityManager entityManager;
    private SpriteManager spriteManager;
    private Renderer renderer;
    private InputHandler inputHandler;
    private CollisionHandler collisionHandler;
    private GamePanel gamePanel;
    private ItemPanel itemPanel;
    private MainMenu mainMenu;
    private GameState gameState;
    private LootManager lootManager;
    private EntitySpawner entitySpawner;
    private WaveManager waveManager;
    private Player player;
    private Ground ground;
    private GameLoop gameLoop;
    private String playerType;
    private Weapon playerWeapon;

    public Game(String playerType) {
        this.playerType = playerType;
        initializeSystems(playerType);
    }

    public void initializeSystems(String playerType) {
        entityManager = new EntityManager();
        spriteManager = SpriteManager.getInstance();
        spriteManager.loadAllSprites();
        inputHandler = new InputHandler();
        AudioManager.getInstance().stopMusic();
        AudioManager.getInstance().playMusic("songOne");

        if (playerType.equals("melee")) {
            playerWeapon = (Weapon)new StarterSword();
        } else {
            playerWeapon = (Weapon)new StarterSpell();
        }
        player = new Player(new Vector2(400, 468), new Vector2(-16, -32), 32, 64, new StatsComponent(5, 20, 20, 5), playerType, playerWeapon);
        entityManager.forceAddEntity(player);

        ground = new Ground(new Vector2(0, 500), new Vector2(0, 0), 800, 100);
        entityManager.forceAddEntity(ground);

        lootManager = new LootManager();
        entitySpawner = new EntitySpawner(entityManager);
        waveManager = new WaveManager(entitySpawner, entityManager);
        gameState = new GameState(waveManager, player);
        renderer = new Renderer(entityManager, spriteManager, waveManager);
        mainMenu = new MainMenu();
        itemPanel = new ItemPanel(player);
        gamePanel = new GamePanel(renderer, itemPanel);
        gamePanel.addKeyListener(inputHandler);
        collisionHandler = new CollisionHandler(GamePanel.getPanelWidth(), GamePanel.getPanelHeight(), entityManager);

        /*
         * initialize other objects
         */

        gameLoop = new GameLoop(this, entityManager, inputHandler, collisionHandler, waveManager, lootManager, gamePanel, itemPanel, player);

        frame = new JFrame("Echoes of Lazarus");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void startGame() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
            gameLoop.start();
        });
    }

    public void closeGame() {
        frame.dispose();
    }

    public void showMainMenu() {
        mainMenu.show();
    }

}