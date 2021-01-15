package com.zivt.observers;

import com.zivt.gameGraphics.IImage;
import com.zivt.managers.Consts;
import com.zivt.managers.GameManager;
import com.zivt.managers.LevelManger;
import com.zivt.strategies.IMove;
import com.zivt.strategies.VerticalMove;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;

import static com.zivt.managers.Consts.PLANE;

public class ParachutistsTimer implements ITimerObserver {
    private final LevelManger levelManager;
    private GameManager gameManager;
    private static final String parachutistImageLoc ="parachutist.png";
    private static final int velocity = 1;

    public ParachutistsTimer(GameManager gameManager) {
        this.gameManager = gameManager;
        this.levelManager = new LevelManger();
    }

    @Override
    public void fired() {
        Pair levelRange = getLevelRange();
        if(levelManager.isToCreateParachutist(gameManager.getCount(),(int)levelRange.getKey(),(int)levelRange.getValue())){
            IMove verMove =  new VerticalMove(gameManager.seaLevel());
            IImage plane = gameManager.getImages().get(PLANE);
            Image img =new ImageIcon(getClass().getClassLoader().getResource( parachutistImageLoc)).getImage();
            gameManager.getGraphicsRepository().addImage(String.valueOf(gameManager.getCount()),plane.getX(),plane.getImage().getHeight(null),velocity,img,verMove);
        }
    }

    private Pair<Integer,Integer> getLevelRange(){
        return new Pair<>(15,20);
    }
}
