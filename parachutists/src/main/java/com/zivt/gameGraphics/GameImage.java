package com.zivt.gameGraphics;

import com.zivt.strategies.IMove;

import java.awt.*;

public class GameImage implements IImage  {
    int x;
    int y;
    int velocity;

    private Image image;
    private IMove move;
    public GameImage(int initX, int initY, int velocity, Image image, IMove move) {
        this.velocity=velocity;
        this.x= initX;
        this.y=initY;
        this.move = move;
        this.image = image;
    }

    @Override
    public Image getImage() {
        return image;
    }
    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x=x;
    }

    @Override
    public void setY(int y) {
        this.y=y;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getVel() {
        return velocity;
    }

    @Override
    public void setVel(int x) {
        velocity =x;
    }

    @Override
    public void move() {
        this.move.move(this);
    }
}
