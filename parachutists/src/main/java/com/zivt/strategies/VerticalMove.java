package com.zivt.strategies;

import com.zivt.gameGraphics.IImage;

public class VerticalMove implements IMove {

    private int yBorder;

    public VerticalMove(int yBorder) {
        this.yBorder = yBorder;
    }

    @Override
    public void move(IImage image) {
        if (image.getY() <= yBorder)
            image.setY(image.getY() + image.getVel()) ;
    }
}
