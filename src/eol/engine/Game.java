package eol.engine;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import eol.render.*;
import eol.entities.Player;
import eol.utils.Vector2;

public class Game {
    private JFrame frame;
    private EntityManager entityManager;
    private SpriteManager spriteManager;
    private Renderer renderer;
    private InputHandler inputHandler;
    private GamePanel gamePanel;
    private Player player;
    private GameLoop gameLoop;

    public Game() {
        initializeSystems();
    }

    public void initializeSystems() {
        entityManager = new EntityManager();
        spriteManager = new SpriteManager();
        inputHandler = new InputHandler();
        renderer = new Renderer(entityManager, spriteManager);
        gamePanel = new GamePanel(renderer);
        player = new Player(new Vector2(400, 300));
        
        /*
         * initialize other objects
         */

        gameLoop = new GameLoop(entityManager, inputHandler, gamePanel, player);

        frame = new JFrame("Echoes of Lazarus");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        // frame.add(gamePanel);
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
