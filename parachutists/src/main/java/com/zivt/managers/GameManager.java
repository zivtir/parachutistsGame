package com.zivt.managers;

import com.zivt.gameGraphics.GraphicsRepository;
import com.zivt.gameGraphics.IImage;
import com.zivt.listeners.ListenersBuilder;
import com.zivt.observers.IMoveObserver;
import com.zivt.observers.ITimerObserver;
import com.zivt.observers.ObserversBuilder;
import com.zivt.strategies.HorizontalCycleMove;
import com.zivt.strategies.HorizontalMove;
import com.zivt.strategies.IMove;
import com.zivt.swing.MainPanel;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.*;

public class GameManager{
    private GraphicsRepository graphicsRepository;
    private int count=0;
    private MainPanel mainPanel;
    private List<IMoveObserver> moveObservers;
    private List<ITimerObserver> timerCycleObservers;
    private static final int planeVelocity =-2;
    private Timer timer ;
    private int scorePerHit;

    public GameManager(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.graphicsRepository = new GraphicsRepository();

        ObserversBuilder observersBuilder = ObserversBuilder.getInstance();
        moveObservers = observersBuilder.buildMoveObservers(this);
        timerCycleObservers = observersBuilder.buildTimerObservers(this);

        ListenersBuilder listenersBuilder = ListenersBuilder.getInstance();
        addListenersToPanel(listenersBuilder.getListeners(this));
    }

    private void addListenersToPanel(ArrayList<EventListener> listeners) {
        listeners.forEach(l -> {
            if (l instanceof KeyListener){
                mainPanel.addKeyListener((KeyListener)l); // for now we support only KeyListener , in the future can support MouseListener
            }
        });
    }

    public void start(int scorePerHit, int lives,int speed){
        initiateGraphics();
        this.scorePerHit = scorePerHit;
        mainPanel.setLives(lives);
        timer = new Timer(speed, e -> timerCycle());
        timer.start();
    }

    public int getScorePerHit() {
        return scorePerHit;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    private void initiateGraphics(){
        int panelWidth = mainPanel.getSize().width;
//        Image img =new ImageIcon(Consts.resourcesPath()+ Consts.PLANE_IMAGE).getImage();
        Image img =new ImageIcon(getClass().getClassLoader().getResource(Consts.PLANE_IMAGE)).getImage();
        IMove horizontalCycleMove =  new HorizontalCycleMove(panelWidth);
        graphicsRepository.addImage(Consts.PLANE,panelWidth,0,planeVelocity,img,horizontalCycleMove);

        IMove horMove =  new HorizontalMove(mainPanel.getSize().width);
        img =new ImageIcon(getClass().getClassLoader().getResource(Consts.BOAT_IMAGE)).getImage();
        graphicsRepository.addImage(Consts.BOAT,panelWidth/2,mainPanel.seaLevel()-img.getHeight(null)+25,0,img,horMove);
    }

    public int getCount(){
        return count;
    }

    public GraphicsRepository getGraphicsRepository(){
        return this.graphicsRepository;
    }

    public void stop(){
        timer.stop();
    }

    public HashMap<String, IImage> getImages(){
        return graphicsRepository.getImages();
    }

    public void removeImage(String id){
        graphicsRepository.getImages().remove(id);
    }

    public int seaLevel(){
        return mainPanel.seaLevel();
    }

    private void timerCycle() {
        increaseCount();
        move();
        mainPanel.repaint(graphicsRepository.getImages());
        timerCycleObservers.forEach(o-> o.fired());
    }

    private void increaseCount() {
        if (count == Integer.MAX_VALUE)
            count=0;
        count++;
    }

    private void move() {
        for (Map.Entry<String, IImage> imageEntry : graphicsRepository.getImages().entrySet()){
            IImage image = imageEntry.getValue();
            image.move();
            moveObservers.forEach(o -> o.fired(imageEntry));
        }
        moveObservers.forEach(o -> o.postMove());
    }
}
