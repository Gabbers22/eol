package eol.ui;

import eol.audio.AudioManager;
import eol.engine.Game;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MainMenu {

    static JFrame mainMenuFrame;
    static JPanel mainMenuPanel, instructionsPanel, characterSelectionPanel;
    static JButton muteSound, newGame, loadGame, instructions, quit, backButton, knightButton, mageButton;
    static JLabel instructionsLabel, gameTitle, controlsLabel, gameplayLabel;
    private String playerType;

    public void show() {
        AudioManager.getInstance().stopMusic();
        AudioManager.getInstance().playMusic("menu");
        ImageIcon back = new ImageIcon(getClass().getResource("/assets/icons/back.png"));
        ImageIcon mute = new ImageIcon(getClass().getResource("/assets/icons/SpeakerMute.png"));
        ImageIcon speaker = new ImageIcon(getClass().getResource("/assets/icons/Speaker.png"));

        ImageIcon backIcon = new ImageIcon(back.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        ImageIcon muteIcon = new ImageIcon(mute.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        ImageIcon speakerIcon = new ImageIcon(speaker.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        gameTitle = new JLabel("Echoes of Lazarus");
        gameTitle.setFont(new Font("Martian Mono", Font.BOLD, 50));
        gameTitle.setForeground(Color.WHITE);
        gameTitle.setBounds(0, 100, 1000, 100);
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);

        newGame = new JButton("NEW GAME");
        newGame.setFont(new Font("Martian Mono", Font.BOLD, 25));
        newGame.setFocusPainted(false);
        newGame.setBounds(250, 300, 500, 60);
        newGame.setBackground(Color.WHITE);
        newGame.setForeground(new Color(32, 33, 36));
        newGame.setBorderPainted(false);

        loadGame = new JButton("LOAD GAME");
        loadGame.setFont(new Font("Martian Mono", Font.BOLD, 25));
        loadGame.setFocusPainted(false);
        loadGame.setBounds(250, 375, 500, 60);
        loadGame.setBackground(Color.WHITE);
        loadGame.setForeground(new Color(32, 33, 36));
        loadGame.setBorderPainted(false);

        instructions = new JButton("INSTRUCTIONS");
        instructions.setFont(new Font("Martian Mono", Font.BOLD, 25));
        instructions.setFocusPainted(false);
        instructions.setBounds(250, 450, 500, 60);
        instructions.setBackground(Color.WHITE);
        instructions.setForeground(new Color(32, 33, 36));
        instructions.setBorderPainted(false);

        quit = new JButton("QUIT GAME");
        quit.setFont(new Font("Martian Mono", Font.BOLD, 25));
        quit.setFocusPainted(false);
        quit.setBounds(250, 525, 500, 60);
        quit.setBackground(Color.WHITE);
        quit.setForeground(new Color(32, 33, 36));
        quit.setBorderPainted(false);

        muteSound = new JButton("");
        muteSound.setFont(new Font("Martian Mono", Font.BOLD, 25));
        muteSound.setFocusPainted(false);
        muteSound.setBounds(925, 670, 50, 50);
        muteSound.setBackground(Color.WHITE);
        muteSound.setForeground(new Color(32, 33, 36));
        muteSound.setBorderPainted(false);
        muteSound.setIcon(speakerIcon);

        mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(null);
        mainMenuPanel.setBackground(new Color(32, 33, 36));
        mainMenuPanel.add(muteSound);
        mainMenuPanel.add(newGame);
        mainMenuPanel.add(loadGame);
        mainMenuPanel.add(instructions);
        mainMenuPanel.add(quit);
        mainMenuPanel.add(gameTitle);

        mainMenuFrame = new JFrame("Echoes of Lazarus");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setSize(new Dimension(1000, 768));
        mainMenuFrame.setResizable(true);
        mainMenuFrame.add(mainMenuPanel);
        mainMenuFrame.setVisible(true);
        mainMenuFrame.setLocationRelativeTo(null);

        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                mainMenuPanel.setVisible(false);
                mainMenuFrame.setSize(new Dimension(700, 500));

                backButton = new JButton("");
                backButton.setFont(new Font("Martian Mono", Font.BOLD, 25));
                backButton.setFocusPainted(false);
                backButton.setBounds(10, 10, 50, 50);
                backButton.setBackground(Color.WHITE);
                backButton.setForeground(new Color(32, 33, 36));
                backButton.setBorderPainted(false);
                backButton.setIcon(backIcon);

                knightButton = new JButton("KNIGHT");
                knightButton.setFont(new Font("Martian Mono", Font.BOLD, 25));
                knightButton.setFocusPainted(false);
                knightButton.setBounds(100, 350, 200, 60);
                knightButton.setBackground(Color.WHITE);
                knightButton.setForeground(new Color(32, 33, 36));
                knightButton.setBorderPainted(false);

                mageButton = new JButton("MAGE");
                mageButton.setFont(new Font("Martian Mono", Font.BOLD, 25));
                mageButton.setFocusPainted(false);
                mageButton.setBounds(380, 350, 200, 60);
                mageButton.setBackground(Color.WHITE);
                mageButton.setForeground(new Color(32, 33, 36));
                mageButton.setBorderPainted(false);

                characterSelectionPanel = new JPanel();
                characterSelectionPanel.setLayout(null);
                characterSelectionPanel.setBackground(new Color(32, 33, 36));
                characterSelectionPanel.add(knightButton);
                characterSelectionPanel.add(mageButton);
                characterSelectionPanel.add(backButton);

                mainMenuFrame.add(characterSelectionPanel);
                characterSelectionPanel.setVisible(true);

                backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        characterSelectionPanel.setVisible(false);
                        mainMenuPanel.setVisible(true);
                        mainMenuFrame.setSize(new Dimension(1000, 768));

                    }
                });

                knightButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        playerType = "melee";
                        Game game = new Game(playerType);
                        game.startGame();
                        mainMenuFrame.setVisible(false);
                        mainMenuFrame.dispose();

                    }
                });

                mageButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        playerType = "ranged";
                        Game game = new Game(playerType);
                        game.startGame();
                        mainMenuFrame.setVisible(false);
                        mainMenuFrame.dispose();

                    }
                });

            }
        });

        loadGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //load the save file.

            }
        });

        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                mainMenuPanel.setVisible(false);

                instructionsLabel = new JLabel("Instructions");
                instructionsLabel.setFont(new Font("Martian Mono", Font.BOLD, 50));
                instructionsLabel.setForeground(Color.WHITE);
                instructionsLabel.setBounds(0, 10, 1000, 100);
                instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);

                controlsLabel = new JLabel("<html>Controls:<br>Use the arrow keys to move,or W A S D.<br>Press X to perform an attack.</html>");
                controlsLabel.setFont(new Font("Martian Mono", Font.BOLD, 25));
                controlsLabel.setForeground(Color.WHITE);
                controlsLabel.setBounds(10, 100, 900, 300);

                gameplayLabel = new JLabel("<html>Gameplay:<br>1. Select either Knight or Mage at the beginning to continue. The classses affect the weapon choices later in the game.<br>2. Defeat waves of enemies to progress through the game.<br>3. Choose items that make you stronger by changing your stats (based on the descrption) permanently or temporarily.<br>4. Get weapons or spells and equip them if it matches your playstyle.<html>");
                gameplayLabel.setFont(new Font("Martian Mono", Font.BOLD, 25));
                gameplayLabel.setForeground(Color.WHITE);
                gameplayLabel.setBounds(10, 300, 900, 400);

                backButton = new JButton("");
                backButton.setFont(new Font("Martian Mono", Font.BOLD, 25));
                backButton.setFocusPainted(false);
                backButton.setBounds(10, 10, 50, 50);
                backButton.setBackground(Color.WHITE);
                backButton.setForeground(new Color(32, 33, 36));
                backButton.setBorderPainted(false);
                backButton.setIcon(backIcon);
                instructionsPanel = new JPanel();
                instructionsPanel.setLayout(null);
                instructionsPanel.setBackground(new Color(32, 33, 36));
                instructionsPanel.add(backButton);
                instructionsPanel.add(instructionsLabel);
                instructionsPanel.add(controlsLabel);
                instructionsPanel.add(gameplayLabel);

                mainMenuFrame.add(instructionsPanel);
                instructionsPanel.setVisible(true);

                backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        instructionsPanel.setVisible(false);
                        mainMenuPanel.setVisible(true);

                    }
                });

            }
        });

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                mainMenuFrame.dispose();
                System.exit(0);
                //mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });

        muteSound.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (muteSound.getIcon().equals(speakerIcon)) {
                    muteSound.setIcon(muteIcon);
                    // mute sound
                } else {
                    muteSound.setIcon(speakerIcon);
                    // unmute sound
                }

            }
        });

    }

}
