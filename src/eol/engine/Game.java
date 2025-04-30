package eol.engine;

import javax.swing.*;

public class Game {
    private JFrame frame;
    private EntityManager entityManager;
    private GameLoop gameLoop;

    public Game() {
        initializeSystems();
    }

    public void initializeSystems() {
        entityManager = new EntityManager();
        
        /*
         * initialize other objects
         */

        gameLoop = new GameLoop(entityManager);

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
