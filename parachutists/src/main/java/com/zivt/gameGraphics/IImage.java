package com.zivt.gameGraphics;

import java.awt.*;

public interface IImage {
    Image getImage();
    int getX();
    void setX(int x);
    void setY(int y);
    int getY();
    int getVel();
    void setVel(int x);
    void move();
}
