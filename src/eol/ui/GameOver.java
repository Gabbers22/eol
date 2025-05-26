package eol.ui;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOver {

    private JFrame frame;
    private JPanel panel;

    /**
     * Create the application.
     */
    public GameOver() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Echoes of Lazarus");
        frame.setSize(new Dimension(1000, 768));
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        JButton btnStartOver = new JButton("Start Over");
        btnStartOver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenu().show();
            }
        });

        btnStartOver.setFont(new Font("Martian Mono", Font.BOLD, 25));
        btnStartOver.setFocusPainted(false);
        btnStartOver.setBounds(250, 300, 500, 60);
        btnStartOver.setBackground(Color.WHITE);
        btnStartOver.setForeground(new Color(32, 33, 36));
        btnStartOver.setBorderPainted(false);

        JButton btnQuitGame = new JButton("Quit Game");
        btnQuitGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnQuitGame.setFont(new Font("Martian Mono", Font.BOLD, 25));
        btnQuitGame.setFocusPainted(false);
        btnQuitGame.setBounds(250, 375, 500, 60);
        btnQuitGame.setBackground(Color.WHITE);
        btnQuitGame.setForeground(new Color(32, 33, 36));
        btnQuitGame.setBorderPainted(false);


        JLabel lblNewLabel = new JLabel("HAHAHA YOU LOSE!");
        lblNewLabel.setFont(new Font("Martian Mono", Font.BOLD, 50));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(0, 100, 1000, 100);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(32, 33, 36));
        panel.add(lblNewLabel);
        panel.add(btnStartOver);
        panel.add(btnQuitGame);
        frame.add(panel);
    }

    public void show() {
        frame.setVisible(true);
    }
}