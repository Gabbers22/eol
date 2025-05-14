package eol.engine;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import eol.ui.MainMenu;
import eol.render.*;
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
        spriteManager = new SpriteManager();
        inputHandler = new InputHandler();
        renderer = new Renderer(entityManager, spriteManager);
        mainMenu = new MainMenu();
        gamePanel = new GamePanel(renderer);
        gamePanel.addKeyListener(inputHandler);
        collisionHandler = new CollisionHandler(GamePanel.getPanelWidth(), GamePanel.getPanelHeight(), entityManager);

        player = new Player(new Vector2(400, 468), new Vector2(-16, -32), 32, 64, new StatsComponent(5, 5, 5, 5), playerType);
        entityManager.forceAddEntity(player);

        ground = new Ground(new Vector2(0, 500), new Vector2(0, 0), 800, 100);
        entityManager.forceAddEntity(ground);

        /*
        //meleeEnemy = new MeleeEnemy(new Vector2(100, 300), new Vector2(-16, -32), 32, 64, entityManager);
        //entityManager.addEntity(meleeEnemy);

        //rangedEnemy = new RangedEnemy(new Vector2(800, 300), new Vector2(-16, -32), 32, 64, entityManager);
        //entityManager.addEntity(rangedEnemy);
        */

        entitySpawner = new EntitySpawner(entityManager);
        waveManager = new WaveManager(entitySpawner, entityManager);

        
        
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

    public void resetGame() {
        gameLoop.stop();
        frame.dispose();
        mainMenu.show();
    }

}
