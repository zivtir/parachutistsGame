package com.zivt.gameGraphics;

import com.zivt.strategies.IMove;

import java.awt.*;
import java.util.HashMap;

public class GraphicsRepository {

    private HashMap<String, IImage> images = new HashMap<>();

    public HashMap<String, IImage> getImages() {
        return images;
    }

    public void addImage(String id, int x, int y, int velocity, Image img, IMove move){
        IImage image = new GameImage(x,y,velocity,img,move);
        images.put(id,image);
    }
}
