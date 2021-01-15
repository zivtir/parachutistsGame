package com.zivt.listeners;

import com.zivt.gameGraphics.IImage;
import com.zivt.managers.GameManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.zivt.managers.Consts.BOAT;

public class GameKeyListener implements KeyListener {

    private GameManager gameManager;
    private static final int boatVelocity = 2;

    public GameKeyListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code= e.getKeyCode();
        if (code==KeyEvent.VK_RIGHT){
            moveBoat(boatVelocity);
        }
        if (code==KeyEvent.VK_LEFT){
            moveBoat(-boatVelocity);
        }
    }

    private void moveBoat(int vel){
        IImage boat= gameManager.getImages().get(BOAT);
        boat.setVel(vel);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
