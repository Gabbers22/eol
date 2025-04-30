package eol.ui;

import eol.engine.Game;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private JFrame frame;

    public void show() {
        frame = new JFrame("Echoes of Lazarus");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 300));
        frame.setLocationRelativeTo(null);

        // panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // title label

        //buttons
        JButton newGameButton = new JButton("New Game");

        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //add to panel
        panel.add(newGameButton);

        frame.add(panel);
        frame.setVisible(true);

        // action listeners
        newGameButton.addActionListener(e -> {
            Game game = new Game();
            game.startGame();
            frame.dispose();
        });

    }
    
}
