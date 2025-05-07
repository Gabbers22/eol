package eol.engine;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import eol.render.*;
import eol.entities.*;
import eol.utils.Vector2;

public class Game {
    private JFrame frame;
    private EntityManager entityManager;
    private SpriteManager spriteManager;
    private Renderer renderer;
    private InputHandler inputHandler;
    private CollisionHandler collisionHandler;
    private GamePanel gamePanel;
    private Player player;
    private Ground ground;
    private GameLoop gameLoop;
    private Enemy enemy;

    public Game() {
        initializeSystems();
    }

    public void initializeSystems() {
        entityManager = new EntityManager();
        spriteManager = new SpriteManager();
        inputHandler = new InputHandler();
        renderer = new Renderer(entityManager, spriteManager);
        gamePanel = new GamePanel(renderer);
        gamePanel.addKeyListener(inputHandler);
        collisionHandler = new CollisionHandler(GamePanel.getPanelWidth(), GamePanel.getPanelHeight(), entityManager);

        player = new Player(new Vector2(400, 300), new Vector2(-16, -32), 32, 64);
        entityManager.addEntity(player);

        ground = new Ground(new Vector2(0, 500), new Vector2(0, 0), 800, 100);
        entityManager.addEntity(ground);

        enemy = new Enemy(new Vector2(200, 300), new Vector2(-16, -32), 32, 64, entityManager);
        entityManager.addEntity(enemy);
        
        
        /*
         * initialize other objects
         */

        gameLoop = new GameLoop(entityManager, inputHandler, collisionHandler, gamePanel, player);

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

}
