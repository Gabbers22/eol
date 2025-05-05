package eol.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu {
    
  static JFrame mainMenuFrame; 
  static JPanel mainMenuPanel, instructionsPanel;
  static JButton muteSound, newGame, loadGame, instructions, quit, backButton;
  static JLabel instructionsLabel, gameTitle, controlsLabel, gameplayLabel;

  public MainMenu(){

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
    
    muteSound = new JButton("🔊");
    muteSound.setFont(new Font("Martian Mono", Font.BOLD, 25)); 
    muteSound.setFocusPainted(false); 
    muteSound.setBounds(900, 645, 75, 75);
    muteSound.setBackground(Color.WHITE); 
    muteSound.setForeground(new Color(32, 33, 36)); 
    muteSound.setBorderPainted(false);

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
    mainMenuFrame.setSize(new Dimension(1000, 768));
    mainMenuFrame.setResizable(true);
    mainMenuFrame.add(mainMenuPanel);
    mainMenuFrame.setVisible(true);
    mainMenuFrame.setLocationRelativeTo(null);

    newGame.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){

        //run the game code.

      }
    });
    
    loadGame.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){

        //load the save file.

      }
    });

    instructions.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){

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

        backButton = new JButton("🔙");
        backButton.setFont(new Font("Martian Mono", Font.BOLD, 25)); 
        backButton.setFocusPainted(false); 
        backButton.setBounds(900, 645, 75, 75);
        backButton.setBackground(Color.WHITE); 
        backButton.setForeground(new Color(32, 33, 36)); 
        backButton.setBorderPainted(false);

        instructionsPanel = new JPanel();
        instructionsPanel.setLayout(null);
        instructionsPanel.setBackground(new Color(32, 33, 36));
        instructionsPanel.add(backButton);
        instructionsPanel.add(instructionsLabel);
        instructionsPanel.add(controlsLabel);
        instructionsPanel.add(gameplayLabel);

        mainMenuFrame.add(instructionsPanel);
        instructionsPanel.setVisible(true);

      }
    });

    quit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){

        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      }
    });

    muteSound.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){

        //mute sound.

      }
    });

  }

}

