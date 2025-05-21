package eol.engine;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import eol.ui.MainMenu;
import eol.render.*;
import eol.audio.AudioManager;
import eol.components.StatsComponent;
import eol.entities.*;
import eol.logic.EntitySpawner;
import eol.logic.WaveManager;
import eol.utils.Vector2;

public class Game {
    private JFrame frame;
    private EntityManager entityManager;
    private SpriteManager spriteManager;
    private Renderer renderer;
    private InputHandler inputHandler;
    private CollisionHandler collisionHandler;
    private GamePanel gamePanel;
    private MainMenu mainMenu;
    private EntitySpawner entitySpawner;
    private WaveManager waveManager;
    private Player player;
    private Ground ground;
    private GameLoop gameLoop;
    private String playerType;

    public Game(String playerType) {
        this.playerType = playerType;
        initializeSystems(playerType);
    }

    public void initializeSystems(String playerType) {
        entityManager = new EntityManager();
        SpriteManager spriteManager = SpriteManager.getInstance();
        spriteManager.loadAllSprites();
        inputHandler = new InputHandler();
        AudioManager.getInstance().stopMusic();

        player = new Player(new Vector2(400, 468), new Vector2(-16, -32), 32, 64, new StatsComponent(18, 5, 5, 5), playerType);
        entityManager.forceAddEntity(player);

        ground = new Ground(new Vector2(0, 500), new Vector2(0, 0), 800, 100);
        entityManager.forceAddEntity(ground);

        entitySpawner = new EntitySpawner(entityManager);
        waveManager = new WaveManager(entitySpawner, entityManager);        
        renderer = new Renderer(entityManager, spriteManager, waveManager);
        mainMenu = new MainMenu();
        gamePanel = new GamePanel(renderer);
        gamePanel.addKeyListener(inputHandler);
        collisionHandler = new CollisionHandler(GamePanel.getPanelWidth(), GamePanel.getPanelHeight(), entityManager);
        
        /*
         * initialize other objects
         */

        gameLoop = new GameLoop(this, entityManager, inputHandler, collisionHandler, waveManager, gamePanel, player);

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
