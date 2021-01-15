package com.zivt.swing;

import com.zivt.managers.GameManager;

import javax.swing.*;

public class Game {

    public void start(int scorePerHit, int lives, int speed, String title){
        JFrame frame = new JFrame(title);
        MainPanel mainPanel = new MainPanel();
        mainPanel.init();
        frame.add(mainPanel);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new GameManager(mainPanel).start(scorePerHit,  lives,speed);
    }
}
