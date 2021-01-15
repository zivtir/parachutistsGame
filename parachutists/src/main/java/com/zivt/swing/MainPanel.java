package com.zivt.swing;

import com.zivt.gameGraphics.IImage;
import com.zivt.managers.Consts;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainPanel extends JPanel {
    private JLabel  scoreLevelValue,livesValue;
    private boolean isGameOver;
    private static final String bgImageLoc ="background.png";
    private static final String sea ="sea.png";
    private static final String gameOver ="gameover.png";
    private Image bjImg;
    private Image seaImage;
    private HashMap<String, IImage> imagesToPaint = new HashMap<>();

    public void init(){
        setupPanel();
    }

    private void setupPanel() {
        this.bjImg = new ImageIcon(getClass().getClassLoader().getResource( bgImageLoc)).getImage();
        this.seaImage = new ImageIcon(getClass().getClassLoader().getResource(sea)).getImage();
        Dimension panelSize = new Dimension(bjImg.getWidth(null), bjImg.getHeight(null));
        setPreferredSize(panelSize);
        setMinimumSize(panelSize);
        setMaximumSize(panelSize);
        setSize(panelSize);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        scoreLevelValue = new JLabel("0");
        livesValue = new JLabel("");
        add(new JLabel("Score:"));
        add(scoreLevelValue);
        add(new JLabel("Lives:"));
        add(livesValue);
    }

    public void accumulateScore(int addScore){
        Integer score = Integer.parseInt(scoreLevelValue.getText()) + addScore;
        scoreLevelValue.setText(score.toString());
    }

    public int getLives(){
        return Integer.parseInt(livesValue.getText());
    }

    public void setLives(int live) {
        livesValue.setText(Integer.toString(live));
    }

    public int seaLevel(){
        return bjImg.getHeight(null) - seaImage.getHeight(null);
    }

    public void repaint(HashMap<String, IImage> images){
        imagesToPaint=images;
        repaint();
    }

    public void repaintGameOver(){
        isGameOver = true;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bjImg, 0, 0, null);
        HashMap<String, IImage> images = this.imagesToPaint;
        for (Map.Entry<String, IImage> imageEntry : images.entrySet()) {
            IImage image = imageEntry.getValue();
            g.drawImage(image.getImage(), image.getX(), image.getY(), this);
        }
        Image sea = new ImageIcon(getClass().getClassLoader().getResource( MainPanel.sea)).getImage();
        g.drawImage(sea, 0, bjImg.getHeight(null) - sea.getHeight(null), null);
        if (isGameOver){
            Image gameOver = new ImageIcon(getClass().getClassLoader().getResource(MainPanel.gameOver)).getImage();
            g.drawImage(gameOver, getSize(null).width/2 - gameOver.getWidth(null)/ 2 , getSize(null).height/2, null);
        }
    }
}
