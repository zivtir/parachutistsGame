package com.zivt.strategies;

import com.zivt.gameGraphics.IImage;

public class HorizontalMove implements IMove {

    int xBorder;

    public HorizontalMove(int xBorder) {
        this.xBorder = xBorder;
    }

    @Override
    public void move(IImage image) {
        if (image.getX() <0 || image.getX() > xBorder- image.getImage().getWidth(null))
            image.setVel(-image.getVel()) ;
        image.setX(image.getX() + image.getVel()) ;
    }
}
