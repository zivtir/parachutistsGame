package com.zivt.strategies;

import com.zivt.gameGraphics.IImage;

public class HorizontalCycleMove implements IMove {

    int xBorder;

    public HorizontalCycleMove(int xBorder) {
        this.xBorder = xBorder;
    }

    @Override
    public void move(IImage image) {
        if (image.getX() <-image.getImage().getWidth(null)  )
            image.setX(xBorder);
        image.setX(image.getX() + image.getVel());
    }
}
